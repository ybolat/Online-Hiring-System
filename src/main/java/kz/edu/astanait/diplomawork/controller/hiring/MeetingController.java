package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.MeetingDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.MeetingMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/meeting")
@PreAuthorize("hasRole('ROLE_COMMISSION')")
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

    @GetMapping("/get-all/by-day/{day}")
    private ResponseEntity<List<MeetingDtoResponse>> getAllByDay(@PathVariable(name = "day") String day) {
        List<MeetingDtoResponse> meetingDtoResponses = this.meetingService.getAllByDay(day).stream().map(MeetingMapper::meetingToDto).collect(Collectors.toList());
        return new ResponseEntity<>(meetingDtoResponses, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<MeetingDtoResponse>> getAll() {
        List<MeetingDtoResponse> meetingDtoResponse = this.meetingService.getAll().stream().map(MeetingMapper::meetingToDto).collect(Collectors.toList());
        return new ResponseEntity<>(meetingDtoResponse, HttpStatus.OK);
    }

}
