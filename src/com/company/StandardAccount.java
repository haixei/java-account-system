package com.company;

public class StandardAccount implements Account{
    private float balance = 0;
    String accountNumber;
    String clientId;

    public StandardAccount(String clientId){
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
