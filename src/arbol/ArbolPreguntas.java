package arbol;

import java.io.Serializable;
import java.util.Iterator;

public class ArbolPreguntas implements Serializable, Iterable<Pregunta> {

  public static final long serialVersionUID = 1L;
  private Pregunta raiz;

  public ArbolPreguntas(String personajeInicial) {
    crearPreguntaInicial(personajeInicial);
  }

  public Pregunta getRaiz() {
    return raiz;
  }

  public void crearPreguntaInicial(String personajeInicial) {
    raiz = new Adivinar(personajeInicial);
  }

  public void crearNuevaPregunta(
    String personajeSeleccionado,
    String diferencia,
    Pregunta preguntaActual,
    Pregunta preguntaAnterior
  ) {
    Pregunta preguntaPersonaje = new Adivinar(personajeSeleccionado);
    Pregunta nuevaPregunta = new Pregunta(
      diferencia,
      preguntaPersonaje,
      preguntaActual
    );
    if (preguntaAnterior != null) {
      if (
        preguntaAnterior.getCasoSi().equals(preguntaActual)
      ) preguntaAnterior.setCasoSi(
        nuevaPregunta
      ); else preguntaAnterior.setCasoNo(nuevaPregunta);
    } else raiz = nuevaPregunta;
  }

  @Override
  public Iterator<Pregunta> iterator() {
    return new IteradorPreguntas(raiz);
  }

  public int altura(Pregunta actual) {
    return (actual != null)
      ? Math.max(altura(actual.getCasoSi()), altura(actual.getCasoNo())) + 1
      : -1;
  }  
}
