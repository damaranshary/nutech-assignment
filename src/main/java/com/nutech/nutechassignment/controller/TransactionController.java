package com.nutech.nutechassignment.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @GetMapping("/balance")
    public void getBalance() {

    }

    @PostMapping("/topup")
    public void topUpBalance(@RequestBody Long topUpAmount) {

    }

    @PostMapping("/transaction")
    public void doTransaction(@RequestBody String serviceCode) {

    }

    @GetMapping("/transaction/history")
    public void getTransactionHistory(@RequestParam(name = "offset", required = false) Integer offset,
                                      @RequestParam(name = "limit", required = false) Integer limit) {


    }
}
