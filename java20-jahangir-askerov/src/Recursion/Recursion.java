package Recursion;

import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hansı mövqeydə olan fibonacci ədədini görmək istəyirsən ?");
        int position =  Integer.parseInt(scanner.nextLine());
        scanner.close();

        int result = Fibonacci.getFibonacci(position - 1);
        System.out.println(position+"th fibonacci number = " + result);
    }
}

class Fibonacci{
   static int getFibonacci(int param){
       if(param <= 0){
           return 0;
       }else if(param == 1){
           return 1;
       }else{
           return getFibonacci(param-2) + getFibonacci(param-1);
       }
   }
}
