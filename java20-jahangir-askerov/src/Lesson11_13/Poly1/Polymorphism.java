package Lesson11_13.Poly1;

public class Polymorphism {
    public static void main(String[] args) {
        Car[] cars = {new Bmw(), new Audi()};
        for (Car car : cars) {
            if (car instanceof Bmw) {
                System.out.println(((Bmw) car).aboutBmw());
            }
            System.out.println(car.stop());
        }
    }
}
