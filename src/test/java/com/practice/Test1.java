package com.practice;

import org.junit.Test;

public class Test1 {

    @Test
    public void ques1() {
        System.out.println("Hello World!");
        int[] arrNums = {1,2,5,4,9 };
        int sum = 14;
        int k = 0;
        for( int i =0, j=arrNums.length-1 ; i < arrNums.length-1 ; i++, j--) {
            if( sum == arrNums[i] + arrNums[j])
                System.out.println( "" + arrNums[i] + arrNums[j] );
            else
                System.out.println("none") ;
        }

    }


}
