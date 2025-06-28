package com.coreJava;

public class ReflectionTesting {

    private String printHelloPrivate(String name){
        return "Hello from private method "+name;
    }

    private static String printHelloStatic(String name){
        return "Hello from private static method "+name;
    }

    public String printHelloPublic(){
        return "Hello from public method";
    }
}
