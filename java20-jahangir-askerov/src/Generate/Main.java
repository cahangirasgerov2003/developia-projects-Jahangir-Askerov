package Generate;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = List.of(12, 32, 1, 43);

        Predicate<Integer> predicate = n -> n > 10;

        list.stream().filter(predicate::Test).forEach(System.out::println);

        Stream.generate(() -> "Hello").limit(3).forEach(System.out::println);
    }
}
