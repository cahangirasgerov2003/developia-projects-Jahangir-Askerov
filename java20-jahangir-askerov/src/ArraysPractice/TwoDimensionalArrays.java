package ArraysPractice;

public class TwoDimensionalArrays {
    public static void main(String[] args) {
        int[][] twoDimensionalArray = new int[][]{{1, 22, 333}, {11, 33, 444}};
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray[i].length; j++) {
                System.out.println("Array[" + i + "][" + j + "] = " + twoDimensionalArray[i][j]);
            }
        }
    }
}
