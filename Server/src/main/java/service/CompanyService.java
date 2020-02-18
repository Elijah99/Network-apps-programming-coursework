package service;

import dao.CompanyDAO;
import dao.StatisticRecordDAO;
import entity.Company;
import entity.CompanyStatisticsRecord;
import entity.User;

import java.io.*;
import java.util.ArrayList;

public class CompanyService {
    private static CompanyService instance;

    private CompanyService(){}

    public static CompanyService getInstance()
    {
        if(instance == null)
        {
            instance = new CompanyService();
        }
        return instance;
    }

    public void getCompanyStatistics(BufferedReader inputStream, PrintWriter outputStream) {
        try {
            String buf = inputStream.readLine().trim();
            int idUser = Integer.parseInt(buf);
            Company company = CompanyDAO.getInstance().get(idUser);
            ArrayList<CompanyStatisticsRecord> statistics = StatisticRecordDAO.getInstance().get(company.getId());
            if (statistics != null) {
                outputStream.println(statistics.size());
                statistics.forEach(row ->
                {
                    outputStream.println(new String("" + row.getIdCompany() + " " + row.getPeriodStart() + " " +
                            row.getPeriodEnd() + " " + row.getAverageNumberOfSharesOutstanding() + " " +
                            row.getProfitForHoldersOfOrdinaryShares() + " " + row.getDepreciationDeductions() + " " +
                            row.getBeginningMarketYearStockPrice() + " " + row.getEndingMarketYearStockPrice() + " " +
                            row.getDividendValueForPeriod() + " " + row.getAverageMarketSharePrice()));
                    outputStream.flush();
                });
            } else outputStream.write(0);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addStatisticsRecord(BufferedReader inputStream)
    {
        try {
            String receivedRecord = inputStream.readLine();
            String[] values = receivedRecord.trim().split(" ");
            CompanyStatisticsRecord newRecord= new CompanyStatisticsRecord();

            int idUser = Integer.parseInt(values[0]);
            Company company = CompanyDAO.getInstance().get(idUser);
            User user = new User();
            user.setId(idUser); user.setCompany(company);

            newRecord.setIdCompany(company.getId());
            newRecord.setPeriodStart(values[1]);
            newRecord.setPeriodEnd(values[2]);
            newRecord.setAverageNumberOfSharesOutstanding(Double.parseDouble(values[3]));
            newRecord.setProfitForHoldersOfOrdinaryShares(Double.parseDouble(values[4]));
            newRecord.setDepreciationDeductions(Double.parseDouble(values[5]));
            newRecord.setBeginningMarketYearStockPrice(Double.parseDouble(values[6]));
            newRecord.setEndingMarketYearStockPrice(Double.parseDouble(values[7]));
            newRecord.setDividendValueForPeriod(Double.parseDouble(values[8]));
            newRecord.setAverageMarketSharePrice(Double.parseDouble(values[9]));

            user.getCompany().addStatisticsRecord(newRecord);
            StatisticRecordDAO.getInstance().add(newRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
