package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vectors {
    final List<String> names = Collections.synchronizedList(new ArrayList<>());

    public void addListElement() {
        names.add("Caa");
        names.add("Abb");
        names.add("Cbb");
        names.add("Acc");
        names.add("Baa");
        names.add("Bbb");

        sayListElement();
    }

    public void sayListElement() {
        synchronized (names) {
            names.forEach(System.out::println);
        }
    }

}
