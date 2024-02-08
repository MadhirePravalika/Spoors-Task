package com.codeinfinity.spoorstask;

public class Employee {
     int id;
     String name;
     String address;
     String department;
     String gender;
     boolean isFresher;

    public Employee(int id, String name, String address, String department, String gender, boolean isFresher) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.department = department;
        this.gender = gender;
        this.isFresher = isFresher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public String getGender() {
        return gender;
    }

    public boolean isFresher() {
        return isFresher;
    }
}
