/**
 * 
 */
package distribucion.interfaces;

/**
 * @author Mariop
 *
 */
public interface IDistribucionSalida
{
  /**
   * @param tiempoSimulacion
   * @return Tiempo desde el dado hasta el siguiente evento
   */
  public double getSiguienteTiempo(Double tiempoSimulacion);
}
