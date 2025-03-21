package Polymorphism2;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Integer resp = calculator.process(new Multiplication(), 12, 4);
        System.out.println(resp);
    }
}
