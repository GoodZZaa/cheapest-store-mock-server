package com.goodzza.cheapeststore.api.controller.search;

import com.goodzza.cheapeststore.api.application.RandomMartProductGenerator;
import com.goodzza.cheapeststore.api.dto.SearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "search products", description = "키워드로 상품을 검색하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
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
                              .pageSize(pageSize)
                              .pageNumber(pageNumber + 1)
                              .build());
    }

}
