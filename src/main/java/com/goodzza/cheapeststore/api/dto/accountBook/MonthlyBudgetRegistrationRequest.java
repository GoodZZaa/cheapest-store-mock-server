package com.goodzza.cheapeststore.api.dto.accountBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyBudgetRegistrationRequest {

    @NotNull
    private Integer year;
    @NotNull
    private Integer month;
    @NotNull
    private Long budget;
}
