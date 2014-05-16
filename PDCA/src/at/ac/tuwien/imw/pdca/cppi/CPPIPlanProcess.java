package at.ac.tuwien.imw.pdca.cppi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.PlanProcess;

public class CPPIPlanProcess extends PlanProcess {
	
	private final static Logger log = LogManager.getLogger(PlanProcess.class);
	
	private static CPPIPlanProcess instance;

	private CPPIPlanProcess() {
	}

	public static synchronized CPPIPlanProcess getInstance() {
		if (instance == null) {
			instance = new CPPIPlanProcess();
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
			log.info("Plan Process");
		}
	}

	@Override
	public void plan() {
		// TODO Auto-generated method stub

	}

}
