package com.goodzza.cheapeststore.api.dto.checkout;

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
public class MartProductInsertRequest {

    @NotNull
    private Long martProductId;
}
