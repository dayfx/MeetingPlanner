package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.model.Meeting;
import at.fhtw.swen.meetingplanner.model.MeetingNote;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class MeetingNotesViewModel {

    private static final Logger log = LogManager.getLogger(MeetingNotesViewModel.class);

    private Meeting currentMeeting;
    private final ObservableList<MeetingNote> notes = FXCollections.observableArrayList();
    private final StringProperty newNoteText = new SimpleStringProperty();

    public void displayNotes(Meeting meeting) {
        this.currentMeeting = meeting;
        notes.clear();
        if (meeting != null && meeting.getNotes() != null) {
            notes.addAll(meeting.getNotes());
            log.info("Getting all notes for meeting:'{}'", currentMeeting.getTitle());
        }
    }

    public void addNote() {
        if (currentMeeting != null && newNoteText.get() != null && !newNoteText.get().isEmpty()) {
            MeetingNote newNote = new MeetingNote();
            newNote.setText(newNoteText.get());
            newNote.setMeeting(currentMeeting);

            currentMeeting.getNotes().add(newNote);
            notes.add(newNote);
            newNoteText.set("");
        } else {
            log.warn("User wanted to add a note, but no meeting was selected.");
        }
    }

    public void deleteNote(MeetingNote selectedNote) {
        if (currentMeeting != null && selectedNote != null) {
            currentMeeting.getNotes().remove(selectedNote);
            notes.remove(selectedNote);
            log.info("User deleted note for meeting: '{}'", currentMeeting.getTitle());
        }
    }

    public ObservableList<MeetingNote> getNotes() { return notes; }
    public StringProperty newNoteTextProperty() { return newNoteText; }
}