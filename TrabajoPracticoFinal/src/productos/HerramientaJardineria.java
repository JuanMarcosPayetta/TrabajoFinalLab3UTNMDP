package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;
import interfaces.IEstablecerPrecioProductoInerte;
/**
 * 
 * Atributos de los productos clasificacion Herramienta jardineria
 *
 */
public abstract class HerramientaJardineria extends Producto implements IEstablecerPrecioProductoInerte{

	private String material;
	private String funcion; 

	public HerramientaJardineria() {
		super();
		this.material = null;
		this.funcion = null;
	}

	public HerramientaJardineria(String nombre, String marca, double precio, int stock, String descripcion,
			String material, String funcion) {
		super(nombre, marca, precio, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}

	public HerramientaJardineria(String codigo, String nombre, String marca, String clasificacion, double precio,
			int stock, String descripcion, String material, String funcion) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}
	
	public HerramientaJardineria(String nombre, String marca, int stock, String descripcion,
			String material, String funcion) {
		super(nombre, marca, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}
	

	/**
	 * Valida que la funcion de la herramienta pasada por parametro sea correcta
	 * @see #validarFuncionHerramienta(String)
	 * @param String
	 * @return String
	 */
	public static String validarFuncionHerramientaLlamada(String funcion) {
		String mensaje = null; // si devuelve null es correcto
		try {
			validarFuncionHerramienta(funcion);
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
	 * Valida que la funcion de la herramienta pasada por parametro sea correcta, lanzando una excepcion que sera capturada por {@link #validarFuncionHerramientaLlamada(String)}
	 * @see #validarFuncionHerramientaLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarFuncionHerramienta(String funcion)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> funciones = new ArrayList<String>();
		funciones.add("corte");
		funciones.add("desmalezado");
		funciones.add("labrado");
		funciones.add("limpieza");
		funciones.add("riego");
		funciones.add("transporte");

		if (funcion == null) {
			throw new NullPointerException(
					"Error, ingrese una funcion valida (corte, desmalezado, labrado, limpieza, riego, transporte)");
		} else if (funcion.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!funcion.matches("[a-zA-Z]*\\D{5}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 5 letras");
		} else if (!funciones.contains(funcion.toLowerCase())) {
			throw new InputMismatchException(
					"Error, ingrese una funcion valida (corte, desmalezado, labrado, limpieza, riego, transporte)");
		}

	}

	/** Devuelve el material del que esta hecho la herramienta
	 * @return String
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Setea el valor que indica el material de la herramienta, validando que sea correcto
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setMaterial(String material) {
		String mensaje = null;
		mensaje = validarCadenaCaracteresLlamada(material);
		if (mensaje == null) {
			this.material = material;
		}
		return mensaje;
	}

	/**
	 * Devuelve la funcion de la herramienta
	 * @return String
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * Setea el valor que indica la funcion de la herramienta, validando que sea correcta
	 * @see #validarFuncionHerramientaLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setFuncion(String funcion) {
		String mensaje = null;
		mensaje = validarFuncionHerramientaLlamada(funcion);
		if (mensaje == null) {
			this.funcion = funcion;
		}
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", Material: " + material + ", Funcion: " + funcion;
	}

	
}
