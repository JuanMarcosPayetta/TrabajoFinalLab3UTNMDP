package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.function.LongToIntFunction;

public abstract class Producto {

	private String codigo;
	private String nombre;
	private String marca;
	private String clasificacion;
	private double precio;
	private int stock;
	private String descripcion;

	
	public Producto()
	{
		this.codigo=null;
		this.nombre=null;
		this.marca=null;
		this.clasificacion=null;
		this.precio=0;
		this.stock=0;
		this.descripcion=null;
	}

	public Producto(String codigo, String nombre, String marca, String clasificacion, double precio, int stock, String descripcion) 
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.marca = marca; 
		this.clasificacion = clasificacion;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
	}
	
	/*
	 * Constructor con generador de codigo automatico
	 */
	public Producto(String nombre, String marca, double precio, int stock, String descripcion) 
	{
		this.codigo = generarCodigo();
		this.nombre = nombre;
		this.marca = marca; 
		this.clasificacion =null;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
	}
	
	
	public abstract void establecerClasificacion();
		
	
	/* 
	 * Generador automatico del codigo de cada producto ingresado 
	 */
	public String generarCodigo()
	{
		boolean existe=true;
		int longitud=5;
		String codigoGenerado=null;
		String minusculas= "abcdefghijkmnlopqrstuvxyz";
		String numeros= "0123456789";
		String total=minusculas+numeros;
		
		char [] pass= new char[longitud];
		Random random= new Random();
		
	    while(existe) //Mientras el codigo generado actualmente sea = al codigo actual de otro producto
	    {
	    	for(int i =0; i<pass.length; i++)
			{
				pass[i]=total.charAt(random.nextInt(total.length()));
			}
	    	codigoGenerado=String.valueOf(pass); //codigo generado
	
			boolean encontrado=buscarCodigoExistentes(codigoGenerado); //busco si ya existe el codigo generado
			
			if(!encontrado) //Si el codigo generado no se repite con el codigo actual de otro producto, corta el ciclo while
			{
				existe=false;
			}
	    }
		return codigoGenerado;
	}

	/*
	 * Busca si un codigo existe o no actualmente en algun producto
	 */
	public boolean buscarCodigoExistentes(String codigoGenerado)
	{
		//LLAMAR AL METODO QUE ME DEVUELVE EL ARRAYLIST CON LOS CODIGOS ACTUALES
		ArrayList<String>codigosActuales= new ArrayList<String>();
		codigosActuales=obtenerCodigos(); //ESTE METODO DEBE CREARSE EN LA CLASE VIVERO, EL CUAL GUARDARA EN UN ARRAYLIST (Y LO RETORNARA) CON TODOS LOS CODIGOS DE LOS PRODUCTOS CARGADOS, PARA COMPARAR CON EL QUE SE GENERARA, PARA QUE NO SE REPITAN
		
		boolean encontrado=false;
		for(int i=0; i<codigosActuales.size() && !encontrado; i++)
		{
			if(codigosActuales.get(i).equals(codigoGenerado)) //El codigo generado es igual a uno ya existente
			{
				encontrado=true;
			}
		}
		return encontrado;
	}
	
	
	/*
	 * Validacion Nombre/Marca, llamada en el main
	 */
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
	
	public static void validarCadenaCaracteres(String cadena) throws CadenaInvalidaException, NullPointerException
	{
		if(cadena==null)
		{
			throw new NullPointerException("Error");
		}
		else if(!cadena.matches("[a-zA-Z]*\\D{3}")) //El nombre debe contener al menos 3 letras, ya sean miniscula o mayuscula (no numeros)
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 3 letras");
		}
		else if(cadena.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}

	}
	
	
	/*
	 * Validacion Precio/Stock, llamada main
	 */
	public static <T extends Number> String validarValorNumericoLlamada(T valor)
	{
		String mensaje=null;
		try {
			validarValorNumerico(valor);
		} catch (DatoNumeroException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	
	public static <T extends Number> void validarValorNumerico(T valor) throws DatoNumeroException
	{
	    if(valor instanceof Integer)
	    {
	       if(valor.intValue()<0)
	       {
	    	   throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
	       }
		}
	    else if(valor instanceof Double)
	    {
	    	if(valor.doubleValue()<0)
		    {
		    	throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
		    }
	    }
	}
	
	
	
	public String getCodigo() {
		return codigo;
	}

	
	/*
	 * Verifica que el codigo no exista
	 */
	public String setCodigo(String codigo) {

		String mensaje="Error, codigo ya existente, genero uno nuevo porfavor";
		boolean validacion=buscarCodigoExistentes(codigo);
		if(!validacion) //si el codigo no existe
		{
			this.codigo=codigo;
			mensaje="Codigo establecido con exito";
		}	
		return mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public String setNombre(String nombre) {
		String mensaje=validarCadenaCaracteresLlamada(nombre); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.nombre=nombre;
		}
		
		return mensaje;
	}

	public String getMarca() {
		return marca;
	}

	public String setMarca(String marca) {
		
     String mensaje=validarCadenaCaracteresLlamada(marca); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.marca = marca;
		}
		return mensaje;
	}

	public String getClasificacion() {
		return clasificacion;
	}
	
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public double getPrecio() {
		return precio;
	}

	public String setPrecio(double precio) {	
	 String mensaje=validarValorNumericoLlamada(precio); //si devuelve "null" el dato es correcto
			
		if(mensaje==null)
		{
		 this.precio=precio;
		}
		return mensaje;	
	}

	public int getStock() {
		return stock;
	}

	public String setStock(int stock) {
	String mensaje=validarValorNumericoLlamada(stock); //si devuelve "null" el dato es correcto
			
	if(mensaje==null)
	{
	 this.stock=stock;
	}
	return mensaje;			
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Codigo: " + codigo + ", Nombre: " + nombre + ", Marca: " + marca + ", Clasificacion: "
				+ clasificacion + ", Precio: " + precio + ", Stock: " + stock + ", Descripcion: " + descripcion;
	}
	
	
}
