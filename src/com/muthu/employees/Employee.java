package com.muthu.employees;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee {
    protected String firstName;
    protected String lastName;
    LocalDate dob;
    private static final String peopleRegex = "(?<firstName>\\w+),\\s*(?<lastName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<employeeDetails>.*)}.*";
    private static final Pattern pat = Pattern.compile(peopleRegex);
    private static Matcher peopleMat;

    protected Employee() {
        if (peopleMat.matches()) {
            this.firstName = peopleMat.group("firstName");
            this.lastName = peopleMat.group("lastName");
            String[] date = peopleMat.group("dob").split("/");
            dob = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        }
    }

    public static Employee createEmployee(String employeeString){
        peopleMat = pat.matcher(employeeString);
        Employee employee = null;
        if(peopleMat.matches()){
            String role = peopleMat.group("role");
            employee = switch (role){
                case "Programmer" -> new Programmer(peopleMat.group("employeeDetails"));
                default -> new DummyEmployee(employeeString);
            };
        }
        return employee;
    }

    public abstract String toString();

    private static class DummyEmployee extends Employee{
        protected DummyEmployee(String employeeString) {
            super();
        }

        @Override
        public String toString() {
            return String.format("FirstName: %s, LastName: %s", this.firstName, this.lastName);
        }
    }

}
