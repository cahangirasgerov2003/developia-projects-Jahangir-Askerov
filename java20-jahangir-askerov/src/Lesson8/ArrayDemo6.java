package Lesson8;

import java.util.Arrays;

public class ArrayDemo6 {
    public static void main(String[] args) {
        int[] array = {12, 3, 122, 24, 1};
        Arrays.sort(array);

        for(int i=0; i < array.length/2; i++){
            int wrapper = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = wrapper;
        }

        System.out.println(Arrays.toString(array));

    }
}
