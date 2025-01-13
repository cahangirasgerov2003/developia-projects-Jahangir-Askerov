package Lesson11HomeWork;

public class Address {
    private String country;
    private String city;
    private String street;

    public Address(String city, String country, String street) {
        this.city = city;
        this.country = country;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city + " city";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street + " street";
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
