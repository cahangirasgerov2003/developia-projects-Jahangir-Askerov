package Polymorphism;

public class Calculator {
    public int process(Oparation oparation, int x, int y) {
        return oparation.calculate(x, y);
    }
}
