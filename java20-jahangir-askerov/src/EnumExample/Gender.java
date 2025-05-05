package EnumExample;

public enum Gender {
    male("man"), female("woman");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
