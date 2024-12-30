package Lesson8;

public class ArrayDemo {
    public static void main(String[] args) {
       int[] array = {12, 34, 1, 124};
       int sum = 0;
       for (int i = 0; i< array.length; i++){
           sum+=array[i];
       }
       System.out.println("Sum = "+ sum);
    }
}
