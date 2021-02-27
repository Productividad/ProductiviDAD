package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.project.ProjectComment;


/**
 * Class used to interact with the Project_comments table in the database.
 */
public class TableProjectComments {


    /**
     * Insert a project Comment in DB
     * @param projectComment
     * @return the id of the inserted comment
     */
    public static int insertProjectComments(ProjectComment projectComment) {
        String insert = "INSERT INTO project_comments (content_project_comments, FK_ID_task) VALUES ( ?, ?)";
        String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='project_comments'";
        int id = 0;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
            pstmt.setString(1, projectComment.getContent());
            pstmt.setInt(2, projectComment.getIdTask());
            pstmt.executeUpdate();

            Statement stmt = JdbcConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(getPkId);

            while (rs.next()) {
                id = rs.getInt("seq");
            }
            projectComment.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

        return id;
    }


    /**
     * Updates a comment in DB
     * @param projectComment to be updated
     */
    public static void update(ProjectComment projectComment) {
        String update = "UPDATE project_comments SET  content_project_comments = ?, FK_ID_task = ? WHERE ID_project_comments = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
            pstmt.setString(1, projectComment.getContent());
            pstmt.setInt(2, projectComment.getIdTask());
            pstmt.setInt(3, projectComment.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
    }

    /**
     * Delete a project comment in the DB
     * @param projectComment
     */
    public static void delete(ProjectComment projectComment) {

        String delete = "DELETE FROM project_comments WHERE ID_project_comments = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
            pstmt.setInt(1, projectComment.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

    }


    /**
     *
     * @param number of comments to be read
     * @return A list of project comments
     */
    public static List<ProjectComment> read(int number) {
        String select = "SELECT * FROM project_comments ORDER BY ID_project_comments DESC LIMIT ?";
        ResultSet rs = null;
        ArrayList<ProjectComment> arrayList = new ArrayList<ProjectComment>();
        ProjectComment projectComment;

        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                projectComment = new ProjectComment();
                projectComment.setId(rs.getInt("ID_project_comments"));
                projectComment.setContent(rs.getString("content_project_comments"));
                projectComment.setIdTask(rs.getInt("FK_ID_project"));

                arrayList.add(projectComment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return arrayList;
    }
}
