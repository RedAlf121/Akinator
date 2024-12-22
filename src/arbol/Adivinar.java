package arbol;

public class Adivinar extends Pregunta {

  public static final long serialVersionUID = 3L;
  private static final String PREGUNTA_BASICA = "¿Estás pensando en ";
  private String personaje;

  public Adivinar(String personaje) {
    super(PREGUNTA_BASICA, null, null);
    setPersonaje(personaje);
  }

  public void setPersonaje(String personaje) {
    if (
      personaje == null || personaje.equals("")
    ) throw new IllegalArgumentException("Personaje vacío");
    this.personaje = personaje;
  }

  @Override
  public String hacerPregunta() {
    return PREGUNTA_BASICA + personaje + "?";
  }
}
