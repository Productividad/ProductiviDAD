package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/*
 * Class used to interact with the IncomeExpenses table in the database.
 */
public class TableTasks {

	/*
	 * Method to insert a new registry into the table
	 * 
	 * @param title The title of the task
	 * 
	 * @param description The description of the task
	 * 
	 * @param color The color of the task
	 * 
	 * @param deadline The deadline of the task
	 * 
	 * @param icon A route to the icon of the task
	 * 
	 * @param parentTask If the task is a subtask, the ID of the parent Task
	 * 
	 * @param projectID If the task belongs to a project, the ID of the project
	 * 
	 * @return id The id of the inserted task
	 */
	public int insertTask(String title, String description, String color, LocalDate deadline, String icon,
			int parentTask, int project) {
		String insert = "INSERT INTO tasks (title_task, completed, description_task, color_task, deadline_task, FK_ID_Page, FK_ID_Parent_task, FK_ID_project) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='tasks'";
		int id = 0;
		try {
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setString(1, title);
			pstmt.setInt(2, 0);
			pstmt.setString(3, description);
			pstmt.setString(4, color);
			pstmt.setString(5, deadline.toString());
			pstmt.setString(6, "(SELECT id_page FROM pages where page_date=date('now'))");
			pstmt.setString(7, (parentTask != 0) ? String.valueOf(parentTask) : "NULL");
			pstmt.setString(8, (project != 0) ? String.valueOf(project) : "NULL");
			pstmt.executeUpdate();

			Statement stmt = JdbcConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("ID_task");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

	/*
	 * Method to delete an existing income or expense from the table
	 * 
	 * @param id The ID from the registry to be deleted
	 */
	public void deleteIncomeExpense(int id) {
		String delete = "DELETE FROM tasks WHERE ID_task = ?";
		try {
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method to get Registries from the table
	 * 
	 * @param number The number of registries to be shown
	 * 
	 * @return A ResultSet of registries.
	 */
	public ResultSet getRegistries(int number) {
		String select = "SELECT * FROM tasks ORDER BY ID_task DESC LIMIT ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/*
	 * Method to update an existing income or expense from the table
	 * 
	 * @param id The ID from the registry to be updated
	 * 
	 * @param title Task title
	 * 
	 * @param description Task description
	 * 
	 * @param completed Status of the task
	 * 
	 * @param color Color of the task (HEX)
	 * 
	 * @param deadline Task deadline
	 * 
	 * @param icon Route to the icon
	 * 
	 * @param parentTask ID of the parent task
	 * 
	 * @param project ID of the project
	 * 
	 */
	public void updateIncomeExpense(int id, String title, String description, boolean completed, String color,
			LocalDate deadline, String icon, int parentTask, int project) {
		String update = "UPDATE incomeExpenses SET title_task = ? , completed = ?, description_task = ?, color_task ?, "
				+ "deadline_task = ?, FK_ID_Parent_Task = ?, FK_ID_Project = ?, icon_project = ? WHERE ID_task = ?";
		try {
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
			pstmt.setString(1, title);
			pstmt.setInt(2, (completed) ? 1 : 0);
			pstmt.setString(3, description);
			pstmt.setString(4, color);
			pstmt.setString(5, deadline.toString());
			pstmt.setInt(6, parentTask);
			pstmt.setInt(7, project);
			pstmt.setString(8, icon);
			pstmt.setInt(9, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
