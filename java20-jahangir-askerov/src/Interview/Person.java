package Interview;

public class Person implements Cloneable {

    public static class Person1 {
        public int age = 10;
    }

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(Person person) {
        this.setName(person.getName());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        return this.getName().equals(person.getName());
    }

    public Object clone() throws CloneNotSupportedException {
//        Person person = new Person("Ali");
//        Person.Person1 p = new Person.Person1();
//        System.out.println(p.age);
        return super.clone();
    }
}
