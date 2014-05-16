package at.ac.tuwien.imw.pdca.cppi.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.cppi.CPPIActProcess;
import at.ac.tuwien.imw.pdca.cppi.CPPICheckProcess;
import at.ac.tuwien.imw.pdca.cppi.CPPIDoProcess;
import at.ac.tuwien.imw.pdca.cppi.CPPIPlanConfiguration;
import at.ac.tuwien.imw.pdca.cppi.CPPIPlanProcess;

public class CPPISimulation {

	private final static Logger log = LogManager.getLogger(CPPISimulation.class);

	private static CPPIPlanProcess cppiPlanProcess;
	private static CPPIDoProcess cppiDoProcess;
	private static CPPICheckProcess cppiCheckProcess;
	private static CPPIActProcess cppiActProcess;

	// TODO Implement me
	// private static CPPIxyProcess xpProcess;
	// ...

	// TODO Implement me
	// private static Thread xyProcessThread;
	// ...

	public static void main(String[] args) {

		// Init
		CPPIService.getInstance().init();
		CPPIService.getInstance().setPlanConfiguration(new CPPIPlanConfiguration());

		// Threads
		Thread planProcessThread = new Thread(CPPIPlanProcess.getInstance());
		Thread doProcessThread = new Thread(CPPIDoProcess.getInstance());
		Thread checkProcessThread = new Thread(CPPICheckProcess.getInstance());
		Thread actProcessThread = new Thread(CPPIActProcess.getInstance());

		// xyProcess = new CPPITSRxy();
		// xyProcessThread = new Thread(xyProcess);
		// xyProcessThread.start();

		// ...

		new Thread(new CPPIStockPriceGenerator()).start();
		planProcessThread.start();
		doProcessThread.start();
		checkProcessThread.start();
		actProcessThread.start();
	}
}
