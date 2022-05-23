package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CertificateDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.CertificateMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/certificate")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class CertificateController extends ExceptionHandling {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<CertificateDtoResponse> getById(@PathVariable(name = "id") Long id) {
        CertificateDtoResponse certificateDtoResponse = CertificateMapper.certificateToDto(this.certificateService.getByIdThrowException(id));
        return new ResponseEntity<>(certificateDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/get/user-professional-info/id/{id}")
    public ResponseEntity<List<CertificateDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<CertificateDtoResponse> certificateDtoResponseList =
                this.certificateService.getAllByUserProfessionalInfoId(id).stream().map(CertificateMapper::certificateToDto).collect(Collectors.toList());
        return new ResponseEntity<>(certificateDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CertificateDtoResponse> create(@RequestParam(name = "file_name") String fileName,
                                              MultipartFile file,
                                              Principal principal){
        CertificateDtoResponse certificateDtoResponse = CertificateMapper.certificateToDto(this.certificateService.create(fileName, file, principal));
        System.out.println(certificateDtoResponse.getDocumentsDtoResponse().getDocument().length());
        return new ResponseEntity<>(certificateDtoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@ModelAttribute(name = "file")HashMap<String, MultipartFile> file,
                                                Principal principal) {
        this.certificateService.createAll(file, principal);
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
