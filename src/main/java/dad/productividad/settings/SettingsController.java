package dad.productividad.settings;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import dad.productividad.app.App;
import dad.productividad.app.MainController;
import dad.productividad.balanceManager.CurrencyType;
import dad.productividad.dataManager.TasksReportDataProvider;
import dad.productividad.reports.ReportTask;
import dad.productividad.theme.Theme;
import dad.productividad.theme.ThemePicker;
import dad.productividad.utils.Preferences;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * Settings view controller class
 */
public class SettingsController implements Initializable {
    /**
     * Settings view
     */
    @FXML
    private StackPane view;
    /**
     * Bottom Pane, accept and reset dialog
     */
    @FXML
    private GridPane bottomPane, dialogAccept, dialogReset;
    /**
     * Theme wrapper
     */
    @FXML
    private VBox themeWrapper;
    /**
     * Scrollpane
     */
    @FXML
    private ScrollPane scroll;
    /**
     * Save, Reset, Export, Import buttons
     */
    @FXML
    private Button saveButton, resetButton, exportButton, importButton,generateReportButton;
    /**
     * Locale Picker
     */
    @FXML
    private ComboBox<Locale> localePicker;
    /**
     * Currency Picker
     */
    @FXML
    private ComboBox<CurrencyType> currencyPicker;
    /**
     * Currencies
     */
    private ListProperty<CurrencyType> currencies = new SimpleListProperty<>(FXCollections.observableArrayList(CurrencyType.values()));
    /**
     * Locales
     */
    private ListProperty<Locale> languages = new SimpleListProperty<>(FXCollections.observableArrayList(Locale.ENGLISH, new Locale("es"), Locale.FRENCH));
    /**
     * Selected theme
     */
    public static String selectedTheme;
    
    private static final String JRXML_FILE = "/reports/tasks.jrxml";
	private static final String REPORT_PDF_FILE_NAME = "tasks.pdf";

    private ThemePicker pickerBW = new ThemePicker();
    private ThemePicker pickerPB = new ThemePicker();
    private ThemePicker pickerGS = new ThemePicker();
    private ThemePicker pickerCS = new ThemePicker();
    private ThemePicker pickerSM = new ThemePicker();

