package org.georgiancollege.week07;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateBookController implements Initializable {

    @FXML
    private TextField authorTextField;

    @FXML
    private CheckBox availabilityCheckBox;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private Label finalLabel;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private Spinner<Double> priceSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am in the initialize function.");

        // hide the final label
        finalLabel.setVisible(false);

        // add data to genre combo box
        genreComboBox.getItems().addAll(Book.findGenres());

        // initialize price spinner
        SpinnerValueFactory<Double> spinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 20, 5);
        priceSpinner.setValueFactory(spinnerValueFactory);
        priceSpinner.setEditable(true);

        // getting the text field from the spinner
        TextField priceSpinnerTextField = priceSpinner.getEditor();

        // anonymous inner function
//        priceSpinnerTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                // System.out.println(newValue);
//                try {
//                    Double.parseDouble(newValue);
//                    finalLabel.setVisible(false);
//                } catch (Exception e){
//                    finalLabel.setVisible(true);
//                    finalLabel.setText("Please enter a decimal value for price.");
//                    priceSpinnerTextField.setText(oldValue);
//                }
//            }
//        });

        // lambda function
        priceSpinnerTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            // System.out.println(newValue);
            try {
                Double.parseDouble(newValue);
                finalLabel.setVisible(false);
            } catch (Exception e){
                finalLabel.setVisible(true);
                finalLabel.setText("Please enter a decimal value for price.");
                priceSpinnerTextField.setText(oldValue);
            }
        });
    }

    @FXML
    void saveBook(ActionEvent event) {
        System.out.println("Button Clicked!");

        // un-hide the final label
        finalLabel.setVisible(true);

        try {
            // get user data and store it in variables
            String bookName = bookNameTextField.getText();
            String author = authorTextField.getText();
            String genre = genreComboBox.getSelectionModel().getSelectedItem();
            double price = priceSpinner.getValue();
            boolean availability = availabilityCheckBox.isSelected();

            // initialize a book object and pass the values to the constructor
            Book book = new Book(bookName, author, genre, price, availability);

            // adding book data to db
            int generatedBookId = DBUtility.insertBookIntoDB(book);
            finalLabel.setText("Book with id: " + generatedBookId + " added.");
        } catch (Exception e){
            finalLabel.setText(e.getMessage());
        }
    }

    @FXML
    void viewTableButton_onClick(ActionEvent event) {
        SceneChanger.changeScenes(event, "book-table-view.fxml", "View Table!");
    }
}

