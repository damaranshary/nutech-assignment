package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.model.Transaction;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;

import java.sql.SQLException;
import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAllTransactionByUser_Id(String email);

    List<Transaction> findAllTransactionByUser_IdWithLimitAndWithOffset(String email, Integer limit, Integer offset);

    Integer save(Transaction transaction);
}
