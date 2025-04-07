package MethodReferences;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        List<? super String> names = new ArrayList<>();
//        names.add("Adil");
//        Object name0 = names.getFirst();
//        System.out.println(name0);

        List<String> names = new ArrayList<>();
        names.add("Cahangir");
        names.add("Kamran");
        names.add("Anar");
        names.add("Ali");

//        Imperative programming

//        for (String name : names) {
//            System.out.println(name);
//        }

//        Declarative programming

//        names.forEach(System.out::println);
//        Method reference
//        System.out::println

//        Predicate functional interface

        names.stream().map(String::toUpperCase).filter(name -> name.startsWith("A")).forEach(System.out::println);
    }
}
