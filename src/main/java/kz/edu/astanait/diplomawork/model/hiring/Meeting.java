package kz.edu.astanait.diplomawork.model.hiring;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "meeting")
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_link")
    private String meetingLink;

    @Column(name = "meeting_title")
    private String meetingTitle;

    @Column(name = "meeting_description")
    private String meetingDescription;

    @Column(name = "start_date_time")
    private String startDateTime;

    @Column(name = "end_date_time")
    private String endDateTime;
}
