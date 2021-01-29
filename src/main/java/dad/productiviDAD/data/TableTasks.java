package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dad.productiviDAD.controller.MainController;
import dad.productiviDAD.controller.TaskManagerController;
import dad.productiviDAD.model.Note;
import dad.productiviDAD.model.Task;

/*
 * Class used to interact with the IncomeExpenses table in the database.
 */
public class TableTasks {

	/*
	 * Method to insert a new registry into the table
	 * 
	 * @param task The task to be inserted
	 */
	public static void insert(Task task) {
		String insert = "INSERT INTO tasks (title_task, completed, description_task, color_task, deadline_task, FK_ID_Page, FK_ID_Parent_task, FK_ID_project, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='tasks'";
		int id = 0;
		try {
			task.setCompleted(false);
			task.setPage(MainController.getTodaysPage());
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setString(1, task.getTitle());
			pstmt.setInt(2, 0);
			pstmt.setString(3, task.getDescription());
			pstmt.setString(4, task.getColor());
			pstmt.setString(5, task.getDeadLine().toString());
			pstmt.setString(6, "SELECT id_page FROM pages where page_date=date('now')");
			pstmt.setString(7,
					(task.getParentTask().getId() != 0) ? String.valueOf(task.getParentTask().getId()) : "NULL");
			pstmt.setString(8, (task.getProject().getId() != 0) ? String.valueOf(task.getProject().getId()) : "NULL");
			pstmt.executeUpdate();

			Statement stmt = JdbcConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("seq");
			}
			task.setId(id);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to delete an existing income or expense from the table
	 * 
	 * @param task The task to be deleted
	 */
	public void delete(Task task) {
		String delete = "DELETE FROM tasks WHERE ID_task = ?";
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
			pstmt.setInt(1, task.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to get parent Tasks from the table
	 * 
	 * 
	 * @return arrayList List of registries.
	 */
	public List<Task> readParentTasks() {
		String select = "SELECT * FROM tasks WHERE FK_ID_Parent_task = NULL";
		ResultSet rs = null;
		ArrayList<Task> arrayList = new ArrayList<Task>();
		Task task;
		try {
			JdbcConnection.connect();
			Statement stmt = JdbcConnection.connection.createStatement();
			rs = stmt.executeQuery(select);
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt("ID_Task"));
				task.setTitle(rs.getString("title_task"));
				task.setCompleted((rs.getInt("completed") == 1) ? true : false);
				task.setDescription(rs.getString("description_task"));
				task.setColor(rs.getString("color_task"));
				task.setDeadLine(LocalDate.parse(rs.getString("deadline_task")));
				task.setPage(MainController.getTodaysPage());
				//Cargar tareas padre
				
				//task.setParentTask(rs.getInt("FK_ID_Parent_task");
				//Cargar proyectos
				//task.setProject(rs.getInt("FK_ID_project");
				task.setPriority((rs.getInt("priority_task") == 1) ? true : false);
				
				arrayList.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnection.close();
		}
		return arrayList;
	}
	/*
	 * Method to get child tasks from the table
	 * 
	 * @param number The number of registries to be shown
	 * 
	 * @return A ResultSet of registries.
	 */
	public void readChildTasks(Task parentTask) {
		String select = "SELECT * FROM tasks WHERE FK_ID_Parent_task = ?";
		ResultSet rs = null;
		Task task;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setInt(1, parentTask.getId());
			rs = pstmt.executeQuery(select);
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt("ID_Task"));
				task.setTitle(rs.getString("title_task"));
				task.setCompleted((rs.getInt("completed") == 1) ? true : false);
				task.setDescription(rs.getString("description_task"));
				task.setColor(rs.getString("color_task"));
				task.setDeadLine(LocalDate.parse(rs.getString("deadline_task")));
				task.setPage(MainController.getTodaysPage());
				task.setParentTask(parentTask);
				//Cargar proyectos
				//task.setProject(rs.getInt("FK_ID_project");
				task.setPriority((rs.getInt("priority_task") == 1) ? true : false);
				
				parentTask.getChildTasks().add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnection.close();
		}
	}
	/*
	 * Method to update an existing task from the table
	 * 
	 * @param task The task to be updated
	 * 
	 */
	public void update(Task task) {
		String update = "UPDATE tasks SET title_task = ? , completed = ?, description_task = ?, color_task ?, "
				+ "deadline_task = ?, FK_ID_Parent_Task = ?, FK_ID_Project = ?, priority_task = ? WHERE ID_task = ?";
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
			pstmt.setString(1, task.getTitle());
			pstmt.setInt(2, (task.isCompleted()) ? 1 : 0);
			pstmt.setString(3, task.getDescription());
			pstmt.setString(4, task.getColor());
			pstmt.setString(5, task.getDeadLine().toString());
			pstmt.setInt(6, task.getParentTask().getId());
			pstmt.setInt(7, task.getProject().getId());
			pstmt.setInt(8, (task.isPriority()) ? 1 : 0);
			pstmt.setInt(9, task.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}
}
