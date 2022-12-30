package com.goodzza.cheapeststore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MartProductSearchVo extends MartProductVo {

    private Long originalPrice;
    private Integer discountPercent;
    private String unit;
    private Integer unitValue;
    private Long productId;

    public static MartProductSearchVo convert(MartProductVo vo) {
        MartProductSearchVo result = new MartProductSearchVo();
        BeanUtils.copyProperties(vo, result);
        return result;
    }
}
