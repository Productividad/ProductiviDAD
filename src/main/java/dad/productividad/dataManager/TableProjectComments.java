package dad.productividad.dataManager;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.project.ProjectComments;

/**
 * Class used to interact with the Notes table in the database.
 */
public class TableProjectComments {
    /**
     * Method to insert a new note in the database
     *
     * @param projectComments The note to be inserted
     */
    public static int insertProjectComments(ProjectComments projectComments) {
        String insert = "INSERT INTO project_comments (content_project_comments, FK_ID_project) VALUES ( ?, ?)";
        String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='project_comments'";
        int id = 0;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
            pstmt.setString(1, projectComments.getContent());
            pstmt.setInt(2, projectComments.getIdProject());
            pstmt.executeUpdate();

            Statement stmt = JdbcConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(getPkId);

            while (rs.next()) {
                id = rs.getInt("seq");
            }
            projectComments.setId(id);
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
     * @param projectComments The projectComments from the registry to be updated
     *
     */
    public static void update(ProjectComments projectComments) {
        String update = "UPDATE project_comments SET  content_project_comments = ?, FK_ID_project = ? WHERE ID_project_comments = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
            pstmt.setString(1, projectComments.getContent());
            pstmt.setInt(2, projectComments.getIdProject());
            pstmt.setInt(3, projectComments.getId());
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
     * @param projectComments The projectComment to be deleted
     */
    public static void delete(ProjectComments projectComments) {
        String delete = "DELETE FROM project_comments WHERE ID_project_comments = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
            pstmt.setInt(1, projectComments.getId());
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
    public static List<ProjectComments> read(int number) {
        String select = "SELECT * FROM project_comments ORDER BY ID_project_comments DESC LIMIT ?";
        ResultSet rs = null;
        ArrayList<ProjectComments> arrayList = new ArrayList<ProjectComments>();
        ProjectComments projectComments;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                projectComments = new ProjectComments();
                projectComments.setId(rs.getInt("ID_project_comments"));
                projectComments.setContent(rs.getString("content_project_comments"));
                projectComments.setIdProject(rs.getInt("FK_ID_project"));


                arrayList.add(projectComments);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return arrayList;
    }
}
