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



		//calc exposure
		BigDecimal st0 = service.getPreviousStockPrice();
		BigDecimal st1 = service.getCurrentStockPrice();

		partRiskyAsset = values.getPartRiskyAsset();
		partRisklessAsset = values.getPartRisklessAsset();

		BigDecimal TSR = st1.divide(st0,20,  BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1));


		//Formel Wt
		double part2 = partRisklessAsset.doubleValue() * Math.pow(1+values.getConf().getRisklessAssetInterest().doubleValue(), 1.0/365.0);
		exposure = (partRiskyAsset.multiply(new BigDecimal(1).add(TSR))).add(new BigDecimal(part2));

		//calc floor
		double p = portfolio.doubleValue();
		double n = (new BigDecimal(1.0)).add(values.getConf().getRisklessAssetInterest()).doubleValue();
		double e = values.getConf().getRisklessAssetLastDays() - service.getCurrentPeriod();
		double div = e / values.getConf().getRisklessAssetLastDays();		
		floor = new BigDecimal(p / Math.pow(n, div));

		//calc cushion
		cushion = exposure.subtract(floor).max(new BigDecimal(0));


		service.setCppiValues(new CPPIValues(values.getConf(), portfolio, TSR,floor,cushion,exposure,reserveasset,partRiskyAsset, partRisklessAsset,st0,st1));

	}

}
