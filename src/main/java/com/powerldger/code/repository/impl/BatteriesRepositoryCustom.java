package com.powerldger.code.repository.impl;

import com.powerldger.code.model.Batteries;

import java.util.List;

public interface BatteriesRepositoryCustom {

    List<Batteries> findWithRangePostCode(int start, int end);
}
