package Lesson7;

public class Task6_4 {
    public static void main(String[] args) {
        double fractionalNumber = 2.595;

        long roundedNumber = Math.round(fractionalNumber * 10);

        double result = ((double)(roundedNumber)/10);

        System.out.println(result);
    }
}
