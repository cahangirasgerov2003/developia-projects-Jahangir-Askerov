package LessonStrings;

public class Matches {
    String pattern = "[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}";
    public boolean checkPhoneNumber(String phoneNumber){
        return phoneNumber.matches(pattern);
    }
}
