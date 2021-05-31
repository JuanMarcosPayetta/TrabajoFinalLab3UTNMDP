package excepciones;

public class CadenaInvalidaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public static final String LONGITUDYNUMEROSEXCEPTION="Error, el dato ingresado no debe poseer numeros, y tener un minimo de";
	public static final String ESPACIOENBLANCOEXCEPTION="Error, el dato ingresado esta en blanco, intente nuevamente";
	
	public CadenaInvalidaException(String mensaje)
	{
		super(mensaje);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	

}
