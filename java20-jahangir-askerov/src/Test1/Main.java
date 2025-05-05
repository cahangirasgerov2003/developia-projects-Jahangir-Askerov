package Test1;

public class Main {
    static Person person2;

    public static void main(String[] args) {
//        String str1 = new String("Ali");
//        String str2 = new String("Ali");
        Address address = new Address("Baku");
        Person person1 = new Person("Ali", address);
//        Person person3 = person1;

//        System.out.println("Same : " + person3.equals(person1));
        try {
            person2 = (Person) person1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        person1.setAddress(new Address("Ganja"));
        System.out.println(person1.getAddress().getCity());
        System.out.println(person2.getAddress().getCity());

//        System.out.println(person1.equals(person2));

    }
}
