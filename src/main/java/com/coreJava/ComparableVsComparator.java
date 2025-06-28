package com.coreJava;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparable = Alone :: has compareTo(obj) :: Implemented inside the class
 * Comparator = others :: has compare(obj1,obj2) :: Implemented outside the class
 */
public class ComparableVsComparator {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(3, "Alice", 70000),
                new Employee(1, "Bob", 50000),
                new Employee(2, "Charlie", 60000)
        );

        //sort natural
        Collections.sort(employees); //uses comparable
        employees.forEach(System.out::println);

        Collections.sort(employees, new NameThenSalaryComparator());
        employees.forEach(System.out::println);

        //lambda
        employees.sort(
                (e1,e2) -> e1.getName().compareTo(e2.getName())
        );
        employees.forEach(System.out::println);
    }
}

class Employee implements Comparable<Employee> {
    private String name;
    private int id;
    private double salary;

    public Employee(int id, String name, double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    //getters
    String getName(){ return this.name;}
    int getId() {return this.id;}
    double getSalary() {return this.salary;}

    //sorting by name
//    @Override
//    public int compareTo(Employee emp) {
//        return this.name.compareTo(emp.getName());
//    }
//
    //sorting by id
    @Override
    public int compareTo(Employee emp) {
        return Integer.compare(this.id, emp.getId());
    }
    @Override
    public String toString() {
        return id + " - " + name + " - " + salary;
    }
}

/**
 * you can create a custom Comparator to sort by multiple fields —
 * First by name (ascending)
 * Then by salary (descending)
 * Sort by name, then by salary (desc)
 */
class NameThenSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1 , Employee e2){
        int nameCompare  = e1.getName().compareTo(e2.getName());
        if(nameCompare != 0) {
            return nameCompare; //sort only by name first
        } else {
            //sort by salary if name are equal
            return Double.compare(e2.getSalary() , e1.getSalary());
            //descending if e2 - e1 = negative => e1 is larger & e2 should come first
            //ascending if e1 - e2 = negative => e2 is larger & would come first
        }
    }
}

/**
 * Descending: e2 - e1
 * -> Agar result < 0, iska matlab e1 > e2 => e1 pehle aayega
 *
 * Ascending: e1 - e2
 * -> Agar result < 0, iska matlab e1 < e2 => e1 pehle aayega
 *
 * TIP: Negative result => first object comes before second
 */
class EmpIdComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2){
        return e1.getId() - e2.getId(); //ascending
    }
}
