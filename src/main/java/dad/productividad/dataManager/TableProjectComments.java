package dad.productividad.dataManager;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.project.TaskComments;

/**
 * Class used to interact with the Notes table in the database.
 */
public class TableProjectComments {
    /**
     * Method to insert a new note in the database
     *
     * @param taskComments The note to be inserted
     */
    public static int insertProjectComments(TaskComments taskComments) {
        String insert = "INSERT INTO project_comments (content_project_comments, FK_ID_task) VALUES ( ?, ?)";
        String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='project_comments'";
        int id = 0;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
            pstmt.setString(1, taskComments.getContent());
            pstmt.setInt(2, taskComments.getIdTask());
            pstmt.executeUpdate();

            Statement stmt = JdbcConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(getPkId);

            while (rs.next()) {
                id = rs.getInt("seq");
            }
            taskComments.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

        return id;
    }


    /**
     * Method to update an existing projectComment from the table
     *
     * @param taskComments The projectComments from the registry to be updated
     *
     */
    public static void update(TaskComments taskComments) {
        String update = "UPDATE project_comments SET  content_project_comments = ?, FK_ID_task = ? WHERE ID_project_comments = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
            pstmt.setString(1, taskComments.getContent());
            pstmt.setInt(2, taskComments.getIdTask());
            pstmt.setInt(3, taskComments.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
    }



    /**
     * Method to delete an existing projectComment from the table
     *
     * @param taskComments The projectComment to be deleted
     */
    public static void delete(TaskComments taskComments) {
        String delete = "DELETE FROM project_comments WHERE ID_project_comments = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
            pstmt.setInt(1, taskComments.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

    }

    /**
     * Method to get Registries from the table
     *
     * @param number The number of registries to be shown
     *
     * @return arrayList An ArrayList of projectComments objects
     */
    public static List<TaskComments> read(int number) {
        String select = "SELECT * FROM project_comments ORDER BY ID_project_comments DESC LIMIT ?";
        ResultSet rs = null;
        ArrayList<TaskComments> arrayList = new ArrayList<TaskComments>();
        TaskComments taskComments;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                taskComments = new TaskComments();
                taskComments.setId(rs.getInt("ID_project_comments"));
                taskComments.setContent(rs.getString("content_project_comments"));
                taskComments.setIdTask(rs.getInt("FK_ID_task"));


                arrayList.add(taskComments);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return arrayList;
    }
}
