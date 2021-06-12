package domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import productos.Producto;


public class Cliente implements Serializable{

	private int id;
	static private int autoid=1;
	private String nombre;
	private String apellido;
	private String telefono;
	private String dni;
	
	public Cliente() {
		this.id=0;
		this.nombre=null;
		this.apellido=null;
		this.telefono=null;
		this.dni=null;
	}

	public Cliente(String nombre, String apellido, String telefono, String dni) {
		this.id = autoid;
		autoid++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	//creado solo para busqueda en Vivero
	public Cliente(String dni) {
		this.id=0;
		this.nombre=null;
		this.apellido=null;
		this.telefono=null;
		this.dni=dni;
	}
	
	

	public static String validarCadenaCaracteresTELDNILlamada(String nombre) {
		String mensaje = null; // Si el retorno es "null" seria correcto
		try {
			validarCadenaCaracteresTELDNI(nombre);
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	private static void validarCadenaCaracteresTELDNI(String cadena)
			throws CadenaInvalidaException, NullPointerException {
		if (cadena == null) {
			throw new NullPointerException("Error");
		} else if (cadena.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!cadena.matches("[0-9]")) {
			throw new CadenaInvalidaException("Error, ingrese una cadena de al menos 7 numeros (no letras)");
		}
	}

	
	public int getId() {
		return id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public String setNombre(String nombre) {

		String mensaje = Producto.validarCadenaCaracteresLlamada(nombre);

		if (mensaje == null) {

			this.nombre = nombre;
		}
		return mensaje;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public String setApellido(String apellido) {
		String mensaje = Producto.validarCadenaCaracteresLlamada(apellido);

		if (mensaje == null) {

			this.apellido = apellido;
		}
		return mensaje;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public String setTelefono(String telefono) {
		String mensaje = validarCadenaCaracteresTELDNILlamada(telefono);

		if (mensaje == null) {
			this.telefono = telefono;
		}
		return mensaje;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public String setDni(String dni) {

		String mensaje = validarCadenaCaracteresTELDNILlamada(dni);

		if (mensaje == null) {
			this.dni = dni;
		}
		return mensaje;
	}

	@Override
	public int hashCode() {

		return 1;
	}

	@Override
	public boolean equals(Object obj) {

		boolean estado = false;

		if (obj != null) {
			if (obj instanceof Cliente) {
				Cliente aux = (Cliente) obj;

				if (this.getDni().equals(aux.getDni())) {
					estado = true;
				}
			}
		}

		return estado;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Telefono: " + telefono
				+ ", DNI: " + dni;
	}

	public JSONObject javaToJson()
    {
        JSONObject json= new JSONObject();
        try {
            json.put("nombre", getNombre());
            json.put("apellido", getApellido());
            json.put("telefono", getTelefono());
            json.put("id", getId());
            json.put("dni", getDni());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
	
	
}
