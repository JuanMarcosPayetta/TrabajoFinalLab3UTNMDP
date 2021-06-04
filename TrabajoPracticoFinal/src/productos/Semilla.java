package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

public class Semilla extends ProductoOrganico{

	private String destino;
	
	public Semilla()
	{
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
	}
	
	/*
	 * Valida el destino de la semilla, llamada main
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

	public String getDestino() {
		return destino;
	}

	public String setDestino(String destino) {
		String mensaje=validarDestinoSemillaLlamada(destino); //si devuelve "null" el dato es correcto
		
		if(mensaje==null)
		{
			this.destino = destino;
		}
		
		return mensaje;	
	}

	@Override
	public String toString() {
		return super.toString()+ ", destino: " + destino;
	}
	
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Semilla");
	}

	
}
