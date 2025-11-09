package at.fhtw.swen.meetingplanner.ui;

import at.fhtw.swen.meetingplanner.HelloApplication;
import at.fhtw.swen.meetingplanner.model.Meeting;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;


@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class PlannerViewTest {

    @Start
    private void start(Stage stage) throws Exception {

        HelloApplication app = new HelloApplication();

        app.init();
        app.start(stage);
    }

    @Test
    void testCreateNewMeeting(FxRobot robot) {

        robot.clickOn("New Meeting");

        robot.clickOn("#titleField").push(KeyCode.CONTROL, KeyCode.A).write("New TestFX Meeting"); // Keyboard inputs Ctrl A to delete whole placeholder
        robot.clickOn("#fromField").write("13:27");
        robot.clickOn("#toField").write("15:11");
        robot.clickOn("#agendaArea").write("Meeting created with TestFX");

        robot.clickOn("Save Meeting");

        verifyThat("#meetingsListView", (ListView<Meeting> listView) -> {
            for (Meeting meeting : listView.getItems()) {
                if (meeting.getTitle().equals("New TestFX Meeting")) {
                    return true;
                }
            }
            return false;
        });
    }

    @Test
    void testDeleteMeeting(FxRobot robot) {

        robot.clickOn("New Meeting");
        robot.clickOn("#titleField").push(KeyCode.CONTROL, KeyCode.A).write("TestFX Meeting to be deleted");
        robot.clickOn("Save Meeting");

        robot.clickOn("TestFX Meeting to be deleted");
        robot.clickOn("Delete Meeting");

        ListView<at.fhtw.swen.meetingplanner.model.Meeting> listView = robot.lookup("#meetingsListView").queryListView();
        assertEquals(0, listView.getItems().stream().filter(m -> m.getTitle().equals("TestFX Meeting to be deleted")).count(), "The meeting has been deleted.");
    }
}