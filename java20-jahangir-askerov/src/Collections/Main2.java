package Collections;

import java.util.HashSet;
import java.util.Set;

public class Main2 {
    public static void main(String[] args) {

        Set<String> cars = new HashSet<>();

        cars.add("BMW");
        cars.add("Nissan");
        cars.add("Ford");
        cars.add("Nissan");

        cars.forEach(System.out::println);

    }
}
