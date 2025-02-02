package Lesson11_13.ReflectionAPI;

import java.lang.reflect.Field;

public class ReflectionAPI {
    public static void main(String[] args) {
        Person person = new Person("Jahangir", "Askerov", 22);
        Class<?> personClass = person.getClass();
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + ": " + field.get(person));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(field);
        }
    }
}
