package org.georgiancollege.week07.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import org.georgiancollege.week07.Utilities.DBUtility;
import org.georgiancollege.week07.Utilities.SceneChanger;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class BookChartController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private RadioButton availableRadioButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryAxis.setLabel("Book Name");
        numberAxis.setLabel("Units Sold");
        barChart.getData().addAll(DBUtility.getChartDataFromDB());
    }

    @FXML
    void addNewBook_onClick(ActionEvent event) {
        // adding new data to our chart
        XYChart.Series<String, Integer> newData = new XYChart.Series<>();
        SecureRandom secureRandom = new SecureRandom();
        newData.setName(String.valueOf(secureRandom.nextInt(1950, 2024)));

        newData.getData().add(new XYChart.Data<>("Harry Potter", secureRandom.nextInt(20, 100)));
        newData.getData().add(new XYChart.Data<>("Fake Book 2", secureRandom.nextInt(20, 100)));
        newData.getData().add(new XYChart.Data<>("Fake Book 3", secureRandom.nextInt(20, 100)));

        barChart.getData().addAll(newData);
    }

    @FXML
    void availableRadioButton_onClick(ActionEvent event) {
        barChart.getData().clear();
        if(availableRadioButton.isSelected()){
            barChart.getData().addAll(DBUtility.getAvailableChartDataFromDB());
        } else {
            barChart.getData().addAll(DBUtility.getChartDataFromDB());
        }
    }

    @FXML
    void viewTable_onClick(ActionEvent event) {
        SceneChanger.changeScenes(event, "book-table-view.fxml", "View table!");
    }
}
