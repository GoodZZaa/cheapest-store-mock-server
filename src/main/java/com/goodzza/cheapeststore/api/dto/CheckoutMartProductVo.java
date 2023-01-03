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
public class CheckoutMartProductVo extends MartProductVo {
    
    private int amount;

    public static CheckoutMartProductVo convert(MartProductVo vo) {
        CheckoutMartProductVo result = new CheckoutMartProductVo();
        BeanUtils.copyProperties(vo, result);
        return result;
    }
}
