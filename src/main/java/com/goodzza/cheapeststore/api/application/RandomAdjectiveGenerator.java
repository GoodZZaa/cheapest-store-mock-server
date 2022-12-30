package com.goodzza.cheapeststore.api.application;

import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomAdjectiveGenerator {
    private static final List<String> ADJECTIVES = ImmutableList.of(
            "예쁜",
            "꺠끗한",
            "아름다운",
            "합리적인",
            "최고급",
            "명품",
            "누구나 갖고 싶어하는",
            "사람들이 잘 모르는",
            "변변찮은",
            "하찮은",
            "쓸모없는",
            "많은 사람들에게 사랑받는",
            "인기있는",
            "모두가 인정하는",
            "신토불이",
            "조금 아쉬운",
            "비싼",
            "값싼",
            "잘 부러지는",
            "단단한",
            "퓨어한",
            "친환경",
            "지옥에서 온",
            "신성한",
            "악마의 저주가 깃든",
            "신의 은총이 깃든",
            "힘이 솟는",
            "꽃 향기가 나는",
            "손이 잘 안가는"
    );
    private static final Random RAND = new Random();


    public String getRandomAdjective() {
        return ADJECTIVES.get(RAND.nextInt(ADJECTIVES.size()));
    }
}
