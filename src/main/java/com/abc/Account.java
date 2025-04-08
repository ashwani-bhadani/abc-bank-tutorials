package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    public static final int SUPER_SAVINGS = 3;

    private final int accountType;
    public List<Transaction> transactions;
    private double totalAmount;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.totalAmount = 0.0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
            this.totalAmount+=amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
            this.totalAmount-=amount;
        }
    }

    public void transfer(Account recipientAcc, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            recipientAcc.transactions.add(new Transaction(amount));
            transactions.add(new Transaction(-amount));
        }
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:       //accountType=1
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:      //accountType =2
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            case SUPER_SAVINGS:      //accountType =3
                if (amount <= 1000)
                    return amount * 0.04;
                if (amount <= 2000)
                    return 40 + (amount-1000) * 0.07;
                return 110 + (amount-2000) * 0.12;
            default:
                return amount * 0.001; //checking acc accountType = 0
        }
    }

    public double sumTransactions() {
        if(transactions.isEmpty())
            return checkIfTransactionsExist(false);
        else
           return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        if(checkAll) {
            for (Transaction transaction : transactions)
                amount += transaction.amount;
        }
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
