package com.goodzza.cheapeststore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRequest {

    private Boolean isFirst;
    private Integer PageSize;
    private Integer PageNumber;
}
