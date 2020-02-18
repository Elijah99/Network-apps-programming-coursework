package view;

import interfaces.BaseView;
import contoller.RegisterController;

import javax.swing.*;
import java.awt.*;

public class RegisterWindow extends JFrame implements BaseView {
    private JTextField loginTF = new JTextField();
    private JTextField passwordTF = new JTextField();
    private JLabel loginLB = new JLabel("Логин:");
    private JLabel passwordLB = new JLabel("Пароль:");
    private JButton registerBtn = new JButton("Зарегестрировать");
    private JTextField companyNameTF = new JTextField();
    private JLabel companyNameLB = new JLabel("Название компании:");

    private RegisterController registerController = new RegisterController();


    public RegisterWindow() {
        super("Меню регистрации");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        registerController.attachView(this);
        initWindow();
    }

    public void showMessageDialog(String text, int dialogType) {
        JOptionPane.showMessageDialog(this, text, "Авторизация", dialogType);
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(springLayout);

        getContentPane().add(loginTF);
        loginTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginTF, 20, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginTF, -60, SpringLayout.VERTICAL_CENTER, getContentPane());

        getContentPane().add(loginLB);
        springLayout.putConstraint(SpringLayout.EAST, loginLB, 0, SpringLayout.WEST, loginTF);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginLB, -60, SpringLayout.VERTICAL_CENTER, getContentPane());

        getContentPane().add(passwordTF);
        passwordTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordTF, 20, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, passwordTF, 40, SpringLayout.NORTH, loginTF);

        getContentPane().add(passwordLB);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLB, -20, SpringLayout.VERTICAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, passwordLB, 0, SpringLayout.WEST, passwordTF);

        getContentPane().add(companyNameTF);
        companyNameTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, companyNameTF, 20, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, companyNameTF, 40, SpringLayout.NORTH, passwordTF);

        getContentPane().add(companyNameLB);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, companyNameLB, 20, SpringLayout.VERTICAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, companyNameLB, 0, SpringLayout.WEST, companyNameTF);

        getContentPane().add(registerBtn);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registerBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, registerBtn, 40, SpringLayout.NORTH, companyNameTF);


        setSize(450, 300);

        registerBtn.addActionListener(e -> {
            String login = loginTF.getText();
            String password = passwordTF.getText();
            String companyName = companyNameTF.getText();
            registerController.registerUser(login, password,companyName);
        });
    }
}
