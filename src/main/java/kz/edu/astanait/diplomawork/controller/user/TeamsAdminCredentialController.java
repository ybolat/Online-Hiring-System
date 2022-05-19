package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.TeamsAdminCredentialDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.TeamsAdminCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/teams-admin-credential")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TeamsAdminCredentialController extends ExceptionHandling {

    private final TeamsAdminCredentialService teamsAdminCredentialService;

    @Autowired
    public TeamsAdminCredentialController(TeamsAdminCredentialService teamsAdminCredentialService) {
        this.teamsAdminCredentialService = teamsAdminCredentialService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody TeamsAdminCredentialDtoRequest teamsAdminCredentialDtoRequest) {
        this.teamsAdminCredentialService.create(teamsAdminCredentialDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "id") Long id,
                                             @RequestBody TeamsAdminCredentialDtoRequest teamsAdminCredentialDtoRequest) {
        this.teamsAdminCredentialService.update(id, teamsAdminCredentialDtoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.teamsAdminCredentialService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
