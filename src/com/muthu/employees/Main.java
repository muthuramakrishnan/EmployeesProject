package com.muthu.employees;

public class Main {
    public static void main(String[] args) {
        String people = """
                Muthu, Krish, 15/08/1996, Programmer, {locpd=2000, yoe=5, iq=140}
                Rajesh, Sahadevan, 10/07/1998, Programmer, {locpd=1500, yoe=3, iq=150}
                Jake, Chen, 19/08/1990, Manager, {orgSize=10, directReports=3, yoe=10, iq=140}
                MKrish, RamaKrish, 01/01/1992, Manager, {orgSize=7, directReports=7, yoe=8, iq=150}
                Gayathri, Srini, 01/01/1997, Analyst, {projectCount=3, yoe=5, iq=140}
                Ram, Krish, 01/01/1960, CEO, {avgStockPrice=300}
                """;

        people.lines().forEach(line -> {
            Employee e = Employee.createEmployee(line);
            System.out.println(e.toString());
        });
    }
}
