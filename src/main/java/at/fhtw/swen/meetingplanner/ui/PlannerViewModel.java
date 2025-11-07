package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.bl.MeetingService;
import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class PlannerViewModel {

    private final MeetingService meetingService;
    private final MeetingNotesViewModel meetingNotesViewModel;

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty agenda = new SimpleStringProperty();

    private Meeting currentlySelectedMeeting;

    // observableList with all Meetings for UI
    private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

    @Autowired
    public PlannerViewModel(MeetingService meetingService, MeetingNotesViewModel meetingNotesViewModel) {
        this.meetingService = meetingService;
        this.meetingNotesViewModel = meetingNotesViewModel;
    }

    public ObservableList<Meeting> getMeetings() {
        return meetings;
    }

    // put meetings into list
    public void loadMeetings() {
        meetings.clear();
        meetings.addAll(meetingService.getAllMeetings());
    }

    public Meeting prepareNewMeeting() {
        Meeting newMeeting = new Meeting();
        newMeeting.setTitle("New Meeting");

        // add new meeting to list
        this.meetings.add(newMeeting);

        return newMeeting;
    }

    public void selectMeeting(Meeting selectedMeeting) {
        this.currentlySelectedMeeting = selectedMeeting;

        meetingNotesViewModel.displayNotes(selectedMeeting);

        if (selectedMeeting != null) {
            title.set(selectedMeeting.getTitle());
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

    public void saveCurrentMeeting() {
        if (currentlySelectedMeeting != null) {
            currentlySelectedMeeting.setTitle(title.get());
            currentlySelectedMeeting.setFromTime(from.get());
            currentlySelectedMeeting.setToTime(to.get());
            currentlySelectedMeeting.setAgenda(agenda.get());

            meetingService.saveMeeting(currentlySelectedMeeting);
            loadMeetings();
        }
    }

    public void deleteCurrentMeeting() {
        if (currentlySelectedMeeting != null && currentlySelectedMeeting.getId() != null) {
            meetingService.deleteMeeting(currentlySelectedMeeting.getId());
            loadMeetings();
        } else if (currentlySelectedMeeting != null) {
            // remove from current saved list even if it's not in DB yet
            meetings.remove(currentlySelectedMeeting);
        }
    }

    // meeting properties getters

    public StringProperty titleProperty() { return title; }
    public StringProperty fromProperty() { return from; }
    public StringProperty toProperty() { return to; }
    public StringProperty agendaProperty() { return agenda; }
}