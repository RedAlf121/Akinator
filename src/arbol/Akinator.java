package arbol;

import fichero.ByteManager;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public final class Akinator {

  private static ArbolPreguntas cuestionario;
  private HashMap<Pregunta, Integer> alturas;

  private IteradorPreguntas cursoJuego;
  private static File archivo = new File("akinator.data");
  private static Akinator instance;

  private Akinator(String primeraPregunta) {
    cuestionario = new ArbolPreguntas(primeraPregunta);
    alturas = new HashMap<>();
  }

  private Akinator() throws IOException, ClassNotFoundException {
    if (archivo.exists()) {
      RandomAccessFile ras = new RandomAccessFile(archivo, "rw");
      cuestionario = (ArbolPreguntas) ByteManager.readObject(ras);
      ras.close();
    }
    alturas = new HashMap<>();
  }

  public void iniciarJuego() {
    cursoJuego = (IteradorPreguntas) cuestionario.iterator();
  }

  public String preguntar() {
    return cursoJuego.hacerPregunta();
  }

  public boolean tomarDecision(char respuesta) {
    cursoJuego.setAnswer(respuesta);
    boolean hasNext = cursoJuego.hasNext();
    if (hasNext) cursoJuego.next();
    return hasNext;
  }

  public void fallar(String personaje, String diferencia) {
    cuestionario.crearNuevaPregunta(
      personaje,
      diferencia,
      cursoJuego.getActual(),
      cursoJuego.getAnterior()
    );
  }

  public static Akinator cargar() throws IOException, ClassNotFoundException {
    if (instance == null) {
      Akinator juego;
      if (!archivo.exists()) juego =
        new Akinator("Michael Jackson"); else juego = new Akinator();      
      juego.iniciarJuego();
      instance = juego;
    }
    return instance;
  }

  public static void guardar() throws IOException, ClassNotFoundException {
    RandomAccessFile ras = new RandomAccessFile(archivo, "rw");
    ByteManager.writeObject(ras, cuestionario);
  }

  public double porcentajeAltura() {
    return 1.0 / (altura() + 1);
  }

  private int altura() {
    return (alturas.containsKey(cursoJuego.getActual()))
      ? alturas.get(cursoJuego.getActual())
      : cuestionario.altura(cursoJuego.getActual());
  }
}
