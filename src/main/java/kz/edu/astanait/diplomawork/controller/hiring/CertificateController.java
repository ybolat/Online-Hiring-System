package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CertificateDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.CertificateMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<List<CertificateDtoResponse>> getAllByUserId(@PathVariable(name = "id") Long id) {
        List<CertificateDtoResponse> certificateDtoResponseList =
                this.certificateService.getAllByUserId(id).stream().map(CertificateMapper::certificateToDto).collect(Collectors.toList());
        return new ResponseEntity<>(certificateDtoResponseList, HttpStatus.OK);
    }
}
