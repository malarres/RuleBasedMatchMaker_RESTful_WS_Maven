package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */
import com.hp.hpl.jena.ontology.OntModel;
import com.certh.iti.cloud4all.ontology.Solution;
import java.io.InputStream;
import java.io.OutputStream;
import org.prevayler.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UpdateOntModelTransaction implements Serializable, Transaction<Root> 
{
  /**
   * java.io.Serializable with a non changing serialVersionUID
   * will automatically handle backwards compatibility
   * if you add new non transient fields the the class.
   **/
  private static final long serialVersionUID = 1l;

  private String identity;
  private ArrayList<String> allInstances_Solution;

  public UpdateOntModelTransaction() {
  }

  public UpdateOntModelTransaction(String identity, ArrayList<String> tmpAllInstances_Solution) {
    this.identity = identity;
    this.allInstances_Solution = tmpAllInstances_Solution;
  }

  public void executeOn(Root prevalentSystem, Date executionTime) {
    prevalentSystem.getOntologyModels().get(identity).setOwlTextAndSolutionsArray(allInstances_Solution);
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }
}