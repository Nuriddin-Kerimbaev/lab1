package academicplan.ui.search;

import academicplan.models.Discipline;

import java.util.ArrayList;

/**
 * Модель окна поиска
 */
public class SearchModel {

    private ArrayList<Discipline> disciplineList;

    public ArrayList<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(ArrayList<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}