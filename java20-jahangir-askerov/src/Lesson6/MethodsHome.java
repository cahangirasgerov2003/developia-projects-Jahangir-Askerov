package Lesson6;

public class MethodsHome {
    public static void main(String[] args) {
       MethodsHome obj = new MethodsHome();
        obj.printNumbers(12, 13);
        obj.printNumbers(1, 10);
        obj.printNumbers(22, 30);
    }

    void printNumbers(int begin, int end){
        if(end > begin+1){
            for(int i=begin+1; i<end; i++){
                System.out.println(i);
            }
        }else{
            System.out.println("Sizin argument kimi daxil etdiyiniz ededler dogru deyil !");
        }
        System.out.println("-------------------------");
    }

}