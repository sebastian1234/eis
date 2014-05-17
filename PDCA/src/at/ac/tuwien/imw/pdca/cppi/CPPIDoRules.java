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
	private BigDecimal floor, cushion, exposure, reserveasset, partRiskyAsset, partRisklessAsset, portfolio;

	@Override
	public void applyDoRules() {

		values = service.getCppiValues();
		
		//portfolio ist immer 100
		portfolio = values.getPortfolio();
		
		
		//calc floor
		double p = portfolio.doubleValue();
		double n = (new BigDecimal(1.0)).add(values.getConf().getRisklessAssetInterest()).doubleValue();
		double e = 365 - service.getCurrentPeriod();
		double div = e / 365;		
		floor = new BigDecimal(p / Math.pow(n, div)).setScale(2, BigDecimal.ROUND_HALF_UP);

		
		//calc exposure
		BigDecimal st0 = service.getPreviousStockPrice();
		BigDecimal st1 = service.getCurrentStockPrice();
		
		partRiskyAsset = values.getPartRiskyAsset();
		partRisklessAsset = values.getPartRisklessAsset();
		
		BigDecimal TSR = st1.divide(st0,4,  BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1));
		
		//Formel Wt
		
		//parts manuell gesetzt zum testen
		//sollten vom act berechnet werden. beim ersten mal selbst berechnen?
		partRiskyAsset = new BigDecimal(9.5238095);
		partRisklessAsset = new BigDecimal(90.47619);
		double part2 = partRisklessAsset.doubleValue() * Math.pow(1+values.getConf().getRisklessAssetInterest().doubleValue(), 1/365);
		exposure = (partRiskyAsset.multiply(new BigDecimal(1).add(TSR))).add(new BigDecimal(part2)).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//calc cushion
		cushion = exposure.subtract(floor).max(new BigDecimal(0));
		
//		log.info("W(t)= " + exposure +", TSR=" + TSR + ", st0=" +st0+", st1="+st1 + ", part2=" + part2);
//
//		log.info(floor + " - " + service.getCurrentPeriod());
//		
//		log.info("cushion= " + cushion);
		
		
		
		service.setCppiValues(new CPPIValues(values.getConf(), portfolio, TSR,floor,cushion,exposure,reserveasset,partRiskyAsset, partRisklessAsset,st0,st1));

	}

}
