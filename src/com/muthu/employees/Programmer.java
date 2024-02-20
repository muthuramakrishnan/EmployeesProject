package com.muthu.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee{
    private int locpd;
    int yoe;
    int iq;

    private static final String employeeDetailRegex = "locpd\\s*=\\s*(?<locpd>\\d+),\\s*yoe\\s*=\\s*(?<yoe>\\d+),\\s*iq\\s*=\\s*(?<iq>\\d+)";
    private static final Pattern employeeDetailPat = Pattern.compile(employeeDetailRegex);

    public Programmer(String employeeString){
        super();
        Matcher employeeDetailsMat = employeeDetailPat.matcher(employeeString);
        if(employeeDetailsMat.matches()){
            this.locpd = Integer.parseInt(employeeDetailsMat.group("locpd"));
            this.yoe = Integer.parseInt(employeeDetailsMat.group("yoe"));
            this.iq = Integer.parseInt(employeeDetailsMat.group("iq"));
        }
    }

    @Override
    public String toString() {
        return String.format("FirstName: %s, LastName: %s, Locpd: %d, Yoe: %d, IQ: %d", this.firstName, this.lastName, this.locpd, this.yoe, this.iq);
    }
}
