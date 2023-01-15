package com.goodzza.cheapeststore.api.application;

import com.goodzza.cheapeststore.api.dto.MartProductSearchVo;
import com.goodzza.cheapeststore.api.dto.MartProductVo;
import com.goodzza.cheapeststore.api.dto.ProductVo;
import com.goodzza.cheapeststore.api.dto.UnitType;
import com.goodzza.cheapeststore.utils.RandomIdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class RandomMartProductGenerator implements RandomGenerator<MartProductVo> {

    private static final String WHITE_SPACE = " ";
    private static final Random RAND = new Random();
    private final RandomAdjectiveGenerator randomAdjectiveGenerator;
    private final RandomProductGenerator randomProductGenerator;

    private final RandomMartGenerator randomMartGenerator;

    public List<MartProductVo> generate(Integer pageSize, Integer pageNumber) {
        return IntStream.range(0, pageSize)
                        .mapToObj(i -> generate(RandomIdUtils.getRandomId(pageNumber, 100L)))
                        .collect(Collectors.toList());
    }

    public MartProductVo generate(Long id) {
        MartProductVo mock = generate();
        mock.setMartProductId(id);
        return mock;
    }

    private MartProductVo generate() {
        ProductVo product = randomProductGenerator.generate();
        String name = product.getProductName();
        String imageUrl = product.getImageUrl();
        MartProductVo martProduct = MartProductVo.convert(product);

        martProduct.setMartName(randomAdjectiveGenerator.getRandomAdjective() + WHITE_SPACE
                                + randomMartGenerator.generate(0L).getMartName());
        martProduct.setProductName(randomAdjectiveGenerator.getRandomAdjective() + WHITE_SPACE + name);
        martProduct.setImageUrl(imageUrl);
        martProduct.setPrice(RAND.nextLong(1000L, 50000L) % 100 * 100);
        return martProduct;
    }

    public List<MartProductSearchVo> searchMartProducts(String keyword, Integer pageSize, Integer pageNumber) {
        List<MartProductVo> mocks = generate(pageSize, pageNumber);
        List<MartProductSearchVo> vos = mocks.stream().map(MartProductSearchVo::convert).collect(Collectors.toList());
        vos.forEach(mock -> {
            Long originalPrice = mock.getPrice();
            int discountPercent = RAND.nextInt(50);
            UnitType randomUnit = UnitType.getRandomUnit();
            mock.setProductName(randomAdjectiveGenerator.getRandomAdjective() + WHITE_SPACE + keyword);
            mock.setOriginalPrice(originalPrice);
            mock.setDiscountPercent(discountPercent);
            mock.setPrice((long) ((100 - discountPercent) / 100f * originalPrice));
            mock.setUnit(randomUnit.getUnitName());
            mock.setUnitValue(1 + RAND.nextInt(randomUnit.getUpperLimit()));
            mock.setProductId(RandomIdUtils.getRandomId(0, 2000L));
        });

        return vos;
    }
}
