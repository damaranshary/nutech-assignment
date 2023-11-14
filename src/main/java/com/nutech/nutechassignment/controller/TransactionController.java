package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.TransactionRequest;
import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/balance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<BalanceResponse> getBalance() throws SQLException {
        BalanceResponse balanceResponse;
        balanceResponse = transactionService.getBalance("damar@email.com");

        return WebResponse.<BalanceResponse>builder()
                .data(balanceResponse)
                .status(402).message("success").build();
    }

    @PostMapping("/topup")
    public void topUpBalance(@RequestBody Long topUpAmount) {

    }

    @PostMapping(path = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void doTransaction(@RequestBody TransactionRequest request) throws SQLException {
        String email = "damar@email.com";
        System.out.println(request.getService_code());

        transactionService.doTransaction(email, request.getService_code());
    }

    @GetMapping("/transaction/history")
    public void getTransactionHistory(@RequestParam(name = "offset", required = false) Integer offset,
                                      @RequestParam(name = "limit", required = false) Integer limit) {


    }
}
