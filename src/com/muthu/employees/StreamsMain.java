package com.muthu.employees;

import java.util.Comparator;
import java.util.stream.Collectors;

public class StreamsMain {
    public static void main(String[] args) {
        String people = """
                Muthu, Krish, 15/08/1996, Programmer, {locpd=2000, yoe=5, iq=140}
                Muthu, Krish, 15/08/1996, Programmer, {locpd=2000, yoe=5, iq=140}
                Muthu, Krish, 15/08/1996, Programmer, {locpd=2000, yoe=5, iq=140}
                Rajesh, Sahadevan, 10/07/1998, Programmer, {locpd=1500, yoe=3, iq=150}
                Jake, Chen, 19/08/1990, Manager, {orgSize=10, directReports=3, yoe=10, iq=140}
                MKrish, RamaKrish, 01/01/1992, Manager, {orgSize=7, directReports=7, yoe=8, iq=150}
                Gayathri, Srini, 01/01/1997, Analyst, {projectCount=3, yoe=5, iq=140}
                Ram, Krish, 01/01/1960, CEO, {avgStockPrice=300}        
                """;

        int sum = people.lines()
                .map(Employee::createEmployee)
                .mapToInt(Employee::getSalary)
                .sum();

        System.out.println("total salary = " + sum);

        System.out.println("\n Comparing by firstName");
        people.lines().map(Employee::createEmployee).sorted()
                .forEach(System.out::println);

        System.out.println("\n Comparing by lastName");
        people.lines().map(Employee::createEmployee).sorted((o1, o2) -> {
            return o1.getLastName().compareTo(o2.getLastName());
        }).forEach(System.out::println);

        System.out.println("\n Comparing by lastName");
        people.lines().map(Employee::createEmployee).sorted(Comparator.comparing(Employee::getLastName)).forEach(System.out::println);

        //Filtering
        System.out.println("\n Filtering only name equals Muthu");
        people.lines().map(Employee::createEmployee)
                .filter(e -> {
                    return e.getFirstName().equals("Muthu");
                }).forEach(System.out::println);

        //distinct method depends on equals and hashcode method
        System.out.println("\n Distinct items");
        people.lines().map(Employee::createEmployee)
                .distinct().forEach(System.out::println);

        //Collector
        System.out.println("\n Distinct items set");
        people.lines().map(Employee::createEmployee)
                .collect(Collectors.toSet()).stream().forEach(System.out::println);

    }
}
