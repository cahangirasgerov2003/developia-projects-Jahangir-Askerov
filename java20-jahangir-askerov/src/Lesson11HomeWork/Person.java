package Lesson11HomeWork;

public class Person {
    private String name;
    private String surName;
    private int age;
    private String phone;
    private Address address;

    public Person(String name, String surName, int age, String phone, Address address) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    public void printInfo(){
        System.out.println("I am " + surName + " " + name);
        System.out.println("I am " + age + " years old");
        System.out.println("You can contact me : " + phone);
        String addressResult = ((address.getCountry() != null && address.getCity() != null && address.getStreet() != null) ?  address.getStreet() + "," + address.getCity() + "," + address.getCountry() + "." : "");
        System.out.println("I live at : " + addressResult);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
