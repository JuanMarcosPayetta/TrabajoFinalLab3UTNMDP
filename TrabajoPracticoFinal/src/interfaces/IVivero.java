package interfaces;

/**
 * 
 * Metodos genericos utilizados en la clase Vivero
 * 
 * @see #Vivero
 *
 */
public interface IVivero {

	public <T> void agregarElemento(T elemento);

	public <T> String buscarElemento(T elemento);

	public <T> String eliminarElemento(T elemento);

}
