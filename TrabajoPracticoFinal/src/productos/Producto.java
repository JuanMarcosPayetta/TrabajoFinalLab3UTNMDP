package productos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import domain.Vivero;
import excepciones.CadenaInvalidaException;
import excepciones.DatoNumeroException;
import interfaces.IGenerarCodigo;

/**
 * 
 * Atributos de los productos clasificacion Producto
 *
 */
public abstract class Producto implements IGenerarCodigo, Comparable<Producto>, Serializable {

	private String codigo;
	private String nombre;
	private String marca;
	private String clasificacion;
	private double precio;
	private int stock;
	private String descripcion;

	public Producto() {
		this.codigo = null;
		this.nombre = null;
		this.marca = null;
		this.clasificacion = null;
		this.precio = 0;
		this.stock = 0;
		this.descripcion = null;
	}

	public Producto(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.marca = marca;
		this.clasificacion = clasificacion;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
	}

	/*
	 * Constructor para enviar como parametro a los metodos para entrar al mapa
	 */
	public Producto(String codigo, String clasificacion) {
		this.codigo = codigo;
		this.clasificacion = clasificacion;
		this.descripcion = null;
		this.precio = 0;
		this.stock = 0;
		this.marca = null;
		this.nombre = null;
	}

	/*
	 * Constructor con generador de codigo automatico
	 */
	public Producto(String nombre, String marca, double precio, int stock, String descripcion) {
		this.codigo = generarCodigo();
		this.nombre = nombre;
		this.marca = marca;
		this.clasificacion = null;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
	}

	/*
	 * Constructor con generador de codigo automatico pero sin ingreso de precio
	 */
	public Producto(String nombre, String marca, int stock, String descripcion) {
		this.codigo = generarCodigo();
		this.nombre = nombre;
		this.marca = marca;
		this.clasificacion = null;
		this.precio = 0;
		this.stock = stock;
		this.descripcion = descripcion;
	}

	public abstract void establecerClasificacion();

	/**
	 * Genera un codigo unico automatico que correspondera a un producto
	 * 
	 * @param String
	 */
	public String generarCodigo() {
		boolean existe = true;
		int longitud = 5;
		String codigoGenerado = null;
		String minusculas = "abcdefghijkmnlopqrstuvxyz";
		String numeros = "0123456789";
		String total = minusculas + numeros;

		char[] pass = new char[longitud];
		Random random = new Random();

		while (existe) // Mientras el codigo generado actualmente sea = al codigo actual de otro
						// producto
		{
			for (int i = 0; i < pass.length; i++) {
				pass[i] = total.charAt(random.nextInt(total.length()));
			}
			codigoGenerado = String.valueOf(pass); // codigo generado

			boolean encontrado = buscarCodigoExistentes(codigoGenerado); // busco si ya existe el codigo generado

			if (!encontrado) // Si el codigo generado no se repite con el codigo actual de otro producto,
								// corta el ciclo while
			{
				existe = false;
			}
		}
		return codigoGenerado;
	}

	/**
	 * Verifica que el codigo generado corresponda o no a un producto actual
	 * 
	 * @see #obtenerCodigosProucto()
	 * @param String
	 * @return boolean
	 */
	public boolean buscarCodigoExistentes(String codigoGenerado) {
		Vivero vivero = new Vivero();
		ArrayList<String> codigosActuales = new ArrayList<String>();
		codigosActuales = vivero.obtenerCodigosProducto();

		boolean encontrado = false;
		for (int i = 0; i < codigosActuales.size() && !encontrado; i++) {
			if (codigosActuales.get(i).equals(codigoGenerado)) // El codigo generado es igual a uno ya existente
			{
				encontrado = true;
			}
		}
		return encontrado;
	}

	/**
	 * Valida que el String ingresado sea correcto
	 * 
	 * @see #validarCadenaCaracteres(String)
	 * @param String
	 * @return String
	 */
	public static String validarCadenaCaracteresLlamada(String cadena) {
		String mensaje = null; // Si el retorno es "null" seria correcto
		try {
			validarCadenaCaracteres(cadena);
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		}

		return mensaje;
	}

