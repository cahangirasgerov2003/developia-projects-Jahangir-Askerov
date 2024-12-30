package Lesson8;

public class ArrayDemo5 {
    public static void main(String[] args) {
        int[] array = {12, 3, 23, 1, 2, 212};
        for(int i : array){
            int counter = 0;
            for(int j = 2; j < i/2; j++){
                if(i % j == 0){
                    counter++;
                    break;
                }
            }
            if(counter == 1){
                System.out.println("Murekkeb eded : " + i);
            } else if ( i != 1) {
                System.out.println("Sade eded : " + i);
            } else {
                System.out.println("Daxil etdiyiniz eded " + i + "-dir ve o ne sade ne de murekkebdir");
            }
        }
    }
}
