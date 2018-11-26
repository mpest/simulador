/**
 * 
 */
package estaciontrabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * <b>Nombre de la clase</b>: NodoTrabajo
 * <p>
 * <b>Descripción</b>:
 * </p>
 * 
 * @author Mariop
 *
 */
public class NodoTrabajo
{
  /**
   * El atributo listaEstacionesTrabajo de tipo List<EstacionTrabajo> se emplea
   * para almacenar la lista de estaciones de trabajo
   */
  private List<EstacionTrabajo> listaEstacionesTrabajo = new ArrayList<>();;

  /**
   * El atributo listaMaquinasEnCola de tipo List<Maquina> se emplea para
   * almacenar la lista de máquinas en la cola de la estación de trabajo
   */
  private List<Maquina> listaMaquinasEnCola = new ArrayList<>();

  /**
   * Constructor de la clase NodoTrabajo
   *
   * @param listaEstacionesTrabajo
   */
  public NodoTrabajo(List<EstacionTrabajo> listaEstacionesTrabajo)
  {
    this.listaEstacionesTrabajo = listaEstacionesTrabajo;
  }

  /**
   * Método encargado de actualizar la cola de la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  private void actualizarCola()
  {
    int flag = 0;
    while (!this.listaMaquinasEnCola.isEmpty())
    {
      for (EstacionTrabajo estacion : this.listaEstacionesTrabajo)
      {
        if (estacion.getListaMaquinas().size() < estacion.getTamanioEstacion())
        {
          estacion.introducirMaquina(Util.poll(this.listaMaquinasEnCola));
        } else
        {
          flag++;
        }
      } // fin for para recorrer todas las estaciones de trabajo
      if (flag == this.listaEstacionesTrabajo.size())
      {
        return; // Tan sucio que me asquea un poco
      } else
      {
        flag = 0;
      }
    } // fin while para intentar vaciar la lista de máquinas en cola
  }// fin método actualizarCola

  /**
   * Método encargado de introducir una máquina en la cola del nodo de trabajo
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @param maquina
   */
  public void introducirMaquina(Maquina maquina)
  {
    this.listaMaquinasEnCola.add(maquina);
    this.actualizarCola();
  }

  /**
   * Método encargado de extraer una máquina de la cola del nodo de trabajo. Se
   * extrae una máquina al azar, si no hay ninguna máquina en ninguna estación
   * se devuelve null
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  public Maquina extraerMaquina()
  {
    Maquina maquinaExtraida = Util.poll(this.listaEstacionesTrabajo.stream()
            .filter(EstacionTrabajo::tieneMaquinas)
            .map(EstacionTrabajo::getListaMaquinas).findAny().orElse(null));
    this.actualizarCola();
    return maquinaExtraida;
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
  public int numeroMaquinas()
  {
    int output = 0;
    List<Integer> listaMaquinas = this.listaEstacionesTrabajo.stream()
            .map(EstacionTrabajo::getListaMaquinas).map(List::size)
            .collect(Collectors.toList());
    for (Integer num : listaMaquinas)
    {
      output += num;
    }
    return output;
  }

  /**
   * @return the listaEstacionesTrabajo
   */
  public List<EstacionTrabajo> getListaEstacionesTrabajo()
  {
    return listaEstacionesTrabajo;
  }
}
