import Course.Course;
import Student.Student;

public class StudentEnrolment {
    //set value
    private Student student;
    private Course course;
    private String semester;
    //default value
    public StudentEnrolment(){
        student = null;
        course = null;
        semester = "0000";
    }

    public StudentEnrolment(Student student, Course course, String semester){
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return student.toString() + course.toString() + " semester: " + semester;
    }
}
