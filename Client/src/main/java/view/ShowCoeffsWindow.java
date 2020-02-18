package view;

import interfaces.BaseView;
import contoller.ShowCoefficientsController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ShowCoeffsWindow extends JFrame implements BaseView {
    private JTable table = new JTable();
    private JButton backBtn = new JButton("Назад");
    private Vector<String> columnNames = new Vector<>();

    private ShowCoefficientsController controller = new ShowCoefficientsController();

    ShowCoeffsWindow(int id,int role) {
        super("Коэффициенты");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controller.attachView(this);
        controller.setIdAndRole(id,role);
        columnNames.addAll(List.of("Период", "Прибыль на акцию","Поступления на 1 акцию","Капитал.доход",
                "Дивидентный доход","Доход. обычных акций","Цена/Прибыль на 1 акцию"));
        getStatisticsFromDB();
        for(int i = 1; i < columnNames.size(); i++)
        {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setMinWidth(100);
            column.setResizable(true);
        }
        initWindow();
    }

    @Override
    public void initWindow() {
        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        this.setLocationRelativeTo(null);
        setSize(1100, 400);
        getContentPane().add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ), BorderLayout.CENTER);

        getContentPane().add(backBtn,BorderLayout.PAGE_END);
        backBtn.addActionListener(e -> controller.navigateBack());

        this.setVisible(true);
    }

    private void getStatisticsFromDB() {
        controller.getStatisticsFromDB();
    }

    public void onStatisticsLoaded(Vector<Vector> statistics) {
        table = new JTable(statistics, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

}
