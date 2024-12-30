package Lesson8;

public class ArrayDemo3 {
    public static void main(String[] args) {
        // Or use Array.sort();
        int[] array = {12, 3, 55, 1, 123};
        int minimumElement = array[0];
        int maximumElement = array[0];
        for(int i : array){
           if( i < minimumElement ){
               minimumElement = i;
           }
           if( i > maximumElement ){
               maximumElement = i;
           }
        }
        System.out.println("Minimum element = " + minimumElement);
        System.out.println("Maximum element = " + maximumElement);
    }
}
