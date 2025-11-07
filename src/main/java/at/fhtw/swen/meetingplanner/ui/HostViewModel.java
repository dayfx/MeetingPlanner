package at.fhtw.swen.meetingplanner.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

@Component
public class HostViewModel {
    private final StringProperty title = new SimpleStringProperty("Meeting Planner");

    public StringProperty titleProperty() {
        return title;
    }
}