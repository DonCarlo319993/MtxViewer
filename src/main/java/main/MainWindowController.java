package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    Stage stage;
    String tempNazwaPliku;


   @FXML
   Button przyciskWybierz, przyciskStart, przyciskPokazGraf;

   @FXML
   Label etykietaLokalizacji;

   @FXML
    CheckBox metodaMieszana, metodaBezposrednia;








    public static void zamknijProgram() throws IOException {
        org.apache.commons.io.FileUtils.cleanDirectory(new File("C:/MtxViewer/tymczasowaBazaGrafowa"));
        //org.apache.commons.io.FileUtils.cleanDirectory(new File("C:/MtxViewer/tymczasowyPlikCsv"));
        Platform.exit();
        System.exit(0);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void pokazDialogWindowDirectory(){
        try {
            DirectoryChooser dc = new DirectoryChooser();
            File selectedDir = dc.showDialog(getStage());
            etykietaLokalizacji.setText(selectedDir.toString());

        }catch (Exception e){

        }
    }

    @FXML
    public void pokazDialogWindowFile(){
        try {
            FileChooser fc = new FileChooser();
            File selectedFile = fc.showOpenDialog(getStage());
            etykietaLokalizacji.setText(selectedFile.toString());
            tempNazwaPliku = selectedFile.getName();

        }catch (Exception e){

        }
    }

    @FXML
    public void nowaBaza() throws IOException {
        if (metodaBezposrednia.isSelected() && !metodaMieszana.isSelected()) {
            // METODA PIERWSZA
            MetodaBezposrednia nowaBaza = new MetodaBezposrednia();
            nowaBaza.sciezkapliku = etykietaLokalizacji.getText().replace("\\\\", "/");
            nowaBaza.bazaM1();
        }else if (!metodaBezposrednia.isSelected() && metodaMieszana.isSelected()) {
            //METODA DRUGA
            MtxToCsv nowaBaza = new MtxToCsv();
            nowaBaza.sciezkapliku = etykietaLokalizacji.getText().replace("\\\\", "/");
            System.out.println("Zobaczmy jak nazywa sie plik: " + tempNazwaPliku);
            nowaBaza.nazwaPliku = tempNazwaPliku.replace(".mtx", ".csv");
            nowaBaza.bazaM2();
        }
    }


/*    public void otworzBrowser() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/browser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Browser");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        webEngine = webView.getEngine();
        webEngine.load("https://www.google.com");

    }*/
public void funkcja() throws IOException {
    BrowserController browserController = new BrowserController();
    browserController.otworzBrowser();
}





    public void metodaBezposredniSelected(){
        metodaMieszana.setSelected(false);
    }

    public void metodaMieszanaSelected(){
        metodaBezposrednia.setSelected(false);
    }

    public Stage getStage() {return stage; }
    void setStage(Stage stage){this.stage=stage;}


}
