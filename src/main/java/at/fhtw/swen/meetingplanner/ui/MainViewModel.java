package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.bl.MeetingService;
import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainViewModel {

    private final MeetingService meetingService;

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

    // agenda and title methods later
}