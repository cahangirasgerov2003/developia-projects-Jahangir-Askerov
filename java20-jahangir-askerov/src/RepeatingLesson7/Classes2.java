package RepeatingLesson7;

public class Classes2 {
    public static void main(String[] args) {
        Employees employees = new Employees("Cahangir");
    }
}

class Employees {
    String name;
    static String Admin;
    static {
      Admin = "no";
    }
    Employees(String name){
        this.name = name;
    }
}
