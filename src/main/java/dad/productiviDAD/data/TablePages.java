package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
 * Class used to interact with the Pages table in the database.
 */
public class TablePages {
	/*
	 * Method to insert a new page in the database
	 */
	public void insertPage() {
		String insert = "INSERT INTO pages (date_page) VALUES (?)";
		try {
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setString(1, "date('now')");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method to get the ID from a page
	 * 
	 * @param date The date of the page
	 * 
	 * @return id The ID of the page
	 */
	public int getID(LocalDate date) {
		String select = "SELECT ID_page FROM pages WHERE date_page = ?";
		int id = 0;
		try {
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setString(1, date.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
				id = rs.getInt("ID_page");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	/*
	 * Method to get Registries from the table
	 * 
	 * @param number The number of days we want to show
	 * 
	 * @return A ResultSet of registries.
	 */
	public ResultSet getRegistries(int number) {
		String select = "SELECT * FROM pages WHERE date_page < date('now','-? days')";
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

}
