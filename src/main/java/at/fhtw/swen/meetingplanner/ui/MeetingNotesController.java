package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.model.MeetingNote;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class MeetingNotesController {

    private final MeetingNotesViewModel meetingNotesViewModel;

    @FXML private ListView<MeetingNote> notesListView;
    @FXML private TextField newNoteField;

    public MeetingNotesController(MeetingNotesViewModel meetingNotesViewModel) {
        this.meetingNotesViewModel = meetingNotesViewModel;
    }

    @FXML
    public void initialize() {
        notesListView.setItems(meetingNotesViewModel.getNotes());
        newNoteField.textProperty().bindBidirectional(meetingNotesViewModel.newNoteTextProperty());

        notesListView.setEditable(true);

        notesListView.setCellFactory(
                javafx.scene.control.cell.TextFieldListCell.forListView(new javafx.util.StringConverter<MeetingNote>() {
                    @Override
                    public String toString(MeetingNote note) {
                        return note == null ? "" : note.getText(); // get note text or nothing if empty
                    }

                    @Override
                    public MeetingNote fromString(String string) {
                        MeetingNote note = notesListView.getSelectionModel().getSelectedItem();
                        if (note != null) {
                            note.setText(string);
                        }
                        return note;
                    }
                })
        );
    }

    @FXML
    private void handleAddNote() {
        meetingNotesViewModel.addNote();
    }

    @FXML
    private void handleDeleteNote() {
        MeetingNote selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            meetingNotesViewModel.deleteNote(selectedNote);
        }
    }
}