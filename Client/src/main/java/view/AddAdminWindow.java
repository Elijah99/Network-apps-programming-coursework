package view;

import interfaces.BaseView;
import contoller.AddAdminController;

import javax.swing.*;
import java.awt.*;

public class AddAdminWindow extends JFrame implements BaseView {

    private JLabel infoTF = new JLabel("Введите информацию о новом админе");
    private JTextField loginTF = new JTextField();
    private JTextField passwordTF = new JTextField();
    private JLabel loginLB = new JLabel("Логин:");
    private JLabel passwordLB = new JLabel("Пароль:");
    private JButton addAdminBtn = new JButton("Добавить");

    private AddAdminController controller;

    private int role,id;

    public AddAdminWindow(int role, int id) {
        super("Добавить админа");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.id = id;
        this.role = role;
        controller = new AddAdminController(this,role,id);
        initWindow();
    }

    public void showMessageDialog(String text, int dialogType) {
        JOptionPane.showMessageDialog(this, text, "", dialogType);
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

        getContentPane().add(infoTF);
        springLayout.putConstraint(SpringLayout.SOUTH, infoTF, -20, SpringLayout.NORTH, loginTF);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, infoTF, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());

        getContentPane().add(passwordTF);
        passwordTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordTF, 20, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, passwordTF, 40, SpringLayout.NORTH, loginTF);

        getContentPane().add(passwordLB);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLB, 0, SpringLayout.VERTICAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, passwordLB, 0, SpringLayout.WEST, passwordTF);

        getContentPane().add(addAdminBtn);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addAdminBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, addAdminBtn, 20, SpringLayout.SOUTH, passwordTF);
        addAdminBtn.addActionListener(e -> {
            String login = loginTF.getText();
            String password = passwordTF.getText();
            controller.addAdmin(login,password);
        });

        setSize(450, 200);
    }
}
