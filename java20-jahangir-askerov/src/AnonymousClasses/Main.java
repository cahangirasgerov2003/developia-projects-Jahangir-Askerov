package AnonymousClasses;

public class Main {
    public static void main(String[] args) {
        Eatable cat = new Eatable(){
            @Override
            public void eat(String food) {
                System.out.println("The cat drinks " + food);
            }
        };

        cat.eat("milk");
    }
}
