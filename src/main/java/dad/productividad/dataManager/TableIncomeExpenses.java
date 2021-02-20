package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.balanceManager.IncomeExpense;

/**
 * Class used to interact with the IncomeExpenses table in the database.
 */
public class TableIncomeExpenses {
	/**
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

	/**
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

	/**
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

	/**
	 * Method to get Registries from the table
	 * 
	 * @param date
	 * 
	 * @return arrayList An ArrayList of incomeExpense objects
	 */
	public static List<IncomeExpense> read(LocalDate date, int filter) {
//		String select = "SELECT * FROM incomesExpenses ORDER BY ID_incomeExpense DESC LIMIT ?, 10";
		String selectNeg = "SELECT * FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ? AND amount < 0 ORDER BY date(date_incomeExpense) DESC";
		String selectPos = "SELECT * FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ? AND amount > 0 ORDER BY date(date_incomeExpense) DESC";
		String select = "SELECT * FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ? ORDER BY date(date_incomeExpense) DESC";
		ResultSet rs = null;
		ArrayList<IncomeExpense> arrayList = new ArrayList<IncomeExpense>();
		IncomeExpense incomeExpense;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = null;
			switch (filter){
				case 0:
					pstmt = JdbcConnection.connection.prepareStatement(select);
					break;
				case 1:
					 pstmt= JdbcConnection.connection.prepareStatement(selectPos);
					break;
				case 2:
					pstmt= JdbcConnection.connection.prepareStatement(selectNeg);
					break;
			}

//			pstmt.setInt(1, ((number-1)*10));
			pstmt.setString(1, (date.getMonthValue() < 10) ? "0"+String.valueOf(date.getMonthValue()) : String.valueOf(date.getMonthValue()));
			pstmt.setString(2, String.valueOf(date.getYear()));

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
	
	public static double getTotal(LocalDate date) {
		String query = "SELECT SUM(amount) FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ?";
		double amount = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(query);
			pstmt.setString(1, (date.getMonthValue() < 10) ? "0"+String.valueOf(date.getMonthValue()) : String.valueOf(date.getMonthValue()));
			pstmt.setString(2, String.valueOf(date.getYear()));

			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				amount = rs.getDouble("SUM(amount)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return amount;
	}
	public static double getTotal() {
		String query = "SELECT SUM(amount) FROM incomesExpenses";
		double amount = 0;
		try {
			JdbcConnection.connect();
			Statement stmt = JdbcConnection.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
				amount = rs.getDouble("SUM(amount)");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return amount;
	}

	public static boolean readContiguous(LocalDate date){
		String select = "SELECT ID_incomeExpense FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ? ORDER BY ID_incomeExpense DESC";
		ResultSet rs = null;
		int id = 0;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
			pstmt.setString(1, (date.getMonthValue() < 10) ? "0"+String.valueOf(date.getMonthValue()) : String.valueOf(date.getMonthValue()));
			pstmt.setString(2, String.valueOf(date.getYear()));
			rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt("ID_incomeExpense");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		if(id != 0)
			return true;
		else
			return false;
	}
//TODO use this method to find a month that isn't contiguous
	public static LocalDate findNext(IncomeExpense incomeExpense){
		String query = "SELECT date_incomeExpense FROM incomesExpenses WHERE strftime('%Y-%m', ?) > strftime('%Y-%m', date_incomeExpense) ORDER BY date(date_incomeExpense) DESC LIMIT 1";
		ResultSet rs = null;
		LocalDate date = null;
		try {
			JdbcConnection.connect();
			PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(query);
			pstmt.setString(1, incomeExpense.getDate().toString());
			rs = pstmt.executeQuery();
			if (rs.next())
				date = LocalDate.parse(rs.getString("date_incomeExpense"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.close();
		}
		return date;
	}
}
