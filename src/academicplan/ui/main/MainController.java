package academicplan.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import academicplan.database.DatabaseHandler;
import academicplan.models.Discipline;
import academicplan.models.Practice;
import academicplan.ui.discipline.DisciplineController;
import academicplan.ui.search.SearchController;
import academicplan.ui.semester.SemesterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Контроллер главного окна
 */
public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button disciplinesButton;

    @FXML
    private Button practicesButton;

    @FXML
    private Button semestersButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button openDisciplineButton;

    @FXML
    private Button openSemesterButton;

    @FXML
    private HBox disciplinesPanel;

    @FXML
    private HBox practicesPanel;

    @FXML
    private HBox semestersPanel;

    @FXML
    private ListView<Discipline> disciplinesListView;

    @FXML
    private ListView<Practice> practicesListView;

    @FXML
    private ListView<Integer> semestersListView;

    private final MainModel model = new MainModel();

    private final DatabaseHandler dbHandler = new DatabaseHandler();

    @FXML
    void initialize() {
        model.setDisciplineList(dbHandler.getDisciplines()); //Получение дисциплин из базы данных
        model.setPracticeList(dbHandler.getPractices()); //Получение практик из базы данных
        disciplinesButton.setOnAction(actionEvent -> {
            showPanel(disciplinesPanel);
            ObservableList<Discipline> disciplineList = FXCollections.observableList(model.getDisciplineList());
            disciplinesListView.setItems(disciplineList); //Установка объектов дисциплин в ListView
        });
        openDisciplineButton.setOnAction(actionEvent -> openDiscipline(disciplinesListView.getSelectionModel().getSelectedItem()));
        practicesButton.setOnAction(actionEvent -> {
            showPanel(practicesPanel);
            ObservableList<Practice> practiceList = FXCollections.observableList(model.getPracticeList());
            practicesListView.setItems(practiceList); //Установка объектов практик в ListView
        });
        semestersButton.setOnAction(actionEvent -> {
            showPanel(semestersPanel);
            ObservableList<Integer> semesterList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
            semestersListView.setItems(semesterList); //Установка объектов семестров в ListView
        });
        openSemesterButton.setOnAction(actionEvent -> openSemester(semestersListView.getSelectionModel().getSelectedItem()));
        searchButton.setOnAction(actionEvent -> openSearch());
    }

    /**
     * Метод для установки видимости панелей
     */
    private void showPanel(HBox panel){
        disciplinesPanel.setVisible(panel.getId().equals(disciplinesPanel.getId()));
        practicesPanel.setVisible(panel.getId().equals(practicesPanel.getId()));
        semestersPanel.setVisible(panel.getId().equals(semestersPanel.getId()));
    }

    /**
     * Метод для открытия окна дисциплины
     */
    private void openDiscipline(Discipline discipline){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/discipline_view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(discipline.getName()+" ("+discipline.getSemester()+" семестр)");
            stage.setScene(new Scene(root,280,280));
            stage.setResizable(false);
            DisciplineController controller = loader.getController();
            controller.setData(discipline);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для открытия окна семестра
     */
    private void openSemester(Integer semester){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/semester_view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(semester+" семестр");
            stage.setScene(new Scene(root,600,400));
            stage.setResizable(false);
            SemesterController controller = loader.getController();
            controller.setData(model.getDisciplineList(), semester);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для открытия окна поиска
     */
    private void openSearch(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/search_view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Поиск");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            SearchController controller = loader.getController();
            controller.setData(model.getDisciplineList());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
