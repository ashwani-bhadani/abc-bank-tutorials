package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Bank {

    private static final Logger logger = Logger.getLogger(Bank.class.getName());
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer customer : customers)
            summary.append("\n - ").append(customer.getName()).append(" (").append(format(customer.getNumberOfAccounts(), "account")).append(")");
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        try {
            if(customers != null)
                return customers.get(0).getName();
            else
                throw new Exception("There are no customer registered to bank!");
        } catch (Exception ex){
            logger.log(Level.SEVERE, "An error occurred in fetching first customer: " + ex.getMessage(), ex);
            return "Error";
        }
    }
}
