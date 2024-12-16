package Lesson4_2;

public class LessonPractice2_11 {
    public static void main(String[] args) {
        int result = 0;
        int w;
       for(double i = 2; result < 20; i++){
           w=0;
          for ( int j = 2; j <= i/2; j++){
             if(i % j == 0){
                 w++;
                 break;
             }
          }
          if (w == 0){
              System.out.println(i);
              result++;
          }
       }
    }
}