    /**
     * Settings view initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedTheme = App.preferences.getTheme();

        setThemes();

        setSelectedThemeFromJSON();

        localePicker.setItems(languages);

        scroll.setFitToWidth(true);

        currencyPicker.setItems(currencies);

        localePicker.getSelectionModel().select(App.preferences.getLocale());

        currencyPicker.getSelectionModel().select(App.preferences.getCurrency());

        hideDialog();

    }

    /**
     * SettingsController constructor
     */
    public SettingsController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsView.fxml"));
        loader.setResources(ResourceBundle.getBundle("i18n/strings"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset button of bottomPane. Set dialogReset visible
     *
     * @param event
     */
    @FXML
    private void onResetAction(ActionEvent event) {
        dialogReset.setVisible(true);
    }

    /**
     * Accept button of bottomPane. Set dialogAccept visible
     *
     * @param event
     */
    @FXML
    private void onSaveAction(ActionEvent event) {
        dialogAccept.setVisible(true);
    }

    @FXML
    private void onGenerateReportAction(ActionEvent event) {

		try {
			DirectoryChooser chooser = new DirectoryChooser(); 
		    chooser.setInitialDirectory(new File("."));

		    File selectedDirectory = chooser.showDialog(App.getPrimaryStage());    

		    if (selectedDirectory != null) {
				// Map the parameters for the report
				Map<String, Object> parameters = new HashMap<String, Object>();
	
				List<ReportTask> tasks = TasksReportDataProvider.readReportTasks();
	
				Class cls = Class.forName(this.getClass().getName());
	
				// returns the ClassLoader object associated with this Class
				ClassLoader cLoader = cls.getClassLoader();
	
				// Getting the jrxml
				InputStream is = cls.getResourceAsStream(JRXML_FILE);
	
				// Compile the inputStream
				JasperReport report = JasperCompileManager.compileReport(is);
	
		        ResourceBundle rb = ResourceBundle.getBundle("i18n/strings");
				parameters.put("REPORT_TITLE", rb.getString("reportTitle"));
				parameters.put("TASK_TITLE_HEADER", rb.getString("reportTaskTitleHeader"));
				parameters.put("TASK_DATE_HEADER", rb.getString("reportTaskDateHeader"));
				
				parameters.put("TASKS", tasks);
				
				BufferedImage reportHeaderImage = ImageIO.read(getClass().getResource("/images/report-header.png"));
				parameters.put("HEADER_IMAGE", reportHeaderImage);
				BufferedImage reportCompletedTaskImage = ImageIO.read(getClass().getResource("/images/report-completed-task.png"));
				parameters.put("COMPLETED_TASK_IMAGE", reportCompletedTaskImage);
				BufferedImage reportNotCompletedTaskImage = ImageIO.read(getClass().getResource("/images/report-not-completed-task.png"));
				parameters.put("NOT_COMPLETED_TASK_IMAGE", reportNotCompletedTaskImage);
				
				// Generates the report combining the compiled report with the dataSource
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tasks);
				JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
	
				// Exports the report into a pdf file
				JasperExportManager.exportReportToPdfFile(print, selectedDirectory.getAbsolutePath() + "\\" + REPORT_PDF_FILE_NAME);
	
				// Opens up the file with the default program
				// Check if the desktop is supported
				if (!Desktop.isDesktopSupported()) {
					System.out.println("Desktop is not supported");
					return;
				}
	
				Desktop desktop = Desktop.getDesktop();
	
				File reportFile = new File(selectedDirectory.getAbsolutePath() + "\\" + REPORT_PDF_FILE_NAME);
				// After check if file exists and open it
				if (reportFile.exists()) {
					desktop.open(reportFile);
				}
		    }


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * Export your Settings and your Database as a .db file (zip)
     * https://www.baeldung.com/java-compress-and-uncompress
     *
     * @throws IOException
     */
    @FXML
    private void onExportAction() throws IOException {
        FileChooser saveDialog = new FileChooser();
        saveDialog.setInitialDirectory(new File("."));
        saveDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("ProductiviDAD (*.pdad)", "*.pdad"));
        File file = saveDialog.showSaveDialog(App.getPrimaryStage());
        if (file != null) {
            List<String> srcFiles = Arrays.asList("preferences.json", "productiviDAD.db");
            FileOutputStream fos = new FileOutputStream(file);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (String srcFile : srcFiles) {
                File fileToZip = new File(System.getProperty("user.home"), "." + App.APP_NAME + "/" + srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();
        }
    }

    /**
     * Import your Settings and Database from a .db file (zip)
     * https://www.baeldung.com/java-compress-and-uncompress
     */
    @FXML
    private void onImportAction() {
        try {
            FileChooser importDialog = new FileChooser();
            importDialog.setInitialDirectory(new File("."));
            importDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("ProductiviDAD (*.pdad)", "*.pdad"));
            File file = importDialog.showOpenDialog(App.getPrimaryStage());
            if (file != null) {
                File destDir = new File(System.getProperty("user.home"), "." + App.APP_NAME);
                byte[] buffer = new byte[1024];
                ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
                ZipEntry zipEntry = zis.getNextEntry();
                while (zipEntry != null) {
                    File newFile = new File(destDir, String.valueOf(zipEntry));
                    if (zipEntry.isDirectory()) {
                        if (!newFile.isDirectory() && !newFile.mkdirs()) {
                            throw new IOException("Failed to create directory " + newFile);
                        }
                    } else {
                        // fix for Windows-created archives
                        File parent = newFile.getParentFile();
                        if (!parent.isDirectory() && !parent.mkdirs()) {
                            throw new IOException("Failed to create directory " + parent);
                        }

                        // write file content
                        FileOutputStream fos = new FileOutputStream(newFile);
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                        fos.close();
                    }
                    zipEntry = zis.getNextEntry();
                }
                zis.closeEntry();
                zis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Preferences.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Accept button on acceptDialog.
     * Checks if any preferences are updated and change the JSON preferences file and
     * change the center of mainController borderPane to homeController view
     */
    @FXML
    private void onAcceptDialog() {

        if (!App.preferences.localeProperty().get().equals(localePicker.getValue())) {
            App.preferences.localeProperty().set(localePicker.getValue());
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!App.preferences.themeProperty().get().equals(selectedTheme)) {
            App.preferences.themeProperty().set(selectedTheme);
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!App.preferences.currencyProperty().get().equals(currencyPicker.getValue())) {
            App.preferences.currencyProperty().set(currencyPicker.getValue());
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dialogAccept.setVisible(false);
        MainController.mainController.changeTheme();
        MainController.mainController.getMenuBarController().onHomeManagerSection();
    }

    /**
     * Accept button in reset dialog
     */
    @FXML
    private void onAcceptDialogReset() throws IOException {
        //TODO Resetear las cosas de resetear
        dialogReset.setVisible(false);
        App.preferences = new Preferences();
        App.preferences.save();
    }

    /**
     * Cancel button in reset dialog
     */
    @FXML
    private void onCancelDialogReset() {
        hideDialog();
    }

    
    
    
    /**
     * Hide dialogAccept and DialogReset
     */
    public void hideDialog() {
        dialogAccept.setVisible(false);
        dialogReset.setVisible(false);
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


    /**
     * Set the themes availables on themeWrapper
     */
    private void setThemes() {

        //Black and White
        Theme blackAndWhite = new Theme();
        blackAndWhite.setTitle("Black and White");
        blackAndWhite.setPalette("transparent", "transparent", "transparent", "#FFFFFF", "#E0DBDF", "#4C4C4C");
        blackAndWhite.setPath("/css/Themes/BlackAndWhite.css");
        pickerBW.setTheme(blackAndWhite);
        pickerBW.getStyleClass().addAll("theme-component", "black-and-white-theme");

        //Princess Bubblegum
        Theme princessBubblegum = new Theme();
        princessBubblegum.setTitle("Princess Bubblegum");
        princessBubblegum.setPalette("transparent", "transparent", "transparent", "#E4E0E4", "#F5D8DA", "#EBB3B4");
        princessBubblegum.setPath("/css/Themes/PrincessBubblegum.css");
        pickerPB.setTheme(princessBubblegum);
        pickerPB.getStyleClass().addAll("theme-component", "princess-bubblegum-theme");

        Theme grapeSoda=new Theme(); 
        grapeSoda.setTitle("Grape Soda");
        grapeSoda.setPalette("transparent", "transparent", "transparent", "#AF929D", "#A06B75", "#874F59");
        grapeSoda.setPath("/css/Themes/GrapeSoda.css");
        pickerGS.setTheme(grapeSoda);
        pickerGS.getStyleClass().addAll("theme-component", "grape-soda-theme");
        
        Theme citricSummer=new Theme();
        citricSummer.setTitle("Citric Summer");
        citricSummer.setPalette("transparent", "transparent", "transparent", "#ECE4B7", "#FFD485", "#FFC352");
        citricSummer.setPath("/css/Themes/CitricSummer.css");
        pickerCS.setTheme(citricSummer);
        pickerCS.getStyleClass().addAll("theme-component", "citric-summer-theme");

        Theme scaryMonsters=new Theme();
        scaryMonsters.setTitle("Scary Monsters");
        scaryMonsters.setPalette("transparent", "transparent", "transparent", "#ECE4B7", "#FFD485", "#FFC352");
        scaryMonsters.setPath("/css/Themes/ScaryMonsters.css");
        pickerSM.setTheme(scaryMonsters);
        pickerSM.getStyleClass().addAll("theme-component", "scary-monsters-theme");

        
        themeWrapper.getChildren().addAll(pickerBW, pickerPB, pickerGS, pickerCS, pickerSM);
    }

    /** 
     * Set the selected themePicker on themeWrapper from the preferences JSON
     */
    public void setSelectedThemeFromJSON() {
 
        if (pickerBW.getTheme().getPath().equals(App.preferences.getTheme()))
            pickerBW.setDisable(true);

        if (pickerPB.getTheme().getPath().equals(App.preferences.getTheme()))
            pickerPB.setDisable(true);

    }

    /**
     * Set all the themePickers on themeWrapper disable(false)
     */
    public void setAllThemesDisableFalse() {
        pickerBW.setDisable(false);
        pickerPB.setDisable(false);
        pickerGS.setDisable(false);
        pickerCS.setDisable(false);
    }

    /**
     * Return the view of this controller
     *
     * @return StackPane
     */
    public StackPane getView() {
        return view;
    }

}
