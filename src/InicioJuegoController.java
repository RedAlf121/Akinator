import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioJuegoController implements Initializable{

    @FXML
    private Button jugarButton;

    @FXML
    private Button salirButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jugarButton.setOnAction(event -> jugar(event));
        salirButton.setOnAction(event -> salir(event));        
    }

    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void jugar(ActionEvent event) {
        try {
            new WindowManager().abrirEscena((Stage)jugarButton.getScene().getWindow(), "juego.fxml",500,400);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
    
    
}
