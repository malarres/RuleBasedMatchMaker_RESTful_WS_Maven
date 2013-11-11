package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class Shortcut 
{
    public String name_EN;
    public String name_DE;
    public String name_GR;
    public String name_SP;
    public String category;
    public String desktopLayout;
    public String laptopLayout;
    
    
    public Shortcut()
    {
        name_EN = "";
        name_DE = "";
        name_GR = "";
        name_SP = "";
        category = "";
        desktopLayout = "";
        laptopLayout = "";
    }
    
    @Override
    public String toString()
    {
        String res = "";
        res = "name (EN): " + name_EN + 
                ", name (DE) : " + name_DE + 
                ", name (GR) : " + name_GR + 
                ", name (SP) : " + name_SP + 
                ", category: " + category + 
                ", desktopLayout: " + desktopLayout + 
                ", laptopLayout: " + laptopLayout;
        return res;
    }
}
