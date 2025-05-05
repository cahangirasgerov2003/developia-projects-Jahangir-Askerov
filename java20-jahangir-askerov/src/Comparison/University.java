package Comparison;

public class University {
    private String universityName;
    public Teacher teacher;

    public University(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void addTeacher(String teacherName) {
        teacher = new Teacher(teacherName);
    }
}
