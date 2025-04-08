package com.abc;

import java.time.ZonedDateTime;

public class Transaction {
    public final double amount;

    private final ZonedDateTime transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

}
