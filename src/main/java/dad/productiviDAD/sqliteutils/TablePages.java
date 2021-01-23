package dad.productiviDAD.sqliteutils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablePages {
	
	public void insertPage() {
		String insert = "INSERT INTO pages (date_page) VALUES (?)";
		try {
			PreparedStatement pstmt = JdbcSQLiteConnection.connection.prepareStatement(insert);
			pstmt.setString(1, "date('now')");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
