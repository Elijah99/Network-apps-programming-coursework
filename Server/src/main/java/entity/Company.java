package entity;

import dao.StatisticRecordDAO;

import java.util.ArrayList;

public class Company extends User {
    private String name;
    /* Статистика по компании. За определенные период.*/
    private ArrayList<CompanyStatisticsRecord> statistic = StatisticRecordDAO.getInstance().get(getId());

    public ArrayList<CompanyStatisticsRecord> getStatistic() {
        return statistic;
    }

    public void setStatistic(ArrayList<CompanyStatisticsRecord> statistic) {
        this.statistic = statistic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStatisticsRecord(CompanyStatisticsRecord record)
    {
        statistic.add(record);
    }
}
