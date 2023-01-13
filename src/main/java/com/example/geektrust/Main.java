package com.example.geektrust;

import com.example.geektrust.command.Command;
import com.example.geektrust.command.CommandType;
import com.example.geektrust.handler.CommandHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        CommandHandler commandHandler = new CommandHandler();

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String commandStr = currentLine.substring(0, currentLine.indexOf(" "));
                String commandArguments = currentLine.substring(currentLine.indexOf(commandStr) + 1 + commandStr.length());

                CommandType commandType = CommandType.valueOf(commandStr);
                Command command = new Command(commandType, commandArguments);

                commandHandler.handleCommand(command);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
