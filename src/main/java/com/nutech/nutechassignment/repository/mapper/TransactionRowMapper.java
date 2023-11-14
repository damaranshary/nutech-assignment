package com.nutech.nutechassignment.repository.mapper;

import com.nutech.nutechassignment.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();

        transaction.setUserId(resultSet.getString("user_id"));
        transaction.setInvoiceNumber(resultSet.getString("invoice_number"));
        transaction.setTransactionType(resultSet.getString("transaction_type"));
        transaction.setDescription(resultSet.getString("description"));
        transaction.setTotalAmount(resultSet.getLong("total_amount"));
        transaction.setCreatedOn(resultSet.getTimestamp("created_on").toLocalDateTime());

        return transaction;
    }
}
