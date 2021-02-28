package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dad.productividad.app.MainController;
import dad.productividad.note.Note;
import dad.productividad.pomodoro.PomodoroSetup;

/**
 * Class used to interact with the Pomodoro table in the database.
 */
public class TablePomodoro {
    /**
     * Inserts new pomodoro into the database
     *
     * @param pomodoroSetup The pomodoro to be inserted
     */
    public static void insertPomodoro(PomodoroSetup pomodoroSetup) {

        String insert = "INSERT INTO pomodoro (title_pomodoro, time_spent, FK_ID_page) VALUES (?, ?, (SELECT id_page FROM pages where date_page=date('now'))";
        String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='pomodoro'";
        int id = 0;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
            pstmt.setString(1, pomodoroSetup.getTitlePomodoro());
            pstmt.setInt(2, pomodoroSetup.getTimeSpent());
           


            pstmt.executeUpdate();

            Statement stmt = JdbcConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(getPkId);

            while (rs.next()) {
                id = rs.getInt("seq");
            }
            pomodoroSetup.setIdPomodoro(id);
            pomodoroSetup.setIdPage(MainController.getTodaysPage().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }


    }


}
