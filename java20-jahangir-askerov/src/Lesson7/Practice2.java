package Lesson7;

public class Practice2 {
    public static void main(String[] args) {
        Car car1 = new Car("BMW");
        Car car2 = null;

        System.out.println(car1.carName);
    }
}

class Car {

    String carName;

    Car(String carName){
        this.carName = carName;
        System.out.println("This is default constructor !");
    }

}
