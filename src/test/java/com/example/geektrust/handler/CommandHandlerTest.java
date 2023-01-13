package com.example.geektrust.handler;

import com.example.geektrust.command.Command;
import com.example.geektrust.service.FundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.geektrust.command.CommandType.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommandHandlerTest {

    @Mock
    private FundService fundService;

    @InjectMocks
    private CommandHandler commandHandler;

    @Test
    void shouldTriggerAddStocksMethod() {
        commandHandler.handleCommand(new Command(ADD_STOCK, "hdfc stock"));

        verify(fundService, Mockito.times(1)).addStock("hdfc stock");
    }

    @Test
    void shouldTriggerCalculatOverlapMethod() {
        commandHandler.handleCommand(new Command(CALCULATE_OVERLAP, "HDFC_FUND"));

        verify(fundService, Mockito.times(1)).calculateOverlap("HDFC_FUND");
    }

    @Test
    void shouldTriggerSaveUserPortfolioMethod() {
        commandHandler.handleCommand(new Command(CURRENT_PORTFOLIO, "HDFC_FUND"));

        verify(fundService, Mockito.times(1)).saveUserPortfolio("HDFC_FUND");
    }
}