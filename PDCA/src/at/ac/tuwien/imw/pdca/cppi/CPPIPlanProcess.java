package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.PlanProcess;

public class CPPIPlanProcess extends PlanProcess {
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
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			System.out.println("Plan Process");
		}
	}

	@Override
	public void plan() {
		// TODO Auto-generated method stub

	}

}
