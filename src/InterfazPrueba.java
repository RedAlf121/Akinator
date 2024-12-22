import arbol.Akinator;

import java.io.IOException;
import java.util.Scanner;

public final class InterfazPrueba {

  private InterfazPrueba() {}

  public static void jugar() {
    try {
      Akinator juego;
      juego = Akinator.cargar();
      cicloJuego(juego);
      Akinator.guardar();
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  
  private static void cicloJuego(Akinator juego) {
    boolean adivinando;
    boolean continuePlaying = false;
    try (Scanner scan = new Scanner(System.in)) {
        do {
          System.out.println(juego.preguntar());
          char respuesta = scan.nextLine().charAt(0);
          adivinando = juego.tomarDecision(respuesta);
          if (!adivinando && respuesta == 'y') System.out.println(
            "Adiviné"
          ); else if (!adivinando) {
            System.out.println(
              "Oh vaya. ¿En qué personaje estabas pensando? ¿En que se diferencian?"
            );
            String personaje = scan.nextLine();
            String diferencia = scan.nextLine();
            System.out.println("Aprendido");
            juego.fallar(personaje, diferencia);
          }
          if (!adivinando) {
            System.out.println("¿Quieres seguir jugando?");
            respuesta = scan.nextLine().charAt(0);
            continuePlaying = (respuesta == 'y');
            if (continuePlaying) juego.iniciarJuego();
          }
        } while (adivinando || continuePlaying);
    }
  }
  
}
