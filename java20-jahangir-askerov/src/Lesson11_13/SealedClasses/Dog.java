package Lesson11_13.SealedClasses;

final public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("woof ... ");
    }
}
