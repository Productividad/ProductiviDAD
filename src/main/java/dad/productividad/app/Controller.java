package dad.productividad.app;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * Controller class, to be used in the future.
 */
public abstract class Controller implements Initializable {
    /**
     * The .fxml file to be loaded
     */
    private String fxml;

    /**
     * Controller Constructor
     * @param fxml
     * @throws IOException
     */
    public Controller(String fxml) throws IOException {
        this.fxml = fxml;
        loadUI();
    }

    /**
     * Loads the UI
     * @throws IOException
     */
    private void loadUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setResources(ResourceBundle.getBundle("i18n/strings", Locale.getDefault()));
        loader.setController(this);
        loader.load();
    }

    /**
     * Reloads UI, calling loadUI
     * @throws IOException
     */
    public void reloadUI() throws IOException {
        loadUI();
    }
}
