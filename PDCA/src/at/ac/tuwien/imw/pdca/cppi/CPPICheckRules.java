package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPICheckRules implements CheckingRules {

	private final static Logger log = LogManager.getLogger(CPPICheckProcess.class);

	@Override
	public void applyCheckingRules() {

		if (CPPIService.getInstance().getTsrChange().getValue().compareTo(BigDecimal.ZERO) <= 0) {
			// getTsrChange() missbraucht f�r Cushion!!!!!!
			log.info("Cushion <= 0: " + CPPIService.getInstance().getTsrChange().getValue().doubleValue() + " System exit");
			CPPIService.getInstance().exit();
		}
	}
}
