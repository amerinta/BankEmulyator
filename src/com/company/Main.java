package com.company;

import java.lang.reflect.Constructor;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Use command help for get command's list.");
        Bank NY = new Bank();

        while (true) {
            String input = scan.nextLine();
            String[] inputs = input.split(" ");
            inputs[0] = CommandCorrect(inputs[0]);
            try {
                Class<?> command = Class.forName("com.company." + inputs[0] + "Command");
                Constructor<?> constructor = command.getConstructor(Bank.class, String[].class);
                Object instance = constructor.newInstance(NY, inputs);
                executeCommand((AbstractCommand)instance);
            } catch (Exception e){
                System.out.println("Unknown Command");
            }

        }
    }

    private static void executeCommand(AbstractCommand command) {
        if (command.execute()) {

        }
    }
    private static String CommandCorrect(String vArgs){
        String[] array = vArgs.split("-");
        String result = "";
        for (String iWord : array) {
            result = result + iWord;
        }
        return result;
    }
}
