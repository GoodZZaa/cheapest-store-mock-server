package com.goodzza.cheapeststore.api.controller.checkout;


import com.goodzza.cheapeststore.api.application.RandomMartGenerator;
import com.goodzza.cheapeststore.api.application.RandomMartProductGenerator;
import com.goodzza.cheapeststore.api.dto.MartProductVo;
import com.goodzza.cheapeststore.api.dto.MartVo;
import com.goodzza.cheapeststore.api.dto.checkout.AmountChangeRequest;
import com.goodzza.cheapeststore.api.dto.checkout.CheckoutMartProductVo;
import com.goodzza.cheapeststore.api.dto.checkout.CheckoutResponse;
import com.goodzza.cheapeststore.api.dto.checkout.MartProductDeleteRequest;
import com.goodzza.cheapeststore.api.dto.checkout.MartProductInsertRequest;
import com.goodzza.cheapeststore.api.dto.recommendation.MartRecommendationVo;
import com.goodzza.cheapeststore.api.dto.recommendation.RecommendationVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/checkout")
@RestController
public class CheckoutController {

    private static final Random RAND = new Random();
    private final RandomMartProductGenerator randomMartProductGenerator;
    private final RandomMartGenerator randomMartGenerator;

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

    @Operation(summary = "insert mart product into checkout", description = "장바구니에 마트 상품 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping
    public ResponseEntity<Boolean> insertMartProduct(@Valid @RequestBody MartProductInsertRequest request) {
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Operation(summary = "update mart product amount", description = "장바구니에서 품목을 증가하거나 뺀다. \namountChangeType - INCREASE, DECREASE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PutMapping("/mart-product-amount")
    public ResponseEntity<Boolean> updateMartProductAmount(
            @Valid @RequestBody AmountChangeRequest amountChangeRequest) {
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Operation(summary = "delete mart product", description = "장바구니에서 품목을 완전히 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @DeleteMapping("/mart-product")
    public ResponseEntity<Boolean> deleteMartProductAmount(
            @Valid @RequestBody MartProductDeleteRequest martProductDeleteRequest) {
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Operation(summary = "create cheapest mart recommendation", description = "최저가 추천 마트받기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/recommendation")
    public ResponseEntity<RecommendationVo> createRecommendation() {
        List<MartVo> marts = randomMartGenerator.generate(3, 0);
        List<MartRecommendationVo> recommendationMarts = marts.stream().map(MartRecommendationVo::convert).toList();
        recommendationMarts.forEach(mart -> {
            long price = RAND.nextLong(20000) % 100 * 100;
            float distance = RAND.nextFloat(3);
            distance = (float) (int) distance + (int) (distance * 10) / 10.0f;
            mart.setEstimatedPrice(price);
            mart.setDistance(distance);
        });

        return ResponseEntity.ok(RecommendationVo.builder()
                                                 .recommendationMarts(recommendationMarts)
                                                 .build());
    }

}