	/**
	 * Valida que el String ingresado sea correcto, lanzando una excepcion que es
	 * capturada por {@link #validarCadenaCaracteresLlamada(String)}
	 * 
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @throws CadenaInvalidaException
	 * @throws NullPointerException
	 */
	private static void validarCadenaCaracteres(String cadena) throws CadenaInvalidaException, NullPointerException {
		if (cadena == null) {
			throw new NullPointerException("Error");
		} else if (cadena.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!cadena.matches("[a-zA-Z ]*\\D{3}")) {

			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 3 letras");
		}

	}

	/**
	 * Metodo generico que valida que el dato numerico ingresado sea valido
	 * 
	 * @see #validarValorNumerico(Number)
	 * @param <T> Valor numerico
	 * @return String
	 */
	public static <T extends Number> String validarValorNumericoLlamada(T valor) {
		String mensaje = null;
		try {
			validarValorNumerico(valor);
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Metodo generico que valida que el dato numerico ingresado sea valido,
	 * lanzando una excepcion que es capturada por
	 * {@link #validarValorNumericoLlamada(Number)}
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param <T> Valor numerico
	 * @throws DatoNumeroException
	 * @throws NullPointerException
	 */
	private static <T extends Number> void validarValorNumerico(T valor)
			throws DatoNumeroException, NullPointerException {
		if (valor == null) {
			throw new NullPointerException("Error, ingrese un dato numerico valido");
		} else if (valor instanceof Integer) {
			if (valor.intValue() < 0) {
				throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
			}
		} else if (valor instanceof Double) {
			if (valor.doubleValue() < 0) {
				throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION);
			}
		}
	}

	/**
	 * Valida que el dato boolean ingresado sea valido
	 * 
	 * @see #validarBoolean(Boolean)
	 * @param Boolean
	 * @return String
	 */
	public static String validarBooleanLlamada(Boolean tipo) {
		String mensaje = null;
		try {
			validarBoolean(tipo);
		} catch (InputMismatchException e) {
			mensaje = e.getMessage();
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida que el dato boolean ingresado sea valido, lanzando una excepcion que
	 * es capturada por {@link #validarBooleanLlamada(Boolean)}
	 * 
	 * @see #validarBooleanLlamada(Boolean)
	 * @param Boolean
	 * @throws NullPointerException
	 * @throws InputMismatchException
	 */
	private static void validarBoolean(Boolean tipo) throws NullPointerException, InputMismatchException {
		if (tipo == null) {
			throw new NullPointerException("Error, ingrese true or false");
		} else if (!Boolean.FALSE && !Boolean.TRUE) {
			throw new InputMismatchException("Ingrese un dato valido, true o false");
		}

	}

	/**
	 * Valida que la cantidad ingresada por parametro sea valida
	 * 
	 * @see #validarCantidadCompra(int)
	 * @param int
	 * @return String
	 */
	public static String validarCantidadCompraLlamada(int cantidad) {
		String mensaje = null;
		try {
			validarCantidadCompra(cantidad);
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida que la cantidad ingresada por parametro sea valida, lanzando una
	 * excepcion que es capturada por {@link #validarCantidadCompraLlamada(int)}
	 * 
	 * @see #validarCantidadCompraLlamada(int)
	 * @param int
	 * @throws DatoNumeroException
	 */
	private static void validarCantidadCompra(int cantidad) throws DatoNumeroException {
		if (cantidad <= 0) {
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION + ", ni igual a 0\n");
		}
	}

	/**
	 * Valida que el precio ingresado por parametro sea valido
	 * 
	 * @see #validarPrecio(double)
	 * @param double
	 * @return String
	 */
	public static String validarPrecioLlamada(double precio) {
		String mensaje = null;
		try {
			validarPrecio(precio);
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida que el precio del producto ingresado por parametro sea valido,
	 * lanzando una excepcion que es capturada por
	 * {@link #validarPrecioLlamada(double)}
	 * 
	 * @see #validarPrecioLlamada(double)
	 * @param double
	 * @throws DatoNumeroException
	 */
	private static void validarPrecio(double precio) throws DatoNumeroException {
		if (precio <= 0) {
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION + ", ni igual a 0\n");
		}
	}

	/**
	 * Retorna el codigo correspondiente a un producto
	 * 
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el codigo de un producto, verificando que no este siendo usado por
	 * otro producto
	 * 
	 * @see #buscarCodigoExistentes(String)
	 * @param String
	 * @return String
	 */
	public String setCodigo(String codigo) {

		String mensaje = "Error, codigo ya existente, genero uno nuevo porfavor";
		boolean validacion = buscarCodigoExistentes(codigo);
		if (!validacion) // si el codigo no existe
		{
			this.codigo = codigo;
			mensaje = "Codigo establecido con exito";
		}
		return mensaje;
	}

	/**
	 * Retorna el nombre correspondiente a un producto
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de un producto, validando que sea correcto
	 * 
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setNombre(String nombre) {
		String mensaje = validarCadenaCaracteresLlamada(nombre); // si devuelve "null" el dato es correcto

		if (mensaje == null) {
			this.nombre = nombre;
		}

		return mensaje;
	}

	/**
	 * Retorna la marca correspondiente a un producto
	 * 
	 * @return String
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Establece la marca correspondiente a un producto, validando que sea correcto
	 * 
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setMarca(String marca) {

		String mensaje = validarCadenaCaracteresLlamada(marca); // si devuelve "null" el dato es correcto

		if (mensaje == null) {
			this.marca = marca;
		}
		return mensaje;
	}

	/**
	 * Retorna la clasificacion a la que pertenece un producto
	 * 
	 * @return String
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * Establece la clasifiacion a la que pertenece un producto
	 * 
	 * @param String
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * Retorna el precio correspondiente a un producto
	 * 
	 * @return double
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio correspondiente a un producto, validando que sea correcto
	 * 
	 * @see #validarPrecioLlamada(double)
	 * @param double
	 * @return String
	 */
	public String setPrecio(double precio) {
		String mensaje = validarPrecioLlamada(precio); // si devuelve "null" el dato es correcto

		if (mensaje == null) {
			this.precio = precio;
		}
		return mensaje;
	}

	/**
	 * Retorna el stock correspondiente a un producto
	 * 
	 * @return int
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Establece el stock correspondiente a un producto, validando que sea correcto
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param int
	 * @return String
	 */
	private String setStock(int stock) { // es privado por que se utiliza en aumentar o disminuir stock
		String mensaje = validarValorNumericoLlamada(stock); // si devuelve "null" el dato es correcto

		if (mensaje == null) {
			this.stock = stock;
		}
		return mensaje;
	}

	/**
	 * Aumenta el stock de un producto, validando que la cantidad ingresada sea
	 * correcta
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param int
	 * @return String
	 */
	public String aumentarStock(int unidades) {
		String mensaje = validarValorNumericoLlamada(unidades);
		int nuevoStock = 0;

		if (mensaje == null) {
			nuevoStock = getStock() + unidades;
			mensaje = setStock(nuevoStock);
		}
		return mensaje;
	}

	/**
	 * Disminuye el stock de un producto, validando que la cantidad ingresada sea
	 * correcta
	 * 
	 * @see #validarValorNumericoLlamada(Number)
	 * @param int
	 * @return String
	 */
	public String disminuitStock(int unidades) {
		String mensaje = validarValorNumericoLlamada(unidades);
		int nuevoStock = 0;

		if (mensaje == null) {
			nuevoStock = getStock() - unidades;
			if (nuevoStock < 0) {
				mensaje = "Error el stock no puede ser menor a cero";
			}

			else {
				mensaje = setStock(nuevoStock);
			}
		}
		return mensaje;
	}

	/**
	 * Retorna la descripcion correspondiente a un producto
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion correspondiente a un producto
	 * 
	 * @param String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Codigo: " + codigo + ", Nombre: " + nombre + ", Marca: " + marca + ", Clasificacion: " + clasificacion
				+ ", Precio: " + precio + ", Stock: " + stock + ", Descripcion: " + descripcion;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	/**
	 * Compara dos objetos, retornando true o false en caso de ser iguales o no
	 * 
	 * @param Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		boolean res = false;

		if (obj != null) {
			if (obj instanceof Producto) {
				Producto miProducto = (Producto) obj;
				if (this.getCodigo().equals(miProducto.getCodigo())) {
					res = true;
				}
			}
		}

		return res;
	}

	/**
	 * Valida que el stock actual de un producto sea igual o mayor a la ingresada
	 * por parametro
	 * 
	 * @param int
	 * @return boolean
	 */
	public boolean ComprobarStock(int cantidad) {
		boolean res = false;

		if (this.getStock() >= cantidad) {
			res = true;
		}
		return res;
	}

}
