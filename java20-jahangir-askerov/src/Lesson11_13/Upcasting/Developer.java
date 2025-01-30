package Lesson11_13.Upcasting;

public class Developer extends Person {
    double salary;

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Salary = " + salary);

    }

    public Developer(String name) {
        super(name);
    }
}
