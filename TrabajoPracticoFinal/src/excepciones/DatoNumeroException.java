package excepciones;

public class DatoNumeroException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public static final String VALORNEGATIVOEXCEPTION="El valor ingresado no puede ser negativo"; 
	public static final String CARACTERNUMERICOEXCEPTION="Ingrese unicamente valores numericos";
	public static final String VALORFUERADELRANGOEXCEPTION="Ingrese un valor numerico dentro del rango aceptado";

	
	public DatoNumeroException(String mensaje)
	{
		super(mensaje);
	}
	
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

