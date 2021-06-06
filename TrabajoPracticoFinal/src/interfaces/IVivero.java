package interfaces;

public interface IVivero{

	public <T> void agregarElemento(T elemento); //para mapa y el metodo "existe" pregunto si T instanceof producto, y llamo al metodo en un if
	public <T> String buscarElemento(T elemento);
	public <T> String eliminarElemento(T elemento);
	//public String contar(int opcion); --> a debatir
	
	
	
}
