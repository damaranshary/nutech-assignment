package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.request.TopUpRequest;
import com.nutech.nutechassignment.model.request.TransactionRequest;
import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionHistoryResponse> getTransactionHistoryByUser_Email(
            String email);

    List<TransactionHistoryResponse> getTransactionHistoryByUser_EmailWithLimitAndWithOffset(
            String email, Integer limit, Integer offset
    );

    BalanceResponse getBalance(String email);

    BalanceResponse doTopUp(String email, TopUpRequest topUpRequest);

    TransactionServiceResponse doTransaction(String email, TransactionRequest transactionRequest);

}
