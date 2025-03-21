package Polymorphism2;

public class Addition implements Operation {

    @Override
    public Integer calculate(Integer x, Integer y) {
        return x+y;
    }

}
