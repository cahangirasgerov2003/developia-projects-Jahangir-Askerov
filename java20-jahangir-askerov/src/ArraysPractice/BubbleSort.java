package ArraysPractice;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] unorderedArray = new int[]{12, 3, 45, 12, 21, 1};
        System.out.println("Unordered array : " + Arrays.toString(unorderedArray));
        int[] sortedArray = sortArray(unorderedArray);
        System.out.println("Sorted array : " + Arrays.toString(sortedArray));
    }

    public static int[] sortArray(int[] unorderedArray) {
        int lengthArray = unorderedArray.length;
        int temp;
        boolean isSorted = true;
        for (int i = 0; i < lengthArray - 1; i++) {
            for (int j = lengthArray - 1; j > i; j--) {
                if (unorderedArray[j] > unorderedArray[j - 1]) {
                    temp = unorderedArray[j - 1];
                    unorderedArray[j - 1] = unorderedArray[j];
                    unorderedArray[j] = temp;
                    isSorted = false;
                }
            }

            if (isSorted) break;

        }

        return unorderedArray;
    }
}
