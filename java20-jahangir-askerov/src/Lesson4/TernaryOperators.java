package Lesson4;

public class TernaryOperators {
    public static void main(String[] args) {
       int age, experience;
       age = 22;
       experience = 2;

       String result = age >= 18 && experience >=1 ? "Siz bu iş üçün uyğunsunuz !" : "Üzgünüz amma bizim iş tələblərimizi ödəmirsiniz !";

       System.out.println(result);
    }
}
