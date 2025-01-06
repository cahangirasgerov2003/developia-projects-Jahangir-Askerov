package Lesson9;

public class Person {
    //  Public Access Modifier
    public String name;
    public String surname;
    String hearColor;
    static String country;
    char gender;

    // Static code block
    static {
        country = "AZE";
    }

    //  Private Access Modifier
    private double earning;
    private int age;

    //  Public constructor
    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    //  Default constructor
    Person(){
        System.out.println("This is default constructor !");
    }

    //  Public constructor
    Person(String hairColor){
        this.hearColor = hairColor;
    }

    //  Methods for public Access Modifier
    public void sayName(){
        System.out.println("My name is " + this.name);
    }

    public void saySurname(){
        System.out.println("My surname is " + this.surname);
    }

    //  Methods for private Access Modifier
    public void setEarning(double earning){
        this.earning = earning;
    }

    public void setAge(int age){
        this.age = age;
    }

    //  Methods for private Access Modifier
    public void getEarning(){
        System.out.println("My earning is " + this.earning);
    }

    public void getAge(){
        System.out.println("My age is " + this.age);
    }

    public final void setGender(char gender){
        this.gender = gender;
    }
}
