package com.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class PracticeQues1 {


    /* 1. find th word having highest length in a sentence
//reurns the first longest word occurence
     */
    @Test
    public void ques1() {
        String line = "Quick brown fox jumps over the lazy hare";
        String result = Arrays.asList(line.split(" ")).stream()
                .max(Comparator.comparing(String::length)).get();
        System.out.println("longest word : " + result);
    }


    /* 2. remove duplicates from stream & return in same order
     */
//convert string into int stream => each int means a character
    @Test
    public void ques2() {
        String words = "dabcadefg";
        words.chars().distinct().mapToObj(w -> (char) w)
                .forEach(System.out::print);
    }
}