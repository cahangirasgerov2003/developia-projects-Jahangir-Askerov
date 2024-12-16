package Lesson4_2;

import java.util.Scanner;

public class LessonPractice2_9 {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        System.out.println("Please enter a natural number !");
        int naturalNumber = Integer.parseInt(sc.nextLine());
        if(naturalNumber % 2 == 0){
            System.out.println("This number is even !");
        }else{
            System.out.println("This number is odd !");
        }
    }
}
