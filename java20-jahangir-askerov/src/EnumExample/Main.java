package EnumExample;

public class Main {
    public static void main(String[] args) {
        if (Gender.male.getGender().equals("man")) {
            System.out.println("This is a man !");
        } else {
            System.out.println("This is a woman !");
        }
    }
}
