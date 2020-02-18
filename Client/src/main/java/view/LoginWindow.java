package view;

import interfaces.BaseView;
import contoller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame implements BaseView {
    private JTextField loginTF = new JTextField();
    private JTextField passwordTF = new JTextField();
    private JLabel loginLB = new JLabel("Логин:");
    private JLabel passwordLB = new JLabel("Пароль:");
    private JButton loginBtn = new JButton("Авторизация");

    private LoginController loginController = new LoginController();


    public LoginWindow() {
        super("Авторизация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginController.attachView(this);
        initWindow();
    }

    public void showMessageDialog(String text, int dialogType) {
        JOptionPane.showMessageDialog(this, text, "Авторизация", dialogType);
    }

    public void showRegisterDialog() {
        String[] options = {"Да", "Нет"};
        int x = JOptionPane.showOptionDialog(
                this,
                "Пользователь не найдет,хотите зарегестрироваться?",
                "Ошибка",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (x == 0) {
            this.setVisible(false);
            new RegisterWindow().initWindow();
        }
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(springLayout);

        getContentPane().add(loginTF);
        loginTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginTF, 20, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginTF, -40, SpringLayout.VERTICAL_CENTER, getContentPane());

        getContentPane().add(loginLB);
        springLayout.putConstraint(SpringLayout.EAST, loginLB, 0, SpringLayout.WEST, loginTF);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginLB, -40, SpringLayout.VERTICAL_CENTER, getContentPane());

        getContentPane().add(passwordTF);
        passwordTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordTF, 20, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, passwordTF, 40, SpringLayout.NORTH, loginTF);

        getContentPane().add(passwordLB);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLB, 0, SpringLayout.VERTICAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, passwordLB, 0, SpringLayout.WEST, passwordTF);

        getContentPane().add(loginBtn);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 20, SpringLayout.SOUTH, passwordTF);
        loginBtn.addActionListener(e -> {
            String login = loginTF.getText();
            String password = passwordTF.getText();
            loginController.logInUser(login, password);
        });

        setSize(450, 200);
    }
}
