package com.company;

import java.util.ArrayList;

/**
 * Created by student on 09.05.2019.
 */
public class printcustomersbybalanceCommand implements AbstractCommand{

    Bank tBank;

    public printcustomersbybalanceCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
    }

    @Override
    public boolean execute() {
        ArrayList<Customer> customersbybalance = tBank.getListCustomersByBalance();
        for (Customer iCustomer: customersbybalance) {
            System.out.println("Customer: id=" + iCustomer.id + ", name=" + iCustomer.Name + ", balance=" + Double.toString(iCustomer.Balance));
        }
        return false;
    }
}