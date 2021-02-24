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
     * @param filter 0 for all registries, 1 for positive registries, 2 for negative registries
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
            switch (filter) {
                case 0:
                    pstmt = JdbcConnection.connection.prepareStatement(select);
                    break;
                case 1:
                    pstmt = JdbcConnection.connection.prepareStatement(selectPos);
                    break;
                case 2:
                    pstmt = JdbcConnection.connection.prepareStatement(selectNeg);
                    break;
            }

//			pstmt.setInt(1, ((number-1)*10));
            pstmt.setString(1, (date.getMonthValue() < 10) ? "0" + String.valueOf(date.getMonthValue()) : String.valueOf(date.getMonthValue()));
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

    /**
     * Method to get month balance
     * @param date A date from the month
     * @param filter 0 for all registries, 1 for positive registries, 2 for negative registries
     * @return Balance
     */
    public static double getTotal(LocalDate date, int filter) {
        String queryAll = "SELECT SUM(amount) FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ?";
        String queryNeg = "SELECT SUM(amount) FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ? AND amount < 0";
        String queryPos = "SELECT SUM(amount) FROM incomesExpenses WHERE strftime('%m', date_incomeExpense) = ? AND strftime('%Y', date_incomeExpense) = ? AND amount > 0";
        double amount = 0;
        PreparedStatement pstmt = null;
        try {
            JdbcConnection.connect();
            switch (filter) {
                case 0:
                    pstmt = JdbcConnection.connection.prepareStatement(queryAll);
                    break;
                case 1:
                    pstmt = JdbcConnection.connection.prepareStatement(queryPos);
                    break;
                case 2:
                    pstmt = JdbcConnection.connection.prepareStatement(queryNeg);
                    break;
            }
            pstmt.setString(1, (date.getMonthValue() < 10) ? "0" + String.valueOf(date.getMonthValue()) : String.valueOf(date.getMonthValue()));
            pstmt.setString(2, String.valueOf(date.getYear()));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                amount = rs.getDouble("SUM(amount)");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return amount;
    }
    /**
     * Method to get overall balance
     * @param filter 0 for all registries, 1 for positive registries, 2 for negative registries
     * @return Balance
     */
    public static double getTotal(int filter) {
        String queryAll = "SELECT SUM(amount) FROM incomesExpenses";
        String queryNeg = "SELECT SUM(amount) FROM incomesExpenses WHERE amount < 0";
        String queryPos = "SELECT SUM(amount) FROM incomesExpenses WHERE amount > 0";
        double amount = 0;
        Statement stmt;
        ResultSet rs = null;
        try {
            JdbcConnection.connect();
            stmt = JdbcConnection.connection.createStatement();
            switch (filter) {
                case 0:
                    rs = stmt.executeQuery(queryAll);
                    break;
                case 1:
                    rs = stmt.executeQuery(queryPos);
                    break;
                case 2:
                    rs = stmt.executeQuery(queryNeg);
                    break;
            }

            while (rs.next())
                amount = rs.getDouble("SUM(amount)");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return amount;
    }

    /**
     * Method to find the next or previous Month with registries
     * @param indexDate A date of reference
     * @param filter 0 for next, 1 for previous
     * @return
     */
    public static LocalDate findNext(LocalDate indexDate, int filter) {
        String queryNext = "SELECT date_incomeExpense FROM incomesExpenses WHERE strftime('%Y-%m', ?) < strftime('%Y-%m', date_incomeExpense) ORDER BY date(date_incomeExpense) DESC LIMIT 1";
        String queryPrevious = "SELECT date_incomeExpense FROM incomesExpenses WHERE strftime('%Y-%m', ?) > strftime('%Y-%m', date_incomeExpense) ORDER BY date(date_incomeExpense) ASC LIMIT 1";
        ResultSet rs = null;
        LocalDate date = null;
        PreparedStatement pstmt;
        try {
            JdbcConnection.connect();
            if (filter == 0)
                pstmt = JdbcConnection.connection.prepareStatement(queryNext);
            else
                pstmt = JdbcConnection.connection.prepareStatement(queryPrevious);
            pstmt.setString(1, indexDate.toString());
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
