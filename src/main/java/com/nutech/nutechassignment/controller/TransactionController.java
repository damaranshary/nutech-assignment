package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.exception.TransactionHistoryException;
import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.TopUpRequest;
import com.nutech.nutechassignment.model.request.TransactionRequest;
import com.nutech.nutechassignment.model.response.BalanceResponse;
import com.nutech.nutechassignment.model.response.TransactionHistoryResponse;
import com.nutech.nutechassignment.model.response.TransactionServiceResponse;
import com.nutech.nutechassignment.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(description = "Get Balance API <br> Used for getting current balance from logged in user (need an authorization token)")
    @GetMapping(value = "/balance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<BalanceResponse> getBalance(Authentication authentication) {
        // we will try to get the email from securityContextHolder from spring security using the JWT header token
        String userEmail = authentication.getName();
        BalanceResponse balanceResponse = transactionService.getBalance(userEmail);

        return WebResponse.<BalanceResponse>builder()
                .status(0).data(balanceResponse)
                .message("Get balance success").build();
    }

    @Operation(description = "Top Up user API <br> Used for top up balance for the logged in user (need an authorization token)")
    @PostMapping(path = "/topup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<BalanceResponse> topUpBalance(@RequestBody TopUpRequest topUpRequest,
                                                     Authentication authentication) {
        String userEmail = authentication.getName();

        BalanceResponse balanceResponse = transactionService.doTopUp(userEmail, topUpRequest);
        return WebResponse.<BalanceResponse>builder()
                .status(0).data(balanceResponse)
                .message("Top up balance success").build();
    }

    @Operation(description = "Transaction API <br> Used for doing transaction by logged in user (need an authorization token)")
    @PostMapping(path = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<TransactionServiceResponse> doTransaction(@RequestBody TransactionRequest transactionRequest,
                                                                 Authentication authentication) {
        String userEmail = authentication.getName();

        TransactionServiceResponse transactionResponse = transactionService.doTransaction(userEmail, transactionRequest);
        return WebResponse.<TransactionServiceResponse>builder()
                .status(0).data(transactionResponse)
                .message("Transaction success").build();
    }

    @Operation(description = "Transaction History API <br> Used to get a list of transaction the logged in user completed (need an authorization token)")
    @GetMapping(path = "/transaction/history",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<TransactionHistoryResponse>> getTransactionHistory(
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "limit", required = false) Integer limit,
            Authentication authentication) {
        String userEmail = authentication.getName();

        List<TransactionHistoryResponse> transactionHistory;

        if (limit == null) {
            // if the offset is exists without a limit, we throw exception
            if (offset != null) {
                throw new TransactionHistoryException("You can't set an offset without a limit");
            }

            // we will search without an offset and limit if both offset and limit are null
            transactionHistory = transactionService
                    .getTransactionHistoryByUser_Email(userEmail);
        } else {
            // if the limit is lesser than zero, we throw exception
            if (limit <= 0) {
                throw new TransactionHistoryException("You just can't set your limit less than 0, there will be no data");
            }

            transactionHistory = transactionService
                    .getTransactionHistoryByUser_EmailWithLimitAndWithOffset(userEmail, limit,
                            Objects.requireNonNullElse(offset, 0));
            // basically, since the query in Repository require an offset (not null) we will assign 0
            // as the offset value if the offset are null
        }

        return WebResponse.<List<TransactionHistoryResponse>>builder()
                .status(0).data(transactionHistory)
                .message("Get transaction history success").build();
    }
}
