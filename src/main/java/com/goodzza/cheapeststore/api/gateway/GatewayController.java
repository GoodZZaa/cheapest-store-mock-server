package com.goodzza.cheapeststore.api.gateway;

import com.goodzza.cheapeststore.api.application.RandomMartGenerator;
import com.goodzza.cheapeststore.api.application.RandomMartProductGenerator;
import com.goodzza.cheapeststore.api.dto.GatewayResponse;
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

    @GetMapping
    public ResponseEntity<GatewayResponse> getGatewayPage(@RequestParam(required = false) Boolean isFirst,
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

        if (isFirst) {
            return ResponseEntity.ok(
                    GatewayResponse.builder()
                                   .promotionProducts(randomMartProductGenerator.generate(PROMOTION_EXPOSURE_COUNT, 0))
                                   .cheapestMarts(randomMartGenerator.generate(pageSize, pageNumber))
                                   .cheapestProducts(randomMartProductGenerator.generate(pageSize, pageNumber))
                                   .build());
        } else {
            return ResponseEntity.ok(
                    GatewayResponse.builder()
                                   .promotionProducts(randomMartProductGenerator.generate(pageSize, pageNumber))
                                   .build());
        }
    }

}
