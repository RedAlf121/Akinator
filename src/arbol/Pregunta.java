package arbol;

import java.io.Serializable;

public class Pregunta implements Serializable{

  public static final long serialVersionUID = 2L;
  private String preguntaDecision;
  private Pregunta casoSi;
  private Pregunta casoNo;

  public Pregunta(String pregunta, Pregunta casoSi, Pregunta casoNo) {
    crearPreguntaDecision(pregunta);
    setCasoSi(casoSi);
    setCasoNo(casoNo);
  }

  public String hacerPregunta() {
    return "¿"+preguntaDecision+"?";
  }

  public void crearPreguntaDecision(String pregunta) {
    if (
      pregunta == null || pregunta.equals("")
    ) throw new IllegalArgumentException("Pregunta vacía");
    this.preguntaDecision = pregunta;
  }

  public Pregunta getCasoSi() {
    return casoSi;
  }

  public void setCasoSi(Pregunta casoSi) {
    this.casoSi = casoSi;
  }

  public Pregunta getCasoNo() {
    return casoNo;
  }

  public void setCasoNo(Pregunta casoNo) {
    this.casoNo = casoNo;
  }
}
