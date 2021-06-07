package com.company;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class SavingsAccount implements Account{
    private BigDecimal balance;
    private final BigDecimal percent;
    private String dateCreated;
    String accountNumber;
    String clientId;

    public SavingsAccount(String clientId) throws Exception {
        IdGenerator generator = new IdGenerator();
        this.dateCreated = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.clientId = clientId;
        this.percent = new BigDecimal("1.5");
        this.accountNumber = generator.generateAccountId("SA");
    }

    public BigDecimal calculateMoneySaved(){
        // Calculated the % saved from the input date
        BigDecimal decMultiplied = this.balance.multiply(this.percent);
        BigDecimal savedPerMonth = decMultiplied.divide(new BigDecimal("100"));

        // Calculate amount of months from opening the account
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(this.dateCreated);

        // Get the current date
        String endDate = sdf.format(new Date());

        long monthsBetweenLong = ChronoUnit.MONTHS.between(
                             LocalDate.parse(startDate).withDayOfMonth(1),
                             LocalDate.parse(endDate).withDayOfMonth(1));

        return savedPerMonth.multiply(new BigDecimal(monthsBetweenLong));
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