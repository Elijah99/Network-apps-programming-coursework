package service;

import dao.CompanyDAO;
import dao.StatisticRecordDAO;
import dao.UserDAO;
import entity.CompanyStatisticsRecord;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CoefficientsService {

    private static CoefficientsService instance;

    public CoefficientsService() {
    }

    public static CoefficientsService getInstance() {
        if(instance == null)
        {
            instance = new CoefficientsService();
        }
        return instance;
    }

    public void getCoeffs(BufferedReader inputStream, PrintWriter outputStream)
    {
        try {
            String buf = inputStream.readLine();

            int idUser = Integer.parseInt(buf);
            User user = UserDAO.getInstance().get(idUser);
            user.setCompany(CompanyDAO.getInstance().get(idUser));
            user.getCompany().setStatistic(StatisticRecordDAO.getInstance().get(user.getCompany().getId()));
            ArrayList<CompanyStatisticsRecord> records = user.getCompany().getStatistic();

            outputStream.println(String.valueOf(records.size()));

            for (CompanyStatisticsRecord record : records) {
                String res = record.getPeriodStart() + "-" + record.getPeriodEnd() + " " +
                        calcEPC(record.getProfitForHoldersOfOrdinaryShares(), record.getAverageNumberOfSharesOutstanding())
                        + " " + calcMoneyFor1Share(record.getProfitForHoldersOfOrdinaryShares(), record.getDepreciationDeductions(),
                        record.getAverageNumberOfSharesOutstanding()) + " " + calcCapitalizedProfit(
                        record.getBeginningMarketYearStockPrice(), record.getEndingMarketYearStockPrice()) + " "
                        + calcDividendIncome(record.getDividendValueForPeriod(), record.getBeginningMarketYearStockPrice()) + " " +
                        calcCommonStockReturn(record.getBeginningMarketYearStockPrice(), record.getEndingMarketYearStockPrice(), record.getDividendValueForPeriod()) + " "
                        + calcMultipleProfit(record.getAverageMarketSharePrice(), calcEPC(record.getProfitForHoldersOfOrdinaryShares(), record.getAverageNumberOfSharesOutstanding()));
                outputStream.println(res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double calcEPC( double netProfit,double avgShares)
    {
        double res = netProfit/avgShares;
        res = (double)Math.round(res*1000)/1000;
        return res;
    }

    public double calcMoneyFor1Share(double profitForHolders, double depreciationDeductions, double avgShares)
    {
        double res = (profitForHolders + depreciationDeductions)/avgShares;
        res = (double)Math.round(res*1000)/1000;
        return res;
    }

    public double calcCapitalizedProfit(double beginningMarketYearStockPrice, double endingMarketYearStockPrice)
    {
        double res = (endingMarketYearStockPrice - beginningMarketYearStockPrice)/beginningMarketYearStockPrice;
        res = (double)Math.round(res*1000)/1000;
        return res;
    }

    public double calcDividendIncome(double dividendValueForPeriod, double beginningMarketYearStockPrice)
    {
        double res = dividendValueForPeriod/beginningMarketYearStockPrice;
        res = (double)Math.round(res*1000)/1000;
        return res;
    }

    public double calcCommonStockReturn(double beginningMarketYearStockPrice, double endingMarketYearStockPrice, double  dividendValueForPeriod)
    {
        double res = (calcCapitalizedProfit(beginningMarketYearStockPrice, endingMarketYearStockPrice)+ dividendValueForPeriod/beginningMarketYearStockPrice);
        res = (double)Math.round(res*1000)/1000;
        return res;
    }

    public double calcMultipleProfit(double averageMarketSharePrice, double EPS)
    {
        double res = averageMarketSharePrice/EPS;
        res = (double)Math.round(res*1000)/1000;
        return res;
    }
}
