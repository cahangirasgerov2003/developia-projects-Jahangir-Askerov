package Lesson8;

public class ArrayDemo4 {
    public static void main(String[] args) {
        int[] array = {12, 3, 23, 1, 212};
        for(int i : array){
            if(i % 2 == 0){
                System.out.println("Even : " + i);
                continue;
            }
            System.out.println("Odd : " + i);
        }
    }
}
