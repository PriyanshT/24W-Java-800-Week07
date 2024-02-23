package org.georgiancollege.week07;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.util.ResourceBundle;

public class BookChartController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryAxis.setLabel("Book Name");
        numberAxis.setLabel("Units Sold");
        barChart.getData().addAll(DBUtility.getChartDataFromDB());
    }

    @FXML
    void viewTable_onClick(ActionEvent event) {
        SceneChanger.changeScenes(event, "book-table-view.fxml", "View table!");
    }
}
