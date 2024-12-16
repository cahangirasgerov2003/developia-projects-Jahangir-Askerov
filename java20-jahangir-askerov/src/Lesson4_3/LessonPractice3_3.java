package Lesson4_3;

import java.util.Scanner;

public class LessonPractice3_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zəhmət olmasa bir tam ədəd daxil edin !");
        int variable = Integer.parseInt(sc.nextLine());
        int max = 0;
        sc.close();

        for (; variable != 0; variable = variable / 10){
            if(max < variable % 10){
               max = variable % 10;
            }
        }

        System.out.println("Max = " + max);

    }
}
