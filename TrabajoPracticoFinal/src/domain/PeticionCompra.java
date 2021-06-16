package domain;

import java.io.Serializable;

import excepciones.DatoNumeroException;

/**
 * 
 * Gestiona la informacion relacionada a los productos/servicios adquiridos 
 *
 */
public class PeticionCompra implements Serializable{

	private String codigo;//codigo del producto/servicio
	private double precioUnitario;
	private int cantidad;
	
	public PeticionCompra() {
		this.codigo=null;
		this.precioUnitario=0;
		this.cantidad=0;
	}
	
	public PeticionCompra(String codigo, double precioUnitario, int cantidad) {
		this.codigo = codigo;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
	}
	/**
	 * Captura la exepcion lanzada por {@link #validarCantidad(Integer)}
	 * @see #validarCantidad(Integer)
	 * @param Double
	 * @return String
	 */
	private static String validarPrecioUnitarioLlamada(Double precioU) 
	{
		String mensaje=null;
		try {
			validarPrecioUnitario(precioU);
		}
		catch(NullPointerException e) {
			mensaje = e.getMessage();
		}
		catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}
	
	/**
	 * Lanza una exepcion que es capturada por {@link #validarPrecioUnitarioLlamada(Double)}
	 * @see #validarPrecioUnitarioLlamada(Double)
	 * @param Double
	 * @throws NullPointerException
	 * @throws DatoNumeroException
	 */
	private static void validarPrecioUnitario(Double precioU) throws NullPointerException, DatoNumeroException 
	{
		if(precioU==null) {
			throw new NullPointerException("Error, ingrese un dato numerico valido");
		}
		else if(precioU<0){
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
		}
	}

	/**
	 * Captura la exepcion lanzada por {@link #validarCantidad(Integer)}
	 * @see #validarCantidad(Integer)
	 * @param Integer
	 * @return
	 */
	private static String validarCantidadLlamada(Integer cantidad) 
	{
		String mensaje=null;
		try {
			validarCantidad(cantidad);
		}
		catch(NullPointerException e) {
			mensaje = e.getMessage();
		}
		catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}
	
	/**
	 * Lanza una exepcion que es capturada por {@link #validarCantidadLlamada(Integer)}
	 * @see #validarCantidadLlamada(Integer)
	 * @param Integer
	 * @throws NullPointerException
	 * @throws DatoNumeroException
	 */
	private static void validarCantidad(Integer cantidad) throws NullPointerException, DatoNumeroException 
	{
		if(cantidad==null) {
			throw new NullPointerException("Error, ingrese un dato numerico valido");
		}
		else if(cantidad<1){
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION+ " ni menor a 1");
		}
	}
	
	/**
	 * Retorna el codigo del objeto
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el codigo del objeto
	 * @param String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna el precioUnitario del objeto
	 * @return double
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * Establece el precio unitario del objeto y valida que sea correcto
	 * @see #validarPrecioUnitarioLlamada(Double)
	 * @param double
	 * @return String
	 */
	public String setPrecioUnitario(double precioUnitario) {
		String mensaje=null;
		mensaje=validarPrecioUnitarioLlamada(precioUnitario);
		
		if(mensaje==null) {
			this.precioUnitario = precioUnitario;
		}
		return mensaje;
	}

	/**
	 * Retorna la cantidad solicitada del producto
	 * @return int
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Establece la cantidad solicitada del producto
	 * @param int
	 * @return String
	 */
	public String setCantidad(int cantidad) {
		String mensaje=null;
		mensaje=validarCantidadLlamada(cantidad);
		
		if(mensaje==null) {
			this.cantidad = cantidad;
		}
		return mensaje;
	}
	
	/**
	 * Retorna la informacion relevante del objeto
	 * @return String
	 */
	public String toString() {
		return "Codigo:" + codigo + ", Precio Unitario: " + precioUnitario + ", Cantidad solicitada:" + cantidad+"\n";
	}
	
}
