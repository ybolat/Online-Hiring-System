package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ArticleDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.ArticleMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/article")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMSISSION')")
public class ArticleController extends ExceptionHandling {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/get/user-professional-info/id/{id}")
    public ResponseEntity<List<ArticleDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<ArticleDtoResponse> articleDtoResponseList = this.articleService.getAllByUserProfessionalInfoId(id)
                .stream().map(ArticleMapper::articleToDto).collect(Collectors.toList());
        return new ResponseEntity<>(articleDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get/my-articles")
    public ResponseEntity<List<ArticleDtoResponse>> getMyArticles(Principal principal) {
        List<ArticleDtoResponse> articleDtoResponseList = this.articleService.getMyArticles(principal).stream().
                map(ArticleMapper::articleToDto).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/average")
    public ResponseEntity<Integer> getAverageNumOfArticles(@RequestParam(name = "dateTime") LocalDateTime dateTime,
                                                           @RequestParam(name = "statusId") Long id) {
        Integer average = this.articleService.getAverageNumOfArticles(dateTime, id);
        return new ResponseEntity<>(average, HttpStatus.OK);
    }

    @GetMapping("/get/user-professional-info/id/{id}/order-by/article-name")
    public ResponseEntity<List<ArticleDtoResponse>> getAllByUserProfessionalInfoIdOrderByArticleName(@PathVariable(name = "id") Long id) {
        List<ArticleDtoResponse> articleDtoResponseList = this.articleService.getAllByUserProfessionalInfoIdOrderByArticleName(id)
                .stream().map(ArticleMapper::articleToDto).collect(Collectors.toList());
        return new ResponseEntity<>(articleDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody ArticleDtoRequest articleDtoRequest, Principal principal) {
        this.articleService.create(articleDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@RequestBody List<ArticleDtoRequest> articleDtoRequestList, Principal principal) {
        this.articleService.createAll(articleDtoRequestList, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody ArticleDtoRequest articleDtoRequest,
                                                 @PathVariable(name = "id") Long id) {
        this.articleService.update(articleDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
