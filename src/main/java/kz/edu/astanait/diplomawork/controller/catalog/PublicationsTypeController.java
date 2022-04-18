package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PublicationsTypeDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.catalog.PublicationsTypeMapper;
import kz.edu.astanait.diplomawork.mapper.hiring.PublicationsMapper;
import kz.edu.astanait.diplomawork.model.catalog.PublicationsType;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PublicationsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/publications-type")
@PreAuthorize("hasRole('ROLE_CHALLENGER')")
public class PublicationsTypeController extends ExceptionHandling {

    private final PublicationsTypeService publicationsTypeService;

    @Autowired
    public PublicationsTypeController(PublicationsTypeService publicationsTypeService) {
        this.publicationsTypeService = publicationsTypeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PublicationsTypeDtoResponse>> getAll() {
        List<PublicationsTypeDtoResponse> publicationsTypeDtoResponseList = this.publicationsTypeService.getAll()
                .stream().map(PublicationsTypeMapper::publicationsTypeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(publicationsTypeDtoResponseList, HttpStatus.OK);
    }
}
