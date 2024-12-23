package Lesson6;

public class CircleCalculator {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.radius = 10;
        Calculator calculator = new Calculator();
        calculator.calculateCircleLength(circle);
        System.out.println(circle.length);
    }
}

class Circle {
    double radius;
    double length;
}

class Calculator {
    void calculateCircleLength(Circle circle){
      circle.length = 2 * Math.PI * circle.radius;
    }
}
