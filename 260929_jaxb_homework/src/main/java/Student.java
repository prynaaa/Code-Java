import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@XmlRootElement // defines the root element of the XML file
public class Student {
    private String name;
    private Date dateOfBirth;

    // ad 3
    //your turn: add a list of Exam Objects
    private List<Exam> exams;

    public Student() { // needed from jaxb runtime
        exams = new ArrayList<>();
    }

    public Student(String name, Date dateOfBirth) {
        this();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name") // xml -> object
    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void add(Exam e){
        exams.add(e);
    }

    public List<Exam> getExams() {
        return exams;
    }

    @XmlElementWrapper(name = "exams")
    @XmlElement(name = "exam") // name is of innern elements of exams
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    /* Versuch der scheiterte ... Danke Noah
    @XmlElement(name = "exam")
    public void setExam(Exam exam) {
        exams.add(exam);
    }
     */

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", exams=" + exams +
                '}';
    }

    static void main() {
        Student s = new Student();
        s.setName("Geog Kreisler");
        s.setDateOfBirth(new Date());

        s.add(new Exam(2, "Religion"));
        s.add(new Exam(3, "Turnen"));

        System.out.println(s);
    }
}
