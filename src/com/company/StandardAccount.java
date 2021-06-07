package com.company;

public class StandardAccount implements Account{
    private float balance = 0;
    String accountNumber;
    String clientId;

    public StandardAccount(String clientId) throws Exception {
        IdGenerator generator = new IdGenerator();
        this.clientId = clientId;
        this.accountNumber = generator.generateAccountId("ST");
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
