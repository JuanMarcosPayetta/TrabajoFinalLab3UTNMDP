package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;
/**
 * 
 * Atributos de los productos clasificacion Semilla
 *
 */
public class Semilla extends ProductoOrganico{

	private String destino;
	
	public Semilla() {
		super();
		this.destino=null;
	}

	public Semilla(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos, String destino) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, gramos);
		this.destino = destino;
	}

	public Semilla(String nombre, String marca, double precio, int stock, String descripcion, int gramos,
			String destino) {
		super(nombre, marca, precio, stock, descripcion, gramos);
		this.destino = destino;
		establecerClasificacion();
	}
	
	public Semilla(String nombre, String marca, int stock, String descripcion, int gramos,
			String destino) {
		super(nombre, marca, stock, descripcion, gramos);
		this.destino = destino;
		establecerClasificacion();
		establecerPrecio();
	}
	
	/**
	 * Valida que el valor pasado por parametro sea correcto
	 * @see #validarDestinoSemilla(String)
	 * @param String
	 * @return String 
	 */
	public static String validarDestinoSemillaLlamada(String destino)
	{
		String mensaje=null;
		try {
			validarDestinoSemilla(destino);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	/**
	 * Valida que el valor pasado por parametro sea correcto, lanzando una excepcion que sera capturada por {@link #validarDestinoSemillaLlamada(String)}
	 * @see #validarDestinoSemillaLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarDestinoSemilla(String destino) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>destinosValidos=new ArrayList<String>();
		destinosValidos.add("huerta");
		destinosValidos.add("floral");
		destinosValidos.add("cesped");
		
		if(destino==null)
		{
			throw new NullPointerException("Error, ingrese un destino valido (huerta, floral, cesped)");
		}
		else if(destino.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!destino.matches("[a-zA-Z]*\\D{6}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 6 letras");
		}
		else if(!destinosValidos.contains(destino.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un destino valido (huerta, floral, cesped)");
		}
	
	}

	/**
	 * Devuelve el destino de la semilla
	 * @return String
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Setea el valor que indica el destino de la semilla, validando que sea correcto
	 * @see #validarDestinoSemillaLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setDestino(String destino) {
		String mensaje=validarDestinoSemillaLlamada(destino); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.destino = destino;
		}
		
		return mensaje;	
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+ ", Destino: " + destino;
	}
	
	/**
	 * Establece la clasificacion del objeto a "Semilla"
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Semilla");
	}

	/**
	 * Establece el precio de la semilla
	 * @see #getDestino()
	 */
	@Override
	public void establecerPrecio() {
	
		double precioGramoSemilla=0;
		double precioTotal=0;
		if(getDestino().equalsIgnoreCase("huerta"))
		{
			precioGramoSemilla=4.5;
		}
		else if(getDestino().equalsIgnoreCase("floral"))
		{
			precioGramoSemilla=3.6;
		}
		else
		{
			precioGramoSemilla=1.3;
		}
		
		precioTotal=precioGramoSemilla*getGramos();
		setPrecio(precioTotal);
	}

	
	/**
	 * Compara dos objetos, indicando si son iguales, mayor o menor
	 * @see #getPrecio()
	 * @param Producto
	 * @return int
	 */
	@Override
	public int compareTo(Producto o) {
		int res=-2;
		if(o!=null)
		{
			if(this.getPrecio()==o.getPrecio())
			{
				res=0;
			}
			else if (this.getPrecio()>o.getPrecio())
			{
				res=1;
			}
			else
			{
				res=-1;
			}
		}
		return res;
	}
}
