package com.company;

import java.util.ArrayDeque;

/**
 * Created by student on 08.05.2019.
 */
public class printtransactionsCommand implements AbstractCommand{
    ArrayDeque<Transaction> transactions = new ArrayDeque<Transaction>();
    Bank tBank;

    public printtransactionsCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
    }

    @Override
    public boolean execute() {
        transactions = tBank.getTransactions();
        for (Transaction iTransaction : transactions) {
            System.out.println("Transaction: id=" + iTransaction.id + ", from=" + iTransaction.idFrom +
                    ", to=" + iTransaction.idTo + ", value=" + Double.toString(iTransaction.sum) + ", delay=" + Integer.toString(iTransaction.delay));
        }
        return false;
    }
}