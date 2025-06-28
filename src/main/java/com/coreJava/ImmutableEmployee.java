package com.coreJava;

import java.util.Date;

/**
 * Final class (optional, but prevents subclassing).
 * All fields are private and final.
 * No setters.
 * Only getters (no way to change the state).
 * If a field is a mutable object, return a defensive copy in the getter.
 * Once an object is created, its state cannot change.
 * You can have multiple instances, each with a fixed state.
 * final means you cannot reassign the reference variable, not that the object will live forever.
 * <p>
 * Q1: Fixed state means we can't change values, but can we change memory content if reference is final?
 * Yes, if the object is mutable, a final reference can still modify the internal state.
 * final means the reference cannot point to another object, but the object itself can be modified, if it's mutable.
 * <br>
 * Q2: If we declare an ArrayList as final, can we still modify it?
 * Yes.Declaring an ArrayList as final only prevents changing the reference, not the contents.
 * </p>
 */
public final class ImmutableEmployee {
    private final String name ;
    private final int age;
    private final Date joinDate; //mutable

    public ImmutableEmployee(String name, int age, Date joinDate ){
        this.name = name;
        this.age = age;
        // creating a defensive copy to ensure immutability
        this.joinDate = new Date(joinDate.getTime());
    }

    //no setters only getters
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public Date getJoinDate(){
        //return a copy to preserve immutability
        return new Date(this.joinDate.getTime());
    }

}
