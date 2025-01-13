package Lesson11;

public class Average {
    public static void main(String[] args) {
         double result = Average.getAverage(124, 45, 12, 20, 10);
         System.out.println("Average is " + result);
    }

    public static double getAverage(double... array){
        double average;
        double sum = 0;
        for (double i:array){
            sum = sum + i;
        }

        average = sum / array.length;
        return average;
    }
}
