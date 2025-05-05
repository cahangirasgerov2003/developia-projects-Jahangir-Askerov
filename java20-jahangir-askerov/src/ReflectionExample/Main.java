package ReflectionExample;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Add operation name : ");
            String operation = scanner.nextLine();
            System.out.println("Add number 1 : ");
            Integer num1 = scanner.nextInt();
            System.out.println("Add number 2 : ");
            Integer num2 = scanner.nextInt();

            Class<?> operationClass = Class.forName("ReflectionExample." + operation);

            Object obj = operationClass.getDeclaredConstructor().newInstance();

            Method method = operationClass.getDeclaredMethod("doIt", int.class, int.class);

            int result = (int) method.invoke(obj, num1, num2);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
