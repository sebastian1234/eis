package at.ac.tuwien.imw.pdca.cppi.service;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author ivanstojkovic
 * 
 */
public class CPPIStockPriceGenerator implements Runnable, Closeable {

	private final static Logger log = LogManager.getLogger(CPPIStockPriceGenerator.class);
	private boolean running = true;

	public CPPIStockPriceGenerator() {
		super();
	}

	public void run() {
		log.info("CPPIStockPriceGenerator process started");
		while (running) {
			CPPIService.getInstance().updateActualStockPrice();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws IOException {
		running = false;
	}

}
