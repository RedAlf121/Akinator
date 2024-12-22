import arbol.Akinator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class JuegoController implements Initializable {

  @FXML
  private Button yesButton;

  @FXML
  private Button noButton;

  @FXML
  private Button backButton;

  @FXML
  private TextArea textArea;

  @FXML
  private ProgressIndicator ready;

  private boolean startingGame = false;
  private boolean restartingGame = false;
  private Akinator juego;

  private boolean adivinando = true;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {}

  @FXML
  public void casoSi() {
    try {
      if (restartingGame) {
        juego.iniciarJuego();
        new WindowManager()
          .abrirEscena(
            (Stage) yesButton.getScene().getWindow(),
            "juego.fxml",
            500,
            400
          );
      } else if (!startingGame) {
        textArea.setText("");
        startingGame = true;
      }
      if (juego == null) {
        juego = Akinator.cargar();
      } else if (!restartingGame) {
        adivinando = juego.tomarDecision('y');
      }
      if (adivinando) {
        textArea.setText(juego.preguntar());
        ready.setProgress(juego.porcentajeAltura());
      } else {
        textArea.setText("Adiviné. ¿Desea jugar de nuevo?");
        restartingGame = true;
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void casoNo() {
    try {
      if (!startingGame) textArea.setText(
        "Ah bueno, tómate tu tiempo"
      ); else if (juego != null) {
        adivinando = juego.tomarDecision('n');
        if (adivinando) {
          textArea.setText(juego.preguntar());
          ready.setProgress(juego.porcentajeAltura());
        } else if (!restartingGame) {
          new WindowManager()
            .abrirEscena(
              (Stage) noButton.getScene().getWindow(),
              "fallo.fxml",
              800,
              400
            );
        } else irAtras();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void irAtras() {
    try {
      juego.iniciarJuego();
      new WindowManager()
        .abrirEscena(
          (Stage) backButton.getScene().getWindow(),
          "inicioJuego.fxml",
          600,
          400
        );
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
