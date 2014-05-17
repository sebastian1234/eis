package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.PlanProcess;

public class CPPIPlanProcess extends PlanProcess implements Closeable {

	private final static Logger log = LogManager.getLogger(PlanProcess.class);
	private boolean running = true;
	private static CPPIPlanProcess instance;

	private CPPIPlanProcess() {
		planningRules = new CPPIPlanRules();
	}

	public static synchronized CPPIPlanProcess getInstance() {
		if (instance == null) {
			instance = new CPPIPlanProcess();
		}
		return instance;
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			log.info("Plan Process");
			plan();
		}
	}

	@Override
	public void plan() {
		planningRules.applyPlanningRules();
	}

	@Override
	public void close() throws IOException {
		running = false;
	}
}
