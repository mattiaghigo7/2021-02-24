
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	Match m = this.cmbMatch.getValue();
    	if(m==null) {
    		this.txtResult.setText("Selezionare un match.");
    		return;
    	}
    	model.creaGrafo(m);
    	this.txtResult.setText("Grafo creato\n");
    	this.txtResult.appendText("# VERTICI: "+model.getVerticiSize()+"\n");
    	this.txtResult.appendText("# ARCHI: "+model.getArchiSize()+"\n");
    	this.btnGiocatoreMigliore.setDisable(false);
    	this.txtN.setDisable(false);
    	this.btnSimula.setDisable(false);
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {    	
    	this.txtResult.setText("Giocatore migliore:\n");
    	this.txtResult.appendText(model.getGiocatoreMigliore().toString()+", delta efficienza = "+Math.round(model.getDelta()*1000.0)/1000.0);
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	String input = this.txtN.getText();
    	if(input=="") {
    		this.txtResult.setText("Inserire un valore N.");
    		return;
    	}
    	try {
    		Integer N = Integer.parseInt(input);
    		model.simula(N);
    		this.txtResult.setText("Simulazione eseguita\n");
    		this.txtResult.appendText("Goal squadra di casa: "+model.getGoal1()+"\n");
    		this.txtResult.appendText("Goal squadra in trasferta: "+model.getGoal2()+"\n");
    		this.txtResult.appendText("Espulsioni squadra di casa: "+model.getEsp1()+"\n");
    		this.txtResult.appendText("Espulsioni squadra in trasferta: "+model.getEsp2()+"\n");
    	} catch (NumberFormatException e) {
    		this.txtResult.setText("Il valore immesso non è valido.");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbMatch.getItems().addAll(model.getAllMatches());
    	this.btnGiocatoreMigliore.setDisable(true);
    	this.txtN.setDisable(true);
    	this.btnSimula.setDisable(true);
    }
}
