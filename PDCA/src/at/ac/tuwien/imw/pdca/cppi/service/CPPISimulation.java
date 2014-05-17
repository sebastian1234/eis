package at.ac.tuwien.imw.pdca.cppi.service;

import java.io.IOException;

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
	private static CPPIStockPriceGenerator stock;
 
	private static CPPIService service;
	// TODO Implement me
	// private static CPPIxyProcess xpProcess;
	// ...

	// TODO Implement me
	// private static Thread xyProcessThread;
	// ...

	public static void main(String[] args) {

		// Init
		service = CPPIService.getInstance();
		service.init();
		service.setPlanConfiguration(new CPPIPlanConfiguration()); // auslagern in plan?
		
		
		stock = new CPPIStockPriceGenerator();

		// Threads
		Thread planProcessThread = new Thread(CPPIPlanProcess.getInstance());
		Thread doProcessThread = new Thread(CPPIDoProcess.getInstance());
		Thread checkProcessThread = new Thread(CPPICheckProcess.getInstance());
		Thread actProcessThread = new Thread(CPPIActProcess.getInstance());
		Thread generatorThread = new Thread(stock);

		service.setThreads(planProcessThread, doProcessThread, checkProcessThread, actProcessThread, generatorThread);
		
		generatorThread.start();
		planProcessThread.start();
		doProcessThread.start();
		checkProcessThread.start();
		actProcessThread.start();

	}

	public static void exit() throws IOException {
		CPPIDoProcess.getInstance().close();
		CPPIPlanProcess.getInstance().close();
		CPPICheckProcess.getInstance().close();
		CPPIActProcess.getInstance().close();
		stock.close();
	}
}
