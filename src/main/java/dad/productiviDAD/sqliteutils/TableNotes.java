package dad.productiviDAD.sqliteutils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableNotes {

	public int insertNote(String title, String content) {
		String insert = "INSERT INTO notes (title_note, content_note, FK_ID_page) VALUES (?, ?, ?)";
		String getFkId = "SELECT id FROM pages where date=date('now')";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='pages'";
		int id = 0;
		int fkid = 0;
		try {
			Statement stmt = JdbcSQLiteConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getFkId);

			while (rs.next()) {
				fkid = rs.getInt("ID_note");
			}
			
			PreparedStatement pstmt = JdbcSQLiteConnection.connection.prepareStatement(insert);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, fkid);
			pstmt.executeUpdate();
			
			stmt = JdbcSQLiteConnection.connection.createStatement();
			rs = stmt.executeQuery(getPkId);
			
			while (rs.next()) {
				id = rs.getInt("ID_page");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}
}
