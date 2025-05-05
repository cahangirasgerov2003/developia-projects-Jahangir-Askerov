package ThreadsExample;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Example(1));
        Thread thread2 = new Thread(new Example(2));

        thread2.setPriority(Thread.MIN_PRIORITY);
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.start();
        thread1.start();

        Example example = new Example(12);
        example.Example();
    }
}
