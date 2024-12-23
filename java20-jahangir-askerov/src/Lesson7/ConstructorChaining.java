package Lesson7;

public class ConstructorChaining {
    public static void main(String[] args) {
        Sum sum = new Sum();
        int result = sum.getSum();

        System.out.println("Sum = " + result);
    }
}

class Sum{
    int number1;
    int number2;
    Sum(){
        this(12, 24);
        System.out.println("Default constructor");
    }

    Sum(int number1, int number2){
       this.number1 = number1;
       this.number2 = number2;
       System.out.println("Other constructor");
    }

    public int getSum(){
        return this.number1 + this.number2;
    }
}
