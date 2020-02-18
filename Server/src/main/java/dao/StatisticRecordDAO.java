package dao;

import constants.DBInfo;
import constants.SQLCommands;
import entity.CompanyStatisticsRecord;
import entity.CompanyStatisticsRecord;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class StatisticRecordDAO {
    
    private static StatisticRecordDAO instance;
    private PreparedStatement statement;
    private static Connection connection;
    
    private StatisticRecordDAO(Connection connection){
        this.connection = connection;
    }
    
    public static StatisticRecordDAO getInstance()
    {
        if(instance == null) 
        {
            try {
                connection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = new StatisticRecordDAO(connection);
        }
        return instance;
    }

    public ArrayList<CompanyStatisticsRecord> get(int id) {
        ResultSet resultSet;
        ArrayList<CompanyStatisticsRecord> statisticRecord = new ArrayList<>();
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_STATISTICS_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                statisticRecord.add(initializeStatisticRecord(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticRecord;
    }

    public Collection<CompanyStatisticsRecord> getAll() throws IOException {
        return null;
    }

    public void save(Collection<CompanyStatisticsRecord> entity) throws IOException {

    }

    public void add(CompanyStatisticsRecord record) {
        try{
            statement = connection.prepareStatement(SQLCommands.INSERT_STATISTIC_RECORD);
            statement.setInt(1,record.getIdCompany());
            statement.setString(2,record.getPeriodStart().toString());
            statement.setString(3,record.getPeriodEnd().toString());
            statement.setDouble(4,record.getAverageNumberOfSharesOutstanding());
            statement.setDouble(5,record.getProfitForHoldersOfOrdinaryShares());
            statement.setDouble(6,record.getDepreciationDeductions());
            statement.setDouble(7,record.getBeginningMarketYearStockPrice());
            statement.setDouble(8,record.getEndingMarketYearStockPrice());
            statement.setDouble(9,record.getDividendValueForPeriod());
            statement.setDouble(10,record.getAverageMarketSharePrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CompanyStatisticsRecord record) {

    }

    public void delete(CompanyStatisticsRecord record) {

    }

    private CompanyStatisticsRecord initializeStatisticRecord(ResultSet resultSet)
    {
        CompanyStatisticsRecord statisticRecord = new CompanyStatisticsRecord();
        try {
            statisticRecord.setIdCompany(resultSet.getInt("idCompany"));
            statisticRecord.setPeriodStart(resultSet.getString("periodStart"));
            statisticRecord.setPeriodEnd(resultSet.getString("periodEnd"));
            statisticRecord.setAverageNumberOfSharesOutstanding(resultSet.getDouble("avgShares"));
            statisticRecord.setProfitForHoldersOfOrdinaryShares(resultSet.getDouble("profitForHolders"));
            statisticRecord.setDepreciationDeductions(resultSet.getDouble("depreciationDeductions"));
            statisticRecord.setBeginningMarketYearStockPrice(resultSet.getDouble("beginningMarketYearStockPrice"));
            statisticRecord.setEndingMarketYearStockPrice(resultSet.getDouble("endingMarketYearStockPrice"));
            statisticRecord.setDividendValueForPeriod(resultSet.getDouble("dividendValueForPeriod"));
            statisticRecord.setAverageMarketSharePrice(resultSet.getDouble("averageMarketSharePrice"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticRecord;
    }
}
