package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIActProcess extends ActProcess<String, BigDecimal> implements Closeable {

	private final static Logger log = LogManager.getLogger(ActProcess.class);

	private static CPPIActProcess instance;
	private boolean running = true;
	private CPPIActRules actRules;

	private CPPIActProcess() {
		actRules = new CPPIActRules();
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

			log.info("Act Process: Berechnet wie viel Geld in die sichere- und wie viel in die riskante Anlage investiert werden soll.");
			Deviation<BigDecimal> deviation = CPPIService.getInstance().getTsrChange();
			CorrectiveActOutput<String> actOutput = act(deviation);

			log.info("Configuration period: " + CPPIService.getInstance().getCurrentPeriod() + ", Floor: " + CPPIService.getInstance().getCppiValues().getFloor().setScale(4, BigDecimal.ROUND_HALF_UP) + ", Cushion: " + CPPIService.getInstance().getCppiValues().getCushion().setScale(4, BigDecimal.ROUND_HALF_UP) + ", Exposure: " + CPPIService.getInstance().getCppiValues().getExposure().setScale(4, BigDecimal.ROUND_HALF_UP) + ", PartRisky: " + CPPIService.getInstance().getCppiValues().getPartRiskyAsset().setScale(4, BigDecimal.ROUND_HALF_UP) + ", PartRiskless: " + CPPIService.getInstance().getCppiValues().getPartRisklessAsset().setScale(4, BigDecimal.ROUND_HALF_UP) + ", NewPortfolio: " + CPPIService.getInstance().getCppiValues().getPortfolio().setScale(4, BigDecimal.ROUND_HALF_UP)
					+ ", PreviousStockPrice: " + CPPIService.getInstance().getCppiValues().getPreviousStockPrice().setScale(4, BigDecimal.ROUND_HALF_UP) + ", ActualStockPrice: " + CPPIService.getInstance().getCppiValues().getActualStockPrice().setScale(4, BigDecimal.ROUND_HALF_UP) + "\n");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
	}

	@Override
	public CorrectiveActOutput<String> act(Deviation<BigDecimal> deviation) {
		actRules.applyActRules();
		return null;
	}

	@Override
	public void close() throws IOException {
		running = false;
	}

}
