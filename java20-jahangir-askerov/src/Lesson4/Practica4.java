package Lesson4;

public class Practica4 {
    public static void main(String[] args) {
        int[] arguments = {12, 23, 43, 42, 10};
        for (int i=0; i < arguments.length; i++){
            if(arguments[i] == 43){
                break;
            }
            System.out.println(arguments[i]);
        }
    }
}
