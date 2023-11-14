package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.model.Transaction;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;

import java.sql.SQLException;
import java.util.List;

public interface TransactionRepository {

    List<TransactionHistoryResponse> findAllTransactionByUser_Id(String email) throws SQLException;

    void save(Transaction transaction) throws SQLException;

    ServiceLayanan getServiceLayananByService_Code(String service_code) throws SQLException;
}
