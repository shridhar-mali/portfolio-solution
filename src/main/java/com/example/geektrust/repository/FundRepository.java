package com.example.geektrust.repository;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.Funds;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class FundRepository {
    private Map<String, Fund> fundMap;

    private List<Fund> userFunds;

    public FundRepository(Funds funds) {
        fundMap = funds.getFunds().stream().collect(Collectors.toMap(Fund::getName, fund -> fund));
        this.userFunds = emptyList();
    }

    public void saveUserFunds(List<String> funds) {
        this.userFunds = funds.stream()
                              .map(fundName -> fundMap.get(fundName))
                              .collect(toList());;
    }

    public void saveStock(String stockName, String fundName) {
        if(!fundMap.containsKey(fundName)) {
            System.out.println("FUND_NOT_FOUND");
        }
        else {
            fundMap.get(fundName).getStocks().add(stockName);
        }
    }

    public List<Fund> getUserFunds() {
        return userFunds;
    }

    public Fund getFund(String fundName) {
        return fundMap.get(fundName);
    }
}
