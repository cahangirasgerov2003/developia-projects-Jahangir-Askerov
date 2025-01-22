package LessonStrings;

public class StringMain {
    public static void main(String[] args) {
        ImageFormatter image = new ImageFormatter();
        String result = image.formatImageName("Apple.img");
        if(result.isEmpty()){
            System.out.println("This image name not supported !");
        }else{
            System.out.println("Formatted image name = " + result);
        }
    }
}
