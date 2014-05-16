package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICheckProcess extends CheckProcess {

	private final static Logger log = LogManager.getLogger(CheckProcess.class);
	
	private static CPPICheckProcess instance;
	private CheckingRules checkRules;
  private ObjectiveSetting<BigDecimal> objective;
  private MeasuredPerformanceValue<BigDecimal> performanceMeasureValue;
  
	private CPPICheckProcess() {
	  checkRules = new CPPICheckRules();
	}

	public static synchronized CPPICheckProcess getInstance() {
		if (instance == null) {
			instance = new CPPICheckProcess();
		}
		return instance;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			CPPIService service = CPPIService.getInstance();
			log.info("Check Process");
			
			objective = new CPPIObjectiveSetting(service.getCppiValues().getFloor());
			//CPPIPlanConfiguration config = service.getPlanConfiguration();
			//objective = new CPPIObjectiveSetting(config.getPortfolio().divide(((new BigDecimal(1.0)).add(config.getRisklessAssetInterest()))).pow(service.getCurrentPeriod()));
			performanceMeasureValue = (new CPPIMeasureRules()).measure();
			Deviation<BigDecimal> dev = getCheckResult(objective, performanceMeasureValue);
			service.setTsrChange(dev);
			service.setDeviationValue(dev.getValue());
			checkRules.applyCheckingRules();
		}
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting objective, MeasuredPerformanceValue performanceMeasureValue) {
	  BigDecimal deviation;
	 
	  deviation = ((BigDecimal)performanceMeasureValue.getValue()).subtract((BigDecimal)objective.getObjectiveSetting());
	  
		return new CPPIDeviation(deviation);
	}

}
