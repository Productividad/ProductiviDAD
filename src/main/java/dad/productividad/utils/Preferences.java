package dad.productividad.utils;

import com.google.gson.Gson;
import dad.productividad.app.App;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.commons.io.FileUtils;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Preferences {

    private static Gson GSON = FxGson
            .fullBuilder()
            .setPrettyPrinting()
            .create();

    private static File PREFS_FILE = new File(System.getProperty("user.home"), "." + App.APP_NAME + "/preferences.json");

    public static Preferences load() throws IOException {
        try {
            return GSON.fromJson(new FileReader(PREFS_FILE, StandardCharsets.UTF_8), Preferences.class);
        } catch (FileNotFoundException e) {
            return new Preferences();
        }
    }

    public void save() throws IOException {
        String jsonString = GSON.toJson(this, Preferences.class);
        FileUtils.writeStringToFile(PREFS_FILE, jsonString, StandardCharsets.UTF_8);
    }

    public Preferences() {
        setTheme(Theme.DARK);
        setLocale(Locale.getDefault());
    }

    private ObjectProperty<Theme> theme = new SimpleObjectProperty<>();
    private ObjectProperty<Locale> locale = new SimpleObjectProperty<>();

    public Theme getTheme() {
        return theme.get();
    }

    public ObjectProperty<Theme> themeProperty() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme.set(theme);
    }

    public Locale getLocale() {
        return locale.get();
    }

    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale.set(locale);
    }
}
