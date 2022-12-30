package com.goodzza.cheapeststore.api.application;

import java.util.List;

public interface RandomGenerator<T> {
    List<T> generate(Integer pageSize, Integer pageNumber);

    T generate(Long id);

}
