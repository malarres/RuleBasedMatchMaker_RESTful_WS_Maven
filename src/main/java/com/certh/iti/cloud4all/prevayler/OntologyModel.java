package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */

import com.hp.hpl.jena.ontology.OntModel;
import com.certh.iti.cloud4all.ontology.Solution;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class OntologyModel implements Serializable
{
    /**
   * java.io.Serializable with a non changing serialVersionUID
   * will automatically handle backwards compatibility
   * if you add new non transient fields the the class.
   **/
  private static final long serialVersionUID = 1l;

  private String identity;
  private ArrayList<String> allInstances_Solution;
  
  public OntologyModel()
  {
  }

  public ArrayList<String> getSolutionsArray() {
    return allInstances_Solution;
  }

  public void setOwlTextAndSolutionsArray(ArrayList<String> tmpAllInstances_Solution) {
    this.allInstances_Solution = tmpAllInstances_Solution;
  }  

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String tmpIdentity) {
    this.identity = tmpIdentity;
  }

  @Override
  public boolean equals(Object o) 
  {
    if (this == o) 
        return true;
    if (o == null || getClass() != o.getClass()) 
        return false;

    OntologyModel person = (OntologyModel) o;

    if (identity != null ? !identity.equals(person.identity) : person.identity != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return identity != null ? identity.hashCode() : 0;
  }
}
