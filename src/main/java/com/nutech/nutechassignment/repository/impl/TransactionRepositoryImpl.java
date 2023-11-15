package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.model.Transaction;
import com.nutech.nutechassignment.repository.TransactionRepository;
import com.nutech.nutechassignment.repository.mapper.TransactionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAllTransactionByUser_Id(String email) {
        String sqlQuery = "SELECT * FROM t_transaction WHERE user_id = ?";

        return jdbcTemplate.query(sqlQuery,
                preparedStatement -> preparedStatement.setString(1, email),
                new TransactionRowMapper());
    }

    @Override
    public List<Transaction> findAllTransactionByUser_IdWithLimitAndWithOffset(String email, Integer limit, Integer offset) {
        String sqlQuery = "SELECT * FROM t_transaction WHERE user_id = ? LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sqlQuery,
                preparedStatement -> {
                    preparedStatement.setString(1, email);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                }, new TransactionRowMapper());
    }

    @Override
    public Integer save(Transaction transaction) {
        String sqlQuery = "INSERT INTO t_transaction " +
                "(invoice_number, transaction_type, description, total_amount, created_on, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sqlQuery,
                preparedStatement -> {
                    preparedStatement.setString(1, transaction.getInvoiceNumber());
                    preparedStatement.setString(2, transaction.getTransactionType());
                    preparedStatement.setString(3, transaction.getDescription());
                    preparedStatement.setLong(4, transaction.getTotalAmount());
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getCreatedOn()));
                    preparedStatement.setString(6, transaction.getUserId());
                });
    }

    // this below is the method without using jdbcTemplate;
//    private List<Transaction> findAllTransactionByUser_IdWithoutJdbcTemplate(String email) throws SQLException {
//        ResultSet resultSet;
//
//        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_transaction WHERE email = ?")) {
//            preparedStatement.setString(1, email);
//
//            resultSet = preparedStatement.executeQuery();
//        }
//
//        List<Transaction> transactionHistory = new ArrayList<>();
//
//        while (resultSet.next()) {
//            Transaction transaction = new Transaction();
//
//            transaction.setUserId(resultSet.getString("user_id"));
//            transaction.setInvoiceNumber(resultSet.getString("invoice_number"));
//            transaction.setTransactionType(resultSet.getString("transaction_type"));
//            transaction.setDescription(resultSet.getString("description"));
//            transaction.setTotalAmount(resultSet.getLong("total_amount"));
//            transaction.setCreatedOn(resultSet.getTimestamp("created_on").toLocalDateTime());
//
//            transactionHistory.add(transaction);
//        }
//
//        return transactionHistory;
//    }

//    private void saveWithoutJdbcTemplate(Transaction transaction) throws SQLException {
//        try (PreparedStatement preparedStatement = dbConnect("INSERT INTO t_transaction " +
//                "(invoice_number, transaction_type, description, total_amount, created_on, user_id) " +
//                "VALUES (?, ?, ?, ?, ?, ?)")) {
//
//            preparedStatement.setString(1, transaction.getInvoiceNumber());
//            preparedStatement.setString(2, transaction.getTransactionType());
//            preparedStatement.setString(3, transaction.getDescription());
//            preparedStatement.setLong(4, transaction.getTotalAmount());
//            preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getCreatedOn()));
//            preparedStatement.setString(6, transaction.getUserId());
//
//            preparedStatement.executeUpdate();
//        }
//    }

}
