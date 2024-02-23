module org.georgiancollege.week07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.georgiancollege.week07 to javafx.fxml;
    exports org.georgiancollege.week07;
}