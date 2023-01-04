package com.goodzza.cheapeststore.api.dto.accountBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPayoutVo {

    private LocalDate date;
    private List<String> products;
    private Long totalPrice;
}
