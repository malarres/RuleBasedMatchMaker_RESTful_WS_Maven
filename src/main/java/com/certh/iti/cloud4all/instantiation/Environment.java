package com.certh.iti.cloud4all.instantiation;

import java.util.ArrayList;


/**
 *
 * @author nkak
 */
public class Environment 
{
    OperSystem OS;
    WindowManager windowManager;
    ArrayList<Solution> solutions;
}
class OperSystem
{
    String id;
    String version;
}

class WindowManager
{
    String id;
    String version;
}

class Solution
{
    String id;
    String version;
}
