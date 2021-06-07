package com.company;

import java.math.BigDecimal;

public interface Account {
    BigDecimal changeBalance(BigDecimal newAmount);
    BigDecimal getBalance();
    String getAccountNumber();
}
