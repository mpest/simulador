package estaciontrabajo;

import java.util.List;

public class Util
{
  private Util()
  {
  }

  /**
   * M�todo encargado de extraer una m�quina de la lista y generar una lista
   * nueva sin esa m�quina
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public static Maquina poll(List<Maquina> lista)
  {
    Maquina output = null;
    if (lista != null && lista.size() > 0)
    {
      output = lista.get(0);
      if (lista.size() == 0)
      {
        lista = lista.subList(1, lista.size() - 1);
      } else
      {
        lista.remove(0);
      }
    }
    return output;
  }
}