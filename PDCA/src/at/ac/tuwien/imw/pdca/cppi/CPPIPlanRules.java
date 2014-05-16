package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIPlanRules implements PlanningRules<Object>{

	@Override
	public Object applyPlanningRules() {
		CPPIService.getInstance().setPlanConfiguration(new CPPIPlanConfiguration());
		return null; //warum soll hier auf einmal etwas zurückgegeben werden?
	}

}
