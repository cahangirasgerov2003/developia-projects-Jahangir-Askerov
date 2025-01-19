package RepeatingLesson9;

public class JavaModifierTypes {
    static int counter = 0;
    String name = "Cahangir";
    int age = 22;

    JavaModifierTypes() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        JavaModifierTypes person1 = new JavaModifierTypes();
        JavaModifierTypes person2 = new JavaModifierTypes();
        JavaModifierTypes person3 = new JavaModifierTypes();

        System.out.println(JavaModifierTypes.getCounter());
    }
}
