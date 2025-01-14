package ArraysPractice;

public class VariableArguments {
    public static void main(String[] args) {
        sayNames("Əli", "Cahangir", "Aygün");
    }

    public static void sayNames(String... names){
        int counter = 1;
        StringBuilder result = new StringBuilder();
        for (String name : names){
            if(counter != names.length){
                result.append(name).append(", ");
            }else{
                result.append(name).append(".");
            }
            counter++;
        }

        System.out.println(result);

    }
}
