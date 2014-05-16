package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.AdaptiveActRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIActRules implements AdaptiveActRules {

  private final static Logger log = LogManager.getLogger(ActProcess.class);
  
  CPPIService service;
  
  CPPIActRules(){
    service = CPPIService.getInstance();
  }
	@Override
	public void applyActRules() {
		BigDecimal valueA = service.getPlanConfiguration().getRiskAssetPercent().multiply(service.getTsrChange().getValue()); 
		BigDecimal valueB = service.getPlanConfiguration().getLaverage().multiply(service.getCurrentTSR().getValue()); 
		if (valueA.compareTo(valueB) < 0){
      service.getCppiValues().setPartRiskyAsset(valueA);
      service.getCppiValues().setPartRisklessAsset(service.getCurrentTSR().getValue().subtract(valueA));
      log.info("New risky portion: "+valueA);
      log.info("New riskless portion: "+service.getCurrentTSR().getValue().subtract(valueA));
    } else {
      service.getCppiValues().setPartRiskyAsset(valueB);
      service.getCppiValues().setPartRisklessAsset(service.getCurrentTSR().getValue().subtract(valueB));
      log.info("New risky portion: "+valueB);
      log.info("New riskless portion: "+service.getCurrentTSR().getValue().subtract(valueB));
      
    } 
	}

}
