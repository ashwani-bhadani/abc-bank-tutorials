package com.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Streams1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,254,35,465,3,543,6,734,8,965,0,554,45,42,23,43,65,76,834,32,23,545,566,10);

        List<Emp> empList = Arrays.asList(
                new Emp(23,"Akash"),
                new Emp(37,"Prakash"),
                new Emp(89,"Kartik"),
                new Emp(56,"Vikram"),
                new Emp(36,"Nitin"),
                new Emp(312,"Pooja"),
                new Emp(32,"Sunita")
        );

        empList.stream().distinct().forEach(System.out::println);

        //sort based on number ascending
        numbers.stream().sorted().forEach(System.out::println);

        //sort employee based on if, default ascending (e1.id - e2.id), here descending (e2.id - e1.id)
        empList.stream().sorted((e1, e2) -> e2.empId - e1.empId)
                .forEach(emp -> System.out.println("id: " +emp.empId + ", name: "+ emp.empName ));

        //map(Only transform) & flatMap(transform as well as flatten nested DS) difference
        List<List<String>> employees = Arrays.asList(
                Arrays.asList("Parvati","Priya","Akanksha"), //female winners
                Arrays.asList("Dhrubojyoti","Praveen") //male winners
        );

        Stream<TreeSet<String>> empSet  = employees.stream().map(list -> new TreeSet<>(list))
                .peek(System.out::println); //will not print as terminal operation not called yet, streams are lazy initialized util terminal operation is called.
        Stream<String> empListFlat = employees.stream().flatMap( listWinners -> listWinners.stream())
                .peek(item -> System.out.println("emp = " +item));
        long count = empSet.count();
        long count2 = empListFlat.count();
    }
}

class Emp {
    Integer empId;
    String empName;

    Emp(Integer empId, String empName) {
        this.empId=empId;
        this.empName=empName;
    }
}
