package com.goodzza.cheapeststore.api.dto.recommendation;

import com.goodzza.cheapeststore.api.dto.MartVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MartRecommendationVo extends MartVo {
    private Long estimatedPrice;
    private Float distance;

    public static MartRecommendationVo convert(MartVo vo) {
        MartRecommendationVo result = new MartRecommendationVo();
        BeanUtils.copyProperties(vo, result);
        return result;
    }
}
