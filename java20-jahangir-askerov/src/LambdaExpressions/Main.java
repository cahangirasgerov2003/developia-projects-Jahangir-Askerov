package LambdaExpressions;

public class Main {
    public static void main(String[] args) {
        doIt(food -> System.out.println("Cat eats " + food + "."));
    }

    public static void doIt(Eatable eatable){
        eatable.eat("milk");
    }
}
