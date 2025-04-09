package com.streams;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PracticeQues1 {

    /**
     * 1. find th word having highest length in a sentence
     * reurns the first longest word occurence
     */
    @Test
    public void ques1() {
        String line = "Quick brown fox jumps over the lazy hare";
        String result = Arrays.asList(line.split(" ")).stream()
                .max(Comparator.comparing(String::length)).get();
        System.out.println("longest word : " + result);
    }


    /**
     * 2. remove duplicates from stream & return in same order
     *      convert string into int stream => each int means a character
     *      reduce is a terminal operation
     */
    @Test
    public void ques2() {
        String words = "dabcadefg";
        words.chars().distinct().mapToObj(w -> (char) w) //M-1
                .forEach(System.out::print);
        String result = Arrays.stream(words.split("")).distinct().collect(Collectors.joining()); //M-2
        String res = Arrays.stream(words.split("")).distinct().reduce("",(x,y )-> x+y);  //M-3
        System.out.println("\n"+result);
        System.out.println(res);
    }

    /**3. given a sentence, find word having the nth highest length & then the length of that word too
     * N= 2nd
     */
    @Test
    public void ques3() {
        String line = "I am learning stream in java 8";
        String res = Arrays.stream(line.split(" "))
                .sorted(Comparator.comparing(String::length).reversed()) //returns ascending order
                .skip(1).findFirst().get(); //skip 1st to get 2nd largest
        System.out.println("2nd Longest word "+res);

        //finding length of 2nd longest word
        int res2 = Arrays.stream(line.split(" "))
                .map( w -> w.length())
                .sorted(Comparator.reverseOrder()) //to get descending order
                .skip(1).findFirst().get();
        System.out.println("length : "+ res2);
    }

    /**4. given a sentence, find the #occurences of each word
     *
     */
    @Test
    public void ques4() {
        String line = "I am learning stream in java 8";
        Map<String, Long> occurences = Arrays.stream(line.split(" ")) //give list where u can't add more values
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                //forms the key, this function returns the arg itself (t) -> return t
                                Collectors.counting()//forms the value
                        )
                ); //returns a map of string , long
        System.out.println(occurences);
    }


    /**5. given a sentence, find the word with specified num of vowels
     * find word having exactly 2 vowels
     */
    @Test
    public void ques5() {
        String line = "I am learning stream in java 8";
        Arrays.stream(line.split(" ")).filter(
                word -> word.replaceAll("[^aeiouAEIOU]", "")   //anything other than in regex will be replaced
                        .length() == 2 //checking the length to str left with us
        ).forEach(System.out::println);
    }

    /**6. given a int array divide into 2 lists one having even nos & other having odd
     *
     */
    @Test
    public void ques6() {
        int[] arr = {23,33,44,55,66,77,88,99,11,22,34,67};
        //boxed() -> a Stream consistent of the elements of this stream, each boxed to an Integer
        List<Integer> numbers = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Map<Boolean,List<Integer>> result = numbers.stream().collect(Collectors.groupingBy(
                x -> x%2==0   , Collectors.toList()
        )); //1st part is Boolean & 2nd is list of accepted & rejected, need to take out the list only

        result.entrySet().stream().forEach(x -> System.out.println(x.getValue() ));

    }



    /**
     *
     */
    @Test
    public void ques() {

    }



}