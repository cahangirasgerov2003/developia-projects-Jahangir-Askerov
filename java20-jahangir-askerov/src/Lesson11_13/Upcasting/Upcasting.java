package Lesson11_13.Upcasting;

public class Upcasting {
    public static void main(String[] args) {
        Person[] persons = {new Developer("Cahangir"), new Teacher("Kamran")}; // Upcasting
        for (Person person : persons) {
            person.printInfo();
            if (person instanceof Developer developer) {
                developer.salary = 444.5;
                System.out.println("Salary = " + developer.salary);
            } else if (person instanceof Teacher teacher) {
                teacher.degree = "Bachelor";
                System.out.println("Degree = " + teacher.degree);
            } else {
                System.out.println("Check your code and try again");
            }
        }
    }
}
