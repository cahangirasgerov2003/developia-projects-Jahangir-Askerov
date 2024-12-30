package Lesson8;

public class ArrayDemo2 {
    public static void main(String[] args) {
        int[] array = {12, 34, 1, 124};
        double average = 0;
        int sum = 0;
        for(int i : array){
            sum += i;
        }
        average = ( double )sum / array.length;
        System.out.println("Avarage = " + average);
    }
}
