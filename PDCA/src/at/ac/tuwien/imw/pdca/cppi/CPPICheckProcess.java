package at.ac.tuwien.imw.pdca.cppi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;

public class CPPICheckProcess extends CheckProcess {

	private final static Logger log = LogManager.getLogger(CheckProcess.class);
	
	private static CPPICheckProcess instance;

	private CPPICheckProcess() {
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
			log.info("Check Process");
		}
	}

	@Override
	public Deviation getCheckResult(ObjectiveSetting objective, MeasuredPerformanceValue performanceMeasureValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
