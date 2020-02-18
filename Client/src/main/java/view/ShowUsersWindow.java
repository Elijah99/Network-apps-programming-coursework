package view;

import interfaces.BaseView;
import contoller.ShowUsersController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ShowUsersWindow extends JFrame implements BaseView {
    private JTable table = new JTable();
    private JButton backBtn = new JButton("Назад");
    private Vector<String> columnNames = new Vector<>();

    private ShowUsersController controller = new ShowUsersController();

    private int role,id;

    ShowUsersWindow(int role, int id) {
        super("Users");
        this.id = id;
        this.role = role;
        controller.attachView(this);
        columnNames.addAll(List.of("id", "Логин", "Имя", "Фамилия"));
        controller.getAllUsers();
        initWindow();
    }

    public void setTable(Vector<Vector> data) {
        table = new JTable(data, columnNames);
    }

    @Override
    public void initWindow() {
        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        setLocationRelativeTo(null);

        getContentPane().add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        getContentPane().add(backBtn,BorderLayout.PAGE_END);
        backBtn.addActionListener(e-> {
                        new MenuWindow(role, id).setVisible(true);
                        this.setVisible(false);
                });

        setSize(500, 500);
        this.setVisible(true);
    }
}
