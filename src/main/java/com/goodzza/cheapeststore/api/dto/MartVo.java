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
public class MartVo {

    private Long martId;
    private String martName;
    private String imageUrl;
}
