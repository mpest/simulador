/**
 * Clase generada y codificada por Mariop
 */
package distribucion.implementaciones;

import java.util.Random;

import org.apache.commons.rng.simple.JDKRandomBridge;
import org.apache.commons.rng.simple.RandomSource;

import distribucion.interfaces.IDistribucionSalida;

/**
 *
 * <b>Nombre de la clase</b>: Exponencial
 * <p>
 * <b>Descripci�n</b>: Clase encargada de implementar la distribuci�n
 * exponencial
 * </p>
 * 
 * @author Mariop
 *
 */
public class Exponencial implements IDistribucionSalida
{
  /**
   * El atributo lambda de tipo double se emplea para almacenar el par�metro
   * lambda de la distribuci�n. Se actualiza al vuelo.
   */
  private double lambda = 0.55;

  /**
   * El atributo prng de tipo UniformRandomProvider se emplea para almacenar el
   * generador de n�meros pseudoaleatorio
   */
  private Random prng;

  private double tiempoAcumulado = 0;

  /**
   * Constructor de la clase Exponencial
   *
   */
  public Exponencial(long seed)
  {
    this.prng = new JDKRandomBridge(RandomSource.MT_64, seed);
  }

  /*
   * (non-Javadoc)
   * 
   * @see distribucion.interfaces.IDistribucionSalida#getSiguienteTiempo(double)
   */
  @Override
  public double getSiguienteTiempo(Double tiempoSimulacion)
  {
    if (this.lambda < 1.65)
    {
      this.lambda = 1 / (0.55 + 1.1 * (this.tiempoAcumulado / 1000));
    } else
    {
      this.lambda = 1.65;
    }
    double siguienteValor = prng.nextDouble();
    this.tiempoAcumulado += siguienteValor;
    return (-Math.log(siguienteValor)) / (this.lambda);
  } // fin m�todo getSiguienteTiempo

  /**
   * @return the lambda
   */
  public double getLambda()
  {
    return lambda;
  }

  /**
   * @param lambda the lambda to set
   */
  public void setLambda(double lambda)
  {
    this.lambda = lambda;
  }

  /**
   * M�todo encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public double getTiempoAcumulado()
  {
    return tiempoAcumulado;
  }

}
