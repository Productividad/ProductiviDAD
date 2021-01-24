package dad.productiviDAD.sqliteutils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class used to interact with the Notes table in the database.
 */
public class TableNotes {
	/*
	 * Method to insert a new note in the database
	 * 
	 * @param title The note title
	 * 
	 * @param content The note content
	 * 
	 * @return id The id of the note created
	 */
	public int insertNote(String title, String content) {
		String insert = "INSERT INTO notes (title_note, content_note, FK_ID_page) VALUES (?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='notes'";
		int id = 0;
		try {
			PreparedStatement pstmt = JdbcSQLiteConnection.connection.prepareStatement(insert);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, "(SELECT id_page FROM pages where page_date=date('now'))");
			pstmt.executeUpdate();

			Statement stmt = JdbcSQLiteConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("ID_page");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}
}
