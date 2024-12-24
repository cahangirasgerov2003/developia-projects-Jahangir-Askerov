package Lesson7;

public class PracticeStatic {
    public static void main(String[] args) {
        User.changeName();
        String result = User.name;
        User user = new User();
        User user2 = new User();
    }
}

class User {
    static String name;
    int age;

    static {
        System.out.println("Name : " + name);
    }

    {
        System.out.println(age);
    }

    {
        System.out.println(age + "))");
    }

    public static void changeName(){
        name = "Ceko";
        System.out.println("Name : " + name);
    }
}
