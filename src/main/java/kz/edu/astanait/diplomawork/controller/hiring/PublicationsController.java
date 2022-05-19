package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.PublicationsDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.PublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hiring/publications")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class PublicationsController extends ExceptionHandling {

    private final PublicationsService publicationsService;

    @Autowired
    public PublicationsController(PublicationsService publicationsService) {
        this.publicationsService = publicationsService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody PublicationsDtoRequest publicationsDtoRequest, Principal principal) {
        this.publicationsService.create(publicationsDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody PublicationsDtoRequest publicationsDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.publicationsService.update(publicationsDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.publicationsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@Valid @RequestBody List<PublicationsDtoRequest> publicationsDtoRequestList, Principal principal) {
        this.publicationsService.createAll(publicationsDtoRequestList, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
