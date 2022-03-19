package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ArticleTypeDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.ArticleTypeMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/article-type")
public class ArticleTypeController {

    private final ArticleTypeService articleTypeService;

    @Autowired
    public ArticleTypeController(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ArticleTypeDtoResponse>> getAll() {
        List<ArticleTypeDtoResponse> articleTypeDtoResponseList = this.articleTypeService.getAll()
                .stream().map(ArticleTypeMapper::articleTypeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(articleTypeDtoResponseList, HttpStatus.OK);
    }
}
