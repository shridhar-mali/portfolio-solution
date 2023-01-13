package com.example.geektrust.service;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.Funds;
import com.fasterxml.jackson.databind.ObjectMapper;
import repository.FundRepository;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class FundService {

    private static final double TWO = 2.00;
    private static final double HUNDRED = 100.00;
    private static final String SPACE = " ";
    private static final String FUNDS_SOURCE_URL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

    private FundRepository fundRepository;
    private DecimalFormat overlapPercentFormat = new DecimalFormat("##.00");
    private String overlapStocksOutputFormat = "%s %s %s%%";

    public FundService() throws IOException {
        Funds funds = new ObjectMapper().readValue(new URL(FUNDS_SOURCE_URL), Funds.class);
        this.fundRepository = new FundRepository(funds);
    }

    public void calculateOverlap(String fundName) {
        Fund newFund = fundRepository.getFund(fundName);

        if (newFund == null) {
            System.out.println("FUND_NOT_FOUND");
            return;
        }

        fundRepository.getUserFunds().forEach(fund -> calculateAndPrintOverlapPercentage(newFund, fund));
    }

    private void calculateAndPrintOverlapPercentage(Fund newFund, Fund fund) {
        Set<String> commonStocks = new HashSet<>(fund.getStocks());
        commonStocks.retainAll(newFund.getStocks());

        System.out.println(String.format(overlapStocksOutputFormat,
                newFund.getName(),
                fund.getName(),
                calculateOverlapPercentage(newFund.getStocks().size(), fund.getStocks().size(), commonStocks.size())));
    }

    public String calculateOverlapPercentage(long newFundStocks, long existingFundStocks, long commonStocks) {
        double overlapPercentage = ((TWO * commonStocks) / (newFundStocks + existingFundStocks)) * HUNDRED;
        return overlapPercentFormat.format(overlapPercentage);
    }

    public void addStock(String addStockArguments) {
        String fundName = addStockArguments.substring(0, addStockArguments.indexOf(SPACE));
        String stockName = addStockArguments.substring(addStockArguments.indexOf(SPACE) + 1);

        fundRepository.saveStock(stockName, fundName);
    }

    public void saveUserPortfolio(String userPortfolioArguments) {
        fundRepository.saveUserFunds(asList(userPortfolioArguments.split(SPACE)));
    }
}