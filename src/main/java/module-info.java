module at.fhtw.swen.meetingplanner.meetingplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.fhtw.swen.meetingplanner.meetingplanner to javafx.fxml;
    exports at.fhtw.swen.meetingplanner.meetingplanner;
}