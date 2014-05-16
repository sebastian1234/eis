package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.Deviation;

public class CPPIActProcess extends ActProcess implements Closeable {

	private final static Logger log = LogManager.getLogger(ActProcess.class);

	private static CPPIActProcess instance;
	private boolean running = true;

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

		while (running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			log.info("Act Process");
		}
	}

	@Override
	public CorrectiveActOutput act(Deviation deviation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		running = false;
	}

}
