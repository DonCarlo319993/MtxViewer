package main;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
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
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    Stage stage;
    String tempNazwaPliku;


   @FXML
   Button przyciskWybierz, przyciskStart;

   @FXML
   Label etykietaLokalizacji;

   @FXML
    CheckBox metodaMieszana, metodaBezposrednia;




    public static void zamknijProgram(){
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
    public void nowaBaza() throws FileNotFoundException {
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

    public void metodaBezposredniSelected(){
        metodaMieszana.setSelected(false);
    }

    public void metodaMieszanaSelected(){
        metodaBezposrednia.setSelected(false);
    }

    public Stage getStage() {return stage; }
    void setStage(Stage stage){this.stage=stage;}


}
