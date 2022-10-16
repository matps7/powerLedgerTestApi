package com.powerldger.code.repository.impl;

import com.powerldger.code.model.Batteries;
import com.powerldger.code.repository.BatteriesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class BatteriesRepositoryImpl implements BatteriesRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Batteries> findWithRangePostCode(int start, int end) {

        List<Batteries> batteries = entityManager.createQuery("SELECT b FROM Batteries b WHERE b.postcode >= :start AND b.postcode <= :end", Batteries.class)
                .setParameter("start", start).setParameter("end",end)
                .getResultList();

        return batteries;
    }
}
