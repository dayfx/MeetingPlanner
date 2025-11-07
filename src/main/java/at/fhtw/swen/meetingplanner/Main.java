package at.fhtw.swen.meetingplanner;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This is the magic annotation that enables all of Spring Boot's features.
public class Main {

    public static void main(String[] args) {
        // This command tells JavaFX to start its lifecycle, using our modified HelloApplication class.
        Application.launch(at.fhtw.swen.meetingplanner.HelloApplication.class, args);
    }
}