package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.bl.MeetingService;
import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.format.DateTimeFormatter;

@Component
public class MainViewModel {

    private final MeetingService meetingService;

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty agenda = new SimpleStringProperty();

    // formatting Date and Time
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // observableList with all Meetings for UI
    private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

    @Autowired
    public MainViewModel(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    public ObservableList<Meeting> getMeetings() {
        return meetings;
    }

    // put meetings into list
    public void loadMeetings() {
        meetings.clear();
        meetings.addAll(meetingService.getAllMeetings());
    }

    public void selectMeeting(Meeting selectedMeeting) {
        if (selectedMeeting != null) {
            title.set(selectedMeeting.getTitle());

            // use Date and Time from formatter
            from.set(selectedMeeting.getFromTime());
            to.set(selectedMeeting.getToTime());
            agenda.set(selectedMeeting.getAgenda());
        } else {
            // if nothing selected -> clear fields.
            title.set("");
            from.set("");
            to.set("");
            agenda.set("");
        }
    }

    // meeting properties getters

    public StringProperty titleProperty() { return title; }
    public StringProperty fromProperty() { return from; }
    public StringProperty toProperty() { return to; }
    public StringProperty agendaProperty() { return agenda; }
}