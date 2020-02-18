package dao;

import constants.DBInfo;
import constants.SQLCommands;
import entity.Company;
import entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CompanyDAO implements Dao<Company> {

    private PreparedStatement statement;
    private static Connection connection;

    private static CompanyDAO instance;

    private CompanyDAO(Connection connection) {
        this.connection = connection;
    }

    public static CompanyDAO getInstance()
    {
        if(instance == null)
        {
            try {
                connection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = new CompanyDAO(connection);
        }
        return instance;
    }

    @Override
    public Company get(int id) {
        ResultSet resultSet;
        Company company = null;
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_COMPANY_BY_USER_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet != null)
            {
                resultSet.next();
                return initializeCompany(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;    }

    @Override
    public Collection<Company> getAll() throws IOException {
        ResultSet resultSet;
        Collection<Company> companies = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQLCommands.SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                  /*  User user = initializeCompany(resultSet);
                    companies.add(user);*/
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
    @Override
    public void save(Collection<Company> companies) throws IOException {
        try {
            statement = connection.prepareStatement(SQLCommands.DELETE_ALL_USERS);
            statement.executeUpdate();
            companies.forEach(this::add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Company company) {

    }

    public void add(User user) {
        try{
            statement = connection.prepareStatement(SQLCommands.INSERT_COMPANY);
            statement.setString(1,user.getCompany().getName());
            statement.setInt(2,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void delete(Company company) {
       /* try {
            statement = connection.prepareStatement(SQLCommands.DELETE_USER);
            statement.setInt(1, deletedUser.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private Company initializeCompany(ResultSet resultSet)
    {
        Company company = new Company();
        try {
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

}
