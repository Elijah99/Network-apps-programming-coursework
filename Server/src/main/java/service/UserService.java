package service;

import constants.Role;
import dao.AdminDAO;
import dao.CompanyDAO;
import dao.UserDAO;
import entity.Company;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class UserService {

    private static UserService instance;

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void logIn(BufferedReader inputStream, PrintWriter outputStream) {
        try {
            String queryContent = inputStream.readLine();
            String[] arr = queryContent.split(" ");
            String login = arr[0];
            String password = arr[1];
            boolean isAdmin = false, isSuper = false;
            User user = UserDAO.getInstance().getByLoginAndPass(login, password);
            if (user != null) {
                isAdmin = AdminDAO.getInstance().isAdmin(user.getId());
                if (isAdmin) {
                    isSuper = AdminDAO.getInstance().isSuper(user.getId());
                }
            }
            if (isSuper) {
                outputStream.println(Role.superAdmin);
                outputStream.println(String.valueOf(user.getId()));
            } else if (isAdmin) {
                outputStream.println(Role.admin);
                outputStream.println(String.valueOf(user.getId()));
            } else if (user != null) {
                outputStream.println(Role.user);
                outputStream.println(String.valueOf(user.getId()));
            } else {
                outputStream.println(Role.failed);
                outputStream.println(String.valueOf(-1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers(PrintWriter outputStream) {
        Collection<User> users = UserDAO.getInstance().getAll();

        outputStream.println(String.valueOf(users.size()));

        users.forEach(user ->
        {
            String send = "";
            send += user.getId() + " ";
            send += user.getLogin() + " ";
            if (user.getName() != null) send += user.getName() + " ";
            else send += "Неизвестно ";
            if (user.getSurname() != null) send += user.getSurname();
            else send += "Неизвестно";

            outputStream.println(send);
        });

    }

    public boolean registerUser(BufferedReader inputStream) {
        try {
            String queryContent = inputStream.readLine();
            String[] arr = queryContent.split(" ");
            String login = arr[0];
            String password = arr[1];
            String companyName = arr[2];

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            if (isUserExists(user)) {
                return false;
            } else {
                UserDAO.getInstance().add(user);
                Company company = new Company();
                company.setName(companyName);
                user = UserDAO.getInstance().getByLogin(user.getLogin());
                user.setCompany(company);
                CompanyDAO.getInstance().add(user);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUserExists(User user) {
        User check = UserDAO.getInstance().getByLogin(user.getLogin());
        if (check != null) return true;
        else return false;
    }

    public boolean addAdmin(BufferedReader inputStream) {

        String queryContent = null;
        try {
            queryContent = inputStream.readLine();
            String[] arr = queryContent.split(" ");
            String login = arr[0];
            String password = arr[1];

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);

            if (isUserExists(user)) {
                return false;
            } else {
                UserDAO.getInstance().add(user);
                user = UserDAO.getInstance().getByLogin(user.getLogin());
                UserDAO.getInstance().setUserAsAdmin(user);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
