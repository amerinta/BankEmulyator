package com.company;

/**
 * Created by student on 09.05.2019.
 */
public class printcustomerbyuuidCommand implements AbstractCommand{
    Customer vCustomer = new Customer();
    Bank tBank;

    public printcustomerbyuuidCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
        vCustomer.id = args[1];

    }

    @Override
    public boolean execute() {
        vCustomer = tBank.getCustomerById(vCustomer.id);
        System.out.println("Customer: id=" + vCustomer.id + ", name=" + vCustomer.Name + ", balance=" + Double.toString(vCustomer.Balance));
        return false;
    }
}