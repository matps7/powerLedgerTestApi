package com.powerldger.code.repository;

import java.util.List;

import com.powerldger.code.model.Batteries;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BatteriesRepository extends JpaRepository<Batteries, Long>{

        List<Batteries> findWithRangePostCode(int start, int end);

}

