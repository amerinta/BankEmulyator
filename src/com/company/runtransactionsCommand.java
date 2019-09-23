package com.company;

import java.util.ArrayDeque;

/**
 * Created by student on 09.05.2019.
 */
public class runtransactionsCommand implements AbstractCommand{
    ArrayDeque<Transaction> transactions = new ArrayDeque<Transaction>();
    Transaction transaction;
    Bank tBank;

    public runtransactionsCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
    }

    @Override
    public boolean execute() {
        try {
            transactions = tBank.getTransactions();
            while ((transaction = transactions.poll()) != null) {
                System.out.println("Running Transaction: id=" + transaction.id + ", from=" + transaction.idFrom +
                        ", to=" + transaction.idTo + ", value=" + Double.toString(transaction.sum) + ", delay=" + Integer.toString(transaction.delay));

                Thread.sleep(transaction.delay * 100); // задержка выполнения транзакции в условных единицах

                tBank.getCustomerById(transaction.idTo).Balance = tBank.getCustomerById(transaction.idTo).Balance + transaction.sum;
                tBank.getCustomerById(transaction.idFrom).Balance = tBank.getCustomerById(transaction.idFrom).Balance - transaction.sum;

            }

        } catch (Exception exp){
            System.out.println("The correct customers are not installed");
        }

        return false;
    }
}