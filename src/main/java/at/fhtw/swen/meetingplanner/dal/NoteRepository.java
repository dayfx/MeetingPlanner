package at.fhtw.swen.meetingplanner.dal;

import at.fhtw.swen.meetingplanner.model.MeetingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<MeetingNote, Long> {
    // pretty much only serves Notes Unit Test
}