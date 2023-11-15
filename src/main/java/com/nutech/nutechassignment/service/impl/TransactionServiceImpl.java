package com.nutech.nutechassignment.service.impl;

import com.nutech.nutechassignment.exception.NotEnoughBalanceException;
import com.nutech.nutechassignment.exception.ServiceNotFoundException;
import com.nutech.nutechassignment.exception.UserNotFoundException;
import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.model.Transaction;
import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.request.TopUpRequest;
import com.nutech.nutechassignment.model.request.TransactionRequest;
import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;
import com.nutech.nutechassignment.repository.ServiceRepository;
import com.nutech.nutechassignment.repository.TransactionRepository;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.service.TransactionService;
import com.nutech.nutechassignment.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ValidationService validationService;

    @Override
    public List<TransactionHistoryResponse> getTransactionHistoryByUser_Email(String email) {
        List<Transaction> transactionList = transactionRepository.findAllTransactionByUser_Id(email);

        return transactionList.stream()
                .map(this::convertTransactionToTransactionHistoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionHistoryResponse> getTransactionHistoryByUser_EmailWithLimitAndWithOffset(String email, Integer limit, Integer offset) {
        List<Transaction> transactionList = transactionRepository.findAllTransactionByUser_IdWithLimitAndWithOffset(email, limit, offset);

        return transactionList.stream()
                .map(this::convertTransactionToTransactionHistoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BalanceResponse getBalance(String email) {
        User user = userRepository.findUserById(email);

        if (user == null) {
            throw new UserNotFoundException();
        }

        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setBalance(user.getBalance());

        return balanceResponse;
    }

    @Override
    public TransactionServiceResponse doTransaction(String email, TransactionRequest request) {
        validationService.validate(request);

        ServiceLayanan serviceLayanan = serviceRepository.findServiceLayananById(request.getService_code());
        User user = userRepository.findUserById(email);

        // since we need the service layanan exists in the db, we will throw an exception if we didn't match any
        if (serviceLayanan == null) {
            throw new ServiceNotFoundException();
        }

        // the user is needed too, so we will throw an exception
        if (user == null) {
            throw new UserNotFoundException();
        }

        // if the user doesn't have enough balance we will throw an exception
        if (user.getBalance() < serviceLayanan.getServiceTariff()) {
            throw new NotEnoughBalanceException();
        }

        Transaction transaction = new Transaction();

        // these variables created to make sure the value will not be consistent thorough the functions
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
    public BalanceResponse doTopUp(String email, TopUpRequest request) {
        validationService.validate(request);

        User user = userRepository.findUserById(email);

        if (user == null) {
            throw new UserNotFoundException();
        }

        BalanceResponse balanceResponse = new BalanceResponse();
        Transaction transaction = new Transaction();

        balanceResponse.setBalance(user.getBalance() + request.getTop_up_amount());

        String generatedInvoice = UUID.randomUUID().toString();
        Timestamp createdOn = new Timestamp(System.currentTimeMillis());

        transaction.setInvoiceNumber(generatedInvoice);
        transaction.setTransactionType("TOPUP");
        transaction.setDescription("Top Up Balance");
        transaction.setTotalAmount(request.getTop_up_amount());
        transaction.setCreatedOn(createdOn.toLocalDateTime());
        transaction.setUserId(email);

        transactionRepository.save(transaction);

        userRepository.updateBalance(email, balanceResponse.getBalance());

        return balanceResponse;
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
