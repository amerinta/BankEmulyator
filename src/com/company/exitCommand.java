package com.company;

/**
 * Created by student on 09.05.2019.
 */
public class exitCommand implements AbstractCommand{
    Transaction vTransaction = new Transaction();
    Bank tBank;

    public exitCommand(Bank vBank, String args[]) {

    }

    @Override
    public boolean execute() {

        System.exit(0);
        return false;
    }
}