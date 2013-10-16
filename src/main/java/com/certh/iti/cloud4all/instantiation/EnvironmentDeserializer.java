package com.certh.iti.cloud4all.instantiation;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author nkak
 */
public class EnvironmentDeserializer implements JsonDeserializer<Environment> 
{

  @Override
  public Environment deserialize(final JsonElement json, 
        final Type typeOfT, 
        final JsonDeserializationContext context) 
            throws JsonParseException 
  {

    Environment tmpEnvironment = new Environment();
    // Parsing will be done here.
    return tmpEnvironment;
  }
}
