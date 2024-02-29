package com.muthu.employees;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements Comparable<Employee> {
    protected String firstName;
    protected String lastName;
    LocalDate dob;
    private static final String peopleRegex = "(?<firstName>\\w+),\\s*(?<lastName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<employeeDetails>.*)}.*";
    private static final Pattern pat = Pattern.compile(peopleRegex);
    private static Matcher peopleMat;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    protected Employee() {
        if (peopleMat.matches()) {
            this.firstName = peopleMat.group("firstName");
            this.lastName = peopleMat.group("lastName");
            String[] date = peopleMat.group("dob").split("/");
            dob = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        }
    }

    @Override
    public int compareTo(Employee o2) {
        return this.firstName.compareTo(o2.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee e1 = (Employee) obj;
        return this.getFirstName().equals(e1.getFirstName());
    }

    public static Employee createEmployee(String employeeString) {
        peopleMat = pat.matcher(employeeString);
        Employee employee = null;
        if (peopleMat.matches()) {
            String role = peopleMat.group("role");
            String employDetailString = peopleMat.group("employeeDetails");
            employee = switch (role) {
                case "Programmer" -> new Programmer(employDetailString);
                case "Manager" -> new Manager(employDetailString);
                default -> new DummyEmployee(employeeString);
            };
        }
        return employee;
    }

    public abstract String toString();

    public abstract int getSalary();

    private static class DummyEmployee extends Employee {
        protected DummyEmployee(String employeeString) {
            super();
        }

        public int getSalary() {
            return 0;
        }

        ;

        @Override
        public String toString() {
            return String.format("FirstName: %s, LastName: %s", this.firstName, this.lastName);
        }
    }

}
