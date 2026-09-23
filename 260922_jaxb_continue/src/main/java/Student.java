import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Student {
    private String name;
    private Date dateOfBirth;

    private List<Exam> exams;

    // ad 3
    // your turn: add a list of Exam Objects

    public Student(){
        exams = new ArrayList<>();
    } // needed for jaxb runtime

    public Student(String name, Date dateOfBirth, List<Exam> exams) {
        this();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.exams = exams;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlElement(name = "exams")
    public void setExamList(List<Exam> examList) {
        this.exams = examList;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Exam> getExamList() {
        return exams;
    }

    /*
    public void addExam(Exam e){
        examList.add(e);
    }

    @XmlElement(name = "exam")
    public void setExam(Exam exam) {
        examList.add(exam);
    }

     */

    @XmlElementWrapper(name = "exams")
    @XmlElement(name = "exam")
    public void setExam(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", examList=" + exams +
                '}';
    }

    static void main() {
        try {
            TheXmlDoing xmlDoing = new TheXmlDoing();
            Student s = xmlDoing.unmarshalling("datav4.xml"); // datav3 is failed from myself

             xmlDoing.marshalling(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
