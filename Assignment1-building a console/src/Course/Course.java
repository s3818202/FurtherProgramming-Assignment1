package Course;

public class Course {
    private String id;
    private String name;
    private String credits;

    public Course(){
        id = "0000";
        name = "default";
        credits = "000";
    }

    public Course(String id, String name, String credits){
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "id: " + id + " course name: " + name + " credits: " + credits;
    }
}
