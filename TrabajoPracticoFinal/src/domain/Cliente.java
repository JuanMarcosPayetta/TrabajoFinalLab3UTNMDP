package domain;

public class Cliente {

	private int id;
	static private int autoid;
	private String nombre;
	private String apellido;
	private int telefono;
	private String dni;

	
	public Cliente(String nombre, String apellido, int telefono, String dni) {
		this.id =autoid;
		autoid++; 
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dni = dni;
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
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	/**
	 * @return the telefono
	 */
	public int getTelefono() {
		return telefono;
	}


	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
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
	public void setDni(String dni) {
		this.dni = dni;
	} 
	
	
	
	
}
