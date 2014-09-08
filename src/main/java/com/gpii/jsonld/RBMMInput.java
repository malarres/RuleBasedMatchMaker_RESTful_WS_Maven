package com.gpii.jsonld;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author nkak
 */
public class RBMMInput
{
    @JsonProperty("input")
    private ArrayList<RBMMInputItem> input;
    
   
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("input")
    public ArrayList<RBMMInputItem> getInput() {
        return input;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("input")
    public void setInput(ArrayList<RBMMInputItem> input) {
        this.input = input;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }
}
