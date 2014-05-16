package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.MeasureRules;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIMeasureRules implements MeasureRules {

	@Override
	public MeasuredPerformanceValue<BigDecimal> measure() {
		return CPPIService.getInstance().getCurrentTSR();
	}

}
