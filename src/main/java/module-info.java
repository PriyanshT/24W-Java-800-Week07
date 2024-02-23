module org.georgiancollege.week07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.georgiancollege.week07 to javafx.fxml;
    exports org.georgiancollege.week07;
    exports org.georgiancollege.week07.Model;
    opens org.georgiancollege.week07.Model to javafx.fxml;
    exports org.georgiancollege.week07.Controller;
    opens org.georgiancollege.week07.Controller to javafx.fxml;
    exports org.georgiancollege.week07.Utilities;
    opens org.georgiancollege.week07.Utilities to javafx.fxml;
}