package view;

import interfaces.BaseView;
import contoller.ShowGraphicsController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ShowGraphicsWindow extends JFrame implements BaseView, ItemListener {

    private int id,role;

    private JButton backBtn = new JButton("Back");
    private Vector<String> columnNames = new Vector<>();
    private JRadioButton isEPC = new JRadioButton("Прибыль на акцию");
    private JRadioButton isMoneyFor1Share = new JRadioButton("Поступления на 1 акцию");
    private JRadioButton isCapitilizedProfit = new JRadioButton("Капитализированный доход");
    private JRadioButton isDividendProfit = new JRadioButton("Дивидендный доход");
    private JRadioButton isCommonStockReturn = new JRadioButton("Доход от обычных акций");
    private JRadioButton isMultipleProfit = new JRadioButton("Цена/прибыль на 1 акцию");
    private ButtonGroup radioGroup = new ButtonGroup();

    ChartPanel chartPanel;



    private ShowGraphicsController controller = new ShowGraphicsController();

    ShowGraphicsWindow(int id, int role)
    {
        controller.attachView(this);
        controller.setId(id,role);
        this.id = id; this.role = role;
        initWindow();
    }

    @Override
    public void initWindow() {
        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        this.setLocationRelativeTo(null);


        isEPC.addItemListener(this);
        isMoneyFor1Share.addItemListener(this);
        isCapitilizedProfit.addItemListener(this);
        isDividendProfit.addItemListener(this);
        isCommonStockReturn.addItemListener(this);
        isMultipleProfit.addItemListener(this);

        radioGroup.add(isEPC); radioGroup.add(isMoneyFor1Share);
        radioGroup.add(isCapitilizedProfit); radioGroup.add(isDividendProfit);
        radioGroup.add(isCommonStockReturn); radioGroup.add(isMultipleProfit);
        GridLayout gridLayout = new GridLayout(0,1,10,15);
        JPanel grid = new JPanel();
        grid.setLayout(gridLayout);

        grid.add(isEPC); grid.add(isMoneyFor1Share);
        grid.add(isCapitilizedProfit); grid.add(isDividendProfit);
        grid.add(isCommonStockReturn); grid.add(isMultipleProfit);

        getContentPane().add(grid, BorderLayout.WEST);
        chartPanel = new ChartPanel(createChart(createDataset(controller.getStatisticsFromDB(),1)));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        getContentPane().add(chartPanel, BorderLayout.CENTER);

        isEPC.setSelected(true);
        setSize(700, 500);
        this.setVisible(true);
    }

    private void getStatisticsFromDB() {
        controller.getStatisticsFromDB();
    }

    public void onStatisticsLoaded(ArrayList<String[]> statistics) {
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        redrawChart(chartPanel);
    }

    private XYDataset createDataset(ArrayList<String[]> arr, int i)
    {
        TimeSeries series = new TimeSeries("");
        arr.forEach(a->{
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(a[0]);
                series.add(new Day(date), Double.parseDouble(a[i]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        TimeSeriesCollection dataSet = new TimeSeriesCollection();
        dataSet.addSeries(series);
        return dataSet;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Изменение коэффициента за период",
                "Период",
                "Коэф",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        DateAxis axis = new DateAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy"));
        plot.setDomainAxis(axis);
        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Изменение коэффициента",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    private void redrawChart(ChartPanel chartPanel)
    {
        int i = -1;
        if(isEPC.isSelected()) i = 1;
        if(isMoneyFor1Share.isSelected()) i = 2;
        if(isCapitilizedProfit.isSelected())i=3;
        if(isDividendProfit.isSelected())i=4;
        if(isCommonStockReturn.isSelected())i=5;
        if(isMultipleProfit.isSelected())i=6;
        if(i!=-1) {
            JFreeChart chart = chartPanel.getChart();
            ((XYPlot) chart.getPlot()).setDataset(createDataset(controller.getStatisticsFromDB(), i));
            chartPanel.setChart(chart);
        }
    }
}
