package domain;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.CadenaInvalidaException;
import productos.Producto;

/**
 * Esta clase se almacena en un HashSet, tiene sobre escrito el metodo equals.
 *
 */

public class Cliente implements Serializable {

	private int id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String dni;

	public Cliente() {
		this.id = 0;
		this.nombre = null;
		this.apellido = null;
		this.telefono = null;
		this.dni = null;
	}

	public Cliente(int id, String nombre, String apellido, String telefono, String dni) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dni = dni;
	}

	// creado solo para busqueda en Vivero
	public Cliente(String dni) {
		this.id = 0;
		this.nombre = null;
		this.apellido = null;
		this.telefono = null;
		this.dni = dni;
	}

	/**
	 * Captura la exepcion lanzada por
	 * {@link #validarCadenaCaracteresTELDNI(String)}
	 * 
	 * @see #validarCadenaCaracteresTELDNI(String)
	 * @param nombre
	 * @return String
	 */
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

	/**
	 * 
	 * @param cadena
	 * @throws CadenaInvalidaException
	 * @throws NullPointerException
	 * @see #validarCadenaCaracteresTELDNILlamada(String) Valida String ingresado y
	 *      lanza una exepcion que es capturada por
	 *      {@link #validarCadenaCaracteresTELDNILlamada(String)}
	 */
	private static void validarCadenaCaracteresTELDNI(String cadena)
			throws CadenaInvalidaException, NullPointerException {
		if (cadena == null) {
			throw new NullPointerException("Error");
		} else if (cadena.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!cadena.matches("[0-9]{7}$")) {
			throw new CadenaInvalidaException("Error, ingrese una cadena de al menos 7 numeros (no letras)");
		}
	}

	/**
	 * Retorna el ID del objeto
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna el nombre del objeto
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del objeto
	 * 
	 * @param nombre the nombre to set
	 * @return String
	 */
	public String setNombre(String nombre) {

		String mensaje = Producto.validarCadenaCaracteresLlamada(nombre);

		if (mensaje == null) {

			this.nombre = nombre;
		}
		return mensaje;
	}

	/**
	 * Retorna el apellido del objeto
	 * 
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del objeto
	 * 
	 * @param apellido
	 * @return String
	 */
	public String setApellido(String apellido) {
		String mensaje = Producto.validarCadenaCaracteresLlamada(apellido);

		if (mensaje == null) {

			this.apellido = apellido;
		}
		return mensaje;
	}

	/**
	 * Retorna el telefono del objeto
	 * 
	 * @return String
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Establece el telefono del objeto
	 * 
	 * @param telefono
	 * @return String
	 */
	public String setTelefono(String telefono) {
		String mensaje = validarCadenaCaracteresTELDNILlamada(telefono);

		if (mensaje == null) {
			this.telefono = telefono;
		}
		return mensaje;
	}

	/**
	 * Retorna el dni del objeto
	 * 
	 * @return String
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el dni del objeto
	 * 
	 * @param dni
	 * @return String
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

	/**
	 * Muestra informacion relevante del objeto
	 * 
	 * @return String
	 */
	public String toString() {
		return "ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Telefono: " + telefono + ", DNI: "
				+ dni;
	}

	/**
	 * Valida que el dni ingresado posea solo numeros, y un total de 8 caracteres
	 * 
	 * @param dni
	 * @return boolean
	 */
	public static boolean validarDNI(String dni) {
		boolean validacion = true;
		ArrayList<String> numeros = new ArrayList<String>();
		numeros.add("1");
		numeros.add("2");
		numeros.add("3");
		numeros.add("4");
		numeros.add("5");
		numeros.add("6");
		numeros.add("7");
		numeros.add("8");
		numeros.add("9");
		numeros.add("0");

		for (int i = 0; i < dni.length() && validacion == true; i++) {
			if (!numeros.contains((dni.substring(i, i + 1)))) {
				validacion = false;
			}
		}

		if (validacion == true) {
			if (dni.length() < 8 && dni.length() > 8) {
				validacion = false;
			}
		}

		return validacion;
	}

}
