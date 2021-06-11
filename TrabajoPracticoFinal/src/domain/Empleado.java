package domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;

public class Empleado implements Serializable{

	private int ID;
	private String nombre;
	private String apellido;
	private String contrasenia;
	private int idSig=1;
	
	public Empleado() {
		this.ID=0;
		this.nombre=null;
		this.apellido=null;
		this.contrasenia=null;
	}

	public Empleado(int iD, String nombre, String apellido, String contrasenia) {
		ID = iD;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia=contrasenia;
	}
	
	public Empleado(String nombre, String apellido, String contrasenia)
	{
		ID=idSig;
		idSig++;
		this.nombre=nombre;
		this.apellido=apellido;
		this.contrasenia=contrasenia;
	}
	
	//creado solo para busqueda en Vivero
	public Empleado(int id) 
	{
		this.ID=id;
		this.nombre=null;
		this.apellido=null;
		this.contrasenia=null;
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
	 * valida la contraseña
	 */
	public static String validarContraseniaLlamada(String contrasenia)
	{
		String mensaje=null;
		try {
			validarContrasenia(contrasenia);
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	private static void validarContrasenia(String contrasenia) throws CadenaInvalidaException, NullPointerException
	{
		if(contrasenia==null)
		{
			throw new NullPointerException("Error");
		}
		else if(contrasenia.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!contrasenia.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.PASSEXCEPTION);
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
	
	
	public String getContrasenia() {
		return contrasenia;
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
	

	@Override
	public String toString() {
		return "ID: " + ID + ", Nombre: " + nombre + ", Apellido: " + apellido;
	}

	public JSONObject javaToJson()
    {
        JSONObject json= new JSONObject();
        try {
            json.put("nombre", getNombre());
            json.put("apellido", getApellido());
            json.put("id", getID());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
