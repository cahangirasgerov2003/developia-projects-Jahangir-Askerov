package RepeatingLesson7;

public class Classes {
    public static void main(String[] args){
        var users = new Users("Cahangir", 22);
        String message = users.sayHello();
        System.out.println(message);
    }
}

class Users {
    String name;
    int age;
    String sayHello(){
        return "Hello " + name;
    }
    public Users(String name, int age){
        this.name = name;
        this.age = age;
    }
}
