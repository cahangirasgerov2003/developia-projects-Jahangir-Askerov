package Record;

public class Record {
    public static void main(String[] args) {
        Person person = new Person("Cahangir", 22);
        int result = person.age();
        System.out.println("Your age is : " + result);
        System.out.println("Your age + 5 is : " + person.addFiveToAge());
    }
}
