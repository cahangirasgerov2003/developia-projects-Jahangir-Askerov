package Recursion;

import java.util.Scanner;

public class FibonacciWithSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hansı mövqeydə olan fibonacci ədədini görmək istəyirsən ?");
        int promiseNumber = Integer.parseInt(scanner.nextLine());
        scanner.close();
        RecursionWithSwitch recursionWithSwitch = new RecursionWithSwitch();
        int result = recursionWithSwitch.getFibonacci(promiseNumber);
        System.out.println(promiseNumber + " mövqeydəki fibonacci ədədi : " + result);
    }
}

class RecursionWithSwitch {
       int getFibonacci(int param){
           if( param <= 0 ){
               System.out.println("0-dan kiçik və ya bərabər index istifadə etmək olmaz !");
               return -1;
           }
           return switch (param) {
               case 1 -> 0;
               case 2 -> 1;
               default -> getFibonacci(param - 2) + getFibonacci(param - 1);
           };
       }
}
