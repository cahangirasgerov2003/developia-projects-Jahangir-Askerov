package ThreadsExample;

public class Example implements Runnable {
    int a;

    public void run() {
        System.out.println("New thread" + a + " will be started !");
    }

    public Example(int a) {
        this.a = a;
    }

    public void Example() {
        System.out.println("Hello");
    }
}
