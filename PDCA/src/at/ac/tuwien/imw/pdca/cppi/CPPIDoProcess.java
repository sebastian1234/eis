package at.ac.tuwien.imw.pdca.cppi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.DoProcess;

public class CPPIDoProcess extends DoProcess {
	
	
	private final static Logger log = LogManager.getLogger(DoProcess.class);
	private static CPPIDoProcess instance;

	private CPPIDoProcess() {
		doRules = new CPPIDoRules();
	}

	public static synchronized CPPIDoProcess getInstance() {
		if (instance == null) {
			instance = new CPPIDoProcess();
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
			log.info("Do Process");
			operate();
		}
	}

	@Override
	public void operate() {
		doRules.applyDoRules();
	}

}
