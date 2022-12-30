package com.goodzza.cheapeststore.api.controller.search;

import com.goodzza.cheapeststore.api.application.RandomMartProductGenerator;
import com.goodzza.cheapeststore.api.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
@RestController
public class SearchController {

    private final RandomMartProductGenerator randomMartProductGenerator;

    @GetMapping("/products")
    public ResponseEntity<SearchResponse> searchProduct(@RequestParam String keyword,
                                                        @RequestParam float latitude,
                                                        @RequestParam float longitude,
                                                        @RequestParam(required = false) Integer pageSize,
                                                        @RequestParam(required = false) Integer pageNumber) {
        if (isNull(pageSize)) {
            pageSize = 20;
        }

        if (isNull(pageNumber)) {
            pageNumber = 0;
        }

        return ResponseEntity.ok(
                SearchResponse.builder()
                              .martProducts(
                                      randomMartProductGenerator.searchMartProducts(keyword, pageSize, pageNumber))
                              .build());
    }

}
