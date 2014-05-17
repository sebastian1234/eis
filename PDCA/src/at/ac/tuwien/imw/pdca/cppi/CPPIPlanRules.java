package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIPlanRules implements PlanningRules<Object>{

  private final static Logger log = LogManager.getLogger(ActProcess.class);

  CPPIService service;
  
	@Override
	public Object applyPlanningRules() {
	  service = CPPIService.getInstance();
	  CPPIPlanConfiguration config = new CPPIPlanConfiguration();
		service.setPlanConfiguration(config);
		
		BigDecimal f0 = config.getPortfolio().divide(new BigDecimal(1).add(config.getRisklessAssetInterest()), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal cushion = config.getPortfolio().subtract(f0);
		log.info("Plan: b:" + config.getRiskAssetPercent()+"; W(0):"+config.getPortfolio());
    log.info("Plan: m:" + config.getLaverage()+"; C(0):"+cushion);
    BigDecimal valueA = config.getRiskAssetPercent().multiply(config.getPortfolio());//.getTsrChange().getValue());
    BigDecimal valueB = config.getLaverage().multiply(cushion);//.getCurrentTSR().getValue());
    if (valueA.compareTo(valueB) < 0) {
      service.getCppiValues().setPartRiskyAsset(valueA);
      service.getCppiValues().setPartRisklessAsset(config.getPortfolio().subtract(valueA));
      log.info("New risky portion: " + valueA);
      log.info("New riskless portion: " + config.getPortfolio().subtract(valueA));
    } else {
      service.getCppiValues().setPartRiskyAsset(valueB);
      service.getCppiValues().setPartRisklessAsset(config.getPortfolio().subtract(valueB));
      log.info("New risky portion: " + valueB);
      log.info("New riskless portion: " + config.getPortfolio().subtract(valueB));

    }
    
		return null; //warum soll hier auf einmal etwas zurückgegeben werden?
	}

}
