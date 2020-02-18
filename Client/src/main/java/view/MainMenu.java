package view;

import interfaces.BaseView;
import client.ClientSocket;

import javax.swing.*;

public class MainMenu extends JFrame implements BaseView {
    private JButton registrationButton = new JButton("Регистрация");
    private JPanel panel1;
    private JButton authorizationButton = new JButton("Авторизация");

    private static MainMenu view;

    public static void main(String[] args) {
        ClientSocket.initSocket();
        view = new MainMenu();
    }

    public MainMenu() {

        super("Главное меню");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initWindow();
    }


    @Override
    public void initWindow() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        getContentPane().add(authorizationButton);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, authorizationButton, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, authorizationButton, -20, SpringLayout.VERTICAL_CENTER, getContentPane());

        getContentPane().add(registrationButton);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registrationButton, 0, SpringLayout.HORIZONTAL_CENTER, authorizationButton);
        springLayout.putConstraint(SpringLayout.NORTH, registrationButton, 20, SpringLayout.SOUTH, authorizationButton);

        authorizationButton.addActionListener(e -> {
            view.setVisible(false);
            new LoginWindow().setVisible(true);
        });
        registrationButton.addActionListener(e -> {
            view.setVisible(false);
            new RegisterWindow().setVisible(true);
        });

        setSize(450, 200);
    }

}
