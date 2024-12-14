package Lesson4;

import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Zəhmət olmasa adınızı giriniz !");
        String name = sc.nextLine();
        System.out.println("Zəhmət olmasa soyadınızı giriniz !");
        String surname = sc2.nextLine();
        System.out.println("You are welcome " + surname + " " + name );

        sc.close();
        sc2.close();

    }
}
