package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.commons.math3.analysis.function.Floor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.DoRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIDoRules implements DoRules {
	
	private final static Logger log = LogManager.getLogger(CPPIValues.class);

	private CPPIService service = CPPIService.getInstance();
	private CPPIValues values;
	private BigDecimal floor, cushion, exposure, reserveasset, partRsikyAsset, partRisklessAsset, portfolio;

	@Override
	public void applyDoRules() {

		values = service.getCppiValues();
		
		//calc floor
		double p = values.getConf().getPortfolio().doubleValue();
		double n = (new BigDecimal(1.0)).add(values.getConf().getRisklessAssetInterest()).doubleValue();
		double e = 365 - service.getCurrentPeriod();
		double div = e / 365;		
		floor = new BigDecimal(p / Math.pow(n, div)).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//calc exposure
				
		
		log.info(floor + " - " + service.getCurrentPeriod());
		
		
		values.setFloor(floor);
		service.setCppiValues(values);

	}

}
