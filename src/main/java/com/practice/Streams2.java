package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Streams2 {

    public static void main(String[] args) {

        //reduce() method
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "grapes", "orange");
        //List.of(e1 ...) returns an unmodifiable/immutable list
        // & Arrays.asList() returns fixed size, cannot add/remove but modify elements
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        //identity: initial seed value which will be returned if stream is empty
        //2nd part is a lambda
        //a= accumulator, final addition list is stored in this
        //n = next number
        int sum = numbers.stream().reduce(0, (a,n)-> a+n);
        System.out.println("sum: "+ sum);

        String concatRes = fruits.stream().reduce("We stock ",(str1, str2) -> str1 + ", "+ str2);
        System.out.println(concatRes);

        List<Student> students = Student.populateStudent();
        System.out.println(students);

    }

}

class Student {
    Integer stdId;
    String stdName;
    Double marks;

    Student(Integer stdId, String stdName, Double marks) {
        this.stdId=stdId;
        this.stdName=stdName;
        this.marks=marks;
    }

    static List<Student> populateStudent() {
        List<Student> students = new ArrayList<>();
        int i = 50;
        Double marks = Math.random() * 100;
        while(i>0) {
            students.add(new Student(i,"Student "+i,marks));
            i--;
        }
        return students;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stdId=" + stdId +
                ", stdName='" + stdName + '\'' +
                ", marks=" + marks +
                '}';
    }
}
