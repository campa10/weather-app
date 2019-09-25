package com.mercadolibre.prediction.repository;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mercadolibre.prediction.model.entities.Prediction;

@Repository
public class PredictionDaoCustomImpl implements IPredictionDaoCustom{
	

	@PersistenceContext
	@Autowired
	EntityManager em;

	public Prediction findMaxByState(String state) {
		
		String sql = "SELECT * FROM Prediction p WHERE p.state = ? order by perimeter DESC limit 1";
		Query query = em.createNativeQuery(sql, Prediction.class);
		query.setParameter(1, state);
		Prediction prediction = (Prediction) query.getSingleResult();
		
		return prediction;
		
	}

}
