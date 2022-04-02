package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DocumentsDtoRequest;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/hiring/documents")
public class DocumentsController {

    private final DocumentsService documentsService;

    @Autowired
    public DocumentsController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @PostMapping("/crete")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody DocumentsDtoRequest documentsDtoRequest) {
        this.documentsService.create(documentsDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody DocumentsDtoRequest documentsDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.documentsService.update(documentsDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.documentsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
