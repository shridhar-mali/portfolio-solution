package com.example.geektrust.command;

public class Command {
    private CommandType commandType;
    private String commandArguments;

    public Command(CommandType commandType, String commandArguments) {
        this.commandType = commandType;
        this.commandArguments = commandArguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getCommandArguments() {
        return commandArguments;
    }
}
