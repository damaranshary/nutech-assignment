package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.model.Transaction;
import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;
import com.nutech.nutechassignment.repository.ServiceRepository;
import com.nutech.nutechassignment.repository.TransactionRepository;
import com.nutech.nutechassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TransactionHistoryResponse> getTransactionHistory(String email) {
        List<Transaction> transactionList = transactionRepository.findAllTransactionByUser_Id(email);

        return transactionList.stream()
                .map(this::convertTransactionToTransactionHistoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BalanceResponse getBalance(String email) {
        User user = userRepository.findUserById(email);
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setBalance(user.getBalance());

        return balanceResponse;
    }

    @Override
    public TransactionServiceResponse doTransaction(String email, String service_code) {
        ServiceLayanan serviceLayanan = serviceRepository.findServiceLayananByService_Code(service_code);
        User user = userRepository.findUserById(email);

        // if the user doesn't have enough balance we will throw an exception
        if (user.getBalance() < serviceLayanan.getServiceTariff()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't have enough balance");
        }

        Transaction transaction = new Transaction();
        String generatedInvoice = UUID.randomUUID().toString();
        Timestamp createdOn = new Timestamp(System.currentTimeMillis());

        transaction.setInvoiceNumber(generatedInvoice);
        transaction.setTransactionType("PAYMENT");
        transaction.setDescription(serviceLayanan.getServiceName());
        transaction.setTotalAmount(serviceLayanan.getServiceTariff());
        transaction.setCreatedOn(createdOn.toLocalDateTime());
        transaction.setUserId(email);

        // save the transaction into transaction table
        transactionRepository.save(transaction);
        // updating the balance of the user
        userRepository.updateBalance(email, user.getBalance() - serviceLayanan.getServiceTariff());

        // convert transaction to transactionResponse/DTO
        return convertTransactionToTransactionServiceResponse(transaction, serviceLayanan);
    }

    @Override
    public BalanceResponse doTopUp(String email, Long totalTopUp) {
        User user = userRepository.findUserById(email);

        BalanceResponse balance = new BalanceResponse();
        Transaction transaction = new Transaction();

        balance.setBalance(user.getBalance() + totalTopUp);

        String generatedInvoice = UUID.randomUUID().toString();
        Timestamp createdOn = new Timestamp(System.currentTimeMillis());

        transaction.setInvoiceNumber(generatedInvoice);
        transaction.setTransactionType("TOPUP");
        transaction.setDescription("Top Up Balance");
        transaction.setTotalAmount(totalTopUp);
        transaction.setCreatedOn(createdOn.toLocalDateTime());
        transaction.setUserId(email);

        transactionRepository.save(transaction);

        userRepository.updateBalance(email, balance.getBalance());

        return balance;
    }

    private TransactionServiceResponse convertTransactionToTransactionServiceResponse(Transaction transaction, ServiceLayanan serviceLayanan) {
        TransactionServiceResponse transactionResponse = new TransactionServiceResponse();

        transactionResponse.setInvoice_number(transaction.getInvoiceNumber());
        transactionResponse.setTransaction_type(transaction.getTransactionType());
        transactionResponse.setService_code(serviceLayanan.getServiceCode());
        transactionResponse.setService_name(serviceLayanan.getServiceName());
        transactionResponse.setTotal_amount(transaction.getTotalAmount());
        transactionResponse.setCreated_on(transaction.getCreatedOn());

        return transactionResponse;
    }

    private TransactionHistoryResponse convertTransactionToTransactionHistoryResponse(Transaction transaction) {
        TransactionHistoryResponse transactionResponse = new TransactionHistoryResponse();

        transactionResponse.setInvoice_number(transaction.getInvoiceNumber());
        transactionResponse.setTransaction_type(transaction.getTransactionType());
        transactionResponse.setDescription(transaction.getDescription());
        transactionResponse.setTotal_amount(transaction.getTotalAmount());
        transactionResponse.setCreated_on(transaction.getCreatedOn());

        return transactionResponse;
    }
}
