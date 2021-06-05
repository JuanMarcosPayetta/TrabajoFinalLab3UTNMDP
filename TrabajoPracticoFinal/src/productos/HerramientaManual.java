package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

public class HerramientaManual extends HerramientaJardineria{
	
	private String tamanio; //grande, mediano, peque�o
	private boolean reforzada;
	
	
	public HerramientaManual() {
		super();
		this.tamanio = null;
		this.reforzada = false;
	}
	
	public HerramientaManual(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String funcion, String tamanio, boolean reforzada) {
		super(nombre, marca, precio, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
		establecerClasificacion();
	}
	
	public HerramientaManual(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String funcion, String tamanio, boolean reforzada) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
	}
	
	/*
	 * Valida el tama�o de la herramienta manual
	 */
	public static String validarTamanioHManualLlamada(String tamanio)
	{
		String mensaje=null; //si devuelve null es correcto
		try {
			validarTamanioHManual(tamanio);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		
		return mensaje;
	}
	
	private static void validarTamanioHManual(String tamanio) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>tamanioValido= new ArrayList<String>();
		tamanioValido.add("grande");
		tamanioValido.add("mediano");
		tamanioValido.add("peque�o");
		
		if(tamanio==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido (grande, mediano, peque�o)");
		}
		else if(tamanio.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tamanio.matches("[a-zA-Z]*\\D{6}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 6 letras");
		}
		else if(!tamanioValido.contains(tamanio.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese un dato valido (grande, mediano, peque�o)");
		}
	}
	
	
	public String getTamanio() {
		return tamanio;
	}
	
	public String setTamanio(String tamanio) {
		String mensaje=null;
		mensaje=validarTamanioHManualLlamada(tamanio);
		if(mensaje==null)
		{
			this.tamanio = tamanio;
		}
	 
		return mensaje;
	}
	public boolean isReforzada() {
		return reforzada;
	}
	
	public String setReforzada(boolean reforzada) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(reforzada);
		if(mensaje==null)
		{
			this.reforzada = reforzada;
		}
		
		return mensaje;
	}

	@Override
	public String toString() {
		return super.toString()+", tamanio: " + tamanio + ", reforzada: " + reforzada;
	}
	
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Herramienta manual");
	}

	
	
	
}
