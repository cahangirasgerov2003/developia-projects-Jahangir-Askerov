package Comparison;

public class Main {
    public static void main(String[] args) {
        University university = new University("ADNSU");
        university.addTeacher("Anar");
        System.out.println(university.teacher.getName());

        university = null;

    }
}
