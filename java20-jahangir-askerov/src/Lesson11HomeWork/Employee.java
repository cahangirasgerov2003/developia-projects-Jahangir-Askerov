package Lesson11HomeWork;

public class Employee extends Person{
    double salary;
    String department;
    String userName;
    String password;

    public Employee(String name, String surName, int age, String phone, Address address, double salary, String department, String password, String userName) {
        super(name, surName, age, phone, address);
        this.salary = salary;
        this.department = department;
        this.password = password;
        this.userName = userName;
    }

    public void printInfo(){
        super.printInfo();
        System.out.println("Salary : " + salary);
        System.out.println("Department : " + department);
        System.out.println("User name : " + userName);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
