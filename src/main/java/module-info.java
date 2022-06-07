module com.first_person_camera {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.first_person_camera to javafx.fxml;
    exports com.first_person_camera;
}