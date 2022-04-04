package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.IntelligenceLegalDocumentDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.IntelligenceLegalDocumentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.IntelligenceLegalDocumentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/intelligence-legal-document")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class IntelligenceLegalDocumentController {

    private final IntelligenceLegalDocumentService intelligenceLegalDocumentService;

    @Autowired
    public IntelligenceLegalDocumentController(IntelligenceLegalDocumentService intelligenceLegalDocumentService) {
        this.intelligenceLegalDocumentService = intelligenceLegalDocumentService;
    }

    @GetMapping("/get/user-professional/id/{id}")
    public ResponseEntity<List<IntelligenceLegalDocumentDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<IntelligenceLegalDocumentDtoResponse> intelligenceLegalDocumentDtoResponseList =
                this.intelligenceLegalDocumentService.getAllByUserProfessionalInfoId(id).stream().map(IntelligenceLegalDocumentMapper::intelligenceLegalDocumentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(intelligenceLegalDocumentDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody IntelligenceLegalDocumentDtoRequest intelligenceLegalDocumentDtoRequest, Principal principal) {
        this.intelligenceLegalDocumentService.create(intelligenceLegalDocumentDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody IntelligenceLegalDocumentDtoRequest intelligenceLegalDocumentDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.intelligenceLegalDocumentService.update(intelligenceLegalDocumentDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.intelligenceLegalDocumentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
