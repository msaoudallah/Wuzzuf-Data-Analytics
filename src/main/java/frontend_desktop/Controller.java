package frontend_desktop;

import frontend.Consumer;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.knowm.xchart.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    static Consumer consumer = new Consumer();
    public Button exit_button = new Button();
    public void top_10_jobs_button_clicked()
    {
        HashMap<String,Integer> jobs_receiver = consumer.mostPopularJobTitles();
        CategoryChart most_popular_jobs_bar_chart = new CategoryChartBuilder().width(1500).height(600).title("Most popular job titles").xAxisTitle("Job").yAxisTitle("Number of jobs").build();
        ArrayList<String> job_titles = new ArrayList<String>(jobs_receiver.keySet());
        ArrayList<Integer> job_count = new ArrayList<Integer>(jobs_receiver.values());
        most_popular_jobs_bar_chart.addSeries("Jobs",job_titles,job_count);
        most_popular_jobs_bar_chart.getStyler().setXAxisLabelRotation(45);
        JFrame frame = new JFrame("Top 10 Jobs Bar Chart");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel chartPanel = new XChartPanel(most_popular_jobs_bar_chart);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
    public void top_10_areas_button_clicked()
    {
        HashMap<String,Integer> areas_receiver = consumer.mostPopularAreas();
        CategoryChart most_popular_areas_bar_chart = new CategoryChartBuilder().width(1500).height(600).title("Most popular areas").xAxisTitle("Area").yAxisTitle("Number of jobs").build();
        ArrayList<String> areas = new ArrayList<String>(areas_receiver.keySet());
        ArrayList<Integer> areas_count = new ArrayList<Integer>(areas_receiver.values());
        most_popular_areas_bar_chart.addSeries("Jobs",areas,areas_count);
        JFrame frame = new JFrame("Top 10 Areas Bar Chart");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel chartPanel = new XChartPanel(most_popular_areas_bar_chart);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void top_10_companies_button_clicked()
    {
        HashMap<String,Double> hm= consumer.jobsPerCompanyPie();
        List<Double> Count= new ArrayList<Double>();
        List<String> Companies =  new ArrayList<String>();
        hm.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            Count.add(entry.getValue());
            Companies.add(entry.getKey());
        });
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Pie Chart with 4 Slices").build();
        for (int i =0; i<Count.size(); i++) {

            chart.addSeries(Companies.get(i),Count.get(i));
        }
        JFrame frame = new JFrame("Top 10 Companies Pie Chart");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel chartPanel = new XChartPanel(chart);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void summary_button_clicked()
    {
        JLabel label = new JLabel("Hello");

        String text = consumer.summary();
        JTextArea textArea = new JTextArea(2, 20);
        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(UIManager.getColor("Label.background"));
        textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setBorder(UIManager.getBorder("Label.border"));

        JFrame frame = new JFrame("Top 10 Companies Pie Chart");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(textArea);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void exit_button_clicked()
    {
        ((Stage)exit_button.getScene().getWindow()).close();
    }
}
