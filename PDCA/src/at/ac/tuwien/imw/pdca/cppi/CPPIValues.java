package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIValues {

	private final static Logger log = LogManager.getLogger(CPPIValues.class);

	private CPPIService service = CPPIService.getInstance();

	private CPPIPlanConfiguration conf;
	private BigDecimal portfolio; // F(t)
	private BigDecimal tsr; // TSR
	private BigDecimal floor; // f(t) Sicherer Teil um am Ende W(t) zu haben.
	private BigDecimal cushion; // Cushion
	private BigDecimal exposure; // W(t)
	private BigDecimal reserveasset;
	private BigDecimal partRiskyAsset; // X(r,t)
	private BigDecimal partRisklessAsset; // X(f,t)
	private BigDecimal previousStockPrice; // S(t-1)
	private BigDecimal actualStockPrice; // S(t)

	public CPPIValues(CPPIPlanConfiguration conf) {
		super();
		this.conf = conf;
		this.portfolio = conf.getPortfolio();

		this.floor = new BigDecimal(0);
		this.cushion = new BigDecimal(0);
		this.exposure = new BigDecimal(0);
		this.reserveasset = new BigDecimal(0);
		this.partRisklessAsset = new BigDecimal(0);
		this.partRiskyAsset = new BigDecimal(0);

		// TODO Implement me
		// some calculations
		// i.e. floor, cushion, exposure, ...

	}

	public CPPIValues(CPPIPlanConfiguration conf, BigDecimal portfolio, BigDecimal tsr, BigDecimal floor, BigDecimal cushion, BigDecimal exposure, BigDecimal reserveasset, BigDecimal partRiskyAsset, BigDecimal partRisklessAsset, BigDecimal previousStockPrice, BigDecimal actualStockPrice) {
		super();
		this.conf = conf;
		this.portfolio = portfolio;
		this.tsr = tsr;
		this.floor = floor;
		this.cushion = cushion;
		this.exposure = exposure;
		this.reserveasset = reserveasset;
		this.partRiskyAsset = partRiskyAsset;
		this.partRisklessAsset = partRisklessAsset;
		this.previousStockPrice = previousStockPrice;
		this.actualStockPrice = actualStockPrice;

		/*
		 * log.info("Configuration period: " + service.getCurrentPeriod() + ", Floor: " + floor.setScale(4, BigDecimal.ROUND_HALF_UP) + ", Cushion: " + cushion.setScale(4, BigDecimal.ROUND_HALF_UP) + ", Exposure: " + exposure.setScale(4, BigDecimal.ROUND_HALF_UP) + ", PartRisky: " + partRiskyAsset.setScale(4, BigDecimal.ROUND_HALF_UP) + ", PartRiskless: " + partRisklessAsset.setScale(4, BigDecimal.ROUND_HALF_UP) + ", NewPortfolio: " + portfolio.setScale(4, BigDecimal.ROUND_HALF_UP) + ", PreviousStockPrice: " + previousStockPrice.setScale(4, BigDecimal.ROUND_HALF_UP) + ", ActualStockPrice: " + actualStockPrice.setScale(4, BigDecimal.ROUND_HALF_UP));
		 */
	}

	private void init() {
		portfolio = conf.getPortfolio();
	}

	public CPPIPlanConfiguration getConf() {
		return conf;

	}

	public BigDecimal getPortfolio() {
		return portfolio;
	}

	public BigDecimal getTsr() {
		return tsr;
	}

	public BigDecimal getFloor() {
		return floor;
	}

	public BigDecimal getCushion() {
		return cushion;
	}

	public BigDecimal getExposure() {
		return exposure;
	}

	public BigDecimal getReserveasset() {
		return reserveasset;
	}

	public BigDecimal getRiskAssetValue() {
		return portfolio.subtract(reserveasset);
	}

	public BigDecimal getPartRiskyAsset() {
		return partRiskyAsset;
	}

	public BigDecimal getPartRisklessAsset() {
		return partRisklessAsset;
	}

	public BigDecimal getPreviousStockPrice() {
		return previousStockPrice;
	}

	public BigDecimal getActualStockPrice() {
		return actualStockPrice;
	}

	public void setConf(CPPIPlanConfiguration conf) {
		this.conf = conf;
		init();
	}

	public void setPortfolio(BigDecimal portfolio) {
		this.portfolio = portfolio;
	}

	public void setTsr(BigDecimal tsr) {
		this.tsr = tsr;
	}

	public void setFloor(BigDecimal floor) {
		this.floor = floor;
	}

	public void setCushion(BigDecimal cushion) {
		this.cushion = cushion;
	}

	public void setExposure(BigDecimal exposure) {
		this.exposure = exposure;
	}

	public void setReserveasset(BigDecimal reserveasset) {
		this.reserveasset = reserveasset;
	}

	public void setPartRiskyAsset(BigDecimal partRiskyAsset) {
		this.partRiskyAsset = partRiskyAsset;
	}

	public void setPartRisklessAsset(BigDecimal partRisklessAsset) {
		this.partRisklessAsset = partRisklessAsset;
	}

	public void setPreviousStockPrice(BigDecimal previousStockPrice) {
		this.previousStockPrice = previousStockPrice;
	}

	public void setActualStockPrice(BigDecimal actualStockPrice) {
		this.actualStockPrice = actualStockPrice;
	}

}
