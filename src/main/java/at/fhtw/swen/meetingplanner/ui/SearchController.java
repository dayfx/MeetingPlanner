package at.fhtw.swen.meetingplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    private final SearchViewModel searchViewModel;
    @FXML private TextField searchField;

    public SearchController(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @FXML
    public void initialize() {
        // Two-way bind the text field to the property in the ViewModel.
        searchField.textProperty().bindBidirectional(searchViewModel.searchTextProperty());
    }
}