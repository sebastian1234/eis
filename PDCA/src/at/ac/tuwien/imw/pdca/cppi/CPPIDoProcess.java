package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.DoProcess;

public class CPPIDoProcess extends DoProcess implements Closeable {

	private final static Logger log = LogManager.getLogger(DoProcess.class);
	private static CPPIDoProcess instance;

	private boolean running = true;

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
		while (running) {
			log.info("Do Prozess: Berechnung aller benötigten Variablen: Cushion, Floor, Portfoliowert, TSR");
			operate();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
	}

	@Override
	public void operate() {
		doRules.applyDoRules();
	}

	@Override
	public void close() throws IOException {
		running = false;

	}

}
