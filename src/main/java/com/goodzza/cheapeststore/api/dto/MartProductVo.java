package com.goodzza.cheapeststore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MartProductVo {
    private Long martProductId;
    private String productName;
    private String imageUrl;
    private Long price;
}
