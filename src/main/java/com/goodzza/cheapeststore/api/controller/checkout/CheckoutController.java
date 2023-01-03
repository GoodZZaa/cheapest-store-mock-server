package com.goodzza.cheapeststore.api.controller.checkout;


import com.goodzza.cheapeststore.api.application.RandomMartProductGenerator;
import com.goodzza.cheapeststore.api.dto.CheckoutMartProductVo;
import com.goodzza.cheapeststore.api.dto.CheckoutResponse;
import com.goodzza.cheapeststore.api.dto.MartProductVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/checkout")
@RestController
public class CheckoutController {

    private static final Random RAND = new Random();
    private final RandomMartProductGenerator randomMartProductGenerator;

    @Operation(summary = "get current checkout", description = "장바구니에 있는 품목들을 가져온다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping
    public ResponseEntity<CheckoutResponse> getCheckout() {
        List<MartProductVo> martProducts = randomMartProductGenerator.generate(RAND.nextInt(5) + 1, 0);

        List<CheckoutMartProductVo> checkoutMartProducts =
                martProducts.stream().map(CheckoutMartProductVo::convert).toList();
        checkoutMartProducts.forEach(vo -> vo.setAmount(RAND.nextInt(5) + 1));

        return ResponseEntity.ok(CheckoutResponse.builder()
                                                 .products(checkoutMartProducts)
                                                 .build());
    }
}
