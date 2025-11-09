package at.fhtw.swen.meetingplanner.bl;

import at.fhtw.swen.meetingplanner.dal.MeetingRepository;
import at.fhtw.swen.meetingplanner.model.Meeting;
import at.fhtw.swen.meetingplanner.model.MeetingNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // so every test runs for itself and doesn't interfere with other tests

class MeetingServiceTest {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingRepository meetingRepository; // inject repository

    @BeforeEach
    void setUp() {
        meetingRepository.deleteAll();
    }

    @Test
    void testSaveMeetingAndID() {

        Meeting meeting = new Meeting();
        meeting.setTitle("First Unit Test - Test Meeting + ID");

        // Act
        Meeting savedMeeting = meetingService.saveMeeting(meeting);

        // Assert
        assertNotNull(savedMeeting.getId(), "Test meeting + ID not null.");
    }

    @Test
    void testGetAllMeetingsReturnsCorrectAmountOfMeetings() {

        List<Meeting> initialMeetings = meetingService.getAllMeetings();

        assertTrue(initialMeetings.isEmpty(), "First list is empty.");

        Meeting meeting = new Meeting();
        meeting.setTitle("First Test meeting");
        meetingService.saveMeeting(meeting);

        List<Meeting> meetingsAfterAdd = meetingService.getAllMeetings();

        assertEquals(1, meetingsAfterAdd.size(), "Meeting count is 1");
        assertEquals("First Test meeting", meetingsAfterAdd.get(0).getTitle(), "Correct amount of meetings.");
    }

    @Test
    void testUpdateMeetingTitleIsChanged() {

        Meeting meeting = new Meeting();
        meeting.setTitle("First Title");
        Meeting savedMeeting = meetingService.saveMeeting(meeting);

        savedMeeting.setTitle("Changed Title");
        meetingService.saveMeeting(savedMeeting);
        Meeting updatedMeeting = meetingService.getAllMeetings().get(0);

        assertEquals("Changed Title", updatedMeeting.getTitle(), "Shows updated title.");
    }

    @Test
    void testDeleteMeeting() {

        Meeting meeting = meetingService.saveMeeting(new Meeting());
        assertFalse(meetingService.getAllMeetings().isEmpty(), "Meeting exists.");

        meetingService.deleteMeeting(meeting.getId());

        assertTrue(meetingService.getAllMeetings().isEmpty(), "No meetings exist anymore.");
    }

    @Test
    void testSaveMeetingAndNotes() {

        Meeting meeting = new Meeting();
        meeting.setTitle("Meeting and notes");

        MeetingNote note1 = new MeetingNote();
        note1.setText("First note");
        note1.setMeeting(meeting);

        MeetingNote note2 = new MeetingNote();
        note2.setText("Second note");
        note2.setMeeting(meeting);

        meeting.getNotes().add(note1);
        meeting.getNotes().add(note2);

        meetingService.saveMeeting(meeting);
        Meeting retrievedMeeting = meetingService.getAllMeetings().get(0);

        assertEquals(2, retrievedMeeting.getNotes().size(), "Meeting should have 2 notes.");
    }

    @Test
    void testUpdateNoteText() {

        // tests if cascade works as intended
        Meeting firstMeeting = new Meeting();
        MeetingNote note = new MeetingNote();

        note.setText("First note text");
        note.setMeeting(firstMeeting);

        firstMeeting.getNotes().add(note);
        meetingService.saveMeeting(firstMeeting);

        Meeting retrievedMeeting = meetingService.getAllMeetings().get(0);
        retrievedMeeting.getNotes().get(0).setText("Changed note text");

        meetingService.saveMeeting(retrievedMeeting);
        Meeting checkMeeting = meetingService.getAllMeetings().get(0);

        assertEquals("Changed note text", checkMeeting.getNotes().get(0).getText());
    }

    @Test
    void testDeleteNoteFromMeeting() {

        // tests if orphan-removal works as intended
        Meeting meeting = new Meeting();
        MeetingNote note = new MeetingNote();

        note.setText("Note to be deleted");
        note.setMeeting(meeting);

        meeting.getNotes().add(note);
        meetingService.saveMeeting(meeting);

        Meeting retrievedMeeting = meetingService.getAllMeetings().get(0);
        assertEquals(1, retrievedMeeting.getNotes().size()); // note still there

        retrievedMeeting.getNotes().clear();
        meetingService.saveMeeting(retrievedMeeting);
        Meeting checkMeeting = meetingService.getAllMeetings().get(0);

        assertTrue(checkMeeting.getNotes().isEmpty(), "The meeting has no notes anymore."); // note gone
    }
}