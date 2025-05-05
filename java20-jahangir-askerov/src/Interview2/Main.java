package Interview2;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstanse();
        System.out.println(singleton.getWater(10));

        ComparatorExample example = new ComparatorExample();
        example.setList();
        example.sortList();
        example.getListElements();

    }
}
