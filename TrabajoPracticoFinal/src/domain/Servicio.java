package domain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import excepciones.CadenaInvalidaException;
import excepciones.DatoNumeroException;
import interfaces.IGenerarCodigo;

public class Servicio implements IGenerarCodigo{

	private String codigo;
	private double precio;
	private String nombre;
	private String descripcion;
	private boolean materialesIncluidos;
	
	public Servicio()
	{
		this.codigo=null;
		this.precio=0;
		this.nombre=null;
		this.descripcion=null;
		this.materialesIncluidos=false;
	}

	public Servicio(String codigo, String nombre, double precio, String descripcion, boolean materialesIncluidos) {
		this.codigo = codigo;
		this.nombre=nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.materialesIncluidos = materialesIncluidos;
	}
	
	public Servicio(String nombre, double precio, String descripcion, boolean materialesIncluidos)
	{
		this.codigo=generarCodigo();
		this.nombre=nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.materialesIncluidos = materialesIncluidos;
	}

	@Override
	public String generarCodigo() {
		boolean existe=true;
		int longitud=5;
		String codigoGenerado=null;
		String minusculas= "abcdefghijkmnlopqrstuvxyz";
		String numeros= "0123456789";
		String total=minusculas+numeros;
		
		char [] pass= new char[longitud];
		Random random= new Random();
		
	    while(existe) //Mientras el codigo generado actualmente sea = al codigo actual de servicio
	    {
	    	for(int i =0; i<pass.length; i++)
			{
				pass[i]=total.charAt(random.nextInt(total.length()));
			}
	    	codigoGenerado=String.valueOf(pass); //codigo generado
	
			boolean encontrado=buscarCodigoExistentes(codigoGenerado); //busco si ya existe el codigo generado
			
			if(!encontrado) //Si el codigo generado no se repite con el codigo actual de otro servicio, corta el ciclo while
			{
				existe=false;
			}
	    }
		return codigoGenerado;
	}
	
	/*
	 * Busca si un codigo existe o no actualmente en algun servicio
	 */
	public boolean buscarCodigoExistentes(String codigoGenerado)
	{
		
		Vivero vivero= new Vivero();
		ArrayList<String>codigosActuales= new ArrayList<String>();
		codigosActuales=vivero.obtenerCodigosServicio(); 
		
		boolean encontrado=false;
		for(int i=0; i<codigosActuales.size() && !encontrado; i++)
		{
			if(codigosActuales.get(i).equals(codigoGenerado)) 
			{
				encontrado=true;
			}
		}
		return encontrado;
	}

	
	
	public static String validarCadenaCaracteresLlamada(String nombre)
	{
		    String mensaje=null;  //Si el retorno es "null" seria correcto
			try {
				validarCadenaCaracteres(nombre);
			} catch (NullPointerException e) {
				mensaje=e.getMessage();
			} catch (CadenaInvalidaException e) {
				mensaje=e.getMessage();
			}
			
			return mensaje;
	}
	
	
	private static void validarCadenaCaracteres(String cadena) throws CadenaInvalidaException, NullPointerException
	{
		if(cadena==null)
		{
			throw new NullPointerException("Error");
		}
		else if(cadena.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!cadena.matches("[a-zA-Z]*\\D{3}")) //El nombre debe contener al menos 3 letras, ya sean miniscula o mayuscula (no numeros)
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 3 letras");
		}
		
	}
	
	
	/*
	 * Validacion Precio/Stock, llamada main
	 */
	public static String validarValorNumericoLlamada(Double precio)
	{
		String mensaje=null;
		try {
			validarValorNumerico(precio);
		} catch (DatoNumeroException e) {
			mensaje=e.getMessage();
		}
	    catch (NullPointerException e) {
		    mensaje=e.getMessage();
	    }
		return mensaje;
	}
	
	
	private static void validarValorNumerico(Double precio) throws DatoNumeroException, NullPointerException
	{
		if(precio==null)
		{
			throw new NullPointerException("Error, ingrese un dato numerico valido");
		}
	    else if(precio<0)
	    {
		    throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
	    }
	}
	
	
	/*
	 * Valida si es un boolean, llamada main
	 */
	public static String validarBooleanLlamada(Boolean tipo)
	{
		String mensaje=null;
		try {
			validarBoolean(tipo);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	

	private static void validarBoolean(Boolean tipo)throws NullPointerException, InputMismatchException
	{
		if(tipo==null)
		{
			throw new NullPointerException("Error, ingrese true or false");
		}
		else if(!Boolean.FALSE && !Boolean.TRUE)
		{
			throw new InputMismatchException("Ingrese un dato valido, true o false");
		}
 
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public double getPrecio() {
		return precio;
	}

	public String setPrecio(double precio) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(precio);
		if(mensaje==null)
		{
			this.precio = precio;
		}
		
		return mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public String setNombre(String nombre) {
		String mensaje=null;
		mensaje=validarCadenaCaracteresLlamada(nombre);
		if(mensaje==null)
		{
			this.nombre = nombre;
		}	
		return mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isMaterialesIncluidos() {
		return materialesIncluidos;
	}

	public String setMaterialesIncluidos(boolean materialesIncluidos) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(materialesIncluidos);
		if(mensaje==null)
		{
			this.materialesIncluidos = materialesIncluidos;
		}	
		return mensaje;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + ", precio: " + precio + ", nombre: " + nombre + ", descripcion: "
				+ descripcion + ", materialesIncluidos: " + materialesIncluidos;
	}
	
	
	
}
