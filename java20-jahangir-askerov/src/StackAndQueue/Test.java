package StackAndQueue;

import java.util.Stack;

public class Test {


    public static void check() {
        Stack<Character> characterStack = new Stack<>();

        characterStack.push('E');

        Character pop = characterStack.pop();
        System.out.println(pop);
    }

}
