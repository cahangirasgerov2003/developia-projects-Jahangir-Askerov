package Switch;

public class Switch {
    public static void main(String[] args) {
      String result = StudentPoint.getResult(-22);
      System.out.println("Your student result is : " + result);
    }
}

class StudentPoint{
    public static String getResult(int point){
        return switch (point) {
            case 1 -> "Very bad";
            case 2 -> "Bad";
            case 3 -> "Not good";
            case 4 -> "Good";
            case 5 -> "Excellent";
            default -> "Your point not true, check again please";
        };
    }
}
