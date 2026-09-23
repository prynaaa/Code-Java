import jakarta.xml.bind.annotation.XmlElement;


public class Exam {
    private int grade;
    private String description;

    public Exam(){}

    public Exam(int grade, String description) {
        this.grade = grade;
        this.description = description;
    }

    public int getGrade() {
        return grade;
    }

    @XmlElement (name = "grade")
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement (name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Exam getExam(){
        return this;
    }

    @Override
    public String toString(){
        return "Grade=" + grade + ", description=" + description;
    }
}
