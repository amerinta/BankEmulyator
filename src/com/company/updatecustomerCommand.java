package com.company;

/**
 * Created by student on 09.05.2019.
 */
public class updatecustomerCommand implements AbstractCommand{
    Customer vCustomer = new Customer();
    Bank tBank;

    public updatecustomerCommand(Bank vBank, String args[]) {
        this.tBank = vBank;
        vCustomer.id = args[1];
        vCustomer.Name = args[2];
        vCustomer.Balance = Double.parseDouble(args[3]);

    }

    @Override
    public boolean execute() {
        Customer nCustomer = tBank.getCustomerById(vCustomer.id);
        tBank.removeCustomer(vCustomer.id);
        vCustomer = tBank.addcustomer(vCustomer);
        System.out.println("Update Customer: id=" + vCustomer.id + ", name=" + vCustomer.Name + ", balance=" + Double.toString(vCustomer.Balance));
        return false;
    }
}