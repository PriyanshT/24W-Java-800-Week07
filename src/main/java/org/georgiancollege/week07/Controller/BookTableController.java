package org.georgiancollege.week07.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.georgiancollege.week07.Utilities.DBUtility;
import org.georgiancollege.week07.Model.Book;
import org.georgiancollege.week07.Utilities.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookTableController implements Initializable {

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, Boolean> availabilityColumn;

    @FXML
    private TableColumn<Book, Integer> bookIdColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    private TableColumn<Book, Double> priceColumn;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, Integer> unitsSoldColumn;

    @FXML
    private CheckBox availableCheckBox;

    @FXML
    private CheckBox expensiveCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Book> returnedBooks = DBUtility.getBooksFromDB("1");
        System.out.println(returnedBooks);

        // initialize all the columns with the model class
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        unitsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));

        // add the data to the table view
        tableView.getItems().addAll(returnedBooks);
    }

    @FXML
    void manipulateTable(ActionEvent event) {
        tableView.getItems().clear();
        if(availableCheckBox.isSelected() && expensiveCheckBox.isSelected()){
//            System.out.println("Both selected");
            tableView.getItems().addAll(DBUtility.getBooksFromDB("is_available = true AND price > 20"));
        } else if (availableCheckBox.isSelected() && !expensiveCheckBox.isSelected()) {
            tableView.getItems().addAll(DBUtility.getBooksFromDB("is_available = true"));
        } else if (!availableCheckBox.isSelected() && expensiveCheckBox.isSelected()) {
            tableView.getItems().addAll(DBUtility.getBooksFromDB("price > 20"));
        } else {
            tableView.getItems().addAll(DBUtility.getBooksFromDB("1"));
        }
    }

    @FXML
    void addNewBook_onClick(ActionEvent event) {
        SceneChanger.changeScenes(event, "create-book-view.fxml", "Create New Book!");
    }

    @FXML
    void viewChart_onClick(ActionEvent event) {
        SceneChanger.changeScenes(event, "book-chart-view.fxml", "View Chart!");
    }
}
