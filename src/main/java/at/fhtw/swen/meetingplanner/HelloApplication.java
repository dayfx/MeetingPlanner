package at.fhtw.swen.meetingplanner;

import at.fhtw.swen.meetingplanner.ui.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // initialize spring
        applicationContext = new SpringApplicationBuilder(at.fhtw.swen.meetingplanner.Main.class).run();
    }

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}