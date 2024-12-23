package Lesson6;

public class MainClass {
    public static void main(String[] args) {
        Student s1 = new Student("Adil", 14);
        Student s2 = new Student("Cavid", 17);
        Student s3 = s2;
        Student s4 = s1;
        s1 = null;
        s4 = null;
        s4 = new Student("Həsən", 11);
        System.out.println("s1 : " + s1);
        System.out.println("s2 : " + s2);
        System.out.println("s3 : " + s3);
        System.out.println("s4 : " + s4);
    }
}
