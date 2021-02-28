package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dad.productividad.reports.ReportTask;

public class TasksReportDataProvider {

	/**
	 * Returns a list of tasks to be provided to the report
	 *
	 * @return			List of tasks
	 */
	public static List<ReportTask> readReportTasks() {
		String select = "select t.title_task as 'title', t.description_task as 'description', coalesce(t.completed, 0) as 'completed', coalesce(t.completed_date, p.date_page) as 'date'\r\n" + 
				"from tasks t\r\n" + 
				"left join pages p on p.ID_page = t.FK_ID_page\r\n" + 
				"order by date asc;";
		ResultSet resultSet = null;
		ArrayList<ReportTask> arrayList = new ArrayList<>();
		try {
			JdbcConnection.connect();
	        ResourceBundle rb = ResourceBundle.getBundle("i18n/strings");
			DateTimeFormatter sourceFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(rb.getString("reportDateFormat"));
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				ReportTask reportTask = new ReportTask();
				reportTask.setTitle(resultSet.getString("title"));
				reportTask.setDescription(resultSet.getString("description"));
				reportTask.setCompleted(resultSet.getInt("completed") == 1 ? true : false);
				String date = resultSet.getString("date");
				if (date != null) {
					reportTask.setDate(outputFormatter.format(LocalDate.parse(date, sourceFormatter)));
				}

				arrayList.add(reportTask);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return arrayList;
	}
}
