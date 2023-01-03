package com.goodzza.cheapeststore.api.dto.accountBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBookResponse {
    private Integer month;
    private List<DaySummaryVo> dailySummary;
    private List<PayoutVo> dayPayoutHistory;
}
