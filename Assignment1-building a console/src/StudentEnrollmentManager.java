
import Course.Course;
import Student.Student;

public interface StudentEnrollmentManager {
    public void add(Student student, Course course, String semester); 
    public void update(Student student, Course course, String semester, String option, Course newCourse);
    public void delete(Student student, Course course, String semester);
    public StudentEnrolment getOne(Student student, Course course, String semester);
    public void getAll();
}
