package Collections2;

import java.util.Set;
import java.util.TreeSet;

public class Collection {
    Set<String> set = new TreeSet<>();

    public void addElement() {
        set.add("Banana");
        set.add("Apple");
        set.add("Mango");
    }

    public void showElements() {
        System.out.println(set);
        set.forEach(System.out::println);
    }

//    HashSet ucun .add(null) yazmaq olur amma TreeSet-de olmur cunki, TreeSet compareTo() metodunu calisdirir
}
