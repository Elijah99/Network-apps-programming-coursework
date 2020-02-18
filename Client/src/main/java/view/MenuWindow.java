package view;

import constants.Role;
import interfaces.BaseView;

import javax.swing.*;

public class MenuWindow extends JFrame implements BaseView {
    private JButton showStatisticsBtn = new JButton("Показать статистику компании");
    private JButton addStatisticsBtn = new JButton("Добавить запись статистики");
    private JButton showCoeffiсientsBtn = new JButton("Показать финансовые коэффициенты");
    private JButton showAllUsersBtn = new JButton("Показать список пользователей");
    private JButton addAdminBtn = new JButton("Добавить админа");
    private JButton showGraphics = new JButton("Показать графики коэффициентов");

    private int role;
    private int id;

    public MenuWindow(int role, int id) {
        super("Меню пользователя");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.id = id;
        this.role = role;
        initWindow();
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        getContentPane().setLayout(springLayout);

        getContentPane().add(showStatisticsBtn);
        showStatisticsBtn.addActionListener(e -> {
            new ShowCompanyStatistics(id,role).setVisible(true);
            this.setVisible(false);
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showStatisticsBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showStatisticsBtn, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(addStatisticsBtn);
        addStatisticsBtn.addActionListener(e -> {
            new AddCompanyStatistics(id,role).setVisible(true);
            this.setVisible(false);
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addStatisticsBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, addStatisticsBtn, 20, SpringLayout.SOUTH, showStatisticsBtn);



        getContentPane().add(showCoeffiсientsBtn);
        showCoeffiсientsBtn.addActionListener(e -> {
            new ShowCoeffsWindow(id,role);
            this.setVisible(false);
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showCoeffiсientsBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showCoeffiсientsBtn, 20, SpringLayout.SOUTH, addStatisticsBtn);

        getContentPane().add(showGraphics);
        showGraphics.addActionListener(e -> {
            new ShowGraphicsWindow(id,role);
            this.setVisible(false);
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showGraphics, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showGraphics, 20, SpringLayout.SOUTH, showCoeffiсientsBtn);


        getContentPane().add(showAllUsersBtn);
        showAllUsersBtn.setVisible(role == 1 || role == 2);
        showAllUsersBtn.addActionListener(e ->
        {
            new ShowUsersWindow(role,id).setVisible(true);
            this.setVisible(false);
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showAllUsersBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showAllUsersBtn, 20, SpringLayout.SOUTH, showGraphics);

        getContentPane().add(addAdminBtn);
        addAdminBtn.setVisible(role == 2);
        addAdminBtn.addActionListener(e ->
        {
            new AddAdminWindow(role,id).setVisible(true);
            this.setVisible(false);
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addAdminBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, addAdminBtn, 20, SpringLayout.SOUTH, showAllUsersBtn);

        setSize(350, 400);

    }
}
