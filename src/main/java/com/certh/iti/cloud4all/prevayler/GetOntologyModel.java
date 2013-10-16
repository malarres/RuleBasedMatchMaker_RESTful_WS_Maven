package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */
import org.prevayler.Query;

import java.util.Date;

public class GetOntologyModel implements Query<Root, OntologyModel> 
{
  private String identity;

  public GetOntologyModel(String identity) {
    this.identity = identity;
  }

  public OntologyModel query(Root prevalentSystem, Date executionTime) throws Exception {
    return prevalentSystem.getOntologyModels().get(identity);
  }
}