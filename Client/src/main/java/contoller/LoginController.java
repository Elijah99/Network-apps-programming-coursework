package contoller;

import interfaces.BaseController;
import constants.Role;
import constants.ServerActions;
import view.LoginWindow;
import view.MenuWindow;

import javax.swing.*;

public class LoginController extends BaseController<LoginWindow> {

    public void logInUser(String login, String password) {
        if (!login.isEmpty() && !password.isEmpty()) {
            sendDataToServer(ServerActions.LOGIN);
            sendDataToServer(login + " " + password);
            Role role = Role.valueOf(getDataFromServer());
            String buf = getDataFromServer();
            int id = Integer.parseInt(buf);
            if (role == Role.failed) {
                view.showRegisterDialog();
            } else if (role == Role.user) {
                view.showMessageDialog("Вы вошли как обычный пользователь", JOptionPane.INFORMATION_MESSAGE);
                view.setVisible(false);
                new MenuWindow(0, id).setVisible(true);
            } else if (role == Role.admin) {
                view.showMessageDialog("Вы вошли как админ", JOptionPane.INFORMATION_MESSAGE);
                view.setVisible(false);
                new MenuWindow(1, id).setVisible(true);
            }
            else if (role == Role.superAdmin)
            {
                view.showMessageDialog("Вы вошли как супер админ", JOptionPane.INFORMATION_MESSAGE);
                view.setVisible(false);
                new MenuWindow(2, id).setVisible(true);
            }
        } else {
            view.showMessageDialog("Заполните поля!", JOptionPane.ERROR_MESSAGE);
        }
    }


}
