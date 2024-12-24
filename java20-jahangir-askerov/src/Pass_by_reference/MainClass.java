package Pass_by_reference;

public class MainClass {
    public static void main(String[] args) {
       Person person1 = new Person("Cahangir", "Askerov", 2, "xxx-xxx-xx-xx");
       SalaryCalculator salaryCalculator = new SalaryCalculator();
       salaryCalculator.getSalary(person1);
       person1.printInfo();
    }
}
