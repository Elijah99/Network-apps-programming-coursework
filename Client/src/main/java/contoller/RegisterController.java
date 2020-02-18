package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.LoginWindow;
import view.MainMenu;
import view.RegisterWindow;

import javax.swing.*;

public class RegisterController extends BaseController<RegisterWindow> {

    public void registerUser(String login, String password, String companyName) {
        sendDataToServer(ServerActions.REGISTER);
        sendDataToServer(login + " " + password + " " + companyName);
        String result = getDataFromServer();
        if (result.equalsIgnoreCase("REGISTERED")) {
            view.showMessageDialog("Registered", JOptionPane.INFORMATION_MESSAGE);
            LoginController loginController = new LoginController();
            loginController.attachView(new LoginWindow());
            loginController.logInUser(login, password);
            view.setVisible(false);
        }
        if(result.equalsIgnoreCase("USER_EXISTS"))
        {
            view.showMessageDialog("User exists, try again", JOptionPane.INFORMATION_MESSAGE);
            view.setVisible(false);
            new MainMenu().initWindow();
        }
    }

}
