package org.georgiancollege.week07.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.georgiancollege.week07.Main;

public class SceneChanger {
    // a static method that changes the scene
    public static void changeScenes(ActionEvent event, String fxmlFileName, String fileTitle){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(fileTitle);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
