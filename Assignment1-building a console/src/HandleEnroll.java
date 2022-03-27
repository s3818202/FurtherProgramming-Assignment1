import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Course.Course;
import Student.Student;

public class HandleEnroll implements StudentEnrollmentManager {
  
    Scanner sc = new Scanner(System.in);
    private ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<StudentEnrolment> studentEnrollmentList = new ArrayList<StudentEnrolment>();

    // check if have 2 student or course have the same studentID or courseID
    public boolean isExistInList (Object object){
        if(object.getClass().equals(Student.class)){
            for (Student student: studentList){
                if(student.getId().equals(((Student)object).getId())){
                    return true;
                }
            }
        } else {
            for(Course course: courseList){
                if(course.getId().equals(((Course)object).getId())){
                    return true;
                }
            }
        }
        return false;
    }

    
    
    public void fetchStudentData(){
        try{
            String line= null;
            String url = "src/StudentData/default.csv";
            BufferedReader br = new BufferedReader(new FileReader(url));
            while ((line = br.readLine()) != null){
                String[] temp = line.split(",");

                Student newStudent = new Student(temp[0], temp[1], temp[2]);
                Course  newCourse = new Course(temp[3], temp[4], temp[5]);
                StudentEnrolment newStudentEnrollment= new StudentEnrolment(newStudent, newCourse, temp[6]);
                studentEnrollmentList.add(newStudentEnrollment);
                if (!isExistInList(newStudent)){
                   studentList.add(newStudent);
                }
                if (!isExistInList(newCourse)){
                    courseList.add(newCourse);
                }
                 
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Some error happened");
            e.printStackTrace();
        }
        
    }


    public void printAll(){
        
        for (StudentEnrolment se: studentEnrollmentList){
            System.out.println(se.toString());
        }
    }

    public Student studentReturn(String inpuStudentID){
        for (Student s: studentList){
            if(s.getId().toLowerCase().equals(inpuStudentID.toLowerCase())){
                return  s;
            }
        }      
        return null;
    }

    public Course courseReturn(String inputCourse){
        for (Course c: courseList){
            if(c.getId().toLowerCase().equals(inputCourse.toLowerCase())){
                return c;
            } else if (c.getName().toLowerCase().equals(inputCourse.toLowerCase())){
                return c;
            }
        }
        return null;
    }

    public StudentEnrolment enrollStudentReturn(Student student){
        for (StudentEnrolment se: studentEnrollmentList){
            if (se.getStudent().equals(student)){
                return se;
            }
        }
        return null;
    }



    @Override
    public void add() {
        String inputStudentID= null;
        String inputCourse = null; 
        String inputSemester = null;
        StudentEnrolment a = null;
        do {
            System.out.println("Enter the input required ()");
            System.out.println("Enter the StudentID: ");
            inputStudentID = sc.nextLine(); 
            System.out.println("Enter the Course (Course ID or Course Name) to enroll: ");
            inputCourse = sc.nextLine();
            System.out.println("Enter the semester to enroll: ");
            inputSemester = sc.nextLine();

            a = new StudentEnrolment(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester);

                
        } while (studentReturn(inputStudentID) == null &&  courseReturn(inputCourse) == null);

        studentEnrollmentList.add(a);
        System.out.println("Sucessfull enroll the student!!!");
        printAll();

    }


    @Override
    public void update() {
        // TODO Auto-generated method stub
        printAll();
        System.out.println("Enter the Student ID: ");
        String inputStudentID = sc.nextLine();
        if (studentReturn(inputStudentID) != null){
            

        }
        
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getOne() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getAll() {
        // TODO Auto-generated method stub
        
    }
}
