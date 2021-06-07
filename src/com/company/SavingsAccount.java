package com.company;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount implements Account{
    private BigDecimal balance;
    private final double percent = 1.5;
    private String dateCreated;
    String accountNumber;
    String clientId;

    public SavingsAccount(String clientId) throws Exception {
        IdGenerator generator = new IdGenerator();
        this.dateCreated = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.clientId = clientId;
        this.accountNumber = generator.generateAccountId("SA");
    }

    @Override
    public String getAccountNumber() {
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