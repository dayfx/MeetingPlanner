package at.fhtw.swen.meetingplanner.model;

import javax.persistence.*;
import java.time.LocalDateTime; // for actual time
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    @Column(columnDefinition = "TEXT")
    private String agenda;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MeetingNote> notes = new ArrayList<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public List<MeetingNote> getNotes() {
        return notes;
    }

    public void setNotes(List<MeetingNote> notes) {
        this.notes = notes;
    }
}