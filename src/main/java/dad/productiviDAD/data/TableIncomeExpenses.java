package dad.productiviDAD.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

		String insert = "INSERT INTO incomesExpenses (amount, concept, date_incomeExpense) VALUES (?, ?, ?)";
		String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='incomesExpenses'";
		int id = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
			pstmt.setDouble(1, incomeExpense.getAmount());
			pstmt.setString(2, incomeExpense.getConcept());
			pstmt.setString(3, incomeExpense.getDate().toString());
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
		String update = "UPDATE incomesExpenses SET amount = ? , concept = ?, date_incomeExpense = ? WHERE ID_incomeExpense = ?";
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
			pstmt.setDouble(1, incomeExpense.getAmount());
			pstmt.setString(2, incomeExpense.getConcept());
			pstmt.setString(3, incomeExpense.getDate().toString());
			pstmt.setInt(4, incomeExpense.getId());
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
		String delete = "DELETE FROM incomesExpenses WHERE ID_incomeExpense = ?";
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
		String select = "SELECT * FROM incomesExpenses ORDER BY ID_incomeExpense DESC LIMIT ?";
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
				incomeExpense.setDate(LocalDate.parse(rs.getString("date_incomeExpense")));
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
