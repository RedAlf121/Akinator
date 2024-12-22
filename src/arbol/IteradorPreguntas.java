package arbol;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorPreguntas implements Iterator<Pregunta> {

  private Pregunta actual;
  private Pregunta anterior;

  private char answer;

  public IteradorPreguntas(Pregunta inicial) {
    setActual(inicial);
  }

  @Override
  public boolean hasNext() {
    return !(actual instanceof Adivinar);
  }

  @Override
  public Pregunta next() {
    if (!hasNext()) throw new NoSuchElementException();
    anterior = actual;
    if (answer == 'y') actual = actual.getCasoSi(); else actual =
      actual.getCasoNo();
    return actual;
  }

  public void setActual(Pregunta actual) {
    this.actual = actual;
  }

  public String hacerPregunta() {
    return actual.hacerPregunta();
  }

  public void setAnswer(char answer) {
    this.answer = answer;
  }

  public Pregunta getAnterior() {
    return anterior;
  }

public Pregunta getActual() {
    return actual;
}
}
