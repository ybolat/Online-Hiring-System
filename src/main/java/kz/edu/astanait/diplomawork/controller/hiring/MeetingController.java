package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.MeetingDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.MeetingDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.MeetingMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/meeting")
public class MeetingController extends ExceptionHandling {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<MeetingDtoResponse> getById(@PathVariable(name = "id") Long id) {
        MeetingDtoResponse meetingDtoResponse = MeetingMapper.meetingToDto(this.meetingService.getByIdThrowException(id));
        return new ResponseEntity<>(meetingDtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody MeetingDtoRequest meetingDtoRequest) {
        this.meetingService.create(meetingDtoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
