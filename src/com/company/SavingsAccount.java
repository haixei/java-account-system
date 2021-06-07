package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount implements Account{
    private float balance = 0;
    private final double percent = 1.5;
    private String dateCreated;
    String accountNumber;
    String clientId;

    public SavingsAccount(String clientId){
        this.dateCreated = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.clientId = clientId;
    }

    @Override
    public float changeBalance(float newAmount) {
        this.balance += newAmount;
        return this.balance;
    }

    @Override
    public float getBalance(){
        return this.balance;
    }
}