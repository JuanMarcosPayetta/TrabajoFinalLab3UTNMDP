package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import interfaces.IEstablecerPrecioProductoInerte;

public class SanidadVegetal extends Producto implements IEstablecerPrecioProductoInerte{

	private String funcion;
	private int centimetroCubico;
	
	public SanidadVegetal()
	{
		super();
		this.funcion=null;
		this.centimetroCubico=0;
	}

	public SanidadVegetal(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String funcion, int centimetroCubico) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.funcion = funcion;
		this.centimetroCubico = centimetroCubico;
	}

	public SanidadVegetal(String nombre, String marca, double precio, int stock, String descripcion, String funcion,
			int centimetroCubico) {
		super(nombre, marca, precio, stock, descripcion);
		this.funcion = funcion;
		this.centimetroCubico = centimetroCubico;
		establecerClasificacion();
	}
	
	public SanidadVegetal(String nombre, String marca, int stock, String descripcion, String funcion,
			int centimetroCubico) {
		super(nombre, marca, stock, descripcion);
		this.funcion = funcion;
		this.centimetroCubico = centimetroCubico;
		establecerClasificacion();
		establecerPrecio();
	}

	/**
	 * Verifica que se ingrese una funcion valida para el producto organico
	 * @param String
	 * @return String
	 * @see #validarSanidad(String)
	 */
	public static String validarSanidadLlamada(String funcion)
	{
		String mensaje=null;
		try {
			validarSanidad(funcion);
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
	 * Valida que el String ingresado sea valido y lanza una excepcion que es capturada por {@link #validarSanidadLlamada(String)}
	 *@see #validarSanidadLlamada(String)
	 * @param funcion
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarSanidad(String funcion) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>funciones=new ArrayList<String>();
		funciones.add("insecticida");
		funciones.add("funguicida");
		funciones.add("herbicida");
		
		
		if(funcion==null)
		{
			throw new NullPointerException("Error, ingrese una funcion valida(insecticida, funguicida, herbicida)");
		}
		else if(funcion.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!funcion.matches("[a-zA-Z]*\\D{9}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 9 letras");
		}
		else if(!funciones.contains(funcion.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese una funcion valida(insecticida, funguicida, herbicida)");
		}
	
	}
	
	/**
	 * Devuelve la funcion correspondiente al producto de sanidad vegetal
	 * @return String
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * Establece la funcion correspondiente al producto de sanidad vegetal, validando que sea correcta
	 * @see #validarSanidadLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setFuncion(String funcion) {
	String mensaje=validarSanidadLlamada(funcion); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.funcion = funcion;
		}
		
		return mensaje;	
	}

	/**
	 * Devuelve la cantidad de centimetros cubicos de producto
	 * @return int
	 */
	public int getCentimetroCubico() {
		return centimetroCubico;
	}

	/**
	 * Establece la cantidad de centimetros cubicos de producto, validando que sea correcto
	 * @see #validarValorNumericoLlamada(Number)
	 * @param int
	 * @return String
	 */
	public String setCentimetroCubico(int centimetroCubico) {
      String mensaje=validarValorNumericoLlamada(centimetroCubico); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.centimetroCubico = centimetroCubico;
		}
		
		return mensaje;	
		
	}

	/**
	 * Devuelve informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", funcion: " + funcion +", volumen: " + centimetroCubico;
	}
	
	/**
	 * Establece la clsificacion a la que pertenece el objeto
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Sanidad vegetal");
	}

	/**
	 * Establece el precio del producto de sanidad vegetal
	 */
	@Override
	public void establecerPrecio() {
		
		double precioCentimetroCubico=0;
		double precioFinal=0;
		
		if(getFuncion().equalsIgnoreCase("insecticida"))
		{
			precioCentimetroCubico=1.8;
		}
		else if(getFuncion().equalsIgnoreCase("funguicida"))
		{
			precioCentimetroCubico=4;
		}
		else
		{
			precioCentimetroCubico=3.3;
		}
		
		precioFinal=precioCentimetroCubico*getCentimetroCubico();
		setPrecio(precioFinal);
	}

	/**
	 * Compara dos objetos de sanidad vegetal, indicando si son iguales, mayor o menor
	 * @param Objeto producto
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

	@Override
	public JSONObject javaToJson() {
		// TODO Auto-generated method stub
		return null;
	}
}
