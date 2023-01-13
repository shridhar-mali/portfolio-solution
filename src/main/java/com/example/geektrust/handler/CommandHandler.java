package com.example.geektrust.handler;

import com.example.geektrust.command.Command;
import com.example.geektrust.service.FundService;

import java.io.IOException;

public class CommandHandler {

    private FundService fundService;

    public CommandHandler() throws IOException {
        this.fundService = new FundService();
    }

    public void handleCommand(Command command) {

        switch (command.getCommandType()) {
            case ADD_STOCK:
                fundService.addStock(command.getCommandArguments());
                break;

            case CALCULATE_OVERLAP:
                fundService.calculateOverlap(command.getCommandArguments());
                break;

            case CURRENT_PORTFOLIO:
                fundService.saveUserPortfolio(command.getCommandArguments());
                break;

            default:
                throw new IllegalArgumentException("Invalid command found");
        }

    }
}
