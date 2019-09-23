package com.company;


import java.util.ArrayList;

/**
 * Created by student on 09.05.2019.
 */
public class printcustomersbynameCommand implements AbstractCommand{
    String CustomerName = "";
    Bank tBank;

    public printcustomersbynameCommand(Bank vBank, String args[]) {
        this.tBank = vBank;

        if (args.length > 1) { CustomerName = args[1];}

    }

    @Override
    public boolean execute() {
        ArrayList<Customer> customersbyname = tBank.getListCustomersByName(CustomerName);
        for (Customer iCustomer: customersbyname) {
            System.out.println("Customer: id=" + iCustomer.id + ", name=" + iCustomer.Name + ", balance=" + Double.toString(iCustomer.Balance));
        }
        return false;
    }
}