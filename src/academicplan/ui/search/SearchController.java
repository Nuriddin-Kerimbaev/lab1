package academicplan.ui.search;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import academicplan.models.Discipline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * Контроллер окна поиска
 */
public class SearchController {

    @FXML
    private Button searchDisciplinesButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button semesterButton;

    @FXML
    private Button disciplinesButton;

    @FXML
    private Button trudoemkostButton;

    @FXML
    private AnchorPane semesterPanel;

    @FXML
    private ComboBox<Integer> semesterComboBox;

    @FXML
    private Label listLabel;

    @FXML
    private AnchorPane disciplinesPanel;

    @FXML
    private ComboBox<Integer> disciplineSemesterComboBox;

    @FXML
    private CheckBox disciplinesLabCB;

    @FXML
    private CheckBox disciplinesPrCB;

    @FXML
    private CheckBox disciplinesLecCB;

    @FXML
    private CheckBox disciplinesCourseWorksCB;

    @FXML
    private CheckBox disciplinesZachetCB;

    @FXML
    private CheckBox disciplinesExamsCB;

    @FXML
    private ListView<Discipline> disciplinesListView;

    @FXML
    private AnchorPane trudoemkostPanel;

    @FXML
    private ComboBox<Integer> trudSemesterCB;

    @FXML
    private ListView<String> trudoemkostListView;

    private final SearchModel model = new SearchModel();

    @FXML
    void initialize() {
        semesterComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8)); //Установка списка семестров в ComboBox
        disciplineSemesterComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
        trudSemesterCB.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));

        semesterButton.setOnAction(actionEvent -> showPanel(semesterPanel));
        disciplinesButton.setOnAction(actionEvent -> showPanel(disciplinesPanel));
        trudoemkostButton.setOnAction(actionEvent -> {
            showPanel(trudoemkostPanel);
            setTrudoemkostView();
        });

        semesterComboBox.setOnAction(actionEvent -> updateListLabel());
        searchDisciplinesButton.setOnAction(actionEvent -> searchDisciplines());
        trudSemesterCB.setOnAction(actionEvent -> setTrudoemkostView());
    }

    /**
     * Метод для записи списка дисциплин в модель
     */
    public void setData(ArrayList<Discipline> disciplines) {
        model.setDisciplineList(disciplines);
    }

    /**
     * Метод для установки видимости панелей
     */
    void showPanel(AnchorPane panel) {
        semesterPanel.setVisible(panel.getId().equals(semesterPanel.getId()));
        disciplinesPanel.setVisible(panel.getId().equals(disciplinesPanel.getId()));
        trudoemkostPanel.setVisible(panel.getId().equals(trudoemkostPanel.getId()));
    }

    /**
     * Обновление списка экзаменов, экзаменов и курсовых
     */
    void updateListLabel() {
        int exams = 0, zachets = 0, works = 0;
        for (Discipline d : model.getDisciplineList()) {
            if (d.getSemester() == semesterComboBox.getSelectionModel().getSelectedItem()) {
                if (d.getControl().toLowerCase().equals("зачёт")) {
                    zachets++;
                }
                if (d.getControl().toLowerCase().equals("экзамен")) {
                    exams++;
                }
                if (d.isCoursework()) {
                    works++;
                }
            }
        }
        listLabel.setText(zachets + " зачётов, " + exams + " экзаменов, " + works + " курсовых");
    }

    /**
     * Метод для фильтрации списка дисциплин по указанным параметрам
     */
    void searchDisciplines() {
        ArrayList<Discipline> filtered = new ArrayList<>();
        for (Discipline d : model.getDisciplineList()) {
            if(disciplineSemesterComboBox.getSelectionModel().getSelectedItem()!=null){
                if(d.getSemester()!=disciplineSemesterComboBox.getSelectionModel().getSelectedItem()){
                    continue;
                }
            }
            if (d.getLaboratories() != 0 && disciplinesLabCB.isSelected()) {
                filtered.add(d);
            } else if (d.getPractices() != 0 && disciplinesPrCB.isSelected()) {
                filtered.add(d);
            } else if (d.getLectures() != 0 && disciplinesLecCB.isSelected()) {
                filtered.add(d);
            } else if (d.isCoursework() && disciplinesCourseWorksCB.isSelected()) {
                filtered.add(d);
            } else if (d.getControl().toLowerCase().equals("зачёт") && disciplinesZachetCB.isSelected()) {
                filtered.add(d);
            } else if (d.getControl().toLowerCase().equals("экзамен") && disciplinesExamsCB.isSelected()) {
                filtered.add(d);
            }
        }
        disciplinesListView.setItems(FXCollections.observableList(filtered));
    }

    /**
     * Метод для установки рейтинга трудоёмкости
     */
    void setTrudoemkostView() {
        ArrayList<String> disciplines = new ArrayList<>();
        model.getDisciplineList().sort(Comparator.comparingInt(Discipline::getTrudoemkost));
        for(Discipline d: model.getDisciplineList()){
            if(trudSemesterCB.getSelectionModel().getSelectedItem()!=null){
                if(d.getSemester() != trudSemesterCB.getSelectionModel().getSelectedItem()){
                    continue;
                }
            }
            disciplines.add(d.getName()+", трудоёмкость: "+d.getTrudoemkost());
        }
        trudoemkostListView.setItems(FXCollections.observableArrayList(disciplines));
    }
}