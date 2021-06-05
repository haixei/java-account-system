package com.company;

public class Transaction {
    public float recipientAccountNumber;
    public float transactionAmount;
    private final long transactionDate = System.currentTimeMillis();
}
