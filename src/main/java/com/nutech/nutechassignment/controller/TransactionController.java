package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.TopUpRequest;
import com.nutech.nutechassignment.model.request.TransactionRequest;
import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;
import com.nutech.nutechassignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/balance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<BalanceResponse> getBalance(Authentication authentication) {
        BalanceResponse balanceResponse = transactionService.getBalance(authentication.getName());

        return WebResponse.<BalanceResponse>builder()
                .data(balanceResponse)
                .status(402).message("success").build();
    }

    @PostMapping(path = "/topup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<BalanceResponse> topUpBalance(@RequestBody TopUpRequest topUpRequest, Authentication authentication) {
        BalanceResponse balanceResponse = transactionService.doTopUp(authentication.getName(), topUpRequest.getTotal_amount());

        return WebResponse.<BalanceResponse>builder().status(200).data(balanceResponse).message("Success").build();
    }

    @PostMapping(path = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<TransactionServiceResponse> doTransaction(@RequestBody TransactionRequest request, Authentication authentication) {
        TransactionServiceResponse transactionResponse = transactionService.doTransaction(authentication.getName(), request.getService_code());

        return WebResponse.<TransactionServiceResponse>builder().status(200).data(transactionResponse).message("success").build();
    }

    @GetMapping("/transaction/history")
    public WebResponse<List<TransactionHistoryResponse>> getTransactionHistory(
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "limit", required = false) Integer limit,
            Authentication authentication) {
        List<TransactionHistoryResponse> transactionHistory = transactionService.getTransactionHistory(authentication.getName());

        return WebResponse.<List<TransactionHistoryResponse>>builder().status(200).data(transactionHistory).message("success").build();

    }
}
