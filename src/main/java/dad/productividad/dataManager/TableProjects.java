package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.project.Project;

/**
 * Class used to interact with the Projects table in the database.
 */
public class TableProjects {
    /**
     * Method to insert a new Project into the table
     *
     * @param project The project to be inserted
     */
    public static void create(Project project) {

        String insert = "INSERT INTO projects " + "(title_project, progress, description_project, completed_project,"
                + " deadline_project, color_project, whitetext_project) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='projects'";
        int id = 0;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
            pstmt.setString(1, project.getTitle());
            pstmt.setDouble(2, project.getProgress());
            pstmt.setString(3, project.getDescription());
            pstmt.setInt(4, ((project.isCompleted()) ? 1 : 0));
            pstmt.setString(5, project.getDeadLine().toString());
            pstmt.setString(6, project.getColor());
            pstmt.setInt(7, (project.isWhite()) ? 1 : 0);
            pstmt.executeUpdate();

            Statement stmt = JdbcConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(getPkId);

            while (rs.next()) {
                id = rs.getInt("seq");
            }
            project.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

    }

    /**
     * Method to update an existing Project from the table
     *
     * @param project The project from the registry to be updated
     */
    public static void update(Project project) {
        String update = "UPDATE projects SET "
                + "title_project = ?, progress = ?, description_project = ?,"
                + " completed_project = ?, deadline_project = ?, color_project = ?, whitetext_project = ? "
                + "WHERE ID_project = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
            pstmt.setString(1, project.getTitle());
            pstmt.setDouble(2, project.getProgress());
            pstmt.setString(3, project.getDescription());
            pstmt.setInt(4, ((project.isCompleted()) ? 1 : 0));
            pstmt.setString(5, project.getDeadLine().toString());
            pstmt.setString(6, project.getColor());
            pstmt.setInt(7, (project.isWhite()) ? 1 : 0);
            pstmt.setInt(8, project.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

    }

    /**
     * Method to delete an existing project from the table
     *
     * @param project The project to be deleted
     */
    public static void delete(Project project) {
        String delete = "DELETE FROM projects WHERE ID_project = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
            pstmt.setInt(1, project.getId());
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
     * @return arrayList An ArrayList of incomeExpense objects
     */
    public static List<Project> read(int number) {
        String select = "SELECT * FROM projects ORDER BY ID_project DESC LIMIT ?";
        ResultSet rs = null;
        ArrayList<Project> arrayList = new ArrayList<Project>();
        Project project;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                project = new Project();
                project.setId(rs.getInt("ID_project"));
                project.setTitle(rs.getString("title_project"));
                project.setProgress(rs.getDouble("progress"));
                project.setDescription(rs.getString("description_project"));
                project.setCompleted((rs.getInt("completed_project") == 1) ? true : false);
                project.setDeadLine(LocalDate.parse(rs.getString("deadline_project")));
                project.setColor(rs.getString("color_project"));
                project.setWhite((rs.getInt("whitetext_project") == 1) ? true : false);

                arrayList.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return arrayList;
    }
}
