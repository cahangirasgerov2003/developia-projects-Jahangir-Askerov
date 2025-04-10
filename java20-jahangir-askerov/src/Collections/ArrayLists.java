package Collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayLists {
    List<String> names = new ArrayList<>();
    ArrayList<String> names2;

    public void addListElement() {
        names.add("Caa");
        names.add("Abb");
        names.add("Cbb");
        names.add("Acc");
        names.add("Baa");
        names.add("Bbb");
        this.cloneList();
    }

    public void cloneList() {
        names2 = new ArrayList<>(names);
        sayListElement();
    }

    private void sayListElement() {
        names2.forEach(System.out::println);
    }
}
