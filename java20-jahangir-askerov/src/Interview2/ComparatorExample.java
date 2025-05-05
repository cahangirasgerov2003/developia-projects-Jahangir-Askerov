package Interview2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    List<String> list;

    public void setList() {
        list = new ArrayList<>(List.of("Salam", "Sagol", "Ali"));
    }

    public void sortList() {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void getListElements() {
        list.forEach(System.out::println);
    }
}
