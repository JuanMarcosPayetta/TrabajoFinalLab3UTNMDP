package domain;

import excepciones.DatoNumeroException;

public class PeticionCompra {

	private String codigo; //codigo del producto/servicio
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
	
	private static void validarPrecioUnitario(Double precioU) throws NullPointerException, DatoNumeroException 
	{
		if(precioU==null) {
			throw new NullPointerException("Error, ingrese un dato numerico valido");
		}
		else if(precioU<0){
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
		}
	}

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
	
	private static void validarCantidad(Integer cantidad) throws NullPointerException, DatoNumeroException 
	{
		if(cantidad==null) {
			throw new NullPointerException("Error, ingrese un dato numerico valido");
		}
		else if(cantidad<1){
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION+ " ni menor a 1");
		}
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public String setPrecioUnitario(double precioUnitario) {
		String mensaje=null;
		mensaje=validarPrecioUnitarioLlamada(precioUnitario);
		
		if(mensaje==null) {
			this.precioUnitario = precioUnitario;
		}
		return mensaje;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String setCantidad(int cantidad) {
		String mensaje=null;
		mensaje=validarCantidadLlamada(cantidad);
		
		if(mensaje==null) {
			this.cantidad = cantidad;
		}
		return mensaje;
	}
	
	
	@Override
	public String toString() {
		return "codigo producto:" + codigo + ", precio unitario: " + precioUnitario + ", cantidad:" + cantidad+"\n";
	}
	
}
