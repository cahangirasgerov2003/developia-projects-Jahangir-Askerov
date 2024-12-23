package Lesson7;

public class Practice {
    public static void main(String[] args) {
        Person person1 = new Person("Cahangir", 22);

        String aboutMe = person1.sayAbout();
        System.out.println(aboutMe);
    }
}

class Person {
    String name;
    int age;

    String sayAbout(){
      return "Hello my name is " + name + " and i am " + age + " years old!";
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}
