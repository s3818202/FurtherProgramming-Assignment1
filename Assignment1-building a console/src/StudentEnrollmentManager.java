import java.io.IOException;

public interface StudentEnrollmentManager {
    public void add() throws IOException;
    public void update();
    public void delete(String inputStudentID);
    public void getOne();
    public void getAll();
}
