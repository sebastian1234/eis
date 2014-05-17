package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.PlanProcess;
import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIPlanRules implements PlanningRules<BigDecimal>{

  private final static Logger log = LogManager.getLogger(PlanProcess.class);

  CPPIService service;
  
	@Override
	public BigDecimal applyPlanningRules() {
	  service = CPPIService.getInstance();
	  CPPIPlanConfiguration config = new CPPIPlanConfiguration();
		service.setPlanConfiguration(config);
		
		BigDecimal f0 = config.getPortfolio().divide(new BigDecimal(1).add(config.getRisklessAssetInterest()), 20, BigDecimal.ROUND_HALF_UP);
		service.getCppiValues().setFloor(f0);
		BigDecimal cushion = config.getPortfolio().subtract(f0);
		service.getCppiValues().setCushion(cushion);
		service.getCppiValues().setExposure(config.getPortfolio());

    BigDecimal valueA = config.getRiskAssetPercent().multiply(config.getPortfolio());
    BigDecimal valueB = config.getLaverage().multiply(cushion);
    if (valueA.compareTo(valueB) < 0) {
      service.getCppiValues().setPartRiskyAsset(valueA);
      service.getCppiValues().setPartRisklessAsset(config.getPortfolio().subtract(valueA));

    } else {
      service.getCppiValues().setPartRiskyAsset(valueB);
      service.getCppiValues().setPartRisklessAsset(config.getPortfolio().subtract(valueB));


    }
    
    log.info("Configuration period: " + service.getCurrentPeriod() + 
        ", Floor: " + service.getCppiValues().getFloor().setScale(4, BigDecimal.ROUND_HALF_UP) + 
        ", Cushion: " + service.getCppiValues().getCushion().setScale(4, BigDecimal.ROUND_HALF_UP) + 
        ", Exposure: " + service.getCppiValues().getExposure().setScale(4, BigDecimal.ROUND_HALF_UP) + 
        ", PartRisky: " + service.getCppiValues().getPartRiskyAsset().setScale(4, BigDecimal.ROUND_HALF_UP) + 
        ", PartRiskless: " + service.getCppiValues().getPartRisklessAsset().setScale(4, BigDecimal.ROUND_HALF_UP) + 
        ", NewPortfolio: " + service.getCppiValues().getPortfolio().setScale(4, BigDecimal.ROUND_HALF_UP) +
        //", PreviousStockPrice: " + service.getCppiValues().getPreviousStockPrice().setScale(4, BigDecimal.ROUND_HALF_UP) +
        //", ActualStockPrice: " + service.getCppiValues().getActualStockPrice().setScale(4, BigDecimal.ROUND_HALF_UP)
        " ");
		return null; //warum soll hier auf einmal etwas zurückgegeben werden?
	}

}
