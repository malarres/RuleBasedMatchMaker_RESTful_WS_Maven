package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */
import org.prevayler.TransactionWithQuery;

import java.io.Serializable;
import java.util.Date;

public class DeleteOntologyModelTransaction implements TransactionWithQuery<Root, OntologyModel>, Serializable 
{
  /**
   * java.io.Serializable with a non changing serialVersionUID
   * will automatically handle backwards compatibility
   * if you add new non transient fields the the class.
   **/
  private static final long serialVersionUID = 1l;

  private String identity;

  public DeleteOntologyModelTransaction() {
  }

  public DeleteOntologyModelTransaction(String identity) {
    this.identity = identity;
  }

  public OntologyModel executeAndQuery(Root prevalentSystem, Date executionTime) throws Exception 
  {
    return prevalentSystem.getOntologyModels().remove(identity);
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }
}