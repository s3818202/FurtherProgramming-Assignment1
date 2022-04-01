import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


import Course.Course;
import Student.Student;

public class HandleEnroll implements StudentEnrollmentManager {
    //set general value
    Scanner sc = new Scanner(System.in);
    private ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<StudentEnrolment> studentEnrollmentList = new ArrayList<StudentEnrolment>();


    public ArrayList<StudentEnrolment> getStudentEnrollmentList() {
        return studentEnrollmentList;
    }
    

    /*
        @desc:   check if having the student with the studentID in studentList      
        @param:  inputStudentID
        @return: Student st
    */
    public Student isExistInStudentList(String inputStudentID){
        for (Student st: studentList){
            if( st.getId().toLowerCase().equals(inputStudentID.toLowerCase()) ){
                return st;
            }
        }
        return null;
    }


    /*
        @desc:   check if having the course with the courseID in courseList
        @param:  inputCourseID
        @return: Course c
    */
    public Course isExistInCourseList(String inputCourseID){
        for (Course c: courseList ){
            if ( c.getId().toLowerCase().equals(inputCourseID.toLowerCase()) ){
                return c; 
            }
        }
        return null;
    }


    /*
        @desc:   get input StudentID from user and check if the input is on studentList or not
        @param:  scanner input
        @return: student with the inputStudentID
    */
    public Student getInputStudent(){
        String inputStudentID;
        Student student;
        System.out.println("Please Enter StudentID: ");
        inputStudentID = sc.nextLine();
        if ( isExistInStudentList(inputStudentID) != null){
            System.out.println("Student is valid!");
            return student = isExistInStudentList(inputStudentID);
        } else{
            System.out.println("The Student with the ID " + inputStudentID + " is not on the system");
            return null;
        }

    }


    /*
        @desc:   get input CourseID from user and check if the input is on courseList or not
        @param:  scanner input
        @return: course with the inputCourseID
    */
    public Course getInputCourse(){
        String inputCourse;
        System.out.println("Please Enter CourseID: ");
        Course course;
        inputCourse = sc.nextLine();
        if (isExistInCourseList(inputCourse) != null){
            System.out.println("Course is valid");
            return course = isExistInCourseList(inputCourse);
        } else {
            System.out.println("The Course with the ID " + inputCourse + " is not on the system" );
            return null;
        }
    }


    /*
        @desc:   get input semester from user and check if null or not
        @param:  scanner input
        @return: String semester
    */
    public String getInputSemester(){
        String inputSemester;
        do {
            System.out.println("Please Enter A Semester: ");
            inputSemester = sc.nextLine();
        } while (inputSemester == null);
        return inputSemester;
    }


    /*
        @desc:   get input filename from user and check if null or not
        @param:  scanner input
        @return: String inputFile
    */
    public String getInputFile(){
        String inputFile;
        do {
            System.out.println("Enter the file that you want to check in(For example: default.csv) if you want to check your file please save in StudentData foudler: ");
            inputFile = sc.nextLine();
        } while (inputFile == null);
        return inputFile;
    }


    /*
        @desc:  check if the object class with object name is on StudentList or CourseList
        @param:  Object object
        @return: true or false 
    */
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


