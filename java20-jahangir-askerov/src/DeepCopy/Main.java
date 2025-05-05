package DeepCopy;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Baku");
        Person person = new Person("Cahangir", address);

        try {
            Person person1 = (Person) person.clone();
            person1.setAddress(new Address("Ganja"));
            System.out.println(person.getAddress().getCity() + " : Person1");
            System.out.println(person1.getAddress().getCity() + " : Person2");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
