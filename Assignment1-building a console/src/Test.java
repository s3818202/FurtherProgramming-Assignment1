import java.io.FileNotFoundException;

import javax.management.openmbean.SimpleType;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

public class Test {
    private static HandleEnroll handleEnroll = new HandleEnroll();
    
    @BeforeAll
    static void BeforeAll() throws FileNotFoundException {
        handleEnroll.fetchStudentData("default.csv");
    }

    @Test

    void testAddFunction(){
        /*
        @desc: testing Add function is check if the new student enrollment is appear in StudentEnrollmentList, if it appear the add function is work well
        @param: studentID, courseID, semester
        @return: StudentEnrollment
        */
        System.out.println("Test Add function");
        
        StudentEnrolment se = new StudentEnrolment();
        String studentID = "S101312";
        String courseID = "COSC3321";
        String semester = "2021A";

        handleEnroll.add(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester);
        se = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester);
        Assertions.assertEquals(true, handleEnroll.getStudentEnrollmentList().contains(se));

    }

    @Test
    void testUpdateFunction(){
        /*
        @desc: testing Update function have 2 option:
               option-1 add a new course for the student that appear in StudentEnrollmentList, the Update function will work if the 2 new studentEnrollment appear in StudentEnrollmentList
               option-2 delete a course that appear in StudentEnrollmentLis, check if the studentEnrollment that choose in deleteFunction is not appear in StudenEnrollmentList.
        @param: studentID, courseID, semester, newCourseID, option
        @return: StudentEnrollment
        */
        System.out.println("Test Update function");

        StudentEnrolment se = new StudentEnrolment();
        StudentEnrolment updateSe = new StudentEnrolment();
        String option = "1";
        String studentID = "S101312";
        String courseID = "BUS2232";
        String semester = "2020C";
        String newCourseID = "COSC3321";
        handleEnroll.update(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester, option, handleEnroll.isExistInCourseList(newCourse));
        se = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester);
        updateSe = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(newCourseID), semester);
        Assertions.assertEquals(true, handleEnroll.getStudentEnrollmentList().contains(se));
        Assertions.assertEquals(true, handleEnroll.getStudentEnrollmentList().contains(updateSe));

        StudentEnrolment se2 = new StudentEnrolment();
        StudentEnrolment updateSe2 = new StudentEnrolment();
        String option2 = "2";
        String studentID2 = "S101312";
        String courseID2 = "BUS2232";
        String semester2 = "2020C";
        String newCourseID2 = "COSC3321";
        handleEnroll.update(handleEnroll.isExistInStudentList(studentID2), handleEnroll.isExistInCourseList(courseID2), semester2, option2, handleEnroll.isExistInCourseList(newCourse2));
        se = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID2), handleEnroll.isExistInCourseList(courseID2), semester2);
        updateSe2 = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID2), handleEnroll.isExistInCourseList(newCourseID2), semester2);
        Assertions.assertEquals(true, handleEnroll.getStudentEnrollmentList().contains(se));
        Assertions.assertEquals(true, !handleEnroll.getStudentEnrollmentList().contains(updateSe2));

    }

    @Test
    void testDeleteFunction(){
        /*
        @desc:  delete a course that appear in StudentEnrollmentLis, check if the studentEnrollment that choose in deleteFunction is not appear in StudenEnrollmentList.
        @param: studentID, courseID, semester, 
        @return: StudentEnrollment
        */

        System.out.println("Test Delete function");
        StudentEnrolment se = new StudentEnrolment();
        String studentID = "S101312";
        String courseID = "BUS2232";
        String semester = "2020C";

        handleEnroll.delete(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester);
        se = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester);
        Assertions.assertEquals(true, !handleEnroll.getStudentEnrollmentList().contains(se));
    }

    @Test
    void testGetOne(){
        /*
        @desc:   check if the studentEnrollment that choose in GetOne is appear in StudenEnrollmentList.
        @param: studentID, courseID, semester, 
        @return: StudentEnrollment
        */
        System.out.println("Test GetOne function");
        StudentEnrolment se = new StudentEnrolment();
        String studentID = "S101312";
        String courseID = "BUS2232";
        String semester = "2020C";

        se = handleEnroll.getOne(handleEnroll.isExistInStudentList(studentID), handleEnroll.isExistInCourseList(courseID), semester);
        Assertions.assertEquals(false, handleEnroll.getStudentEnrollmentList().contains(se));
    }
}
