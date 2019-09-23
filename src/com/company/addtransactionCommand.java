package com.company;

import java.util.UUID;

/**
 * Created by student on 08.05.2019.
 */
public class addtransactionCommand implements AbstractCommand{
    Transaction vTransaction = new Transaction();
    Bank tBank;

    public addtransactionCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
        vTransaction.id = UUID.randomUUID().toString();
        vTransaction.idFrom = args[1];
        vTransaction.idTo = args[2];
        vTransaction.sum = Double.parseDouble(args[3]);
        vTransaction.delay = Integer.parseInt(args[4]);
    }

    @Override
    public boolean execute() {
        try {
            boolean result = tBank.addTransaction(vTransaction);
            if (result) {
                System.out.println("New Transaction: id=" + vTransaction.id + ", from=" + vTransaction.idFrom +
                        ", to=" + vTransaction.idTo + ", value=" + Double.toString(vTransaction.sum) + ", delay=" + Integer.toString(vTransaction.delay));
            } else {
                System.out.println("Не удалось добавить транзакцию");
            }
        } catch (CustomerNotFoundException exp){
            System.out.println("Указаны несуществующие клиенты или клиент");
        }

        return false;
    }
}