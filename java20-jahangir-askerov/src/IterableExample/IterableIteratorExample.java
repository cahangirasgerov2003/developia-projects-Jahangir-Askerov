package IterableExample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class IterableIteratorExample {
    List<String> list = new ArrayList<>(List.of("Ali", "Ceko", "Emil"));

    public void showListElements() {

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
