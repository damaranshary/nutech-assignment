package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {

    List<TransactionHistoryResponse> getAllTransaction(String email) throws SQLException;

    BalanceResponse getBalance(String email) throws SQLException;

    BalanceResponse doTopUp(String email, Long totalTopUp) throws SQLException;

    TransactionServiceResponse doTransaction(String email, String service_code) throws SQLException;

}
