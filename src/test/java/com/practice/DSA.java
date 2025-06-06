package com.practice;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DSA {

    /**
     * 2 sum problem : find pair of integers which total to a target sum
     *
     *
     *
     * A guaranteed pair (e.g. array includes 60 and 40)
     * A version with duplicates
     * An array in sorted order for two-pointer technique
     * A version with negative numbers for edge case testing
     */
    @Test
    public void ques1() {
        int[] arr = {
                12, 45, 3, 22, 7, 98, 34, 56, 17, 89,
                31, 64, 21, 73, 50, 29, 14, 67, 38, 92,
                26, 5, 81, 19, 60, 8, 90, 13, 25, 77,
                42, 16, 58, 91, 33, 2, 76, 10, 30, 28,
                74, 4, 35, 48, 62, 71, 85, 6, 23, 18,
                11, 97, 46, 15, 43, 9, 24, 53, 66, 1,
                36, 32, 27, 52, 40, 44, 20, 65, 63, 57,
                39, 70, 49, 59, 55, 68, 41, 75, 61, 47,
                86, 78, 37, 93, 87, 80, 51, 99, 94, 100,
                69, 83, 95, 96, 79, 88, 82, 84, 72, 54
        };
        int target = 100;
        Map<Integer, Integer> result = new HashMap<>();
        //M-1 : Brute force O(n-squared)
        for(int j = 0 ; j< arr.length; j++){
            for(int num : arr) {
                if(arr[j] == target - num) {
                    result.put(arr[j], num);
                }
            }
        }
        System.out.println(result);

        //M-2 : streams using int stream WRONG !!
        Map<IntStream, List<Integer>> result1 =
        IntStream.range(0, arr.length).boxed()
                .collect(
                        Collectors.groupingBy(
                                x -> Arrays.stream(arr).filter( y -> target-y == arr[x]),
                                Collectors.toList()
                        )
                );
//        System.out.println(result1);

        //M-3 : standard hashSet based two sum, can return either true false or the pair
        Set<Integer> pairs2 = new HashSet<>();
        Set<Integer[]> pairs = new HashSet<>();
        for(int num : arr) {
            if(pairs2.contains(target-num)) {
                pairs.add(new Integer[]{num, target-num});
            }
            pairs2.add(num);
        }
        pairs.stream().forEach( arrNum -> System.out.println(Arrays.asList(arrNum)));


        int[] arrNum = {
                12, 45, 3, 22, 7, 98, 34, 56, 17, 89,
                31, 64, 21, 73, 50, 29, 14, 67, 38, 92,
                26, 5, 81, 19, 60, 8, 90, 13, 25, 77,
                42, 16, 58, 91, 33, 2, 76, 10, 30, 28,
                74, 4, 35, 48, 62, 71, 85, 6, 23, 18,
                11, 97, 46, 15, 43, 9, 24, 53, 66, 1,
                36, 32, 27, 52, 40, 44, 20, 65, 63, 57,
                39, 70, 49, 59, 55, 68, 41, 75, 61, 47,
                86, 78, 37, 93, 87, 80, 51, 99, 94, 100,
                69, 83, 95, 96, 79, 88, 82, 84, 72, 54, 50, 50, 56, 13, 89,3,12
        };

        /**
         *  Best solution for array with duplicates
         *  calculate complement first
         *  always have 2 sets
         *          -> one to store seen valyes, it can be some complement of a next num
         *          -> one to store unique pairs
         *          -> Arrays does not have hashcode & equals, so it use set<String> to have final result
         *  calc max & min of a number so that you have unique pairs only
         */
        int target100 = 100;

        Set<Integer> seenNums = new HashSet<>();
        Set<String> result2SumPairs = new HashSet<>();

        for(int num : arrNum ) {
            int complement = 100 - num;
            if( seenNums.contains(complement) ) {
                int max = Math.max(num, complement);
                int min = Math.min(num, complement);
                result2SumPairs.add("[" + min + ":" + max + "]");
            }
            seenNums.add(num);
        }

        System.out.println(result2SumPairs.size());
        System.out.println(result2SumPairs);

    }

    /**
     * find pair of numbers which do not have duplicates
     * And avoid repeated pair strings like (1,4) and (1,4)
     * Avoid (5,5) showing up:
     * Avoid (4,5) and (5,4) both showing up:
     */
    @Test
    public void ques2() {
        Set<Integer> uniqueArr1 = new HashSet<>(Arrays.asList(1,2));
        Set<Integer> uniqueArr2 = new HashSet<>(Arrays.asList(4,5,6));

        Set<String> numberPairs =  new HashSet<>();

        for(int num1 : uniqueArr1) {
            for(int num2: uniqueArr2) {
                if(num1 != num2) {
                    numberPairs.add("("+Math.max(num1, num2)+","+Math.min(num1, num2)+")");
                }
            }
        }
        System.out.println(numberPairs);
    }

    /**
     * To count pairs with a given sum from an array
     * arr = [1, 5, 7, -1, 5], K = 6
     * Valid pairs: (1, 5), (1, 5), (7, -1)
     * Count = 3
     *      ->for unique pairs use set
     *      ->use HM for unsorted arrays
     *      -> use 2 pointers for sorted arrays
     */
    @Test
    public void ques3() {
        //for unsorted O(n)
        int[] arr = {1, 5, 7, -1, 5};
        int target = 6;
        int occurences = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : arr) {
            int complement = target-num;
            if(map.containsKey(complement)){
                occurences = occurences + map.get(complement);
//                map.computeIfPresent(complement , (k,v) -> v+1);
                System.out.println("Pair: (" + num + ", " + complement + ")");
            }
//            map.computeIfAbsent(num, k -> 0);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        System.out.println(map);
        System.out.println(occurences);

    }

    @Test
    public void ques4() {}

    @Test
    public void ques5() {}

    @Test
    public void ques6() {}

    @Test
    public void ques7() {}

    @Test
    public void ques8() {}

    @Test
    public void ques9() {}

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


}
