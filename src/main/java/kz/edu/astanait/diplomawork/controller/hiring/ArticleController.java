package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ArticleDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.ArticleMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
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
}