    /*
        @desc:   check if the studentEnrollment with detail student, course and semester is appear in StudentEnrollment
        @param:  Student student, Course course, semester
        @return: StudentEnrolment
    */
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

    
    /*
        @desc:   fetch csv data return data to studentList, courseList, studentEnrollmentList by using buffer readder the file input
        @param:  String file 
        @return: studentList, courseList, studentEnrollmentList
    */   
    public void fetchStudentData(String file){
        try{
            String line= null;
            String url = "src/StudentData/";
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


    /*
        @desc:   print all studentList, courseList, studentEnrollmentList
        @param:   
        @return: 
    */   
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


    /*
        @desc:   print  studentEnrollmentList
        @param:   
        @return: 
    */   
    public void printStudentEnrollmentList(){
        System.out.println("\n");
        System.out.println("Student Enrollment List\n");
        for (StudentEnrolment se: studentEnrollmentList){
            System.out.println(se.toString());
        }
    }

    /*
        @desc:   print all course from one student in semester
        @param:   
        @return: 
    */   
    public void printAllCourseForOneStudentInSemester(){
        System.out.println("Enter the input required ()");
        Student student = getInputStudent();
        String semester = getInputSemester();
        System.out.println("\n");
        for (StudentEnrolment se: studentEnrollmentList){
            if ( (se.getStudent().getId().toLowerCase()).equals((student.getId()).toLowerCase()) &&
                 se.getSemester().toLowerCase().equals(semester.toLowerCase())){
               System.out.println(se);              
          
            } 
        } 
    }


    /*
        @desc:  print all student of one course in one semester 
        @param:   
        @return: 
    */
    public void printAllStudentOfOneCourseInSemester(){
        System.out.println("Enter the input required ()");
        Course course = getInputCourse();
        String semester = getInputSemester();
        System.out.println("\n");
        for (StudentEnrolment se: studentEnrollmentList){
            if ((se.getCourse().getId().toLowerCase()).equals((course.getId()).toLowerCase()) &&
                se.getSemester().toLowerCase().equals(semester.toLowerCase()) )
               System.out.println(se);                     
            } 
            
    }


    /*
        @desc:   save Student Enroll ment list to a csv file name StudentEnrollmentList
        @param:   
        @return: 
    */
    public void savetoCSVfile(){
        FileWriter fileSavedInfo = null;
        try {
             fileSavedInfo = new FileWriter("src/StudentData/StudentEnrollmentList");
             fileSavedInfo.append("\n");
             Iterator<StudentEnrolment> it = studentEnrollmentList.iterator();
             while(it.hasNext()){
                 StudentEnrolment se = (StudentEnrolment)it.next();
                 fileSavedInfo.append(se.getStudent().getId());
                 fileSavedInfo.append(",");
                 fileSavedInfo.append(se.getStudent().getName());
                 fileSavedInfo.append(",");
                 fileSavedInfo.append(se.getStudent().getBirthdate());
                 fileSavedInfo.append(",");
                 fileSavedInfo.append(se.getCourse().getId());
                 fileSavedInfo.append(",");
                 fileSavedInfo.append(se.getCourse().getName());
                 fileSavedInfo.append(",");
                 fileSavedInfo.append(String.valueOf(se.getCourse().getCredits()));
                 fileSavedInfo.append(",");
                 fileSavedInfo.append(se.getSemester());
                 fileSavedInfo.append(",");
                 fileSavedInfo.append("\n");

             } 
             fileSavedInfo.close();
        } catch(Exception a) {
            a.printStackTrace();
        }
    }


    /*
        @desc:   the main menu of the program display all main functions 
        @param:   
        @return: 
    */
    public void display(){
        String optionInput;

        do {
            fetchStudentData(getInputFile());
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
                case "1" -> {
                              add(getInputStudent(), getInputCourse(), getInputSemester()); 
                              printStudentEnrollmentList();
                              break;
                            }
                case "2" -> { 
                              update(getInputStudent(), getInputCourse(),getInputSemester(), getInputOption(), getInputCourse());
                              printStudentEnrollmentList();
                              break;
                            }

                case "3" -> {
                              printAllCourseForOneStudentInSemester();
                              break;
                            }
                case "4" -> {
                              printAllStudentOfOneCourseInSemester();
                              break;
                            }
                case "5" -> {
                              printAll();
                              break;
                            }
                case "6" -> System.exit(0);
            }

        } while (!optionInput.equals("1") && !optionInput.equals("2") && !optionInput.equals("3") && !optionInput.equals("4") && !optionInput.equals("5"));
    }




    @Override
    /*
        @desc:   add function, add a new studentenrollment to studentEnrollmentList 
        @param:   student , course, semester
        @return:  studentEnrollment
    */
    public void add(Student student, Course course, String semester) {
        if (getOne(student, course, semester) == null){
            studentEnrollmentList.add(new StudentEnrolment(student, course, semester));
            System.out.println("Sucessfull enroll the student with the studentID " + student.getId() + " to the course!");
        } else {
            System.out.println("Student with the studentID " + student.getId() + "is already enroll that course");
        }

    }


    /*
        @desc:   asking user to choose the option 
        @param:   scanner input
        @return: 
    */
    public String getInputOption(){
        String option;
        do {
            System.out.println("Choose | 1 | to add a course for the student " );
            System.out.println("CHoose | 2 | to delete a course for the student ");
            option = sc.nextLine();
             
        } while (option == null);
        return option;
    }


    @Override
    /*
        @desc:   update function have 2 small function choosing by option input
                 option 1 add a new course to the input student in the input semester
                 option 2 delete 1 course from input stdent in the input semester
        @param:   student, course, semester, option, newcourseID
        @return:  a new studentEnrollment
    */
    public void update(Student student, Course course, String semester, String option, Course newCourse) {     
        System.out.println(option);
        switch (option){
            case "1":  if ( getOne(student, course, semester) != null){
                        add(student, newCourse, semester);
                        } else {
                             System.out.println("Student is already enrolled the course" );
                         }
                        break;
            case "2": if ( getOne(student, course, semester) != null){
                         delete(student, newCourse, semester);
                         break;
            } else {
                System.out.println("Student is not enroll the course");
            }
        }                  
    }


    @Override
    /*
        @desc:  delete a input course from the input student and input semester, check if the input studentenrollment is on studentEnrollment List or not
        @param:  student, course, semester
        @return: a new studentEnrollmentList
    */
    public void delete(Student student, Course course, String semester) {     
        StudentEnrolment deleteStudentEnroll = new StudentEnrolment();      
        do {
        
            deleteStudentEnroll = getOne(student, course, semester);
         
        } while ( deleteStudentEnroll == null);       
        studentEnrollmentList.remove(deleteStudentEnroll);
        System.out.println("Successfull delete the course \n" + course.getId() + " in semester " + semester);
        for (StudentEnrolment se: studentEnrollmentList){
            if ( se.getStudent().equals(student) ){
                System.out.println(se);
            }
        }
    }

    @Override
    /*
        @desc:   get one studentEnrollment that have the input student course and semester
        @param:   student, course , semester
        @return:  studentEnrollment
    */
    public StudentEnrolment getOne(Student student, Course course, String semester) {
        StudentEnrolment se = new StudentEnrolment();
        if (isEnrolled(student, course, semester) != null){
            se = isEnrolled(student, course, semester);
            return se;
        }
        
        return null;
    }

    @Override
    /*
        @desc: print all student list , course list, student enrollment list.   
        @param:   
        @return: 
    */
    public void getAll() {
          printStudentEnrollmentList();
    }
}
