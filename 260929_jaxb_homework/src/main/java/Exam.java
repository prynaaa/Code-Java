import jakarta.xml.bind.annotation.XmlElement;

public class Exam {
    private int grade;
    private String description;

    public Exam() {
    }

    public Exam(int grade, String description){
        this.grade = grade;
        this.description = description;
    }

    public int getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "theGrade")
    public void setGrade(int grade) {
        this.grade = grade;
    }

    @XmlElement(name = "theDescription")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "grade=" + grade +
                ", description='" + description + '\'' +
                '}';
    }
}
