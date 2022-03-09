package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.IntelligenceLegalDocumentDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ProjectDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.IntelligenceLegalDocumentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/intelligence-legal-document")
public class IntelligenceLegalDocumentController {

    private final IntelligenceLegalDocumentService intelligenceLegalDocumentService;

    @Autowired
    public IntelligenceLegalDocumentController(IntelligenceLegalDocumentService intelligenceLegalDocumentService) {
        this.intelligenceLegalDocumentService = intelligenceLegalDocumentService;
    }

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<List<IntelligenceLegalDocumentDtoResponse>> getAllByUserId(@PathVariable(name = "id") Long id) {
        List<IntelligenceLegalDocumentDtoResponse> intelligenceLegalDocumentDtoResponseList =
                this.intelligenceLegalDocumentService.getAllByUserId(id).stream().map(IntelligenceLegalDocumentMapper::intelligenceLegalDocumentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(intelligenceLegalDocumentDtoResponseList, HttpStatus.OK);

    }

}
