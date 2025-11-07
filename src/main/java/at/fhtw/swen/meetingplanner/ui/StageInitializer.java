package at.fhtw.swen.meetingplanner.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component // Again, we tell Spring to manage this class.
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    // This method will be called automatically when the StageReadyEvent is published.
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            URL fxmlUrl = getClass().getResource("/at/fhtw/swen/meetingplanner/hello-view.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Parent root = fxmlLoader.load();

            stage.setTitle("Meeting Planner");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            // Proper logging should be added here later.
            throw new RuntimeException(e);
        }
    }
}