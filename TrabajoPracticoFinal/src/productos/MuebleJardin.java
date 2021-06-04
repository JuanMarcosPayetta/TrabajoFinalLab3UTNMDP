package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

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

	public String getTipo() {
		return tipo;
	}

	public double getAlto() {
		return alto;
	}

	public String setAlto(double alto) {

		String mensaje = validarValorNumericoLlamada(alto);

		if (mensaje == null) {

			this.alto = alto;
		}
		return mensaje;
	}

	public double getLargo() {
		return largo;
	}

	public String setLargo(double largo) {
		String mensaje = validarValorNumericoLlamada(largo);

		if (mensaje == null) {

			this.largo = largo;
		}
		return mensaje;
	}

	public double getAncho() {
		return ancho;
	}

	public String setAncho(double ancho) {

		String mensaje = validarValorNumericoLlamada(ancho);

		if (mensaje == null) {

			this.ancho = ancho;
		}
		return mensaje;
	}

	@Override
	public String toString() {
		return super.toString() + ", tipo=" + tipo + ", alto=" + alto + ", largo=" + largo + ", ancho=" + ancho;
	}

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

	private static void validarTipoMueble(String mueble)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tiposMueble = new ArrayList<String>();
		tiposMueble.add("mesa");
		tiposMueble.add("silla");
		tiposMueble.add("reposera");
		tiposMueble.add("sillon");
		tiposMueble.add("hamaca");

		if (mueble == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (mueble.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!mueble.matches("[a-zA-Z]*\\D{4}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + "4 letras");
		} else if (!tiposMueble.contains(mueble.toLowerCase())) {
			throw new InputMismatchException(
					"Ingrese un tipo de mueble valido (mesa, silla, reposera, sill�n, hamaca)");
		}
	}

	public String setTipo(String tipoMueble) {
		String mensaje = null;
		mensaje = validarTipoMuebleLlamada(tipoMueble);

		if (mensaje == null) {
			this.tipo = tipoMueble;
		}
		return mensaje;
	}
	
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Mueble de jardin");
	}


}
