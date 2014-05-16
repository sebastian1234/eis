package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.DoProcess;

public class CPPIDoProcess extends DoProcess {
	private static CPPIDoProcess instance;

	private CPPIDoProcess() {
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
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			System.out.println("Do Process");
		}
	}

	@Override
	public void operate() {
		// TODO Auto-generated method stub

	}

}
