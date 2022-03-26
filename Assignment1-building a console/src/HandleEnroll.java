import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Course.Course;
import Student.Student;

public class HandleEnroll implements StudentEnrollmentManager {
  
  
    private ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<StudentEnrolment> studentEnrollmentList = new ArrayList<StudentEnrolment>();

    public void addStudent (Student student1){
        studentList.add(student1);
    }

    public void addCourse (Course course1){
        courseList.add(course1);
    }

    public void addStudentEnrollment (StudentEnrolment enroll1){
        studentEnrollmentList.add(enroll1);
    }

    
    
    public void fetchStudentData(){
        try{
            String line= null;
            String url = "src/StudentData/default.csv";
            BufferedReader br = new BufferedReader(new FileReader(url));
            while ((line = br.readLine()) != null){
                String[] temp = line.split(",");
                addStudent(new Student(temp[0], temp[1], temp[2]));
                 
            }
        } catch (Exception e) {
            System.out.println("Some error happened");
            e.printStackTrace();
        }
    }

    



    public void printAll(){
        for (Student s: studentList){
            System.out.println(s.toString());
        }
    }






    @Override
    public void add() {
        // The system should ask id of students, semester, and courses that students need to enroll (use Scanner(System.in))

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the StudentID: ");
        String inputStudentID = sc.nextLine();
        System.out.println("Enter the Course to enroll: ");
        String inputCourse = sc.nextLine();
        System.out.println("Enter the semester to enroll: ");
        String inputSemester = sc.nextLine();

        





    }


    @Override
    public void update() {
        // TODO Auto-generated method stub
        
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
