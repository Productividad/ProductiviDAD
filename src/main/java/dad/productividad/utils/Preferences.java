package dad.productividad.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import dad.productividad.balanceManager.CurrencyType;
import org.apache.commons.io.FileUtils;
import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import dad.productividad.app.App;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Preferences {

    private StringProperty theme = new SimpleStringProperty();
    private ObjectProperty<Locale> locale = new SimpleObjectProperty<>();
    private ObjectProperty<CurrencyType> currency = new SimpleObjectProperty<>();

    private static Gson GSON = FxGson
            .fullBuilder()
            .setPrettyPrinting()
            .create();

    private static File PREFS_FILE = new File(System.getProperty("user.home"), "." + App.APP_NAME + "/preferences.json");

    /**
     * @return Loads a preferences from user's home if it's found, or creates a new preferences file if not.
     * @throws IOException
     */
    public static Preferences load() throws IOException {
        try {
            return GSON.fromJson(new FileReader(PREFS_FILE, StandardCharsets.UTF_8), Preferences.class);
        } catch (FileNotFoundException e) {
            return new Preferences();
        }
    }

    /**
     * Saves the current user's preferences.
     *
     * @throws IOException
     */
    public void save() throws IOException {
        String jsonString = GSON.toJson(this, Preferences.class);
        FileUtils.writeStringToFile(PREFS_FILE, jsonString, StandardCharsets.UTF_8);
    }

    /**
     * Preferences Constructor with default values.
     */
    public Preferences() {
        setLocale(Locale.getDefault());
        setTheme("/css/Themes/BlackAndWhite.css");
        setCurrency(CurrencyType.EURO);
    }

    /**
     * @return Preferences Locale.
     */
    public Locale getLocale() {
        return locale.get();
    }

    /**
     * @return ObjectProperty of Locale
     */
    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    /**
     * Sets a new Locale
     *
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.locale.set(locale);
    }

    /**
     * @return StringProperty of current theme
     */
    public final StringProperty themeProperty() {
        return this.theme;
    }

    /**
     * @return String of current theme
     */
    public final String getTheme() {
        return this.themeProperty().get();
    }

    /**
     * Sets a new theme
     *
     * @param theme
     */
    public final void setTheme(final String theme) {
        this.themeProperty().set(theme);
    }

    /**
     * @return Current currency type.
     */
    public CurrencyType getCurrency() {
        return currency.get();
    }

    /**
     * @return ObjectProperty of currency type.
     */
    public ObjectProperty<CurrencyType> currencyProperty() {
        return currency;
    }

    /**
     * Sets a new currency
     *
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency.set(currency);
    }
}
