package domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import productos.Producto;
/**
 * 
 * Esta clase se almacena en un HashSet, tiene sobre escrito el metodo equals
 *
 */
public class Empleado implements Serializable{

	private int ID;
	private String nombre;
	private String apellido;
	private String contrasenia;
	
	public Empleado() {
		this.ID=0;
		this.nombre=null;
		this.apellido=null;
		this.contrasenia=null;
	}

	public Empleado(int iD, String nombre, String apellido, String contrasenia) {
		this.ID=iD;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia=contrasenia;
	}
	
	public Empleado(String nombre, String apellido, String contrasenia)
	{
		this.ID=0;
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

	
	/**
	 * Captura la exepcion lanzada por {@link #validarContrasenia(String)}
	 * @see #validarContrasenia(String)
	 * @param String
	 * @return String
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
	
	/**
	 * Valida String ingresado y lanza una exepcion que es capturada por {@link #validarContraseniaLlamada(String)}
	 * @param String
	 * @throws CadenaInvalidaException
	 * @throws NullPointerException
	 * @see #validarContraseniaLlamada(String)
	 */
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
	
	/**
	 * Retorna el ID del objeto
	 * @return int
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Establece el ID del objeto
	 * @param int
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Retorna el nombre del objeto 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}
	

	/**
	 * Establece el nombrel del objeto
	 * @param String
	 * @return String
	 */
	public String setNombre(String nombre) {
		String mensaje=null;
		mensaje=Producto.validarCadenaCaracteresLlamada(nombre);
		if(mensaje==null)
		{
			this.nombre = nombre;
		}
		return mensaje;
	}

	/**
	 * Retorna el apellido del objeto
	 * @return String
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del objeto
	 * @param String
	 * @return String
	 */
	public String setApellido(String apellido) {
		String mensaje=null;
		mensaje=Producto.validarCadenaCaracteresLlamada(apellido);
		if(mensaje==null)
		{
			this.apellido = apellido;
		}
		return mensaje;
	}
	
	/**
	 * Retorna la contraseña del objeto
	 * @return String
	 */
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

	/**
	 * Muestra informacion relevante del objeto
	 * @return String
	 */
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
