package Interview;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Ali");
        Person person2 = new Person("Ali");

//        Person person3 = new Person(person1);
        Person person3 = new Person("Ali");

        System.out.println(person1.equals(person3));

        Person person4;

        try {
            person4 = (Person) person2.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error");
        }

        person4 = null;

    }
}
