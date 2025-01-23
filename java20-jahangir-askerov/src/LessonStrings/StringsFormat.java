package LessonStrings;

public class StringsFormat {
    String name1 = "Cahangir";
    String name2 = "Kamran";
    int age1 = 22;
    int age2 = 21;
    boolean isStudent1 = true;
    boolean isStudent2 = true;
    double examResult1 = 616.4;
    char examResult2 = 'B';
    public void sayAboutUs(){
        String result1 = String.format("Salam mən %s-əm, mənim bir qardaşım var, adı %s-dır. Mənim %d yaşım var, qardaşımın isə %d yaşı var.", name1, name2, age1, age2);
        String result2 = String.format("Mən tələbəyəm = %b", isStudent1);
        String result3 = String.format("Qardaşım tələbədir = %b", isStudent2);
        String result4 = String.format("Mənim qəbuldan topladığım bal = %f", examResult1);
        String result5 = String.format("Qardaşımın son imtahandan aldığı qiymət = %c", examResult2);
        String result = String.format("%s %s %s %s %s", result1, result2, result3, result4, result5);
        System.out.println("""
                About me :
                """ + result);
    }
}
