package com.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test1 {
    /**
     * find the occurrence of each word in a line
     */
    @Test
    public void ques8() {
        String line = "Hello rocky wonderful is a wonderful & marvelous marvelous moviewonderful";
       Map<String, Long> result = Arrays.asList(line.split(" ")).stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(result);
    }

    /**
     * find
     */
    @Test
    public void ques9() {
        String line = "Hello rocky wonderful is a wonderful & marvelous marvelous moviewonderful";
        String result = Arrays.asList(line.split(" ")).stream()
                .max(Comparator.comparing(String::length)).get();
        System.out.println(result);
    }

    @Test
    public void ques10() {}

    @Test
    public void ques11() {}

    @Test
    public void ques12() {}

    @Test
    public void ques13() {}

    @Test
    public void ques14() {}

    @Test
    public void ques15() {}

    @Test
    public void ques16() {}

    @Test
    public void ques17() {}

    @Test
    public void ques18() {}

    @Test
    public void ques19() {}

    @Test
    public void ques20() {}

    @Test
    public void ques21() {}

    @Test
    public void ques22() {}

    @Test
    public void ques23() {}

    @Test
    public void ques24() {}

    @Test
    public void ques25() {}

}
