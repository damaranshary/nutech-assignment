package com.nutech.nutechassignment.exception;

public class NotEnoughBalanceException extends RuntimeException{

    public NotEnoughBalanceException(){
        super("You don't have enough balance to do this transaction");
    }
}
