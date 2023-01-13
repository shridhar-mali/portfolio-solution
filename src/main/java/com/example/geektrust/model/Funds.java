package com.example.geektrust.model;

import java.util.List;

public class Funds {
    private List<Fund> funds;

    public Funds() {
    }

    public Funds(List<Fund> funds) {
        this.funds = funds;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }
}
