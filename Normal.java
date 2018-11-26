/**
 * 
 */
package distribucion.implementaciones;

import java.util.Random;

import org.apache.commons.rng.simple.JDKRandomBridge;
import org.apache.commons.rng.simple.RandomSource;

import distribucion.interfaces.IDistribucionSalida;

/**
 * @author Mariop
 *
 */
public class Normal implements IDistribucionSalida
{
  /**
   * El atributo media de tipo long se emplea para almacenar la media de la
   * distribución normal. Calculada previamente.
   */
  private double media;

  /**
   * El atributo varianza de tipo long se emplea para almacenar la varianza de
   * la distribución normal. Calculada previamente.
   */
  private double varianza;

  /**
   * El atributo prng de tipo Random se emplea para almacenar el generador de
   * números aleatorios
   */
  private Random prng;

  /**
   * Constructor de la clase Normal
   *
   */
  public Normal()
  {
    this.media = 2.79837817;
    this.varianza = 0.041;
    this.prng = new JDKRandomBridge(RandomSource.MT_64, null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see distribucion.interfaces.IDistribucionSalida#getSiguienteTiempo(long)
   */
  @Override
  public double getSiguienteTiempo(Double tiempoSimulacion)
  {
    // Se usa el algoritmo Box-Mueller para generar los valores de la normal
    // 1.- Necesitamos otro generador
    Random prng2 = new JDKRandomBridge(RandomSource.MT_64, 10);
    // 2.- Se generan los valores
    double r = Math.sqrt(-2 * Math.log(this.prng.nextDouble()));
    double fi = 2 * Math.PI * prng2.nextDouble();
    // 3.- Se obtiene el primero de los valores, el segundo se ignora porque
    // solo necesitamos uno
    double normalNormalizada = r * Math.cos(fi);
    // 4.- Se especializa la normal
    return this.media + normalNormalizada * this.varianza;
  }// fin método getSiguienteTiempo

}
