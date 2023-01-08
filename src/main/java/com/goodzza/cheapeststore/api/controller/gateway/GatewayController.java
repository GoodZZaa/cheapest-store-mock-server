package com.goodzza.cheapeststore.api.controller.gateway;

import com.goodzza.cheapeststore.api.application.RandomMartGenerator;
import com.goodzza.cheapeststore.api.application.RandomMartProductGenerator;
import com.goodzza.cheapeststore.api.dto.GatewayResponse;
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
@RequestMapping("/api/v1/gateway")
@RestController
public class GatewayController {

    private static final Integer PROMOTION_EXPOSURE_COUNT = 5;
    private final RandomMartProductGenerator randomMartProductGenerator;
    private final RandomMartGenerator randomMartGenerator;

    @Operation(summary = "gateway page", description = "게이트 페이지 API, 첫 페이지 이후에는 cheapestProducts만 채워서 응답한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping
    public ResponseEntity<GatewayResponse> getGatewayPage(@RequestParam(required = false) Boolean isFirst,
                                                          @RequestParam float latitude,
                                                          @RequestParam float longitude,
                                                          @RequestParam(required = false) Integer pageSize,
                                                          @RequestParam(required = false) Integer pageNumber) {
        if (isNull(isFirst)) {
            isFirst = Boolean.TRUE;
        }

        if (isNull(pageSize)) {
            pageSize = 20;
        }

        if (isNull(pageNumber)) {
            pageNumber = 0;
            isFirst = Boolean.TRUE;
        }

        GatewayResponse response =
                GatewayResponse.builder()
                               .cheapestProducts(randomMartProductGenerator.generate(pageSize, pageNumber))
                               .pageSize(pageSize)
                               .pageNumber(pageNumber + 1)
                               .build();

        if (isFirst) {
            response.setPromotionProducts(randomMartProductGenerator.generate(PROMOTION_EXPOSURE_COUNT, 0));
            response.setCheapestMarts(randomMartGenerator.generate(pageSize, pageNumber));
        }

        return ResponseEntity.ok(response);
    }

}
