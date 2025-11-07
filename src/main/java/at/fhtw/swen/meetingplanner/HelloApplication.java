package at.fhtw.swen.meetingplanner;

import at.fhtw.swen.meetingplanner.ui.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component // This annotation tells Spring: "You are in charge of this class."
public class HelloApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // This method runs before start(). We use it to initialize Spring.
        applicationContext = new SpringApplicationBuilder(at.fhtw.swen.meetingplanner.Main.class).run();
    }

    @Override
    public void start(Stage stage) {
        // Now that Spring is running, we publish an event to let our
        // StageInitializer know that the JavaFX Stage is ready.
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        // When the application is closed, we stop the Spring context and exit.
        applicationContext.close();
        Platform.exit();
    }
}