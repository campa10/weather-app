package com.mercadolibre.prediction.job;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mercadolibre.prediction.repository.IPredictionDao;
import com.mercadolibre.prediction.init.InitComponent;
import com.mercadolibre.prediction.model.entities.Prediction;

@Component
public class PredictionJob {
		
	private static final Logger log = getLogger(PredictionJob.class);
	
	@Autowired
	private IPredictionDao predictionDao;
	
	@Autowired
	private InitComponent intComponent;
	 
	@Scheduled( cron = "0 10 0 1/1 * ?")
    public void run() {
		
		log.info("### start running job ###");
        
		Prediction prediction = intComponent.getDailyValue();
        this.predictionDao.save(prediction);   
        
        log.info("### end job ###");
    }

}
