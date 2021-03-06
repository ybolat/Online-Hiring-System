package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserDocumentDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDocumentDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.user.UserDocumentMapper;
import kz.edu.astanait.diplomawork.model.user.UserDocument;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/user-document")
@PreAuthorize("hasRole('ROLE_CHALLENGER')")
public class UserDocumentController extends ExceptionHandling {

    private final UserDocumentService userDocumentService;

    @Autowired
    public UserDocumentController(UserDocumentService userDocumentService) {
        this.userDocumentService = userDocumentService;
    }

    @PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
    @GetMapping("/get/id/{id}")
    public ResponseEntity<UserDocumentDtoResponse> getById(@PathVariable(name = "id") Long id){
        UserDocumentDtoResponse userDocumentDtoResponse = UserDocumentMapper.userDocumentToDto(this.userDocumentService.getByIdThrowException(id));
        return new ResponseEntity<>(userDocumentDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/get/my-documents")
    public ResponseEntity<UserDocumentDtoResponse> getMyDocument(Principal principal) {
        Optional<UserDocument> userDocument = this.userDocumentService.getByUserEmail(principal);
        try {
            UserDocumentDtoResponse userDocumentDtoResponse = UserDocumentMapper.userDocumentToDto(userDocument.get());
            return new ResponseEntity<>(userDocumentDtoResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @ModelAttribute  UserDocumentDtoRequest userDocumentDtoRequest, Principal principal){
        this.userDocumentService.create(userDocumentDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@ModelAttribute UserDocumentDtoRequest userDocumentDtoRequest,
                                             @PathVariable(name = "id") Long id){
        this.userDocumentService.update(userDocumentDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id){
        this.userDocumentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
