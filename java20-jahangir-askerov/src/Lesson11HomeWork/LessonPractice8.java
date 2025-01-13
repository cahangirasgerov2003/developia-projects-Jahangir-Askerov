package Lesson11HomeWork;

public class LessonPractice8 {
    public static void main(String[] args) {
        Address address = new Address("Baku", "Azarbaijan", "Alizada");
        Person person1 = new Person("Cahangir", "Əsgərov", 22, "xxx-xxx-xx-xx", address);
        Employee employee = new Employee("Cahangir", "Əsgərov", 22, "xxx-xxx-xx-xx", address, 1111, "IT", "1234", "Admin1234");

        person1.printInfo();
        System.out.println("------------------------");
        employee.printInfo();
    }
}
