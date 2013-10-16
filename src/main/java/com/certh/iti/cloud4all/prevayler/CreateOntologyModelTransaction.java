package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */

import com.hp.hpl.jena.ontology.OntModel;
import org.prevayler.TransactionWithQuery;

import java.io.Serializable;
import java.util.Date;

public class CreateOntologyModelTransaction  implements TransactionWithQuery<Root, OntologyModel>, Serializable
{
   private static final long serialVersionUID = 1l;

  private String identity;

  public CreateOntologyModelTransaction() {
  }

  public CreateOntologyModelTransaction(String identity) {
    this.identity = identity;
  }

  public OntologyModel executeAndQuery(Root prevalentSystem, Date executionTime) throws Exception {
    OntologyModel entity = new OntologyModel();
    entity.setIdentity(identity);
    prevalentSystem.getOntologyModels().put(entity.getIdentity(), entity);
    return entity;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }
}
