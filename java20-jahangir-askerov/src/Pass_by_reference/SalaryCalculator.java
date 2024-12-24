package Pass_by_reference;

public class SalaryCalculator {
    void getSalary(Person person){
        person.salary = person.experienceYear * 500;
    }
}
