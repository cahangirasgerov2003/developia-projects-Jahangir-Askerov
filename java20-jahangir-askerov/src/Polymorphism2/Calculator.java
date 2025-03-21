package Polymorphism2;

public class Calculator {
    public Integer process(Operation operation, Integer x, Integer y){
        return operation.calculate(x, y);
    }
}
