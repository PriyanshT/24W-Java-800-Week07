package org.georgiancollege.week07;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class BookChartController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    void viewTable_onClick(ActionEvent event) {
        SceneChanger.changeScenes(event, "book-table-view.fxml", "View table!");
    }
}
