package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    private final MainViewModel mainViewModel;

    @FXML
    private ListView<Meeting> meetingsListView;

    @Autowired
    public HelloController(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
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

        mainViewModel.loadMeetings();
    }
}