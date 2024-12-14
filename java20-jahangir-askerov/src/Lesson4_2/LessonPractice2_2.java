package Lesson4_2;

import java.util.Scanner;

public class LessonPractice2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Eded1");
        String variable1 =  sc.nextLine();
        System.out.println("Eded2");
        String variable2 = sc2.nextLine();

        int sum = Integer.parseInt(variable2) + Integer.parseInt(variable1);

        System.out.println(sum);

        sc.close();
        sc2.close();

    }
}
