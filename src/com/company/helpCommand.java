package com.company;

import java.util.ArrayList;

/**
 * Created by student on 09.05.2019.
 */
public class helpCommand implements AbstractCommand{

    ArrayList<String> commands = new ArrayList<String>();

    public helpCommand(Bank vBank, String args[]) {
        commands.add("add-customer CustomerName Balance");
        commands.add("update-customer CustomerUUID CustomerName Balance");
        commands.add("delete-customer CustomerUUID");
        commands.add("add-transaction CustomerUUIDFrom CustomerUUIDTo Sum Delay");
        commands.add("run-transactions");
        commands.add("print-transactions");
        commands.add("print-customers-by-balance");
        commands.add("print-customers-by-name");
        commands.add("print-customer-by-uuid CustomerUUID");
        commands.add("save-customers FileName.xml");
        commands.add("load-customers FileName.xml");
        commands.add("save-transactions FileName.xml");
        commands.add("load-transactions FileName.xml");
        commands.add("exit");
    }

    @Override
    public boolean execute() {
        for (String iCommands: commands) {
            System.out.println(iCommands);
        }
        return false;
    }
}