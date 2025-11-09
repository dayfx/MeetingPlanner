package at.fhtw.swen.meetingplanner.bl;

import at.fhtw.swen.meetingplanner.dal.MeetingRepository;
import at.fhtw.swen.meetingplanner.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class MeetingService {

    private static final Logger log = LogManager.getLogger(MeetingService.class);

    private final MeetingRepository meetingRepository;

    @Autowired // dependency injection - creates reference to DAL basically
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
        log.info("MeetingService Database Connection established.");
    }

    // get list of meetings
    public List<Meeting> getAllMeetings() {
        log.info("Getting all Meetings");
        return meetingRepository.findAll();
    }

    // create new meeting
    public Meeting saveMeeting(Meeting meeting) {
        log.info("Saving Meeting: '{}'", meeting.getTitle());
        return meetingRepository.save(meeting);
    }

    // delete meeting
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }
}