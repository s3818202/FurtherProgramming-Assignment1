package Student;


public class Student {
    private String id;
    private String name;
    private String birthdate;

    //default
    public Student(){
        id = "s0001";
        name = "default";
        birthdate = "**/**/****";
    }

    public Student(String id, String name, String birthdate){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "id= " + id + " name " + name + " birtdate " + birthdate;
    }
    
}
