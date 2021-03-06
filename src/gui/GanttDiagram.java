package gui;

import brownlomicki.Period;
import brownlomicki.Product;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class GanttDiagram extends ApplicationFrame {

    private final JFreeChart chart;

    public GanttDiagram(String title, List<Product> listOfProduct) {
        super(title);
        IntervalCategoryDataset dataset = createDataset(listOfProduct);
        chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        setDefaultCloseOperation(ApplicationFrame.DO_NOTHING_ON_CLOSE);
        pack();

    }

    /**
     *
     * @param filePath scierzka do plików
     */
    public void saveGanttDiagramToFile(String filePath) {
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
        File file = new File(filePath);

        try {
            ChartUtilities.saveChartAsPNG(file, chart, 1000, 540);
        } catch (Exception e) {
        }
    }

    /**
     *
     * @param point punkt w czasie
     * @return
     */
    private static Date createTimePoint(final int point) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, 0, 0, point);
        final Date result = calendar.getTime();
        return result;
    }

    /**
     *
     * @param listOfProduct lista produktów
     * @return interwaly czasowe
     */
    public static IntervalCategoryDataset createDataset(List<Product> listOfProduct) {
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        TaskSeries taskSeries;

        for (Product p : listOfProduct) {
            taskSeries = new TaskSeries(p.getIdName() + "(" + (p.getNumber() + 1) + ")");
            int i = 1;
            for (Period period : p.getPeriodWorks()) {
                taskSeries.add(new Task("Machine " + i,
                        createTimePoint(period.getStart()),
                        createTimePoint(period.getEnd())));
                i++;
            }
            collection.add(taskSeries);
        }

        return collection;
    }

    /**
     * tworzenie wykresu
     *
     * @param dataset interwaly
     * @return wykres
     */
    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
                "", // chart title
                "Machines", // domain axis label
                "Times", // range axis label
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
        );
        return chart;
    }

}
