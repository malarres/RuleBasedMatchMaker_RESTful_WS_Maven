package com.certh.iti.cloud4all.solutionsRegistry;

import java.util.ArrayList;


/**
 *
 * @author nkak
 */
public class SolutionsRegistryItem 
{
    String name;
    String id;

    ContextClass contexts;
    ArrayList<SettingsHandlersClass> settingsHandlers;
    LifecycleManagerClass lifecycleManager;

    public class ContextClass
    {
        ArrayList<OSClass> OS;
        
        public class OSClass
        {
            String id;
            String version;
        }
    }
    
    public class SettingsHandlersClass
    {
        String type;
        OptionsClass options;
        ArrayList<String> capabilities;
        CapabilitiesTransformationsClass capabilitiesTransformations;
        SettingsClass settings;
        
        public class CapabilitiesTransformationsClass
        {
            MagnificationClass Magnification;
            InvertClass Invert;
            ExpanderClass expander;
            
            public class MagnificationClass
            {
                ExpanderClass expander;
                DataTypeClass dataType;
            }
            
            public class InvertClass
            {
                ExpanderClass expander;
                DataTypeClass dataType;
            }
            
        }
    }
    
    class LifecycleManagerClass
    {
        ArrayList start;
        ArrayList stop;
    }
}
class OptionsClass
{
    String hKey;
    String path;
    
    OptionsItemClass mouse;
    OptionsItemClass caret;
    OptionsItemClass focus;
    
    class OptionsItemClass
    {
        String outputPath;
        OutputValueClass outputValue;
    }
    
}

class OutputValueClass
{
    int value;
    String dataType;
}

class ExpanderClass
{
    String type;
    String value;
    String inputPath;
    String outputPath;
    Double factor;
    
    OptionsClass options;
}

class DataTypeClass
{
    ExpanderClass expander;
}

class LifecycleManagerStartClass
{
    SetSettingsClass setSettings;
}

class SetSettingsClass
{
    String type;
    String command;
}