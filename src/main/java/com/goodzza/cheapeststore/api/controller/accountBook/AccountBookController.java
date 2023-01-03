package com.goodzza.cheapeststore.api.controller.accountBook;

import com.goodzza.cheapeststore.api.dto.accountBook.AccountBookResponse;
import com.goodzza.cheapeststore.api.dto.accountBook.BalanceVo;
import com.goodzza.cheapeststore.api.dto.accountBook.DaySummaryVo;
import com.goodzza.cheapeststore.api.dto.accountBook.PayoutVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-book")
@RestController
public class AccountBookController {

    private static final Random RAND = new Random();

    @Operation(summary = "monthly balance", description = "목표로 설정한 금액과 현재 남아있는 금액을 보여준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/monthly-balance")
    public ResponseEntity<BalanceVo> getMonthlyBalance(@RequestParam Integer year,
                                                       @RequestParam Integer month) {
        long randomBalance = RAND.nextLong(200000) % 100 * 100;
        long randomRemaining = randomBalance * (RAND.nextLong(5) + 1) / 5L;
        return ResponseEntity.ok(BalanceVo.builder()
                                          .totalBalance(randomBalance)
                                          .remainingBalance(randomRemaining)
                                          .build());
    }

    @Operation(summary = "get payout history for target date", description = "입력한 날짜의 지불 이력과 그 달에 일별로 몇건의 지불 이력이 있는지 반환")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping
    public ResponseEntity<AccountBookResponse> getPayoutHistory(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        int month = date.getMonthValue();

        LocalDate monthStart = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate monthEnd = monthStart.plusDays(monthStart.lengthOfMonth() - 1);
        int dayNum = monthEnd.getDayOfMonth();
        int random = dayNum / 6 + 1;

        List<DaySummaryVo> dailySummaries = IntStream.range(1, dayNum + 1)
                                                     .mapToObj(i -> DaySummaryVo.builder()
                                                                                .day(i)
                                                                                .count((i % random) / 2)
                                                                                .build()).toList();

        int startTime = RAND.nextInt(16) + 1;
        int minute = RAND.nextInt(60) + 1;
        int diffTime = RAND.nextInt(4) + 1;
        List<PayoutVo> payouts = dailySummaries.stream().map(DaySummaryVo::getCount)
                                               .map(count -> PayoutVo.builder()
                                                                     .start(LocalTime.of(startTime, minute))
                                                                     .build())
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(AccountBookResponse.builder()
                                                    .month(month)
                                                    .dailySummary(dailySummaries)
                                                    .dayPayoutHistory(payouts)
                                                    .build());
    }
}
