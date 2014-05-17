package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.PlanProcess;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

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
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}*/
			log.info("Plan Process");
			plan();
			System.out.println("Notify Do");
			CPPIService.getInstance().notifyDo();
			try {
			  synchronized(this){
			    System.out.println("waitNotify Do");
	        wait();
	        System.out.println("waitNotify Do");
	      }
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
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
