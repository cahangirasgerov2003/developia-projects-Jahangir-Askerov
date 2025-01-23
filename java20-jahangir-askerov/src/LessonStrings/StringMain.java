package LessonStrings;

public class StringMain {
    public static void main(String[] args) {
        ImageFormatter image = new ImageFormatter();
        Matches phone = new Matches();
        String resultCheckingImage = image.formatImageName("Apple.img");
        boolean resultCheckingPhone = phone.checkPhoneNumber("050-222-2222");
        if(resultCheckingImage.isEmpty()){
            System.out.println("This image name not supported !");
        }else{
            System.out.println("Formatted image name = " + resultCheckingImage);
        }

        if(resultCheckingPhone){
            System.out.println("This phone number is supported !");
        }else{
            System.out.println("Check phone number and try again !");
        }


        StringsFormat info = new StringsFormat();
        info.sayAboutUs();
    }
}
