package Lesson11_13.Upcasting;

public class Teacher extends Person {
    String degree;

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Degree = " + degree);
    }

    public Teacher(String name) {
        super(name);
    }
}
