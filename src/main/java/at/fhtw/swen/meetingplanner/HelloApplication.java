package at.fhtw.swen.meetingplanner;

import at.fhtw.swen.meetingplanner.bl.ReportService;
import at.fhtw.swen.meetingplanner.ui.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class HelloApplication extends Application {

    private static final Logger log = LogManager.getLogger(HelloApplication.class);

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // initialize spring
        applicationContext = new SpringApplicationBuilder(at.fhtw.swen.meetingplanner.Main.class).run();
        log.info("Meeting Planner app started.");
    }

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        log.info("Meeting Planner app shut down.");
        applicationContext.close();
        Platform.exit();
    }
}