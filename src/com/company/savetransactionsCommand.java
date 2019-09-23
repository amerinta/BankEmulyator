package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by student on 10.05.2019.
 */
public class savetransactionsCommand implements AbstractCommand{
    String CustomerName = "";
    String FileName = "BankTransactions.xml";
    Bank tBank;

    public savetransactionsCommand(Bank vBank, String args[]) {
        this.tBank = vBank;

        if (args.length > 1) { FileName = args[1];}

    }

    @Override
    public boolean execute() {
        new XMLMake(FileName, tBank.getTransactions());
        return false;
    }
}