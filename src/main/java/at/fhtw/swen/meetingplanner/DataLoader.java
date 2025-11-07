package at.fhtw.swen.meetingplanner;

import at.fhtw.swen.meetingplanner.bl.MeetingService;
import at.fhtw.swen.meetingplanner.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final MeetingService meetingService;

    @Autowired
    public DataLoader(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (meetingService.getAllMeetings().isEmpty()) { // runs only if DB empty to fill initially
            Meeting meeting1 = new Meeting();
            meeting1.setTitle("Test Meeting 01");
            meeting1.setFromTime("12:00");
            meeting1.setToTime("13:00");
            meeting1.setAgenda("1. TestAgenda\n2. Test2\n3. Test3");
            meetingService.saveMeeting(meeting1);
        }
    }
}