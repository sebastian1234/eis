package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICheckProcess extends CheckProcess<BigDecimal> implements Closeable {

	private final static Logger log = LogManager.getLogger(CheckProcess.class);

	private static CPPICheckProcess instance;

	private boolean running = true;

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
		while (running) {

			CPPIService service = CPPIService.getInstance();
			log.info("Check Process");
			BigDecimal floor = service.getCppiValues().getFloor();
			objective = new CPPIObjectiveSetting();
			objective.setObjectiveSetting(floor);
			// CPPIPlanConfiguration config = service.getPlanConfiguration();
			// objective = new CPPIObjectiveSetting(config.getPortfolio().divide(((new BigDecimal(1.0)).add(config.getRisklessAssetInterest()))).pow(service.getCurrentPeriod()));
			performanceMeasureValue = (new CPPIMeasureRules()).measure();

			Deviation<BigDecimal> dev = getCheckResult(objective, performanceMeasureValue);
			service.setDeviationValue(dev.getValue());

			if (dev.getValue().compareTo(BigDecimal.ZERO) > 0) {
				service.setTsrChange(dev);
				service.getCppiValues().setCushion(dev.getValue());
			} else {
				service.setTsrChange((new CPPIDeviation(new BigDecimal(0))));
				service.getCppiValues().setCushion(new BigDecimal(0));
			}
			service.setCurrentTSR(performanceMeasureValue);
			checkRules.applyCheckingRules();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
	}

	@Override
	public Deviation<BigDecimal> getCheckResult(ObjectiveSetting objective, MeasuredPerformanceValue performanceMeasureValue) {
		BigDecimal deviation;
		log.info("----------------");
		log.info(objective.getObjectiveSetting());
		log.info("----------------");
		deviation = ((BigDecimal) performanceMeasureValue.getValue()).subtract((BigDecimal) objective.getObjectiveSetting());

		return new CPPIDeviation(deviation);
	}

	@Override
	public void close() throws IOException {
		running = false;
	}

}
