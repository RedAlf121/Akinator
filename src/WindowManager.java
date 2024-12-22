import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class WindowManager {

  public void abrirEscena(Stage primaryStage, String fxml, int width, int heigth) throws IOException {
    if (fxml.endsWith("fxml")) {
      Parent root = FXMLLoader.load(getClass().getResource(fxml));
      Scene escenita = new Scene(root, width, heigth);
      primaryStage.setScene(escenita);
      primaryStage.setTitle("CUJINATOR");
      primaryStage.setResizable(false);
      primaryStage.show();
    }
  }

}
