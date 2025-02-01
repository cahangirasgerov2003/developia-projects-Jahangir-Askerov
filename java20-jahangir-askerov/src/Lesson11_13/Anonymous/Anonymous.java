package Lesson11_13.Anonymous;

public class Anonymous {
    public static void main(String[] args) {
        Greeting greeting = new Greeting() {
            @Override
            public String sayHello() {
                return "You are welcome !";
            }
        };

        String result = greeting.sayHello();
        System.out.println(result);
    }
}
