package com.company;

import java.util.UUID;

/**
 * Created by student on 08.05.2019.
 */
public class addcustomerCommand implements AbstractCommand{
    Customer vCustomer = new Customer();
    Bank tBank;

    public addcustomerCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
        vCustomer.id = UUID.randomUUID().toString();
        vCustomer.Name = args[1];
        vCustomer.Balance = Double.parseDouble(args[2]);

        }

    @Override
    public boolean execute() {
        vCustomer = tBank.addcustomer(vCustomer);
        System.out.println("New Customer: id=" + vCustomer.id + ", name=" + vCustomer.Name + ", balance=" + Double.toString(vCustomer.Balance));
        return false;
        }
}
