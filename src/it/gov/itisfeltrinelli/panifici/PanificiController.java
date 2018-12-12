/**
 * Sample Skeleton for 'Panifici.fxml' Controller Class
 */

package it.gov.itisfeltrinelli.panifici;

import java.util.List;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import PanificiModel.DAOPanifici;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PanificiController {
	DAOPanifici p=new DAOPanifici("root", "");
	
	
    public void setP(DAOPanifici p) {
		this.p = p;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProv"
    private ComboBox<String> cmbProv; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta"
    private ComboBox<String> cmbCitta; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void DoCerca(ActionEvent event) throws SQLException{
    	String prov=cmbProv.getValue();
    	String citta=cmbCitta.getValue();
    	List<String> risultato=p.getPanificio(prov, citta);
    	for (int i = 0; i < risultato.size(); i++) {
			txtResult.appendText(risultato.get(i)+"\n");
		}
    }

    @FXML
    void doSelezionaProvincia(ActionEvent event) {
    	ObservableList<String> options=
    			FXCollections.observableArrayList(
    					p.getCitta(cmbProv.getValue())
    					);
    	cmbCitta.setItems(options);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Panifici.fxml'.";
        assert cmbProv != null : "fx:id=\"cmbProv\" was not injected: check your FXML file 'Panifici.fxml'.";
        assert cmbCitta != null : "fx:id=\"cmbCitta\" was not injected: check your FXML file 'Panifici.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Panifici.fxml'.";
        ObservableList<String> optionsProvince =
        		 FXCollections.observableArrayList(
       				p.provincePerCmb()        		 );
        	cmbProv.setItems(optionsProvince);
        	txtResult.setEditable(false);
        	}
}
