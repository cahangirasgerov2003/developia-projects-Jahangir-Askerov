package Lesson6;

import javax.lang.model.element.Name;

public class ClassAndObjectAndConstructors {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee("Cahangir");
        Employee employee3 = new Employee("Cahangir", "Askerov");
        Employee employee4 = new Employee("Cahangir", "xxx-xxx-xx-xx", "Goranboy");
    }
}

class Employee {
    int id;
    String name;
    String surname;
    String phone;
    String address;
    int salary;
    Employee(){
        System.out.println("It is default constructor");
        System.out.println("--------------------------");
    }

    Employee(String name){
        System.out.println("Name : " + name);
        System.out.println("--------------------------");
        this.name = name;
    }

    Employee(String name, String surname){
        System.out.println("Name : " + name);
        System.out.println("Surname : " + surname);
        System.out.println("--------------------------");
        this.name = name;
        this.surname = surname;
    }

    Employee(String name, String phone, String address){
        System.out.println("Name : " + name);
        System.out.println("Phone number : " + phone);
        System.out.println("Address : " + address);
        System.out.println("--------------------------");
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

}
