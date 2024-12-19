package Lesson5;

public class ClassAndObject {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.id = 1;
        person1.name = "Cahangir";
        person1.surname = "Askerov";
        person1.age = 22;
        person1.phone = "+xxx xxx xx xx";

        Person person2 = new Person();
        person2.id = 2;
        person2.name = "Kamran";
        person2.surname = "Askerov";
        person2.age = 21;
        person2.phone = "+xxx xxx xx xx";

        person1.sayAboutPerson();
        person2.sayAboutPerson();
    }
}

class Person {
    int id;
    String name;
    String surname;
    int age;
    String phone;

    void sayAboutPerson() {
        System.out.println("Id : " + id);
        System.out.println("Name : " + name + " " + surname);
        System.out.println("Age : " + age);
        System.out.println("Phone number : " + phone);
        System.out.println("--------------------------");
    }
}
