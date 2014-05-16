package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.DoRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIDoRules implements DoRules {
	
	private CPPIService service = CPPIService.getInstance();
	private CPPIValues values;

	@Override
	public void applyDoRules() {
		
		values = new CPPIValues(service.getPlanConfiguration());
	}

}
