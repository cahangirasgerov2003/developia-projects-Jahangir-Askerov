package Enum;

public class Order {
    public static void main(String[] args) {
        Product product1 = new Product("Watch", Age.youngPeople);
        Product product2 = new Product("Phone", Age.children);
        System.out.println("Product 1: " + product1.productName + ", Age Group: " + product1.age.getDescription());
        System.out.println("Product 2: " + product2.productName + ", Age Group: " + product2.age.getDescription());
    }
}
