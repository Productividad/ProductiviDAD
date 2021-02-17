package dad.productividad.app;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public abstract class Controller implements Initializable {

    private String fxml;

    public Controller(String fxml) throws IOException {
        this.fxml = fxml;
        loadUI();
    }

    private void loadUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setResources(ResourceBundle.getBundle("i18n/strings", Locale.getDefault()));
        loader.setController(this);
        loader.load();
    }

    public void reloadUI() throws IOException {
        loadUI();
    }
}
