package dad.productiviDAD.sqliteutils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * The class used for connecting the app to the SQLite database.
 *
 */
public class JdbcSQLiteConnection {
	static Connection connection;
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:database/productiviDAD.db";
            connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void closeConnection() {
        try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}