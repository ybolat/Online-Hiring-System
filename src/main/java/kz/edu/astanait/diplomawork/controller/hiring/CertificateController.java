package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CertificateDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CertificateDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.CertificateMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/certificate")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/get/user-professional-info/id/{id}")
    public ResponseEntity<List<CertificateDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<CertificateDtoResponse> certificateDtoResponseList =
                this.certificateService.getAllByUserProfessionalInfoId(id).stream().map(CertificateMapper::certificateToDto).collect(Collectors.toList());
        return new ResponseEntity<>(certificateDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestParam(name = "file_name") String fileName,
                                             MultipartFile file,
                                             Principal principal){
        this.certificateService.create(fileName, file, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "id") Long id,
                                             @RequestParam(name = "file_name", required = false) String fileName,
                                             MultipartFile file,
                                             Principal principal
                                             ) {
        this.certificateService.update(id, fileName, file, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id, Principal principal) {
        this.certificateService.delete(id, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
