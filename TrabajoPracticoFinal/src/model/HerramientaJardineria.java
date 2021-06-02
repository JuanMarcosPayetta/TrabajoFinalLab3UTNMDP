package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

public abstract class HerramientaJardineria extends Producto {

	private String material;
	private String funcion; //riego, corte, limpieza, labrado, carga,...
	
	
	public HerramientaJardineria() {
		super();
		this.material = null;
		this.funcion = null;
	}
	public HerramientaJardineria(String nombre, String marca, double precio, int stock, String descripcion, String material, String funcion) {
		super(nombre, marca, precio, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}
	public HerramientaJardineria(String codigo, String nombre, String marca, String clasificacion, double precio,
			int stock, String descripcion, String material, String funcion) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}
	
	/*
	 * valida la funcion de la herramienta, llamada main
	 */
	public static String validarFuncionHerramientaLlamada(String funcion)
	{
		String mensaje=null; //si devuelve null es correcto
		try {
			validarFuncionHerramienta(funcion);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	private static void validarFuncionHerramienta(String funcion) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String> funciones=new ArrayList<String>();
		funciones.add("corte");
		funciones.add("desmalezado");
		funciones.add("labrado");
		funciones.add("limpieza");
		funciones.add("riego");
		funciones.add("transporte");
		
		if(funcion==null)
		{
			throw new NullPointerException("Error, ingrese una funcion valida (corte, desmalezado, labrado, limpieza, riego, transporte)");
		}
		else if(funcion.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!funcion.matches("[a-zA-Z]*\\D{5}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 5 letras");
		}
		else if(!funciones.contains(funcion))
		{
			throw new InputMismatchException("Error, ingrese una funcion valida (corte, desmalezado, labrado, limpieza, riego, transporte)");
		}
	
	}
	
	
	public String getMaterial() {
		return material;
	}
	
	public String setMaterial(String material) {
		String mensaje=null;
		mensaje=validarCadenaCaracteresLlamada(material);
		if(mensaje==null)
		{
			this.material = material;
		}
		return mensaje;
	}
	
	public String getFuncion() {
		return funcion;
	}
	
	public String setFuncion(String funcion) {
		String mensaje=null;
		mensaje=validarFuncionHerramientaLlamada(funcion);
		if(mensaje==null)
		{
			this.funcion = funcion;
		}
		return mensaje;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + ", material: " + material + ", funcion: " + funcion;
	}
	
	
	
	
}
