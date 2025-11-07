package at.fhtw.swen.meetingplanner.ui;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

// This is a simple message class to carry the main window (Stage)
public class StageReadyEvent extends ApplicationEvent {
    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }
}