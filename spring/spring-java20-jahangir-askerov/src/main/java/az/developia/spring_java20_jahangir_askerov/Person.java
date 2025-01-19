package az.developia.spring_java20_jahangir_askerov;

public class Person {
    String name;
    String surName;

    public Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "Person {" +
                "Name: '" + this.name + '\'' +
                ", Surname='" + this.surName + '\'' +
                '}';
    }
}
