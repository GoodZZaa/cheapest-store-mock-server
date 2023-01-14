package com.goodzza.cheapeststore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MartProductVo {
    private Long martProductId;
    private String martName;
    private String productName;
    private String imageUrl;
    private Long price;

    public static MartProductVo convert(ProductVo source) {
        MartProductVo result = new MartProductVo();
        BeanUtils.copyProperties(source, result);
        return result;
    }
}
