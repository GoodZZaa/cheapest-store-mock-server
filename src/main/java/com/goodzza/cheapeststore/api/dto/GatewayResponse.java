package com.goodzza.cheapeststore.api.dto;

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
public class GatewayResponse {

    private List<MartProductVo> promotionProducts;
    private List<MartVo> cheapestMarts;
    private List<MartProductVo> cheapestProducts;
}
