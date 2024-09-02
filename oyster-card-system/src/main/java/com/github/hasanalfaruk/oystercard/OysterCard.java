package com.github.hasanalfaruk.oystercard;

public class OysterCard {

    private double balance;

    public OysterCard(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deduct(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public void add(double amount) {
        balance += amount;
    }
    
}
