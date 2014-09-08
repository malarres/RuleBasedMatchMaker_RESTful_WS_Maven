package com.gpii.jsonld;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author nkak
 */
public class RBMMInputItem<T> 
{
    @JsonProperty("inputName")
    private String inputName;
    
    @JsonProperty("inputBody")
    private T inputBody;
    
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("inputName")
    public String getInputName() {
        return inputName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("inputName")
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
    
     /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("inputBody")
    public T getInputBody() {
        return inputBody;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("inputBody")
    public void setInputBody(T inputBody) 
    {
        this.inputBody = inputBody;
    }
}
