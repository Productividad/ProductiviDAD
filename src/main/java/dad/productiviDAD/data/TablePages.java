package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dad.productiviDAD.model.IncomeExpense;
import dad.productiviDAD.model.Page;

/*
 * Class used to interact with the Pages table in the database.
 */
public class TablePages {
	/*
	 * Method to insert a new page in the database
	 * 
	 * @param page The page to be inserted
	 */
	public static void insertPage(Page page) {
		String insert = "INSERT INTO pages (date_page) VALUES (?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='pages'";
		int id = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setString(1, page.getDate().toString());
			pstmt.executeUpdate();

			Statement stmt = JdbcConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("seq");
			}
			page.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to check if there's a page of current day
	 * 
	 * @return true if there's a page, false if there isn't.
	 */
	public static boolean todaysPage() {
		boolean result = false;
		String select = "SELECT ID_page FROM pages WHERE date_page = ?";
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setString(1, "date('now')");
			ResultSet rs = pstmt.executeQuery();

			result = (rs.next()) ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return result;
	}

	/*
	 * Method to get the ID from a page
	 * 
	 * @param page The page we are setting its id
	 * 
	 */
	public static void setID(Page page) {
		String select = "SELECT ID_page FROM pages WHERE date_page = ?";
		int id = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setString(1, page.getDate().toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
				id = rs.getInt("ID_page");
			page.setId(id);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to get Registries from the table
	 * 
	 * @param number The number of days we want to show
	 * 
	 * @return A ResultSet of registries.
	 */
	public static List<Page> getRegistries(int number) {
		String select = "SELECT * FROM pages WHERE date_page < date('now','-? days')";
		ResultSet rs = null;
		ArrayList<Page> arrayList = new ArrayList<Page>();
		Page page = new Page();
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				page.setId(rs.getInt("ID_page"));
				page.setDate(LocalDate.parse(rs.getString("date")));

				arrayList.add(page);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return arrayList;
	}

}
