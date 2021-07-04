package academicplan.ui.main;

import academicplan.models.Discipline;
import academicplan.models.Practice;

import java.util.ArrayList;

/**
 * Модель главного окна
 */
public class MainModel {

    private ArrayList<Discipline> disciplineList = new ArrayList<>();
    private ArrayList<Practice> practiceList = new ArrayList<>();

    public ArrayList<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(ArrayList<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    public ArrayList<Practice> getPracticeList() {
        return practiceList;
    }

    public void setPracticeList(ArrayList<Practice> practiceList) {
        this.practiceList = practiceList;
    }
}
