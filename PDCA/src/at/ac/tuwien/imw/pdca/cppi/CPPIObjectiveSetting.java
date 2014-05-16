package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.ObjectiveSetting;

public class CPPIObjectiveSetting extends ObjectiveSetting<BigDecimal>{

  public BigDecimal value;
  
  public CPPIObjectiveSetting(BigDecimal value){
    this.value = value;
  }
}
