package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Root implements Serializable {

  /**
   * java.io.Serializable with a non changing serialVersionUID
   * will automatically handle backwards compatibility
   * if you add new non transient fields the the class.
   **/
  private static final long serialVersionUID = 1l;

  private Map<String, OntologyModel> ontModels = new HashMap<String, OntologyModel>();


  public Map<String, OntologyModel> getOntologyModels() {
    return ontModels;
  }

  public void setOntologyModels(Map<String, OntologyModel> tmpOntModels) {
    this.ontModels = tmpOntModels;
  }
}