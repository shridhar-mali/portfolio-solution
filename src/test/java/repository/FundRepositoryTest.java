package repository;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.Funds;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FundRepositoryTest {

    @Test
    void shouldReturnExistingFunds() {
        FundRepository fundRepository = new FundRepository(buildFunds());

        Fund savedFund = fundRepository.getFund("ICICI");

        assertEquals("ICICI", savedFund.getName());
        assertEquals(1, savedFund.getStocks().size());
        assertTrue(savedFund.getStocks().contains("icici stock name"));
    }

    @Test
    void shouldReturnUserFunds() {
        FundRepository fundRepository = new FundRepository(buildFunds());

        fundRepository.saveUserFunds(asList("ICICI"));

        List<Fund> userFunds = fundRepository.getUserFunds();

        assertEquals(1, userFunds.size());
        assertEquals("ICICI", userFunds.get(0).getName());
        assertTrue(userFunds.get(0).getStocks().contains("icici stock name"));
    }

    @Test
    void shouldSaveStockWithExistingFund() {
        FundRepository fundRepository = new FundRepository(buildFunds());

        fundRepository.saveStock("new stock name", "ICICI");

        Fund savedFund = fundRepository.getFund("ICICI");

        assertEquals("ICICI", savedFund.getName());
        assertEquals(2, savedFund.getStocks().size());
        assertTrue(savedFund.getStocks().contains("icici stock name"));
        assertTrue(savedFund.getStocks().contains("new stock name"));
    }

    private Funds buildFunds() {
        Fund fund = new Fund("ICICI", new HashSet<>(asList("icici stock name")));
        return new Funds(asList(fund));
    }
}