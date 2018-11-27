/**
 * Clase generada y codificada por Mariop
 */
package estaciontrabajo;

import distribucion.implementaciones.Exponencial;

/**
 *
 * <b>Nombre de la clase</b>: EstacionReparacion
 * <p>
 * <b>Descripción</b>: Clase encargada de modelar una estación de reparación en
 * particular
 * </p>
 * 
 * @author Mariop
 *
 */
public class EstacionReparacion
{
  /**
   * El atributo maquinaReparacion de tipo Maquina se emplea para almacenar la
   * máquina que se está reparando.
   */
  private Maquina maquinaReparacion;

  /**
   * El atributo distribucion de tipo Exponencial se emplea para almacenar la
   * distribución exponencial
   */
  private final Exponencial distribucion = new Exponencial(1321549843);

  /**
   * El atributo tiempoOperacion de tipo Double se emplea para acumular el
   * tiempo que el operario ha estado trabajando
   */
  private Double tiempoOperacion = new Double(0);

  private Double tiempoSiguienteReparacion = new Double(-1);

  /**
   * Método encargado de mover la máquina fuera de la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public Maquina extraerMaquina()
  {
    Maquina maquinaExtraida = this.maquinaReparacion;
    this.maquinaReparacion = null;
    this.tiempoSiguienteReparacion = new Double(-1);
    maquinaExtraida.arreglar();
    return maquinaExtraida;
  }

  /**
   * Método encargado de introducir una máquina en la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>: Devuelve el tiempo generado que tardará esa máquina en
   * salir. Viene normalizado, no con arreglo a la simulación.
   *
   * @param maquina
   * @return
   */
  public Double introducirMaquina(Maquina maquina)
  {
    this.maquinaReparacion = maquina;
    this.tiempoSiguienteReparacion = this.distribucion
            .getSiguienteTiempo(this.tiempoOperacion);
    this.tiempoOperacion += this.tiempoSiguienteReparacion;
    return this.tiempoSiguienteReparacion;
  }

  /**
   * @return the tiempoOperacion
   */
  public Double getTiempoOperacion()
  {
    return this.tiempoOperacion;
  }

  /**
   * Método encargado de devolver true si hay una máquina en reparación y false
   * si no
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public boolean tieneMaquina()
  {
    return this.maquinaReparacion == null ? false : true;
  }

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public Double getSiguienteTiempo()
  {
    return this.tiempoSiguienteReparacion;
  }

}
