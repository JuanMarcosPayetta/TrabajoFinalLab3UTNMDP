package domain;

import excepciones.CadenaInvalidaException;

public class Empleado {

	private int ID;
	private String nombre;
	private String apellido;
	private int idSig=1;
	
	public Empleado()
	{
		this.ID=0;
		this.nombre=null;
		this.apellido=null;
	}

	public Empleado(int iD, String nombre, String apellido) {
		ID = iD;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Empleado(String nombre, String apellido)
	{
		ID=idSig;
		idSig++;
		this.nombre=nombre;
		this.apellido=apellido;
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
	
	
	public int getID() {
		return ID;
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

	public String getApellido() {
		return apellido;
	}

	public String setApellido(String apellido) {
		String mensaje=null;
		mensaje=validarCadenaCaracteresLlamada(apellido);
		if(mensaje==null)
		{
			this.apellido = apellido;
		}
		return mensaje;
	}
	
	@Override
	public int hashCode() {
	return 1;
	}
	
	public boolean equals (Object obj)
	{
		boolean validacion=false;
		if(obj!=null)
		{
			if(obj instanceof Empleado)
			{
				Empleado unEmpleado= (Empleado) obj;
				if(this.getID()==unEmpleado.getID())
				{
					validacion=true;
				}
			}
		}
		return validacion;
	}
	
}
