package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

/**
 * 
 * Atributos de los productos clasificacion maceta
 *
 */
public abstract class Maceta extends ProductoDeHogar {

	private String forma;

	public Maceta() {
		super();
		this.forma = null;
	}

	public Maceta(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String forma) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material);
		this.forma = forma;
	}

	public Maceta(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String forma) {
		super(nombre, marca, precio, stock, descripcion, material);
		this.forma = forma;
	}

	public Maceta(String nombre, String marca, int stock, String descripcion, String material, String forma) {
		super(nombre, marca, stock, descripcion, material);
		this.forma = forma;
	}

	/**
	 * Valida la forma de la maceta segun el tipo de maceta (poliedro u redonda)
	 * 
	 * @see #validarFormaMaceta(String, String)
	 * @param String
	 * @param String
	 * @return String
	 */
	public static String validarFormaMacetaLlamada(String opcion, String forma) {
		String mensaje = null;
		try {
			validarFormaMaceta(opcion, forma);
		} catch (InputMismatchException e) {
			mensaje = e.getMessage();
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida la forma de la maceta segun el tipo de maceta (poliedro u redonda),
	 * lanzando una excepcion que sera capturada por
	 * {@link #validarFormaMacetaLlamada(String, String)}
	 * 
	 * @see #validarFormaMacetaLlamada(String, String)
	 * @param String
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarFormaMaceta(String opcion, String forma)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("poliedro");
		opciones.add("redonda");

		ArrayList<String> formasPoliedro = new ArrayList<String>();
		formasPoliedro.add("jardinera");
		formasPoliedro.add("cubo");
		formasPoliedro.add("piramidal");

		ArrayList<String> formasRedonda = new ArrayList<String>();
		formasRedonda.add("ovalada");
		formasRedonda.add("circular");

		if (opcion == null) {
			throw new NullPointerException("Error, ingrese una opcion valida (Poliedro, Redonda)");
		} else if (opcion.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!opcion.matches("[a-zA-Z]*\\D{7}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 7 letras");
		} else if (!opciones.contains(opcion.toLowerCase())) {
			throw new InputMismatchException("Error, ingrese una opcion valida (Poliedro, Redonda)");
		}

		if (forma == null) {
			throw new NullPointerException("Error, ingrese una forma valida");
		} else if (forma.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!forma.matches("[a-zA-Z]*\\D{4}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 4 letras");
		} else if (opcion.equalsIgnoreCase("poliedro")) {
			if (!formasPoliedro.contains(forma.toLowerCase())) {
				throw new InputMismatchException("Error, ingrese una forma valida (jardinera, cubo, piramidal)");
			}
		} else if (opcion.equalsIgnoreCase("redonda")) {
			if (!formasRedonda.contains(forma.toLowerCase())) {
				throw new InputMismatchException("Error, ingrese una forma valida (circular, ovalada)");
			}
		}

	}

	/**
	 * Devuelve la forma de la maceta
	 * 
	 * @return String
	 */
	public String getForma() {
		return forma;
	}

	/**
	 * Setea el valor que indica la forma de la maceta, validando que sea correcta
	 * 
	 * @see #setForma(String)
	 * @param String
	 * @return String
	 */
	public String setForma(String forma) {
		String mensaje = null;
		if (mensaje == null) {
			this.forma = forma;
		}
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", Forma: " + forma;
	}

}
