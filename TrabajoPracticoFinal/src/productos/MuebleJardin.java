package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

/**
 * 
 * Atributos de los productos clasificacion mueblesjardin
 *
 */
public class MuebleJardin extends ProductoDeHogar {

	private String tipo;
	private double alto;
	private double largo;
	private double ancho;

	public MuebleJardin() {
		super();
		this.tipo = null;
		this.alto = 0;
		this.ancho = 0;
		this.largo = 0;
	}

	public MuebleJardin(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String tipo, double alto, double largo, double ancho) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
	}

	public MuebleJardin(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String tipo, double alto, double largo, double ancho) {
		super(nombre, marca, precio, stock, descripcion, material);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
		establecerClasificacion();
	}

	public MuebleJardin(String nombre, String marca, int stock, String descripcion, String material, String tipo,
			double alto, double largo, double ancho) {
		super(nombre, marca, stock, descripcion, material);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
		establecerClasificacion();
		establecerPrecio();
	}

	/**
	 * Devuelve el tipo de mueble
	 * 
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Devuelve el alto del mueble
	 * 
	 * @return double
	 */
	public double getAlto() {
		return alto;
	}

	/**
	 * Setea el valor que indica el alto del mueble, validando que sea correcto
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setAlto(double alto) {

		String mensaje = validarValorNumericoLlamada(alto);

		if (mensaje == null) {

			this.alto = alto;
		}
		return mensaje;
	}

	/**
	 * Devuelve el largo del mueble
	 * 
	 * @return double
	 */
	public double getLargo() {
		return largo;
	}

	/**
	 * Setea el valor que indica el largo del mueble, validando que sea correcto
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setLargo(double largo) {
		String mensaje = validarValorNumericoLlamada(largo);

		if (mensaje == null) {

			this.largo = largo;
		}
		return mensaje;
	}

	/**
	 * Devuelve el ancho del mueble
	 * 
	 * @return double
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * Setea el valor que indica el ancho del mueble, validando que sea correcto
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setAncho(double ancho) {

		String mensaje = validarValorNumericoLlamada(ancho);

		if (mensaje == null) {

			this.ancho = ancho;
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
		return super.toString() + ", Tipo:" + tipo + ", Alto: " + alto + ", Largo: " + largo + ", Ancho: " + ancho;
	}

	/**
	 * Valida el tipo de mueble pasado por parametro
	 * 
	 * @see #validarTipoMueble(String)
	 * @param String
	 * @return String
	 */
	public static String validarTipoMuebleLlamada(String mueble) {
		String mensaje = null;
		try {
			validarTipoMueble(mueble);
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		} catch (InputMismatchException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida el tipo de mueble pasado por parametro, lanzando una excepcion que
	 * sera capturada por {@link #validarTipoMuebleLlamada(String)}
	 * 
	 * @see #validarTipoMuebleLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarTipoMueble(String mueble)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tiposMueble = new ArrayList<String>();
		tiposMueble.add("mesa");
		tiposMueble.add("silla");
		tiposMueble.add("reposera");
		tiposMueble.add("camastro");
		tiposMueble.add("hamaca");

		if (mueble == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (mueble.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!mueble.matches("[a-zA-Z]*\\D{4}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + "4 letras");
		} else if (!tiposMueble.contains(mueble.toLowerCase())) {
			throw new InputMismatchException(
					"Ingrese un tipo de mueble valido (mesa, silla, reposera, camastro, hamaca)");
		}
	}

	/**
	 * Setea el valor que indica el tipo de mueble, validando que sea correcto
	 * 
	 * @see #validarTipoMuebleLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setTipo(String tipoMueble) {
		String mensaje = null;
		mensaje = validarTipoMuebleLlamada(tipoMueble);

		if (mensaje == null) {
			this.tipo = tipoMueble;
		}
		return mensaje;
	}

	/**
	 * Establece la clasificacion del objeto a: "Mueble de jardin"
	 * 
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Mueble de jardin");
	}

	/**
	 * Establece el precio del mueble
	 * 
	 * @see #getTipo()
	 * @see #getLargo()
	 */
	@Override
	public void establecerPrecio() {
		double precioMueble = 0;

		if (getTipo().equalsIgnoreCase("mesa")) {
			if (getLargo() < 120) {
				precioMueble = 11500;
			} else if (getLargo() > 120 && getLargo() < 180) {
				precioMueble = 16000;
			} else if (getLargo() > 180 && getLargo() < 220) {
				precioMueble = 24.500;
			} else {
				precioMueble = 30000;
			}

		} else if (getTipo().equalsIgnoreCase("silla")) {
			precioMueble = 9000;
		} else if (getTipo().equalsIgnoreCase("reposera")) {
			precioMueble = 5200;
		} else if (getTipo().equalsIgnoreCase("camastro")) {
			precioMueble = 45.450;
		} else if (getTipo().equalsIgnoreCase("hamaca")) {
			precioMueble = 37.780;
		}

		setPrecio(precioMueble);

	}

	/**
	 * Compara dos objetos, indicando si son iguales, mayor o menor
	 * 
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
