package at.fhtw.swen.meetingplanner.dal;

import at.fhtw.swen.meetingplanner.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}