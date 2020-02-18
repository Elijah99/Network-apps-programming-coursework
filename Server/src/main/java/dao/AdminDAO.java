package dao;

import constants.DBInfo;
import constants.SQLCommands;
import entity.Admin;
import entity.Admin;

import java.sql.*;

import static constants.DBInfo.*;

public class AdminDAO {

    private static AdminDAO instance;

    private PreparedStatement statement;
    private static Connection connection;

    private AdminDAO(Connection connection){
        this.connection = connection;
    }

    public static AdminDAO getInstance()
    {
        if(instance == null)
        {
            try {
                connection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = new AdminDAO(connection);
        }
        return instance;
    }

    public boolean isAdmin(int id) {
        ResultSet resultSet;
        Admin user = null;
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_ADMIN_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next() != false)
            {
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isSuper(int id) {
        ResultSet resultSet;
        Admin user = null;
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_ADMIN_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next() != false)
            {
                boolean isSuper = resultSet.getBoolean("isSuper");
                return isSuper;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
