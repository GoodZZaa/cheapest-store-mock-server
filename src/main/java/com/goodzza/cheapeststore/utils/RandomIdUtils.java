package com.goodzza.cheapeststore.utils;

import java.util.Random;

public class RandomIdUtils {

    private static final Random RAND = new Random();

    public static Long getRandomId(Integer pageNumber, Long bound) {
        return pageNumber * bound + RAND.nextLong(bound);
    }
}
