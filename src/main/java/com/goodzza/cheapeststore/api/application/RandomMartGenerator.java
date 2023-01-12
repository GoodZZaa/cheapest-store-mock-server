package com.goodzza.cheapeststore.api.application;

import com.goodzza.cheapeststore.api.dto.MartVo;
import com.goodzza.cheapeststore.utils.RandomIdUtils;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class RandomMartGenerator implements RandomGenerator<MartVo> {

    private static final List<Pair<String, String>> MOCK_MARTS = ImmutableList.of(
            Pair.of("흥부네마트",
                    "https://images.unsplash.com/photo-1583922146273-68f11083858e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2078&q=80"),
            Pair.of("다이써마트",
                    "https://images.unsplash.com/photo-1526152505827-d2f3b5b4a52a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("카트만 홈마트",
                    "https://images.unsplash.com/photo-1580440282860-8555b1ae102c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("미소가 이쁜 마트",
                    "https://images.unsplash.com/photo-1554228326-2539c9639a1d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80"),
            Pair.of("할미 빵 전문 마트",
                    "https://images.unsplash.com/photo-1578295113955-a850c30678ce?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2688&q=80"),
            Pair.of("여기 어때 마트",
                    "https://images.unsplash.com/photo-1574722772581-f4a4e07e147b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=953&q=80"),
            Pair.of("페밀리마트",
                    "https://images.unsplash.com/photo-1580674287405-80cd77a2fee2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80"),
            Pair.of("다세일마트",
                    "https://images.unsplash.com/photo-1601599963565-b7ba29c8e3ff?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1364&q=80"),
            Pair.of("왜 없어 마트",
                    "https://images.unsplash.com/photo-1578916171728-46686eac8d58?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2148&q=80"),
            Pair.of("사진찍기 좋은 마트",
                    "https://images.unsplash.com/photo-1578916171728-46686eac8d58?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2148&q=80"),
            Pair.of("커피 마트",
                    "https://images.unsplash.com/photo-1584680226833-0d680d0a0794?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80"),
            Pair.of("강부자 마트",
                    "https://images.unsplash.com/photo-1601600576337-c1d8a0d1373c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("카프레디엠",
                    "https://images.unsplash.com/photo-1621244320421-cc9782f5ce28?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("세인트 마트",
                    "https://images.unsplash.com/photo-1566454825481-4e48f80aa4d7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1480&q=80"),
            Pair.of("아마존 GO",
                    "https://images.unsplash.com/photo-1611279522012-6c3e2d2c604f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("E-MART",
                    "https://images.unsplash.com/photo-1605447813584-26aeb3f8e6ae?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80"),
            Pair.of("미스트 마트",
                    "https://images.unsplash.com/photo-1606824722920-4c652a70f348?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1335&q=80"),
            Pair.of("순양 마트",
                    "https://plus.unsplash.com/premium_photo-1661381021048-5498177b2c3d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80")
    );
    private static final String WHITE_SPACE = " ";
    private static final Random RAND = new Random();
    private final RandomAdjectiveGenerator randomAdjectiveGenerator;

    public List<MartVo> generate(Integer pageSize, Integer pageNumber) {
        return IntStream.range(0, pageSize)
                        .mapToObj(i -> generate(RandomIdUtils.getRandomId(pageNumber, 100L)))
                        .collect(Collectors.toList());
    }

    public MartVo generate(Long id) {
        MartVo mock = generate();
        mock.setMartId(id);
        return mock;
    }

    private MartVo generate() {
        Pair<String, String> randomMartProduct = MOCK_MARTS.get(RAND.nextInt(MOCK_MARTS.size()));
        String name = randomMartProduct.getLeft();
        String imageUrl = randomMartProduct.getRight();

        return MartVo.builder()
                     .martName(randomAdjectiveGenerator.getRandomAdjective() + WHITE_SPACE + name)
                     .imageUrl(imageUrl)
                     .build();
    }
}
