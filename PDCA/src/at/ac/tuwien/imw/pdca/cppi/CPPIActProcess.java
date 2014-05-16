package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.Deviation;

public class CPPIActProcess extends ActProcess {

	private static CPPIActProcess instance;

	private CPPIActProcess() {
	}

	public static synchronized CPPIActProcess getInstance() {
		if (instance == null) {
			instance = new CPPIActProcess();
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
			System.out.println("Act Process");
		}
	}

	@Override
	public CorrectiveActOutput act(Deviation deviation) {
		// TODO Auto-generated method stub
		return null;
	}

}
