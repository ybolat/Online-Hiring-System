package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArticleControllerTest {

    private final ArticleService articleService;

    ArticleControllerTest(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Test()
    void getAllByUserProfessionalInfoId() {
        Long id = 1L;
        assertFalse(this.articleService.getAllByUserProfessionalInfoId(id).isEmpty());
    }

    @Test
    void getAllByUserProfessionalInfoIdOrderByArticleName() {
        Long id = 1L;
        assertFalse(this.articleService.getAllByUserProfessionalInfoIdOrderByArticleName(id).isEmpty());
    }

    @Test
    void create() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}