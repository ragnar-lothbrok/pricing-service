package com.xyz.assignment.dtos;

public class Tax {

    private String label;
    private Float taxAmount;
    private Float percentage;
    private Float fixedTaxAmount;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getFixedTaxAmount() {
        return fixedTaxAmount;
    }

    public void setFixedTaxAmount(Float fixedTaxAmount) {
        this.fixedTaxAmount = fixedTaxAmount;
    }
}
