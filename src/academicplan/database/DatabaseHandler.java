package academicplan.database;

import academicplan.models.Discipline;
import academicplan.models.Practice;
import java.util.ArrayList;
import java.sql.*;

/**
 * Обработчик базы данных для получения дисциплин и практик
 */
public class DatabaseHandler extends Configs{

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    /**
     * Метод для получения дисциплин из базы данных
     */
    public ArrayList<Discipline> getDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try(
                Statement statement = getDbConnection().createStatement();
                ResultSet set = statement.executeQuery("Select * From " + Const.DISCIPLINE_TABLE)
        ){
            while (set.next()){
                int id = set.getInt(Const.DISCIPLINE_ID);
                int semester = set.getInt(Const.DISCIPLINE_SEMESTER);
                String name = set.getString(Const.DISCIPLINE_NAME);
                int lectures = set.getInt(Const.DISCIPLINE_LECTURES);
                int practices = set.getInt(Const.DISCIPLINE_PRACTICES);
                int laboratories = set.getInt(Const.DISCIPLINE_LABORATORIES);
                int trudoemkost = set.getInt(Const.DISCIPLINE_TRUDOEMKOST);
                String control = set.getString(Const.DISCIPLINE_CONTROL);
                boolean coursework = set.getBoolean(Const.DISCIPLINE_COURSEWORK);
                disciplines.add(new Discipline(id, semester, name, lectures, practices, laboratories, trudoemkost, control, coursework));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return disciplines;
    }

    /**
     * Метод для получения практик из базы данных
     */
    public ArrayList<Practice> getPractices(){
        ArrayList<Practice> practices = new ArrayList<>();
        try(
                Statement statement = getDbConnection().createStatement();
                ResultSet set = statement.executeQuery("Select * From " + Const.PRACTICE_TABLE)
                ) {
            while (set.next()){
                int id = set.getInt(Const.PRACTICE_ID);
                int semester = set.getInt(Const.PRACTICE_SEMESTER);
                int duration = set.getInt(Const.PRACTICE_DURATION);
                String type = set.getString(Const.PRACTICE_TYPE);
                practices.add(new Practice(id, semester, duration, type));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return practices;
    }
}
