package dad.productividad.dataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dad.productividad.note.Note;

/**
 * Class used to interact with the Notes table in the database.
 */
public class TableNotes {
    /**
     * Method to insert a new note in the database
     *
     * @param note The note to be inserted
     */
    public static int insertNote(Note note) {
        String insert = "INSERT INTO notes (content_note, FK_ID_page, color_note) VALUES ( ?, ?, ?)";
        String getPkId = "SELECT seq FROM sqlite_sequence WHERE name='notes'";
        int id = 0;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(insert);
            pstmt.setString(1, note.getContent());
            pstmt.setInt(2, note.getIdPage());
            pstmt.setString(3, "/css/notes/noteComponent1.css");
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

    /**
     * Method to get the color from a task
     *
     * @param id
     * @return a color from css
     */
    public static String returnColor(int id) {
        String query = "SELECT color_note FROM notes WHERE ID_note=?";
        String color = "";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                color = rs.getString("color_note");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }

        return color;
    }

    /**
     * Method to update an existing note from the table
     *
     * @param note The note from the registry to be updated
     */
    public static void update(Note note) {
        String update = "UPDATE notes SET  content_note = ?, FK_ID_page = ? WHERE ID_note = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
            pstmt.setString(1, note.getContent());
            pstmt.setInt(2, note.getIdPage());
            pstmt.setInt(3, note.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
    }

    /**
     * Method to update an existing note from the table
     *
     * @param note The note from the registry to be updated
     */
    public static void updateColor(Note note) {
        String update = "UPDATE notes SET  color_note = ? WHERE ID_note = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(update);
            pstmt.setString(1, note.getColor());
            pstmt.setInt(2, note.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
    }

    /**
     * Method to delete an existing note from the table
     *
     * @param note The note to be deleted
     */
    public static void delete(Note note) {
        String delete = "DELETE FROM notes WHERE ID_note = ?";
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(delete);
            pstmt.setInt(1, note.getId());
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
     * @param number The number of registries to be shown
     * @return arrayList An ArrayList of Note objects
     */
    public static List<Note> read(int number) {
        String select = "SELECT * FROM notes ORDER BY ID_note DESC LIMIT ?";
        ResultSet rs = null;
        ArrayList<Note> arrayList = new ArrayList<Note>();
        Note note;
        try {
            JdbcConnection.connect();
            PreparedStatement pstmt = JdbcConnection.connection.prepareStatement(select);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                note = new Note();
                note.setId(rs.getInt("ID_note"));
                note.setContent(rs.getString("content_note"));
                note.setIdPage(rs.getInt("FK_ID_page"));
                note.setColor(rs.getString("color_note"));

                arrayList.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.close();
        }
        return arrayList;
    }
}
