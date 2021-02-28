package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.reports.TasksReport;

public class TasksReportDataProvider {

	public static List<TasksReport> readTasks(int number) {
		String select = "SELECT * FROM tasks ORDER BY title_task DESC LIMIT ?";
		ResultSet resultSet = null;
		ArrayList<TasksReport> arrayList = new ArrayList<TasksReport>();
		TasksReport task;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setInt(1, number);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				task = new TasksReport();
				task.setId(resultSet.getInt("ID_task"));
				task.setTitle(resultSet.getString("title_task"));
				task.setCompleted((resultSet.getBoolean("completed")));
				task.setIdPage(resultSet.getInt("FK_ID_Page"));

				arrayList.add(task);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return arrayList;
	}

}
