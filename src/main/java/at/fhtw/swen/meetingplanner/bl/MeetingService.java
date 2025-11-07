package at.fhtw.swen.meetingplanner.bl;

import at.fhtw.swen.meetingplanner.dal.MeetingRepository;
import at.fhtw.swen.meetingplanner.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired // dependency injection - creates reference to DAL basically
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // get list of meetings
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    // create new meeting
    public Meeting saveMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    // delete meeting
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }
}