package contoller;

import interfaces.BaseController;
import constants.ServerActions;
import view.AddAdminWindow;
import view.MenuWindow;

import javax.swing.*;

public class AddAdminController extends BaseController<AddAdminWindow> {

    private int id, role;

    public AddAdminController(AddAdminWindow addAdminWindow,int role, int id)
    {
        attachView(addAdminWindow);
        this.id = id;
        this.role=role;
    }

    public void addAdmin(String login, String password) {
        sendDataToServer(ServerActions.ADD_ADMIN);
        sendDataToServer(login + " " + password);
        String result = getDataFromServer().trim();
        if (result.equalsIgnoreCase("ADMIN_ADDED")) {
            view.showMessageDialog("Админ добавлен",
                    JOptionPane.INFORMATION_MESSAGE);
            view.setVisible(false);
            new MenuWindow(role, id).initWindow();
        }
        if(result.equalsIgnoreCase("USER_EXISTS"))
        {
            view.showMessageDialog("Пользователь уже существует, введите другие данные",
                    JOptionPane.INFORMATION_MESSAGE);
            new AddAdminWindow(role,id).initWindow();
        }
    }
}
