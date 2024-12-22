import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import arbol.Akinator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FailController implements Initializable {

  @FXML
  private Button acceptButton;

  @FXML
  private Button helpButton;

  @FXML
  private TextField characterText;

  @FXML
  private TextField differenceText;

  @FXML
  private Text errorText;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

  }

  @FXML
  public void fail(){
    if(characterText.getText().trim().equals("") && differenceText.getText().trim().equals(""))
        errorText.setVisible(true);
    else{
        try {
            Akinator juego = Akinator.cargar();
            juego.fallar(characterText.getText(), differenceText.getText());
            juego.iniciarJuego();
            new WindowManager().abrirEscena((Stage)acceptButton.getScene().getWindow(), "juego.fxml",500,400);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
  }

  @FXML
  public void help(){
    try {
      Parent root = FXMLLoader.load(getClass().getResource("help.fxml"));
      Scene scene = new Scene(root,600,400);
      Stage stage = new Stage();
      stage.setTitle("Ayuda");
      stage.setScene(scene);
      stage.setResizable(false);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


}
