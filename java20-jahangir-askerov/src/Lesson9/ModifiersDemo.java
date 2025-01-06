package Lesson9;

public class ModifiersDemo {
    public static void main(String[] args) {
        Person person = new Person("Cahangir", "Askerov");
        Person person2 = new Person("Black");
        person.setAge(23);
        person.setEarning(1111);
        person.sayName();
        person.saySurname();
        person.getAge();
        person.getEarning();
        person.setGender('M');
    }
}
