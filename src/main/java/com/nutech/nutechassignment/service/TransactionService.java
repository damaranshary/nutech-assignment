package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {

    List<TransactionHistoryResponse> getTransactionHistory(String email);

    BalanceResponse getBalance(String email);

    BalanceResponse doTopUp(String email, Long totalTopUp);

    TransactionServiceResponse doTransaction(String email, String service_code);

}
