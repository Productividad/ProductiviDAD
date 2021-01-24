package dad.productiviDAD.sqliteutils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class used to interact with the IncomeExpenses table in the database.
 */
public class TableIncomeExpenses {
	/*
	 * Method to insert a new income or expense into the table
	 * 
	 * @param amount The amount of money of the transaction
	 * 
	 * @param concept The concept of the transaction
	 * 
	 * @return id The ID of the inserted registry
	 */
	public int insertIncomeExpense(double amount, String concept) {
		String insert = "INSERT INTO incomeExpenses (amount, concept, FK_ID_page) VALUES (?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='incomeExpenses'";
		int id = 0;
		try {
			PreparedStatement pstmt = JdbcSQLiteConnection.connection.prepareStatement(insert);
			pstmt.setDouble(1, amount);
			pstmt.setString(2, concept);
			pstmt.setString(3, "(SELECT id_page FROM pages where page_date=date('now'))");
			pstmt.executeUpdate();

			Statement stmt = JdbcSQLiteConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("ID_incomeExpense");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

	/*
	 * Method to update an existing income or expense from the table
	 * 
	 * @param id The ID from the registry to be updated
	 * 
	 * @param amount The amount of money of the transaction
	 * 
	 * @param concept The concept of the transaction
	 * 
	 */
	public void updateIncomeExpense(int id, double amount, String concept) {
		String update = "UPDATE incomeExpenses SET amount = ? , concept = ? WHERE id_incomeExpense = ?";
		try {
			PreparedStatement pstmt = JdbcSQLiteConnection.connection.prepareStatement(update);
			pstmt.setDouble(1, amount);
			pstmt.setString(2, concept);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method to delete an existing income or expense from the table
	 * 
	 * @param id The ID from the registry to be deleted
	 */
	public void deleteIncomeExpense(int id) {
		String delete = "DELETE FROM incomeExpenses WHERE id_incomeExpense = ?";
		try {
			PreparedStatement pstmt = JdbcSQLiteConnection.connection.prepareStatement(delete);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
