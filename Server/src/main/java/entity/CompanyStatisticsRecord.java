package entity;

import java.util.Calendar;

public class CompanyStatisticsRecord extends Company {
    private int idCompany;
    private String periodStart;
    private String periodEnd;
    private double averageNumberOfSharesOutstanding;
    private double profitForHoldersOfOrdinaryShares;
    private double depreciationDeductions;
    private double beginningMarketYearStockPrice;
    private double endingMarketYearStockPrice;
    private double dividendValueForPeriod;
    private double averageMarketSharePrice;

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public double getAverageNumberOfSharesOutstanding() {
        return averageNumberOfSharesOutstanding;
    }

    public void setAverageNumberOfSharesOutstanding(double averageNumberOfSharesOutstanding) {
        this.averageNumberOfSharesOutstanding = averageNumberOfSharesOutstanding;
    }

    public double getProfitForHoldersOfOrdinaryShares() {
        return profitForHoldersOfOrdinaryShares;
    }

    public void setProfitForHoldersOfOrdinaryShares(double profitForHoldersOfOrdinaryShares) {
        this.profitForHoldersOfOrdinaryShares = profitForHoldersOfOrdinaryShares;
    }

    public double getDepreciationDeductions() {
        return depreciationDeductions;
    }

    public void setDepreciationDeductions(double depreciationDeductions) {
        this.depreciationDeductions = depreciationDeductions;
    }

    public double getBeginningMarketYearStockPrice() {
        return beginningMarketYearStockPrice;
    }

    public void setBeginningMarketYearStockPrice(double beginningMarketYearStockPrice) {
        this.beginningMarketYearStockPrice = beginningMarketYearStockPrice;
    }

    public double getEndingMarketYearStockPrice() {
        return endingMarketYearStockPrice;
    }

    public void setEndingMarketYearStockPrice(double endingMarketYearStockPrice) {
        this.endingMarketYearStockPrice = endingMarketYearStockPrice;
    }

    public double getDividendValueForPeriod() {
        return dividendValueForPeriod;
    }

    public void setDividendValueForPeriod(double dividendValueForPeriod) {
        this.dividendValueForPeriod = dividendValueForPeriod;
    }

    public double getAverageMarketSharePrice() {
        return averageMarketSharePrice;
    }

    public void setAverageMarketSharePrice(double averageMarketSharePrice) {
        this.averageMarketSharePrice = averageMarketSharePrice;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }
}
