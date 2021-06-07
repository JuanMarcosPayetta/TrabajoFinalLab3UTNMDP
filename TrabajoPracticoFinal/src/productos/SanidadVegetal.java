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

	/*
	 * valida la funcion del producto sanitario, llamada main
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
	
	private static void validarSanidad(String funcion) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>funciones=new ArrayList<String>();
		funciones.add("insenticida");
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
	
	
	public String getFuncion() {
		return funcion;
	}

	public String setFuncion(String funcion) {
	String mensaje=validarSanidadLlamada(funcion); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.funcion = funcion;
		}
		
		return mensaje;	
	}

	public int getCentimetroCubico() {
		return centimetroCubico;
	}

	public String setCentimetroCubico(int centimetroCubico) {
      String mensaje=validarValorNumericoLlamada(centimetroCubico); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.centimetroCubico = centimetroCubico;
		}
		
		return mensaje;	
		
	}

	@Override
	public String toString() {
		return super.toString()+", funcion: " + funcion + ", centimetroCubico: " + centimetroCubico;
	}
	
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Sanidad vegetal");
	}

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

	public JSONObject javaToJson()
    {
        JSONObject json= new JSONObject();
        try {
            json.put("codigo", getCodigo());
            json.put("nombre", getNombre());
            json.put("marca", getMarca());
            json.put("clasificacion", getClasificacion());
            json.put("precio", getPrecio());
            json.put("stock", getStock());
            json.put("funcion", getFuncion());
            json.put("centimetroCubico", getCentimetroCubico());
            json.put("descripcion", getDescripcion());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
