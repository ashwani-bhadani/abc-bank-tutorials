package com.coreJava;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Step 1: Private static inner class (Bill Pugh method for lazy + thread-safe)
 *  or private static instance variable so that it belongs to class
 * Step 2: Private constructor to prevent instantiation using reflection safe 2nd instance
 * Step 3: Public access method for instance creation
 * Step 4: readResolve for serialization-safe singleton always return same instance
 */
public class SingletonEmployee implements Serializable {

    // Step 1: Private static instance (not initialized yet)
    private static SingletonEmployee emp ;

    // Step 2: Private constructor
    public SingletonEmployee() {
        //protection against reflection
        if(emp != null) throw new RuntimeException("Reflection constructor call not supported");
    }

    // Step 3: Public static method with synchronized block to get instance
    public static SingletonEmployee getInstance(){
        if (emp == null){
            synchronized (SingletonEmployee.class){
                if(emp == null) emp = new SingletonEmployee(); //can't use this here as it is a static context
            }
        }
        return emp;
        // Yes, you can use this keyword inside a Singleton class,
        // but you can't use it to access the singleton instance from
        // a static context (like getInstance()), and you should not
        // use it to create or manage the singleton instance.
        // this refers to the current object.
        // In static methods (like getInstance()), there is no current object,
        // since they're class-level, not object-level.
    }

    // Step 4: readResolve to maintain singleton on deserialization
    protected Object readResolve() throws ObjectStreamException{
        return getInstance();
    }


}
