package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dad.productiviDAD.balanceManager.IncomeExpense;

/*
 * Class used to interact with the IncomeExpenses table in the database.
 */
public class TableIncomeExpenses {
	/*
	 * Method to insert a new income or expense into the table
	 * 
	 * @param incomeExpense The income or expense to be created
	 */
	public static void create(IncomeExpense incomeExpense) {

		String insert = "INSERT INTO incomeExpenses (amount, concept, FK_ID_page) VALUES (?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='incomeExpenses'";
		int id = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setDouble(1, incomeExpense.getAmount());
			pstmt.setString(2, incomeExpense.getConcept());
			pstmt.setString(3, "(SELECT id_page FROM pages where page_date=date('now'))");
			pstmt.executeUpdate();

			Statement stmt = JdbcConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(getPkId);

			while (rs.next()) {
				id = rs.getInt("seq");
			}
			incomeExpense.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to update an existing income or expense from the table
	 * 
	 * @param incomeExpense The income or expense from the registry to be updated
	 * 
	 */
	public static void update(IncomeExpense incomeExpense) {
		String update = "UPDATE incomeExpenses SET amount = ? , concept = ? WHERE ID_incomeExpense = ?";
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
			pstmt.setDouble(1, incomeExpense.getAmount());
			pstmt.setString(2, incomeExpense.getConcept());
			pstmt.setInt(3, incomeExpense.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to delete an existing income or expense from the table
	 * 
	 * @param incomeExpense The income or expense to be deleted
	 */
	public static void delete(IncomeExpense incomeExpense) {
		String delete = "DELETE FROM incomeExpenses WHERE ID_incomeExpense = ?";
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
			pstmt.setInt(1, incomeExpense.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}

	}

	/*
	 * Method to get Registries from the table
	 * 
	 * @param number The number of registries to be shown
	 * 
	 * @return arrayList An ArrayList of incomeExpense objects
	 */
	public static List<IncomeExpense> read(int number) {
		String select = "SELECT * FROM incomeExpenses ORDER BY ID_incomeExpense DESC LIMIT ?";
		ResultSet rs = null;
		ArrayList<IncomeExpense> arrayList = new ArrayList<IncomeExpense>();
		IncomeExpense incomeExpense;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				incomeExpense = new IncomeExpense();
				incomeExpense.setId(rs.getInt("ID_incomeExpense"));
				incomeExpense.setAmount(rs.getDouble("amount"));
				incomeExpense.setConcept(rs.getString("concept"));
				arrayList.add(incomeExpense);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return arrayList;
	}
}
