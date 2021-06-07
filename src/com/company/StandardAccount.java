package com.company;

import java.math.BigDecimal;

public class StandardAccount implements Account{
    private BigDecimal balance;
    String accountNumber;
    String clientId;

    public StandardAccount(String clientId) throws Exception {
        IdGenerator generator = new IdGenerator();
        this.clientId = clientId;
        this.accountNumber = generator.generateAccountId("ST");
    }

    @Override
    public String getAccountNumber(){
        return this.accountNumber;
    }

    @Override
    public BigDecimal changeBalance(BigDecimal newAmount) {
        return this.balance.add(newAmount);
    }

    @Override
    public BigDecimal getBalance(){
        return this.balance;
    }
}
