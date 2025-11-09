package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class SearchViewModel {

    private static final Logger log = LogManager.getLogger(SearchViewModel.class);

    // list of all meetings, loaded from the database.
    private final List<Meeting> allMeetings = new ArrayList<>();

    // filtered list that is actually displayed in the UI.
    private final ObservableList<Meeting> filteredMeetings = FXCollections.observableArrayList();

    private final StringProperty searchText = new SimpleStringProperty("");

    public SearchViewModel() {
        // whenever user types, filter logic will be executed.
        searchText.addListener((obs, oldText, newText) -> filterMeetings(newText));
    }

    public void setAllMeetings(List<Meeting> meetings) {
        allMeetings.clear();
        allMeetings.addAll(meetings);
        filterMeetings(searchText.get()); // Apply initial filter
    }

    private void filterMeetings(String filter) {
        filteredMeetings.clear();
        if (filter == null || filter.isEmpty()) {
            // If search is empty, show all meetings.
            filteredMeetings.addAll(allMeetings);
        } else {
            String lowerCaseFilter = filter.toLowerCase();
            for (Meeting meeting : allMeetings) {
                // Check if title, agenda, or any note contains the search text.
                if (meeting.getTitle().toLowerCase().contains(lowerCaseFilter) ||
                        (meeting.getAgenda() != null && meeting.getAgenda().toLowerCase().contains(lowerCaseFilter)) ||
                        meeting.getNotes().stream().anyMatch(note -> note.getText().toLowerCase().contains(lowerCaseFilter)))
                {
                    log.debug("Searching for meetings with search term: '{}'", filter);
                    filteredMeetings.add(meeting);
                }
            }
        }
    }

    public ObservableList<Meeting> getFilteredMeetings() {
        return filteredMeetings;
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }
}