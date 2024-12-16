package Lesson4_2;

import java.util.Scanner;

public class LessonPractice2_6 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Please enter a natural number !");
            int variable = Integer.parseInt(sc.nextLine(), 10);
            boolean isPrime = true;
            if (variable == 1) {
                System.out.println("1 is neither a prime nor a composite number !");
            } else if (variable <= 0) {
                System.out.println("Please enter a natural number !");
            } else {

                int i = 2;

                do {
                    if (variable % i == 0 && variable != 2) {
                        isPrime = false;
                        break;
                    }
                    i++;
                } while (i < variable);

                if (!isPrime) {
                    System.out.println("The number you entered is a composite number !");
                } else {
                    System.out.println("The number you entered is simple !");
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter a natural number !");
            System.out.println("Error message : " + e.getMessage());
        }
    }
}
