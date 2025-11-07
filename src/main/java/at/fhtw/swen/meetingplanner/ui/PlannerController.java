package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

@Controller
public class PlannerController {

    private final PlannerViewModel mainViewModel;

    @FXML
    private ListView<Meeting> meetingsListView;

    // meeting properties fxml
    @FXML private TextField titleField;
    @FXML private TextField fromField;
    @FXML private TextField toField;
    @FXML private TextArea agendaArea;

    @Autowired
    public PlannerController(PlannerViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    @FXML
    private void handleNewMeeting() {
        Meeting newMeeting = mainViewModel.prepareNewMeeting();
        meetingsListView.getSelectionModel().select(newMeeting);
        // scroll to new Meeting if too far down
        meetingsListView.scrollTo(newMeeting);
    }

    @FXML
    private void handleSaveMeeting() {
        mainViewModel.saveCurrentMeeting();
    }

    @FXML
    private void handleDeleteMeeting() {
        mainViewModel.deleteCurrentMeeting();
    }

    @FXML
    public void initialize() {

        // bind items in ListView to ObservableList in ViewModel.
        meetingsListView.setItems(mainViewModel.getMeetings());

        // getTitle for Meeting title and display it
        meetingsListView.setCellFactory(param -> new javafx.scene.control.ListCell<Meeting>() {
            @Override
            protected void updateItem(Meeting item, boolean empty) {
                super.updateItem(item, empty); // super - normal ListCell behavior first
                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });

        // listen for selection changes in the ListView
        meetingsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mainViewModel.selectMeeting(newValue);
        });

        // bidirectional for two-way-binding, so it can be updated by user and viewmodel
        titleField.textProperty().bindBidirectional(mainViewModel.titleProperty());
        fromField.textProperty().bindBidirectional(mainViewModel.fromProperty());
        toField.textProperty().bindBidirectional(mainViewModel.toProperty());
        agendaArea.textProperty().bindBidirectional(mainViewModel.agendaProperty());

        mainViewModel.loadMeetings();
    }
}