package com.slmn.patient_management.io;

import java.lang.reflect.InvocationTargetException;

public abstract class GSONSerialisable {
     public abstract Object cast(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
