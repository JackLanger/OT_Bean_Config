package org.rodias.jl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Configuration{

    public Configuration(){
        PatchProcessor.INSTANCE.register(this);
    }
}

