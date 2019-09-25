package com.mercadolibre.prediction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mercadolibre.prediction.model.entities.Prediction;

@Repository
public interface IPredictionDao extends JpaRepository<Prediction, Long> {

	Prediction findByDay(int day);
	
	Long countByState(String state);

}
