package kz.edu.astanait.diplomawork.controller.analytics;

import kz.edu.astanait.diplomawork.service.serviceInterface.analytics.ScopusAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
public class Analytics {

    private final ScopusAnalyticsService scopusAnalyticsService;

    @Autowired
    public Analytics(ScopusAnalyticsService scopusAnalyticsService) {
        this.scopusAnalyticsService = scopusAnalyticsService;
    }

    @GetMapping("/article/scopus-id/user/id/{id}")
    public ResponseEntity<Integer> refineArticlesByScopusId(@PathVariable(name = "id") Long id) {
        int count = this.scopusAnalyticsService.refineArticlesByScopusId(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
