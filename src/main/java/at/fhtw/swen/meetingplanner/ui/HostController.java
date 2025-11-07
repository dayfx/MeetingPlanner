package at.fhtw.swen.meetingplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class HostController {

    private final HostViewModel hostViewModel;

    @FXML
    private Label titleLabel;

    @FXML
    private PlannerController plannerViewController;

    public HostController(HostViewModel hostViewModel) {
        this.hostViewModel = hostViewModel;
    }

    @FXML
    public void initialize() {
        titleLabel.textProperty().bind(hostViewModel.titleProperty());
    }
}