package Lesson11_13.SealedClasses;

sealed public interface Animal permits Dog {
    public abstract void makeSound();
}
