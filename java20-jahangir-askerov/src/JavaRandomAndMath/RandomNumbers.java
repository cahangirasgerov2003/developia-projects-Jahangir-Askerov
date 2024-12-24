package JavaRandomAndMath;

import java.util.Random;

public class RandomNumbers {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        System.out.println(randomNumber);
    }
}
