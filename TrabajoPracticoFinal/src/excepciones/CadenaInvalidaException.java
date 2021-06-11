package excepciones;

public class CadenaInvalidaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public static final String LONGITUDYNUMEROSEXCEPTION="Error, el dato ingresado no debe poseer numeros, y tener un minimo de ";
	public static final String ESPACIOENBLANCOEXCEPTION="Error, el dato ingresado esta en blanco, intente nuevamente";
	public static final String PASSEXCEPTION="Error, la contrasenia debe tener una longitud minima de 8 caracteres, poseer al menos un numero, una letra minuscula y una letra mayuscula\n";
	
	public CadenaInvalidaException(String mensaje)
	{
		super(mensaje);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	

}
