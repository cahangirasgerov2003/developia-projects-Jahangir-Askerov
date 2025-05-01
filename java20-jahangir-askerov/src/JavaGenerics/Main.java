package JavaGenerics;

public class Main {
    public static void main(String[] args) {
        Example<String> example = new Example<>();
        example.setToy("Car");
        System.out.println(example.getToy());
    }
}
