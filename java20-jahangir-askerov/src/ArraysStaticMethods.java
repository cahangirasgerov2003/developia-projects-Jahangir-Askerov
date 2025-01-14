import java.util.Arrays;

public class ArraysStaticMethods {
    public static void main(String[] args) {
//        Arrays.sort and Arrays.binarySearch
//        int[] array = new int[]{12, 34, 55, 66, 11};
//        Arrays.sort(array);
//        int index = Arrays.binarySearch(array, 66);
//        System.out.println(index);

//        Arrays.copyOf
//        int[] array = {132, 44, 55, 11, 2};
//        int[] copyArray = Arrays.copyOf(array, 2);
//        System.out.println(Arrays.toString(copyArray));

//        Arrays.copyOfRange
//        int[] array = {132, 44, 55, 11, 2};
//        int[] newArray = Arrays.copyOfRange(array, 1, 4);
//        System.out.println(Arrays.toString(newArray));

//        Arrays.fill
//        int[] array = {12, 44, 5};
//        Arrays.fill(array, 12);
//        System.out.println(Arrays.toString(array));

//        Arrays.equals
//        int[] array1 = {1, 23, 44, 32};
//        int[] array2 = {1, 23, 44, 32};
//        boolean same = Arrays.equals(array1, array2);
//        System.out.println(same);

//        Arrays.deepEquals
        int[][] twoDimensionalArray1 = {{1, 2, 4}, {1, 2, 4, 444}};
        int[][] twoDimensionalArray2 = {{1, 2, 4}, {1, 2, 4, 44}};
        boolean same = Arrays.deepEquals(twoDimensionalArray2, twoDimensionalArray1);
        System.out.println(same);
    }
}
