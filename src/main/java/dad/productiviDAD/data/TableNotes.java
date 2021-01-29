package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dad.productiviDAD.model.Note;

/*
 * Class used to interact with the Notes table in the database.
 */
public class TableNotes {
	/*
	 * Method to insert a new note in the database
	 * 
	 * @param note The note to be inserted
	 */
	public int insertNote(Note note) {
		String insert = "INSERT INTO notes (title_note, content_note, FK_ID_page) VALUES (?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='notes'";
		int id = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setString(1, note.getTitle());
			pstmt.setString(2, note.getContent());
			pstmt.setInt(3, note.getIdPage());
			pstmt.executeUpdate();

			Statement stmt = JdbcConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("seq");
			}
			note.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

		return id;

	}
	
	/*
	 * Method to get Registries from the table
	 * 
	 * @param number The number of registries to be shown
	 * 
	 * @return A ResultSet of registries.
	 */
	public ResultSet getRegistries(int number) {
		String select = "SELECT * FROM notes ORDER BY ID_note DESC LIMIT ?";
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
