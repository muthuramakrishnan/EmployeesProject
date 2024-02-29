package com.muthu.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee{
    private int orgSize;
    private int directReports;
    int yoe;
    int iq;

    private static final String managerDetailRegex = "orgSize\\s*=\\s*(?<orgSize>\\d+),\\s*directReports\\s*=\\s*(?<directReports>\\d+),\\s*yoe\\s*=\\s*(?<yoe>\\d+),\\s*iq\\s*=\\s*(?<iq>\\d+)";
    private static final Pattern managerDetailPat = Pattern.compile(managerDetailRegex);

    public Manager(String employeeString){
        super();
        Matcher managerMat = managerDetailPat.matcher(employeeString);
        if(managerMat.matches()){
            this.orgSize = Integer.parseInt(managerMat.group("orgSize"));
            this.directReports = Integer.parseInt(managerMat.group("directReports"));
            this.yoe = Integer.parseInt(managerMat.group("yoe"));
            this.iq = Integer.parseInt(managerMat.group("iq"));
        }
    }

    public int getSalary(){
        return orgSize * 2 + directReports * 3 + yoe * 10 + iq * 200;
    }

    @Override
    public String toString() {
        return String.format("FirstName: %s, LastName: %s, orgSize: %s, directReports: %s, yoe: %s, iq: %s", this.firstName, this.lastName, this.orgSize, this.directReports, this.yoe, this.iq);
    }
}
