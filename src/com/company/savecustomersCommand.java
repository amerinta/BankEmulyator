package com.company;

import java.util.ArrayList;


/**
 * Created by student on 09.05.2019.
 */
public class savecustomersCommand  implements AbstractCommand{
    String CustomerName = "";
    String FileName = "BankCustomers.xml";
    Bank tBank;

    public savecustomersCommand(Bank vBank, String args[]) {
        this.tBank = vBank;

        if (args.length > 1) { FileName = args[1];}

    }

    @Override
    public boolean execute() {
        new XMLMake(FileName, tBank.getListCustomersByName(CustomerName));
        return false;
    }
}