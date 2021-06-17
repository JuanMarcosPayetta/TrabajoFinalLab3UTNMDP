package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;

public class Sustrato extends ProductoOrganico{

	private boolean abonada;
	private String tipoDeSuelo;
	
	public Sustrato()
	{
		super();
		this.abonada=false;
		this.tipoDeSuelo=null;
	}

	public Sustrato(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos, boolean abonada, String tipoDeSuelo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, gramos);
		this.abonada = abonada;
		this.tipoDeSuelo = tipoDeSuelo;
	}

	public Sustrato(String nombre, String marca, double precio, int stock, String descripcion, int gramos,
			boolean abonada, String tipoDeSuelo) {
		super(nombre, marca, precio, stock, descripcion, gramos);
		this.abonada = abonada;
		this.tipoDeSuelo = tipoDeSuelo;
		establecerClasificacion();
	}

	public Sustrato(String nombre, String marca, int stock, String descripcion, int gramos,
			boolean abonada, String tipoDeSuelo) {
		super(nombre, marca, stock, descripcion, gramos);
		this.abonada = abonada;
		this.tipoDeSuelo = tipoDeSuelo;
		establecerClasificacion();
		establecerPrecio();
	}
	
	
	/**
	 * Valida que el tipo de suelo pasado por parametro sea correcto
	 * @see #validarTipoSuelo(String)
	 * @param String
	 * @return String
	 */
	public static String validarTipoSueloLlamada(String suelo)
	{
		String mensaje=null;
		try {
			validarTipoSuelo(suelo);
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
	 * Valida que el tipo de suelo pasado por parametro sea correcto, lanzando una excepcion que sera capturada por {@link #validarTipoSueloLlamada(String)}
	 *@see #validarTipoSueloLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarTipoSuelo(String suelo) throws NullPointerException, CadenaInvalidaException, InputMismatchException 
	{
		ArrayList<String>suelosValidos=new ArrayList<String>();
		suelosValidos.add("arenoso");
		suelosValidos.add("calizo");
		suelosValidos.add("humifero");
		suelosValidos.add("arcilloso");
		suelosValidos.add("pedregoso");
		suelosValidos.add("mixto");
		
		if(suelo==null)
		{
			throw new NullPointerException("Error, ingrese un tipo de suelo valido");
		}
		else if(suelo.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!suelo.matches("[a-zA-Z]*\\D{5}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 5 letras");
		}
		else if(!suelosValidos.contains(suelo.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese un tipo de suelo valido (arenoso, calizo, humifero, arcilloso, pedregoso, mixto)");
		}

	}
	
	/**
	 * Devuelve si el sustrato esta abonado o no
	 * @return boolean
	 */
	public boolean isAbonada() {
		return abonada;
	}

	/**
	 * Setea el valor que indica si el sustrato esta abonado o no, validando que sea correcto
	 * @see #validarBooleanLlamada(Boolean)
	 * @param abonada
	 * @return String
	 */
	public String setAbonada(boolean abonada) {
		
		String mensaje=validarBooleanLlamada(abonada); //si devuelve "null" el dato es correcto
			
		if(mensaje==null)
		{
			this.abonada = abonada;
		}
		return mensaje;	
	}

	/**
	 * Devuelve el tipo de suelo que corresponde al sustrato
	 * @return String
	 */
	public String getTipoDeSuelo() {
		return tipoDeSuelo;
	}

	/**
	 * Setea el valor que indica el tipo de suelo que corresponde al sustrato, validando que sea correcto
	 * @see #validarTipoSueloLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setTipoDeSuelo(String tipoDeSuelo) {
		
		String mensaje=validarTipoSueloLlamada(tipoDeSuelo); //si devuelve "null" el dato es correcto
			
		if(mensaje==null)
		{
			this.tipoDeSuelo = tipoDeSuelo;
		}
		return mensaje;	
	}
	
	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", abonada: " + abonada + ", tipo de suelo: " + tipoDeSuelo;
	}
	
	/**
	 * Establece la clasificacion del objeto a "Sustrato"
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Sustrato");
	}
	
	/**
	 * Establece el precio del sustrato
	 * @see #isAbonada()
	 * @see #getGramos()
	 * @see #setPrecio(double)
	 */
	@Override
	public void establecerPrecio() 
	{
		double precioGramoSustrato=0;
		double precioTotal=0;
		
		if(isAbonada())
		{
			precioGramoSustrato=0.3;
		}
		else
		{
			precioGramoSustrato=0.2;
		}
		
		precioTotal=precioGramoSustrato*getGramos();
		setPrecio(precioTotal);
	}
	
	public JSONObject javaToJson()
	{
		JSONObject obj= null;
		try
		{
			obj=new JSONObject();
			obj.put("marca", getMarca());
			obj.put("clasificacion", getClasificacion());
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return obj;
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


	
	

	
