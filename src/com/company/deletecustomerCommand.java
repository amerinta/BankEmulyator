package com.company;

import java.util.UUID;

/**
 * Created by student on 09.05.2019.
 */
public class deletecustomerCommand implements AbstractCommand{
    Customer vCustomer = new Customer();
    Bank tBank;

    public deletecustomerCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
        vCustomer.id = args[1];

    }

    @Override
    public boolean execute() {
        vCustomer = tBank.removeCustomer(vCustomer.id);
        System.out.println("Deleted Customer: id=" + vCustomer.id + ", name=" + vCustomer.Name + ", balance=" + Double.toString(vCustomer.Balance));
        return false;
    }
}