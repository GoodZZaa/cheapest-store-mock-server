package com.goodzza.cheapeststore.api.dto.accountBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutVo {

    private String title;
    private LocalTime start;
    private LocalTime end;
    private String description;
    private List<String> keyword;
    private Long price;
}
