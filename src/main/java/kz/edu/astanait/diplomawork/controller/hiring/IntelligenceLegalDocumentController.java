package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.IntelligenceLegalDocumentDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.IntelligenceLegalDocumentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/intelligence-legal-document")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class IntelligenceLegalDocumentController extends ExceptionHandling {

    private final IntelligenceLegalDocumentService intelligenceLegalDocumentService;

    @Autowired
    public IntelligenceLegalDocumentController(IntelligenceLegalDocumentService intelligenceLegalDocumentService) {
        this.intelligenceLegalDocumentService = intelligenceLegalDocumentService;
    }

    @GetMapping("/get/user-professional-info/id/{id}")
    public ResponseEntity<List<IntelligenceLegalDocumentDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<IntelligenceLegalDocumentDtoResponse> intelligenceLegalDocumentDtoResponseList =
                this.intelligenceLegalDocumentService.getAllByUserProfessionalInfoId(id).stream().map(IntelligenceLegalDocumentMapper::intelligenceLegalDocumentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(intelligenceLegalDocumentDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestParam(name = "file_name") String fileName,
                                             MultipartFile file,
                                             Principal principal) {
        this.intelligenceLegalDocumentService.create(fileName, file, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "id") Long id,
                                             @RequestParam(name = "file_name", required = false) String fileName,
                                             MultipartFile file,
                                             Principal principal) {
        this.intelligenceLegalDocumentService.update(id, fileName, file, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id, Principal principal) {
        this.intelligenceLegalDocumentService.delete(id, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@RequestParam(name = "file") HashMap<String, MultipartFile> file,
                                                Principal principal) {
        this.intelligenceLegalDocumentService.createAll(file, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
