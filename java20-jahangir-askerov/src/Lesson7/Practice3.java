package Lesson7;

public class Practice3 {
    public static void main(String[] args) {
       Multiply multiply = new Multiply();
       System.out.println(multiply.getMultiply(12, 34));
    }
}

class Multiply{
    int getMultiply(int a, int b){
        return a*b;
    }
}
