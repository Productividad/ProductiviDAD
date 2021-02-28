package dad.productividad.dataManager;


import dad.productividad.app.App;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class used to open and close the connection in the app to the SQLite database.
 */
public class JdbcConnection {
    /**
     * Connection connection
     */
    static Connection connection;

    /**
     * Opens the connection with the DB stored in user home
     */
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
//            String dbURL = "jdbc:sqlite::resource:database/productiviDAD.db";
            //TODO deprecate line above
			String dbURL = "jdbc:sqlite:" + System.getProperty("user.home")+ "\\." + App.APP_NAME + "\\productiviDAD.db";
            connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
//				System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
//				System.out.println("Driver name: " + dm.getDriverName());
//				System.out.println("Driver version: " + dm.getDriverVersion());
//				System.out.println("Product name: " + dm.getDatabaseProductName());
//				System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the connection
     */
    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
