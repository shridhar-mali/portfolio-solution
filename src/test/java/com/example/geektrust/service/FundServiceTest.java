package com.example.geektrust.service;

import com.example.geektrust.model.Fund;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.geektrust.repository.FundRepository;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FundServiceTest {

    @Mock
    private FundRepository fundRepository;

    @InjectMocks
    private FundService fundService;


    @Test
    void shouldSaveUserPortfolio() {
        fundService.saveUserPortfolio("UTI_NIFTY_INDEX AXIS_MIDCAP PARAG_PARIKH_FLEXI_CAP");

        verify(fundRepository, times(1)).saveUserFunds(Arrays.asList("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"));
    }

    @Test
    void shouldCalculateOverlapPercentage() {
        when(fundRepository.getFund("ICICI_FUND")).thenReturn(new Fund());
        fundService.calculateOverlap("ICICI_FUND");

        verify(fundRepository, times(1)).getFund("ICICI_FUND");
        verify(fundRepository, times(1)).getUserFunds();
    }

    @Test
    void shouldAddStock() {
        fundService.addStock("UTI_NIFTY_INDEX uti sweet stock");

        verify(fundRepository, times(1)).saveStock("uti sweet stock", "UTI_NIFTY_INDEX");
    }

    @Test
    void shouldRoundOverlapPercentageValue() {
        String overlapPercentage = fundService.calculateOverlapPercentage(33, 59, 18);

        Assertions.assertEquals("39.13", overlapPercentage);
    }
}