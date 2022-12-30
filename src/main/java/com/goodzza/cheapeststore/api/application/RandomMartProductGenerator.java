package com.goodzza.cheapeststore.api.application;

import com.goodzza.cheapeststore.api.dto.MartProductVo;
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
public class RandomMartProductGenerator implements RandomGenerator<MartProductVo> {

    private static final List<Pair<String, String>> MOCK_MART_PRODUCTS = ImmutableList.of(
            Pair.of("피망",
                    "https://images.unsplash.com/photo-1526470498-9ae73c665de8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format"),
            Pair.of("종합 야채",
                    "https://images.unsplash.com/photo-1579113800032-c38bd7635818?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("당근",
                    "https://images.unsplash.com/photo-1522184216316-3c25379f9760?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80"),
            Pair.of("파인애플",
                    "https://plus.unsplash.com/premium_photo-1661277707860-e437d3758609?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1480&q=80"),
            Pair.of("시리얼",
                    "https://images.unsplash.com/photo-1556767576-cf0a4a80e5b8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2148&q=80"),
            Pair.of("토마토",
                    "https://images.unsplash.com/photo-1559004536-1c1ceb2763b2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1335&q=80"),
            Pair.of("시금치",
                    "https://images.unsplash.com/photo-1601601245632-0ea1d6a5a00a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"),
            Pair.of("바나나",
                    "https://images.unsplash.com/photo-1543218024-57a70143c369?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1335&q=80"),
            Pair.of("사과",
                    "https://images.unsplash.com/photo-1577028300036-aa112c18d109?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1335&q=80"),
            Pair.of("브로콜리",
                    "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1480&q=80"),
            Pair.of("호박",
                    "https://images.unsplash.com/photo-1568983521157-e70347fac5fe?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1335&q=80"),
            Pair.of("계란",
                    "https://images.unsplash.com/photo-1506976785307-8732e854ad03?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2086&q=80"),
            Pair.of("복숭아",
                    "https://images.unsplash.com/photo-1593629718768-e8860d848a15?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1403&q=80"),
            Pair.of("포도",
                    "https://images.unsplash.com/photo-1615485925763-86786288908a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1480&q=80"),
            Pair.of("옥수수",
                    "https://images.unsplash.com/photo-1615485290161-7eb49a34eba5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2371&q=80")
    );
    private static final String WHITE_SPACE = " ";
    private static final Random RAND = new Random();
    private final RandomAdjectiveGenerator randomAdjectiveGenerator;

    @Override
    public List<MartProductVo> generate(Integer pageSize, Integer pageNumber) {
        return IntStream.range(0, pageSize)
                        .mapToObj(i -> generate(RandomIdUtils.getRandomId(pageNumber, 100L)))
                        .collect(Collectors.toList());
    }

    @Override
    public MartProductVo generate(Long id) {
        MartProductVo mock = generate();
        mock.setMartProductId(id);
        return mock;
    }

    private MartProductVo generate() {
        Pair<String, String> randomMartProduct = MOCK_MART_PRODUCTS.get(RAND.nextInt(MOCK_MART_PRODUCTS.size()));
        String name = randomMartProduct.getLeft();
        String imageUrl = randomMartProduct.getRight();

        return MartProductVo.builder()
                            .productName(randomAdjectiveGenerator.getRandomAdjective() + WHITE_SPACE + name)
                            .imageUrl(imageUrl)
                            .price(RAND.nextLong(1000L, 50000L) % 100 * 100)
                            .build();
    }
}
