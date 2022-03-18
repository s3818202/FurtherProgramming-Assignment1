import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Course.Course;
import Student.Student;

public class HandleEnroll implements StudentEnrollmentManager {
  
  
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<StudentEnrolment> studentEnrollmentList;
    
    public ArrayList<Student> feithStudentData() throws IOException {
        ArrayList<Student> studentData = new ArrayList<>();
        String urlData = "FurtherProgramming-Assignment1/Assignment1-building a console/src/StudentData/default.csv";
        String line = null;
        FileReader fileReader = new FileReader(urlData);
        BufferedReader br = new BufferedReader(fileReader);

        while ( (line = br.readLine()) != null){
            String[] temp = line.split(",");
            studentData.add(new Student(temp[0], temp[1], temp[2] ));
            
            
        }
        br.close();
        return studentData;
    }

    public ArrayList<Course> feithCourseData() throws IOException
    {
        ArrayList<Course> courseData = new ArrayList<>();
        String urlData = "FurtherProgramming-Assignment1/Assignment1-building a console/src/StudentData/default.csv";
        String line = null;
        FileReader fileReader = new FileReader(urlData);
        BufferedReader br = new BufferedReader(fileReader);

        while ( (line = br.readLine()) != null){
            String[] temp = line.split(",");
            courseData.add(new Course(temp[3], temp[4], temp[5]));


        }
        br.close();
        return courseData;
    }



    public void printAll() throws IOException{
        
        for(Course course: feithCourseData()){
            System.out.println(course.toString());
        }
    }


    @Override
    public void add() {
        // TODO Auto-generated method stub
        
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
