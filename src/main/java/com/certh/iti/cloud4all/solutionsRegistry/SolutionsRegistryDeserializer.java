package com.certh.iti.cloud4all.solutionsRegistry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author nkak
 */
public class SolutionsRegistryDeserializer implements JsonDeserializer<SolutionsRegistryItem> 
{

  @Override
  public SolutionsRegistryItem deserialize(final JsonElement json, 
        final Type typeOfT, 
        final JsonDeserializationContext context) 
            throws JsonParseException 
  {
    SolutionsRegistryItem tmpSolutionsRegistry = new SolutionsRegistryItem();
    // Parsing will be done here.
    return tmpSolutionsRegistry;
  }
}