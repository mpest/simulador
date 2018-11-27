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
   * El atributo MIN_LAMBDA de tipo double se emplea para almacenar el valor
   * m�nimo de lambda
   */
  private static final double MIN_LAMBDA = 0.55;

  /**
   * El atributo MAX_LAMBDA de tipo double se emplea para almacenar el valor
   * m�ximo de lambda
   */
  private static final double MAX_LAMBDA = 1.65;

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
    if (this.lambda < MAX_LAMBDA)
    {
      this.lambda = 1 / (MIN_LAMBDA + 1.1 * (tiempoSimulacion / 1000));
    } else
    {
      this.lambda = MAX_LAMBDA;
    }
    return (-Math.log(prng.nextDouble())) / (this.lambda);
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
}
