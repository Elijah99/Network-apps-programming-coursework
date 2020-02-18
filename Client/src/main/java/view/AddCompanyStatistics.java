package view;

import interfaces.BaseView;
import contoller.AddCompanyStatisticsController;

import javax.swing.*;
import java.awt.*;

public class AddCompanyStatistics extends JFrame implements BaseView {

    private int id,role;

    private JTextField periodStartTF = new JTextField();
    private JTextField periodEndTF = new JTextField();
    private JTextField averageSharesTF = new JTextField();
    private JTextField profitForHoldersTF = new JTextField();
    private JTextField deprecationDeductionsTF = new JTextField();
    private JTextField beginningYearPriceTF = new JTextField();
    private JTextField endOfYearPriceTF = new JTextField();
    private JTextField dividendValueTF = new JTextField();
    private JTextField averageMarketShareTF = new JTextField();

    private JLabel periodStartLB = new JLabel("Начало периода");
    private JLabel periodEndLB = new JLabel("Конец периода");
    private JLabel averageSharesLB = new JLabel("Среднее кол-во акций в обращении");
    private JLabel profitForHoldersLB = new JLabel("Прибыль владельцев акций");
    private JLabel deprecationDeductionsLB = new JLabel("Амортизационные отчисления");
    private JLabel beginningYearPriceLB = new JLabel("Цена акции на начало периода");
    private JLabel endOfYearPriceLB = new JLabel("Цена акции в конце периода");
    private JLabel dividendValueLB = new JLabel("Величина дивиденда за период");
    private JLabel averageMarketShareLB = new JLabel("Среднее значение цены акции");

    private JButton addRecordBtn = new JButton("Добавить");

    AddCompanyStatisticsController controller = new AddCompanyStatisticsController(this,id,role);

    public AddCompanyStatistics(int id, int role)
    {
        super("Добавить запись");
        this.id = id;
        this.role = role;
        initWindow();
    }

    @Override
    public void initWindow() {
        GridLayout gridLayout = new GridLayout(0,2,10,15);
        JPanel grid = new JPanel();
        grid.setLayout(gridLayout);
        setSize(300,700);
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        Dimension tfSize = new Dimension(50,20);

        periodStartTF.setPreferredSize(tfSize);
        periodEndTF.setPreferredSize(tfSize);
        averageSharesTF.setPreferredSize(tfSize);
        profitForHoldersTF.setPreferredSize(tfSize);
        deprecationDeductionsTF.setPreferredSize(tfSize);
        beginningYearPriceTF.setPreferredSize(tfSize);
        endOfYearPriceTF.setPreferredSize(tfSize);
        dividendValueTF.setPreferredSize(tfSize);
        averageMarketShareTF.setPreferredSize(tfSize);
        addRecordBtn.setPreferredSize(new Dimension(40,30));

        addRecordBtn.addActionListener(e->
        {
            controller.addRecord();
            this.setVisible(false);
            new MenuWindow(role, id).initWindow();
        });

        grid.add(periodStartLB); grid.add(periodStartTF);
        grid.add(periodEndLB); grid.add(periodEndTF);
        grid.add(averageSharesLB); grid.add(averageSharesTF);
        grid.add(profitForHoldersLB); grid.add(profitForHoldersTF);
        grid.add(deprecationDeductionsLB); grid.add(deprecationDeductionsTF);
        grid.add(beginningYearPriceLB); grid.add(beginningYearPriceTF);
        grid.add(endOfYearPriceLB); grid.add(endOfYearPriceTF);
        grid.add(dividendValueLB); grid.add(dividendValueTF);
        grid.add(averageMarketShareLB); grid.add(averageMarketShareTF);
        grid.add(addRecordBtn);

        getContentPane().add(grid);
        pack();
        setLocationRelativeTo(null);

     setVisible(true);
    }

    public String[] getTFValues()
    {
        String[] result = new String[11];
        result[0] = String.valueOf(id);
        result[1] = periodEndTF.getText();
        result[2] = periodEndTF.getText();
        result[3] = averageSharesTF.getText();
        result[4] = profitForHoldersTF.getText();
        result[5] = deprecationDeductionsTF.getText();
        result[6] = beginningYearPriceTF.getText();
        result[7] = endOfYearPriceTF.getText();
        result[8] = dividendValueTF.getText();
        result[9] = averageMarketShareTF.getText();
        return result;
    }
}
