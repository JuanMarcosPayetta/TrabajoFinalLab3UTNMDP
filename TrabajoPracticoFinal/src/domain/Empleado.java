package domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import productos.Producto;

public class Empleado implements Serializable{

	private int ID;
	private String nombre;
	private String apellido;
	private String contrasenia;
	private static int idSig=0;
	
	public Empleado() {
		this.ID=idSig++;
		this.nombre=null;
		this.apellido=null;
		this.contrasenia=null;
	}

	public Empleado(int iD, String nombre, String apellido, String contrasenia) {
		this.ID=idSig++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia=contrasenia;
	}
	
	public Empleado(String nombre, String apellido, String contrasenia)
	{
		this.ID=idSig++;
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

	
	/*
	 * valida la contraseņa
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
		mensaje=Producto.validarCadenaCaracteresLlamada(nombre);
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
		mensaje=Producto.validarCadenaCaracteresLlamada(apellido);
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
