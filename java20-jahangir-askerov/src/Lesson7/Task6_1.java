package Lesson7;

import java.util.Random;

public class Task6_1 {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(21) + 30;

        System.out.println("Random number [30-50] : " + randomNumber);
    }
}
