package AnonymousClasses2;

public class Main {
    public static void main(String[] args) {
//         Eatable dog = new Eatable(){
//             @Override
//             public void Run(String who){
//                 System.out.println("The " + who + " runs !");
//             }
//         };

        Eatable dog = (who) -> System.out.println("The" + who + " runs !");

        dog.Run("Wolfy");
    }
}
