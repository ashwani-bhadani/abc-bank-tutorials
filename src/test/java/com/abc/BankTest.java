package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }


    @Test
    public void super_savings_account_when_deposit_greaterThan2000() {
        Bank bank = new Bank();
        Account superSavingsAccount = new Account(Account.SUPER_SAVINGS);
        bank.addCustomer(new Customer("Super Saver Bill").openAccount(superSavingsAccount));
        superSavingsAccount.deposit(3000.0);
        assertEquals(230.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void super_savings_account_when_deposit_greaterThan1000() {
        Bank bank = new Bank();
        Account superSavingsAccount = new Account(Account.SUPER_SAVINGS);
        bank.addCustomer(new Customer("Super Saver Bill").openAccount(superSavingsAccount));
        superSavingsAccount.deposit(1500.0);
        assertEquals(75.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void super_savings_account_when_deposit_lesserThan1000() {
        Bank bank = new Bank();
        Account superSavingsAccount = new Account(Account.SUPER_SAVINGS);
        bank.addCustomer(new Customer("Super Saver Bill").openAccount(superSavingsAccount));
        superSavingsAccount.deposit(500.0);
        assertEquals(20.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void first_Customer_Test(){
        Bank bank = new Bank();

        Customer johnDoe1 = new Customer("First Customer");
        johnDoe1.openAccount(new Account(Account.CHECKING));

        Customer johnDoe2 = new Customer("Second Customer");
        johnDoe2.openAccount(new Account(Account.CHECKING));

        bank.addCustomer(johnDoe1);
        bank.addCustomer(johnDoe2);

        assertEquals(bank.getFirstCustomer(), "First Customer");
        assertNotEquals(bank.getFirstCustomer(), "Second Customer");
    }

}
