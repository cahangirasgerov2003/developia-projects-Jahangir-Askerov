package LessonStrings;

import java.util.UUID;

public class ImageFormatter {
    public String formatImageName(String imageName) {
        String[] array = imageName.split("\\.");
        String formattedStr = "";
        if (array.length == 2) {
            array[0] = UUID.randomUUID().toString();
            formattedStr = String.join(".", array);
        }
        return formattedStr;
    }
}
