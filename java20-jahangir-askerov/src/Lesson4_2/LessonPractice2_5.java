package Lesson4_2;

import java.util.Scanner;

public class LessonPractice2_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zəhmət olmasa bir ədəd daxil edin !");
        String variable = sc.nextLine();
        int variable2 = Integer.parseInt(variable, 10);
        int sum = 0;
        sc.close();

        for (; variable2 != 0 ; variable2/=10){
            sum += variable2 % 10;
        }

        System.out.println("Sum = " + sum);

    }
}