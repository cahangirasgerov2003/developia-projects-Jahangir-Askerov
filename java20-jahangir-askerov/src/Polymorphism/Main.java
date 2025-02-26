package Polymorphism;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.process(new Addition(), 12, 20));
    }
}
