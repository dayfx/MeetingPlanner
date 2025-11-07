package at.fhtw.swen.meetingplanner.bl;

import at.fhtw.swen.meetingplanner.model.Meeting;
import at.fhtw.swen.meetingplanner.model.MeetingNote;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ReportService {

    public void generateMeetingReport(Meeting meeting, File file) throws IOException, DocumentException {

        try (Document document = new Document()) {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Fonts
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // TItle
            Paragraph title = new Paragraph("Meeting Report: " + meeting.getTitle(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Meeting times
            document.add(new Paragraph("From: ", boldFont));
            document.add(new Paragraph(meeting.getFromTime(), normalFont));
            document.add(new Paragraph("To: ", boldFont));
            document.add(new Paragraph(meeting.getToTime(), normalFont));
            document.add(Chunk.NEWLINE);

            // Meeting agenda
            document.add(new Paragraph("Agenda:", boldFont));
            document.add(new Paragraph(meeting.getAgenda(), normalFont));
            document.add(Chunk.NEWLINE);

            // Meeting notes
            if (meeting.getNotes() != null && !meeting.getNotes().isEmpty()) {
                document.add(new Paragraph("Notes:", boldFont));
                com.lowagie.text.List noteList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
                for (MeetingNote note : meeting.getNotes()) {
                    noteList.add(new ListItem(note.getText(), normalFont));
                }
                document.add(noteList);
            }
        }
    }
}