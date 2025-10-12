package AnonymousClasses;

public class Main {
    private static Eatable cat;

    public static void main(String[] args) {
//        Eatable cat = food -> System.out.println("The cat drinks " + food);

        Eatable cat = new Eatable() {
            @Override
            public void eat(String food) {
                System.out.println("The cat drinks " + food);
            }
        };

        cat.eat("milk");
    }
}
