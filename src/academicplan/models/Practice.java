package academicplan.models;

/**
 * Класс практика
 */
public class Practice {

    private int id;
    private int semester;
    private int duration;
    private String type;

    public Practice(int id, int semester, int duration, String type) {
        this.id = id;
        this.semester = semester;
        this.duration = duration;
        this.type = type;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type+", продолжительность: "+duration+" дней, семестр: "+semester;
    }
}
