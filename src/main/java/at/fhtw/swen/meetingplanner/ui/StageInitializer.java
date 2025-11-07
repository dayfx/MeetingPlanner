package at.fhtw.swen.meetingplanner.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final ConfigurableApplicationContext applicationContext;

    @Autowired
    public StageInitializer(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage stage = event.getStage();

            // Minimum application window size
            stage.setMinWidth(750);
            stage.setMinHeight(650);

            URL fxmlUrl = getClass().getResource("/at/fhtw/swen/meetingplanner/HostView.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

            fxmlLoader.setControllerFactory(applicationContext::getBean); // ask Spring for controller

            Parent root = fxmlLoader.load();

            stage.setTitle("Meeting Planner");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}