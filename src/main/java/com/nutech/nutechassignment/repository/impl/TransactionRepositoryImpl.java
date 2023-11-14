package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.model.Transaction;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.nutech.nutechassignment.repository.DatabaseConnect.dbConnect;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    public List<TransactionHistoryResponse> findAllTransactionByUser_Id(String email) throws SQLException {
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_transaction WHERE email = ?")) {
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
        }

        List<TransactionHistoryResponse> transactionHistory = new ArrayList<>();

        while (resultSet.next()) {
            String invoiceNumber = resultSet.getString("invoice_number");
            String transactionType = resultSet.getString("transaction_type");
            String description = resultSet.getString("description");
            Long totalAmount = resultSet.getLong("total_amount");

            Timestamp timestamp = resultSet.getTimestamp("created_on");
            LocalDateTime createdOn = timestamp.toLocalDateTime();

            transactionHistory.add(new TransactionHistoryResponse(invoiceNumber, transactionType, description, totalAmount, createdOn));
        }

        return transactionHistory;
    }

    public void save(Transaction transaction) throws SQLException {
        try (PreparedStatement preparedStatement = dbConnect("INSERT INTO t_transaction " +
                "(invoice_number, transaction_type, description, total_amount, created_on, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, transaction.getInvoiceNumber());
            preparedStatement.setString(2, transaction.getTransactionType());
            preparedStatement.setString(3, transaction.getDescription());
            preparedStatement.setLong(4, transaction.getTotalAmount());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getCreatedOn()));
            preparedStatement.setString(6, transaction.getUserId());

            preparedStatement.executeUpdate();
        }
    }

    public ServiceLayanan getServiceLayananByService_Code(String service_code) throws SQLException {
        ResultSet resultSet;
        ServiceLayanan serviceLayanan = new ServiceLayanan();
        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_service WHERE code = ?")) {
            preparedStatement.setString(1, service_code);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                serviceLayanan.setServiceCode(resultSet.getString("code"));
                serviceLayanan.setServiceName(resultSet.getString("name"));
                serviceLayanan.setServiceIcon(resultSet.getString("icon"));
                serviceLayanan.setServiceTariff(resultSet.getLong("tariff"));
            }
        }

        return serviceLayanan;
    }

}
