import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

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

    public StudentEnrolment isEnrolled (Student student, Course course, String semester){
        for (StudentEnrolment se: studentEnrollmentList){
            if( 
                (se.getStudent().getId()).toLowerCase().equals((student.getId()).toLowerCase() ) &&
                (se.getCourse().getId()).toLowerCase().equals((course.getId()).toLowerCase() ) &&
                (se.getSemester().toLowerCase()).equals(semester.toLowerCase())
            )
            {
                return se;
            }
        }
        return null;
    }

    
    
    public void fetchStudentData(){
        try{
            String line= null;
            String url = "src/StudentData/";
            System.out.println("Enter the file that you want to check in(For example: default.csv) if you want to check your file please save in StudentData foudler: ");
            String file = sc.nextLine();
            BufferedReader br = new BufferedReader(new FileReader(url+file));
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
            printAll();
            br.close();
        } catch (Exception e) {
            System.out.println("Some error happened");
            e.printStackTrace();
        }
        
    }


    public void printAll(){
        System.out.println("\n");
        System.out.println("Student List\n");
        for (Student s: studentList){
            System.out.println(s.toString());
        }
        System.out.println("\n");
        System.out.println("Course List\n");
        for (Course c: courseList){
            System.out.println(c.toString());
        }
        System.out.println("\n");
        System.out.println("Student Enrollment List\n");
        for (StudentEnrolment se: studentEnrollmentList){
            System.out.println(se.toString());
        }
        
    }

    public void printStudentEnrollmentList(){
        System.out.println("\n");
        System.out.println("Student Enrollment List\n");
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

    public void printAllCourseForOneStudentInSemester(){
        System.out.println("Enter the input required ()");
        System.out.println("Enter the StudentID: ");
        String inputStudentID = sc.nextLine(); 
        System.out.println("Enter the semester that you want to print out: ");
        String inputSemester = sc.nextLine();
        System.out.println("\n");
        for (StudentEnrolment se: studentEnrollmentList){
            if ((se.getStudent().getId()).toLowerCase().equals((inputStudentID.toLowerCase())) &&
               (se.getSemester().toLowerCase()).equals(inputSemester.toLowerCase())){
               System.out.println(se);              
          
            } 
        } 
    }

    public void printAllStudentOfOneCourseInSemester(){
        System.out.println("Enter the input required ()");
        System.out.println("Enter the course: ");
        String inputCourse = sc.nextLine();
        System.out.println("Enter the semester that you want to print out: ");
        String inputSemester = sc.nextLine();
        System.out.println("\n");
        for (StudentEnrolment se: studentEnrollmentList){
            if ((se.getCourse().getId()).toLowerCase().equals((inputCourse.toLowerCase())) &&
               (se.getSemester().toLowerCase()).equals(inputSemester.toLowerCase())){
               System.out.println(se);                
        
            } 
        }
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

        if (isEnrolled(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester) == null){
            studentEnrollmentList.add(a);
            
            printStudentEnrollmentList();
            System.out.println("Sucessfull enroll the student with the studentID " +inputStudentID+ " to the course!");
        } else {
            System.out.println("Student with the studentID " + inputStudentID + "is already enroll that course");
        }
        
        

    }


    @Override
    public void update() {
        System.out.println("Enter the input required ()");
        System.out.println("Enter the StudentID: ");
        String inputStudentID = sc.nextLine(); 
        System.out.println("Enter the semester to enroll: ");
        String inputSemester = sc.nextLine();
        boolean isExist = false;
        String option;
        for (StudentEnrolment se: studentEnrollmentList){
            if ((se.getStudent().getId()).toLowerCase().equals((inputStudentID.toLowerCase())) &&
               (se.getSemester().toLowerCase()).equals(inputSemester.toLowerCase())){
               System.out.println(se);    
               isExist = true; 
               break;           
            } else { isExist = false;}
        } 
        if (isExist) {
           System.out.println("Choose | 1 | to add a course for the student " + inputStudentID);
           System.out.println("CHoose | 2 | to delete a course for the student " + inputStudentID);
           option = sc.nextLine();
           System.out.println(option);
           switch (option){
               case "1": System.out.println("Enter the Course (Course ID or Course Name) to enroll: ");
                         String inputCourse = sc.nextLine();
                         StudentEnrolment a = new StudentEnrolment(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester);
                         if (courseReturn(inputCourse) != null && isEnrolled(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester) == null ){
                            studentEnrollmentList.add(a);
                            System.out.println("Successfful enrolled a new course for student " + inputStudentID);
                            for (StudentEnrolment se: studentEnrollmentList){
                                if ( (se.getStudent().getId()).toLowerCase().equals((inputStudentID.toLowerCase()))){
                                    System.out.println(se);
                                }
                            }
                         } else {
                             System.out.println("Student is already enrolled the course:" + inputCourse);
                         }
                        break;
                case "2": delete(inputStudentID);
                        break;
                }
                     
        } else {
            System.out.println("Student " + inputStudentID + " is not enroll any course");
        }
    }

    public void display(){
        String optionInput;

        do {
            System.out.println("*********************************************");
            System.out.println("*** WELCOME TO STUDENT ENROLLMENT SYSTEMS ***");
            System.out.println("*********************************************");
            System.out.println("Please choose your option below:");
            System.out.println("1: To Enroll A new Student to the Enrollment System");
            System.out.println("2: To Update information in the Enrollment System");
            System.out.println("3: Display All Courses for 1 Student in 1 Semester");
            System.out.println("4: Display All Students of 1 Course in 1 Semester");
            System.out.println("5: Display All Information");
            System.out.println("6: Exit the program");
            System.out.println("*********************************************");

            optionInput = sc.nextLine();
            switch (optionInput) {
                case "1" -> add();
                case "2" -> update();
                case "3" -> printAllCourseForOneStudentInSemester();
                case "4" -> printAllStudentOfOneCourseInSemester();
                case "5" -> printAll();
                case "6" -> System.exit(0);
            }

        } while (!optionInput.equals("1") && !optionInput.equals("2") && !optionInput.equals("3") && !optionInput.equals("4") && !optionInput.equals("5"));
    }


    @Override
    public void delete(String inputStudentID) {
        String inputCourse = ""; 
        String inputSemester = "";
        StudentEnrolment deleteStudentEnroll = new StudentEnrolment();
        

        do {
            System.out.println("Enter the input required ()");       
            System.out.println("Enter the Course (Course ID or Course Name) to Delete: ");
            inputCourse = sc.nextLine();
            System.out.println("Enter the semester to delete: ");
            inputSemester = sc.nextLine();
            deleteStudentEnroll = isEnrolled(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester);
         
        } while ( deleteStudentEnroll == null);       
        studentEnrollmentList.remove(deleteStudentEnroll);
        System.out.println("Successfull delete the course \n" + inputCourse + " in semester " + inputSemester);
        for (StudentEnrolment se: studentEnrollmentList){
            if ( (se.getStudent().getId()).toLowerCase().equals((inputStudentID.toLowerCase()))){
                System.out.println(se);
            }
        }
    }

    @Override
    public void getOne() {
        String inputStudentID= null;
        String inputCourse = null; 
        String inputSemester = null;
        

        do {
            System.out.println("Enter the input required ()");
            System.out.println("Enter the StudentID: ");
            inputStudentID = sc.nextLine(); 
            System.out.println("Enter the Course (Course ID or Course Name) to enroll: ");
            inputCourse = sc.nextLine();
            System.out.println("Enter the semester to enroll: ");
            inputSemester = sc.nextLine();
         
        } while (isEnrolled(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester) == null);

        System.out.println("the student you want to selected is:");
        System.out.println(isEnrolled(studentReturn(inputStudentID), courseReturn(inputCourse), inputSemester).toString());
    }

    @Override
    public void getAll() {
          printStudentEnrollmentList();
    }
}
