package Record;

public record Person(String name, int age) {
   int addFiveToAge(){
       return age+5;
   }
}
