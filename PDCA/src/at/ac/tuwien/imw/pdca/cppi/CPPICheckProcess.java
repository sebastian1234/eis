package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;

public class CPPICheckProcess extends CheckProcess {

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
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			System.out.println("Check Process");
		}
	}

	@Override
	public Deviation getCheckResult(ObjectiveSetting objective, MeasuredPerformanceValue performanceMeasureValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
