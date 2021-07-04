package academicplan.ui.semester;

import academicplan.models.Discipline;

import java.util.ArrayList;

/**
 * Модель окна семестра
 */
public class SemesterModel {

    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private Integer semester;

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
