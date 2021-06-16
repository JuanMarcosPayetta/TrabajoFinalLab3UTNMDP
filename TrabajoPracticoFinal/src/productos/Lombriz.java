package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;

public class Lombriz extends ProductoOrganico{

	private String especie;
	
	public Lombriz()
	{
		super();
		this.especie=null;
	}

	public Lombriz(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos, String especie) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, gramos);
		this.especie = especie;
	}

	public Lombriz(String nombre, String marca, double precio, int stock, String descripcion, int gramos,
			String especie) {
		super(nombre, marca, precio, stock, descripcion, gramos);
		this.especie = especie;
		establecerClasificacion();
	}

	public Lombriz(String nombre, String marca, int stock, String descripcion, int gramos,
			String especie) {
		super(nombre, marca, stock, descripcion, gramos);
		this.especie = especie;
		establecerClasificacion();
		establecerPrecio();
	}

	
	/*
	 * valida la especie de lombriz, llamada main
	 */
	public static String validarEspecieLombrizLlamada(String especie)
	{
		String mensaje=null;
		try {
			validarEspecieLombriz(especie);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	private static void validarEspecieLombriz(String especie) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>especiesLombriz=new ArrayList<String>();
		especiesLombriz.add("rayada");
		especiesLombriz.add("dendra");
		especiesLombriz.add("roja");
		especiesLombriz.add("comun");
		
		if(especie==null)
		{
			throw new NullPointerException("Error, ingrese una especie de lombriz valida (rayada, dendra, roja, comun)");
		}
		else if(especie.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!especie.matches("[a-zA-Z]*\\D{4}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 4 letras");
		}
		else if(!especiesLombriz.contains(especie.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese una especie de lombriz valida (rayada, dendra, roja, comun)");
		}
	
	}
	
	public String getEspecie() {
		return especie;
	}

	public String setEspecie(String especie) {
	String mensaje=validarEspecieLombrizLlamada(especie);
		
		if(mensaje==null)
		{
			this.especie = especie;
		}	
		return mensaje;
	}

	@Override
	public String toString() {
		return super.toString()+", especie: " + especie;
	}
	
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Lombriz");
	}

	@Override
	public void establecerPrecio() {
	double precioGramoLombriz=0;
	double precioFinal=0;
		
		if(getEspecie().equalsIgnoreCase("rayada"))
		{
			precioGramoLombriz=180;
		}
		else if(getEspecie().equalsIgnoreCase("dendra"))
		{
			precioGramoLombriz=150;
		}
		else if(getEspecie().equalsIgnoreCase("roja"))
		{
			precioGramoLombriz=220;
		}
		else if(getEspecie().equalsIgnoreCase("comun"))
		{
			precioGramoLombriz=100;
		}
		
		precioFinal=precioGramoLombriz*getGramos();
		setPrecio(precioFinal);
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

