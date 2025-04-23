package Collections2;

import java.util.ArrayList;
import java.util.List;

public class ArrayLists {

    List<String> array = new ArrayList<>();

    @SuppressWarnings("unchecked")
    void shallowCopy() {
        array.add("Ceko");
        ArrayList<String> arrayCopy = (ArrayList<String>) ((ArrayList<?>) array).clone();
    }

}
