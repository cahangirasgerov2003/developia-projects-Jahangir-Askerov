package Test1;

public class Address implements Cloneable, Comparable<String> {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(String city) {
        return this.getCity().compareTo(city);
    }
}
