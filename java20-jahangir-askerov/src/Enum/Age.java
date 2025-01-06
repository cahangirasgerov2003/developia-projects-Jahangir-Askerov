package Enum;

public enum Age {
    children("For children aged 1-12 years"),
    youngPeople("For young people between 12 and 25 years old"),
    adults("For adults over 25 years old");

    private final String description;

    private Age(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
