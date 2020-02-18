package dao;

import constants.SQLCommands;
import entity.Company;
import entity.User;
import constants.DBInfo;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserDAO implements Dao<User> {

    private static UserDAO instance;

    private PreparedStatement statement;
    private static Connection connection;

    private UserDAO(Connection connection){
        this.connection = connection;
    }

    public static UserDAO getInstance()
    {
        if(instance == null)
        {
            try {
                connection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = new UserDAO(connection);
        }
        return instance;
    }

    @Override
    public User get(int id) {
        ResultSet resultSet;
        User user = null;
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_USER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next() != false)
            {
                return initializeUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        ResultSet resultSet;
        Collection<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQLCommands.SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            if(resultSet != null) {
                while (resultSet.next()) {
                    User user = initializeUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(Collection<User> users) throws IOException {
        try {
            statement = connection.prepareStatement(SQLCommands.DELETE_ALL_USERS);
            statement.executeUpdate();
            users.forEach(this::add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try{
            statement = connection.prepareStatement(SQLCommands.INSERT_USER);
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getName());
            statement.setString(4,user.getSurname());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User updatedUser) {
      try{
          statement = connection.prepareStatement(SQLCommands.UPDATE_USER);
          statement.setString(1,updatedUser.getLogin());
          statement.setString(2,updatedUser.getPassword());
          statement.setString(3,updatedUser.getName());
          statement.setString(4,updatedUser.getSurname());
          statement.setInt(5, updatedUser.getId());
          statement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    @Override
    public void delete(User deletedUser) {
        try {
            statement = connection.prepareStatement(SQLCommands.DELETE_USER);
            statement.setInt(1, deletedUser.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getByLoginAndPass(String login, String password) {
        ResultSet resultSet;
        User user = null;
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1,login);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next() != false)
            {
                return initializeUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByLogin(String login)
    {
        ResultSet resultSet;
        User user = null;
        try{
            statement = connection.prepareStatement(SQLCommands.SELECT_USER_BY_LOGIN);
            statement.setString(1,login);
            resultSet = statement.executeQuery();
            if(resultSet.next() != false)
            {
                return initializeUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setUserAsAdmin(User user)
    {
        try{
            statement = connection.prepareStatement(SQLCommands.INSERT_NEW_ADMIN);
            statement.setInt(1,user.getId());
            statement.setBoolean(2,false);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User initializeUser(ResultSet resultSet)
    {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setCompany(CompanyDAO.getInstance().get(user.getId()));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
