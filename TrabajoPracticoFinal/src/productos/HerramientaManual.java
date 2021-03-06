package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

/**
 * 
 * Atributos de los productos clasificacion Herramienta Manual
 *
 */
public class HerramientaManual extends HerramientaJardineria {

	private String tamanio;
	private boolean reforzada;

	public HerramientaManual() {
		super();
		this.tamanio = null;
		this.reforzada = false;
	}

	public HerramientaManual(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String funcion, String tamanio, boolean reforzada) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
	}

	public HerramientaManual(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String funcion, String tamanio, boolean reforzada) {
		super(nombre, marca, precio, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
		establecerClasificacion();
	}

	public HerramientaManual(String nombre, String marca, int stock, String descripcion, String material,
			String funcion, String tamanio, boolean reforzada) {
		super(nombre, marca, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
		establecerClasificacion();
		establecerPrecio();
	}

	/**
	 * Valida que el tamanio pasado por parametro de la herramienta manual sea
	 * correcto
	 * 
	 * @see #validarTamanioHManual(String)
	 * @param String
	 * @return String
	 */
	public static String validarTamanioHManualLlamada(String tamanio) {
		String mensaje = null; // si devuelve null es correcto
		try {
			validarTamanioHManual(tamanio);
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
	 * Valida que el tamanio pasado por parametro de la herramienta manual sea
	 * correcto, lanzando una excepcion que sera capturada por
	 * {@link #validarTamanioHManualLlamada(String)}
	 * 
	 * @see #validarTamanioHManualLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarTamanioHManual(String tamanio)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tamanioValido = new ArrayList<String>();
		tamanioValido.add("grande");
		tamanioValido.add("mediano");
		tamanioValido.add("peque???o");

		if (tamanio == null) {
			throw new NullPointerException("Error, ingrese un dato valido (grande, mediano, peque???o)");
		} else if (tamanio.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!tamanio.matches("[a-zA-Z]*\\D{6}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 6 letras");
		} else if (!tamanioValido.contains(tamanio.toLowerCase())) {
			throw new InputMismatchException("Error, ingrese un dato valido (grande, mediano, peque???o)");
		}
	}

	/**
	 * Devuelve el tamanio de la herramienta
	 * 
	 * @return String
	 */
	public String getTamanio() {
		return tamanio;
	}

	/**
	 * Setea el valor que indica el tamanio de la herramienta, validando que sea
	 * correcto
	 * 
	 * @see #validarTamanioHManualLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setTamanio(String tamanio) {
		String mensaje = null;
		mensaje = validarTamanioHManualLlamada(tamanio);
		if (mensaje == null) {
			this.tamanio = tamanio;
		}

		return mensaje;
	}

	/**
	 * Devuelve si la herramienta esta reforzada o no
	 * 
	 * @return String
	 */
	public boolean isReforzada() {
		return reforzada;
	}

	/**
	 * Setea el valor que indica si la herramienta esta reforzada o no lo esta,
	 * validando que sea correcto
	 * 
	 * @see #validarBooleanLlamada(Boolean)
	 * @param boolean
	 * @return String
	 */
	public String setReforzada(boolean reforzada) {
		String mensaje = null;
		mensaje = validarBooleanLlamada(reforzada);
		if (mensaje == null) {
			this.reforzada = reforzada;
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
		return super.toString() + ", Tamanio: " + tamanio + ", Es reforzada: " + reforzada;
	}

	/**
	 * Establece la clasificacion del objeto a "Herramienta manual"
	 * 
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Herramienta manual");
	}

	/**
	 * Establece el precio de la herramienta
	 * 
	 * @see #getFuncion()
	 * @see #getTamanio()
	 * @see #isReforzada()
	 * @see #setPrecio(double)
	 */
	@Override
	public void establecerPrecio() {

		double precioH = 0;
		if (getFuncion().equalsIgnoreCase("corte")) {
			if (getTamanio().equalsIgnoreCase("grande")) {
				precioH = 5799.9;
				if (isReforzada()) {
					precioH = 6400;
				}
			} else if (getTamanio().equalsIgnoreCase("mediano")) {
				precioH = 4300;
				if (isReforzada()) {
					precioH = 4700;
				}
			} else {
				precioH = 2600;
				if (isReforzada()) {
					precioH = 3200;
				}
			}
		} else if (getFuncion().equalsIgnoreCase("desmalezado")) {
			if (getTamanio().equalsIgnoreCase("grande")) {
				precioH = 2150.6;
				if (isReforzada()) {
					precioH = 2420;
				}
			} else if (getTamanio().equalsIgnoreCase("mediano")) {
				precioH = 1630.2;
				if (isReforzada()) {
					precioH = 1993;
				}
			} else {
				precioH = 1040.8;
				if (isReforzada()) {
					precioH = 1330.2;
				}
			}
		} else if (getFuncion().equalsIgnoreCase("labrado")) {
			if (getTamanio().equalsIgnoreCase("grande")) {
				precioH = 2799;
				if (isReforzada()) {
					precioH = 3680.4;
				}
			} else if (getTamanio().equalsIgnoreCase("mediano")) {
				precioH = 2100;
				if (isReforzada()) {
					precioH = 2460.7;
				}
			} else {
				precioH = 1658;
				if (isReforzada()) {
					precioH = 1950.5;
				}
			}
		} else if (getFuncion().equalsIgnoreCase("limpieza")) {
			if (getTamanio().equalsIgnoreCase("grande")) {
				precioH = 780;
				if (isReforzada()) {
					precioH = 1246.3;
				}
			} else if (getTamanio().equalsIgnoreCase("mediano")) {
				precioH = 530;
				if (isReforzada()) {
					precioH = 699;
				}
			} else {
				precioH = 400;
				if (isReforzada()) {
					precioH = 500;
				}
			}
		} else if (getFuncion().equalsIgnoreCase("riego")) {
			if (getTamanio().equalsIgnoreCase("grande")) {
				precioH = 15600;
				if (isReforzada()) {
					precioH = 16800.8;
				}
			} else if (getTamanio().equalsIgnoreCase("mediano")) {
				precioH = 8600;
				if (isReforzada()) {
					precioH = 10280.4;
				}
			} else {
				precioH = 3699;
				if (isReforzada()) {
					precioH = 4400;
				}
			}
		} else if (getFuncion().equalsIgnoreCase("transporte")) {
			if (getTamanio().equalsIgnoreCase("grande")) {
				precioH = 16400;
				if (isReforzada()) {
					precioH = 18200;
				}
			} else if (getTamanio().equalsIgnoreCase("mediano")) {
				precioH = 12000;
				if (isReforzada()) {
					precioH = 13560;
				}
			} else {
				precioH = 7800;
				if (isReforzada()) {
					precioH = 9450;
				}
			}
		}

		setPrecio(precioH);
	}

	/**
	 * Compara dos objetos, indicando si son iguales, mayor o menor
	 * 
	 * @see #getPrecio()
	 * @param Producto
	 * @return int
	 */
	@Override
	public int compareTo(Producto o) {
		int res = -2;
		if (o != null) {
			if (this.getPrecio() == o.getPrecio()) {
				res = 0;
			} else if (this.getPrecio() > o.getPrecio()) {
				res = 1;
			} else {
				res = -1;
			}
		}
		return res;
	}
}
