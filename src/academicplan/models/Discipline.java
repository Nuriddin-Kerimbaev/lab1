package academicplan.models;

/**
 * Класс дисциплина
 */
public class Discipline {
    private int id;
    private int semester;
    private String name;
    private int lectures;
    private int practices;
    private int laboratories;
    private int trudoemkost;
    private String control;
    private boolean coursework;

    public Discipline(int id, int semester, String name, int lectures, int practices, int laboratories, int trudoemkost, String control, boolean coursework) {
        this.id = id;
        this.semester = semester;
        this.name = name;
        this.lectures = lectures;
        this.practices = practices;
        this.laboratories = laboratories;
        this.trudoemkost = trudoemkost;
        this.control = control;
        this.coursework = coursework;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLectures() {
        return lectures;
    }

    public void setLectures(int lectures) {
        this.lectures = lectures;
    }

    public int getPractices() {
        return practices;
    }

    public void setPractices(int practices) {
        this.practices = practices;
    }

    public int getLaboratories() {
        return laboratories;
    }

    public void setLaboratories(int laboratories) {
        this.laboratories = laboratories;
    }

    public int getTrudoemkost() {
        return trudoemkost;
    }

    public void setTrudoemkost(int trudoemkost) {
        this.trudoemkost = trudoemkost;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public boolean isCoursework() {
        return coursework;
    }

    public void setCoursework(boolean coursework) {
        this.coursework = coursework;
    }

    @Override
    public String toString() {
        return name+" ("+semester+" сем.)";
    }

    public String toStringWithControl() {
        return name+": "+control+(coursework?", курсовая":"");
    }
}
