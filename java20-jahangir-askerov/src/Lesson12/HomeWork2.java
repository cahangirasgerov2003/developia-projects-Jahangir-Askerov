package Lesson12;

public class HomeWork2 {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double result1 = calculator.add(12, 3.5, 4);
        System.out.println(result1);

        ExtendedCalculator extendedCalculator = new ExtendedCalculator();
        double result2 = extendedCalculator.add(12, 3.5, 4);
        System.out.println(result1);
        double result3 = extendedCalculator.subtraction(3.4, 2);
        System.out.println(result3);
    }
}

class Calculator {
    public double add(double... array) {
        double sum = 0;
        for (double i : array) sum += i;
        return sum;
    }
}

class ExtendedCalculator extends Calculator {
    @Override
    public double add(double... array) {
        return super.add(array);
    }

    public double subtraction(double input1, double input2) {
        return input1 - input2;
    }
}
