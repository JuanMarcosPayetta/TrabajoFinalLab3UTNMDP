package domain;

import excepciones.CadenaInvalidaException;


public class Cliente {

	private int id;
	static private int autoid=1;
	private String nombre;
	private String apellido;
	private String telefono;
	private String dni;

	public Cliente(String nombre, String apellido, String telefono, String dni) {
		this.id = autoid;
		autoid++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dni = dni;
	}

	/*
	 * Validacion Nombre, llamada en el main
	 */
	public static String validarCadenaCaracteresLlamada(String nombre) {
		String mensaje = null; // Si el retorno es "null" seria correcto
		try {
			validarCadenaCaracteres(nombre);
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		}

		return mensaje;
	}

	private static void validarCadenaCaracteres(String cadena) throws CadenaInvalidaException, NullPointerException {
		if (cadena == null) {
			throw new NullPointerException("Error");
		} else if (cadena.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!cadena.matches("[a-zA-Z]*\\D{3}")) // El nombre debe contener al menos 3 letras, ya sean miniscula o
														// mayuscula (no numeros)
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 3 letras");
		}

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
		} else if (!cadena.matches("[0-9]*\\D{7}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 7 caracteres");
		}
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

		String mensaje = validarCadenaCaracteresLlamada(nombre);

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
		String mensaje = validarCadenaCaracteresLlamada(apellido);

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

				if (this.dni.equals(aux.getDni())) {
					estado = true;
				}
			}
		}

		return estado;
	}

}
