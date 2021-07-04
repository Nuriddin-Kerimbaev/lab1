package academicplan.ui.discipline;

import java.net.URL;
import java.util.ResourceBundle;
import academicplan.models.Discipline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Контроллер окна дисциплины
 */
public class DisciplineController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lecturesLabel;

    @FXML
    private Label laboratoriesLabel;

    @FXML
    private Label practicesLabel;

    @FXML
    private Label trudoemkostLabel;

    @FXML
    private Label controlLabel;

    @FXML
    private Label courseWorkLabel;

    private final DisciplineModel model = new DisciplineModel();

    @FXML
    void initialize() {}

    /**
     * Метод для инициализации данных окна
     */
    public void setData(Discipline discipline){
        model.setDiscipline(discipline);
        switch (discipline.getLectures()) {
            case 1 -> lecturesLabel.setText("1 пара в две недели");
            case 2 -> lecturesLabel.setText("1 пара в неделю");
            case 3 -> lecturesLabel.setText("3 пары в две недели");
            case 4 -> lecturesLabel.setText("2 пары в неделю");
            default -> lecturesLabel.setText(discipline.getLectures() + " пар в две недели");
        }
        switch (discipline.getLaboratories()) {
            case 1 -> laboratoriesLabel.setText("1 пара в две недели");
            case 2 -> laboratoriesLabel.setText("1 пара в неделю");
            case 3 -> laboratoriesLabel.setText("3 пары в две недели");
            case 4 -> laboratoriesLabel.setText("2 пары в неделю");
            default -> laboratoriesLabel.setText(discipline.getLectures() + " пар в две недели");
        }
        switch (discipline.getPractices()) {
            case 1 -> practicesLabel.setText("1 пара в две недели");
            case 2 -> practicesLabel.setText("1 пара в неделю");
            case 3 -> practicesLabel.setText("3 пары в две недели");
            case 4 -> practicesLabel.setText("2 пары в неделю");
            default -> practicesLabel.setText(discipline.getLectures() + " пар в две недели");
        }
        trudoemkostLabel.setText(discipline.getTrudoemkost()+" часов");
        controlLabel.setText(discipline.getControl());
        if(discipline.isCoursework()){
            courseWorkLabel.setText("Есть");
        } else {
            courseWorkLabel.setText("Нет");
        }
    }
}