package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ArticleDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.ArticleMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<List<ArticleDtoResponse>> getAllByUserId(@PathVariable(name = "id") Long id) {
        List<ArticleDtoResponse> articleDtoResponseList = articleService.getAllByUserId(id)
                .stream().map(ArticleMapper::articleToDto).collect(Collectors.toList());
        return new ResponseEntity<>(articleDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody ArticleDtoRequest articleDtoRequest) {
        this.articleService.create(articleDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody ArticleDtoRequest articleDtoRequest,
                                                 @PathVariable(name = "id") Long id) {
        this.articleService.update(articleDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
