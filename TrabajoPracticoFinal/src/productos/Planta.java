package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;
import excepciones.DatoNumeroException;
import interfaces.IEstablecerPrecioPlanta;

/**
 * 
 * Atributos de los productos clasificacion Planta
 */
public abstract class Planta extends Producto implements IEstablecerPrecioPlanta {

	private int mesesDeVida;
	private String estacionPlantacion;
	private String habitat;
	private int altura;
	private boolean flor;
	private String nivelExposicionSolar;
	private String tipoRaiz;
	private boolean aromatica;
	private int cantidadFertilizante;

	public Planta() {
		super();
		this.mesesDeVida = 0;
		this.estacionPlantacion = null;
		this.habitat = null;
		this.altura = 0;
		this.flor = false;
		this.nivelExposicionSolar = null;
		this.tipoRaiz = null;
		this.aromatica = false;
		this.cantidadFertilizante = 0;
	}

	public Planta(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.mesesDeVida = mesesDeVida;
		this.estacionPlantacion = estacionPlantacion;
		this.habitat = habitat;
		this.altura = altura;
		this.flor = flor;
		this.nivelExposicionSolar = nivelExposicionSolar;
		this.tipoRaiz = tipoRaiz;
		this.aromatica = aromatica;
		this.cantidadFertilizante = cantidadFertilizante;
	}

	public Planta(String nombre, String marca, double precio, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor, String nivelExposicionSolar,
			String tipoRaiz, boolean aromatica) {
		super(nombre, marca, precio, stock, descripcion);
		this.mesesDeVida = mesesDeVida;
		this.estacionPlantacion = estacionPlantacion;
		this.habitat = habitat;
		this.altura = altura;
		this.flor = flor;
		this.nivelExposicionSolar = nivelExposicionSolar;
		this.tipoRaiz = tipoRaiz;
		this.aromatica = aromatica;
		this.cantidadFertilizante = 0;
	}

	public Planta(String nombre, String marca, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor, String nivelExposicionSolar,
			String tipoRaiz, boolean aromatica) {
		super(nombre, marca, stock, descripcion);
		this.mesesDeVida = mesesDeVida;
		this.estacionPlantacion = estacionPlantacion;
		this.habitat = habitat;
		this.altura = altura;
		this.flor = flor;
		this.nivelExposicionSolar = nivelExposicionSolar;
		this.tipoRaiz = tipoRaiz;
		this.aromatica = aromatica;
		this.cantidadFertilizante = 0;
	}

	// para la compra de productos (ya que producto no se puede instanciar por ser
	// abstracta)
	public Planta(String codigo, String clasificacion) {
		super(codigo, clasificacion);
		this.mesesDeVida = 0;
		this.estacionPlantacion = null;
		this.habitat = null;
		this.altura = 0;
		this.flor = false;
		this.nivelExposicionSolar = null;
		this.tipoRaiz = null;
		this.aromatica = false;
		this.cantidadFertilizante = 0;
	}

	/**
	 * Metodo abstracto, establece la cantidad de fertilizante que necesita la
	 * planta
	 */
	public abstract void establecerCantidadFertilizante();

	/**
	 * Valida que el numero pasado por parametro sea correcto
	 * 
	 * @see #validarDatosNumericos(Integer)
	 * @param Integer
	 * @return String
	 */
	public static String validarDatosNumericosLlamada(Integer numero) {
		String mensaje = null; // si el retorno es null es correcto
		try {
			validarDatosNumericos(numero);
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida que el numero pasado por parametro sea correcto, lanzando una
	 * excepcion que es capturada por {@link #validarDatosNumericosLlamada(Integer)}
	 * 
	 * @see #validarDatosNumericosLlamada(Integer)
	 * @param Integer
	 * @throws DatoNumeroException
	 * @throws NullPointerException
	 */
	private static void validarDatosNumericos(Integer numero) throws DatoNumeroException, NullPointerException {
		if (numero == null) {
			throw new NullPointerException("Error");
		} else if (numero <= 0) {
			throw new DatoNumeroException("Ingrese un numero superior a 0");
		}
	}

	/**
	 * Valida que el tipo de raiz pasado por parametro sea correcto
	 * 
	 * @see #validarTipoRaiz(String)
	 * @param String
	 * @return String
	 */
	public static String validarTipoRaizLlamada(String tipo) {
		String mensaje = null; // retorna "null" si es correcto
		try {
			validarTipoRaiz(tipo);
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
	 * Valida que el tipo de raiz sea valido, lanzando una excepcion que es
	 * capturada por {@link #validarTipoRaizLlamada(String)}
	 * 
	 * @see #validarTipoRaizLlamada(String)
	 * @param String
	 */
	private static void validarTipoRaiz(String tipo)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("axonomorfa");
		tipos.add("ramificada");
		tipos.add("fascicular");
		tipos.add("tuberosa");
		tipos.add("napiforme");
		tipos.add("adventicia");

		if (tipo == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!tipo.matches("[a-zA-Z]*\\D{8}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 8 letras");
		} else if (!tipos.contains(tipo.toLowerCase())) {
			throw new InputMismatchException(
					"Ingrese un tipo de raiz valida (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia");
		}
	}

	/**
	 * Valida que el tipo de estacion pasada por parametro sea correcta
	 * 
	 * @see #validarEstacion(String)
	 * @param String
	 * @return String
	 */
	public static String validarEstacionLlamada(String tipo) {
		String mensaje = null; // retorna "null" si es correcto
		try {
			validarEstacion(tipo);
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
	 * Valida que el tipo de estacion pasada por parametro sea correcta, lanzando
	 * una excepcion que es capturada por {@link #validarEstacionLlamada(String)}
	 * 
	 * @see #validarEstacionLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarEstacion(String tipo)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("verano");
		tipos.add("otonio");
		tipos.add("invierno");
		tipos.add("primavera");
		tipos.add("anual");

		if (tipo == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!tipo.matches("[a-zA-Z]*\\D{5}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 5 letras");
		} else if (!tipos.contains(tipo.toLowerCase())) {
			throw new InputMismatchException(
					"Ingrese un tipo de raiz valida (otonio, invierno, primavera, verano, anual)");
		}
	}

	/**
	 * Valida que el habitat pasado por parametro sea correcto
	 * 
	 * @see #validarHabitat(String, String)b
	 * @param String
	 * @param String
	 * @return String
	 */
	public static String validarHabitatLlamada(String opcion, String tipo) {
		String mensaje = null; // retorna "null" si es correcto
		try {
			validarHabitat(opcion, tipo);
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
	 * Valida que el habitat pasado por parametro sea correcto (La opcion es para
	 * determinar los posibles habitat validos segun el tipo de planta (terrestre,
	 * acuatica) Lanza una excepcion que es capturada por
	 * {@link #validarHabitatLlamada(String, String)}
	 * 
	 * @see #validarHabitatLlamada(String, String)
	 * @param String
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarHabitat(String opcion, String tipo)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("terrestre");
		opciones.add("acuatica");

		ArrayList<String> terrestre = new ArrayList<String>();
		terrestre.add("pradera");
		terrestre.add("desierto");
		terrestre.add("bosque");
		terrestre.add("pantano");
		terrestre.add("polar");
		terrestre.add("sabana");
		terrestre.add("selva");

		ArrayList<String> acuatica = new ArrayList<String>();
		acuatica.add("oceano");
		acuatica.add("rio");
		acuatica.add("lago");
		acuatica.add("arrecife");
		acuatica.add("estanque");
		acuatica.add("pantano");
		acuatica.add("delta");

		if (opcion == null) {
			throw new NullPointerException("Error, ingrese una opcion valida (Terrestre, Acuatica)");
		} else if (opcion.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!opcion.matches("[a-zA-Z]*\\D{8}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 8 letras");
		} else if (!opciones.contains(opcion.toLowerCase())) {
			throw new InputMismatchException("Error, ingrese una opcion valida (Terrestre, Acuatica)");
		}

		if (tipo == null) {
			throw new NullPointerException("Error, ingrese un habitat valido");
		} else if (tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!tipo.matches("[a-zA-Z]*\\D{3}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 3 letras");
		} else if (opcion.equalsIgnoreCase("terrestre")) {
			if (!terrestre.contains(tipo.toLowerCase())) {
				throw new InputMismatchException(
						"Error, ingrese un habitat valido (pradera, desierto, bosque, pantano, polar, sabana, selva");
			}
		} else if (opcion.equalsIgnoreCase("acuatica")) {
			if (!acuatica.contains(tipo.toLowerCase())) {
				throw new InputMismatchException(
						"Error, ingrese un habitat valido (oceano, rio, lago, arrecife, estanque, pantano, delta");
			}
		}

	}

	/**
	 * Valida que el nivel de exposicion solar pasado por marametro sea correcto
	 * 
	 * @see #validarExpSolar(String)
	 * @param String
	 * @param String
	 * @return String
	 */
	public static String validarExpSolarLlamada(String tipo) {
		String mensaje = null; // retorna "null" si es correcto
		try {
			validarExpSolar(tipo);
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
	 * Valida que el nivel de exposicion solar pasado por marametro sea correcto,
	 * lanzando una excepcion que es capturada por
	 * {@link #validarExpSolarLlamada(String)}
	 * 
	 * @see #validarExpSolarLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarExpSolar(String tipo)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("sombra");
		tipos.add("mediasombra");
		tipos.add("pleno sol");

		if (tipo == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!tipo.matches("[a-zA-Z]*\\D{5}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 5 letras");
		} else if (!tipos.contains(tipo.toLowerCase())) {
			throw new InputMismatchException(
					"Ingrese un grado de exposicion solar valido (sombra, mediasombra, pleno sol)");
		}

	}

	/**
	 * Devuelve los meses de vida de la planta
	 * 
	 * @return int
	 */
	public int getMesesDeVida() {
		return mesesDeVida;
	}

	/**
	 * Setea los meses de vida de la planta, validando que sea correcto
	 * 
	 * @see #validarDatosNumericosLlamada(Integer)
	 * @param int
	 * @return String
	 */
	public String setMesesDeVida(int mesesDeVida) {
		String mensaje = null;
		mensaje = validarDatosNumericosLlamada(mesesDeVida);
		if (mensaje == null) {
			this.mesesDeVida = mesesDeVida;
		}
		return mensaje;
	}

	/**
	 * Devuelve la altura de la planta
	 * 
	 * @return int
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Setea la altura de la planta, validando que sea correcta
	 * 
	 * @see #validarDatosNumericosLlamada(Integer)
	 * @param int
	 * @return String
	 */
	public String setAltura(int altura) {
		String mensaje = null;
		mensaje = validarDatosNumericosLlamada(altura);
		if (mensaje == null) {
			this.altura = altura;
		}
		return mensaje;
	}

	/**
	 * Devuelve la estacion de plantacion de la planta
	 * 
	 * @return String
	 */
	public String getEstacionPlantacion() {
		return estacionPlantacion;
	}

	/**
	 * Setea el valor de la estacion de la planta, validando que sea correcta
	 * 
	 * @see #validarEstacionLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setEstacionPlantacion(String estacionPlantacion) {

		String mensaje = null;
		mensaje = validarEstacionLlamada(estacionPlantacion);
		if (mensaje == null) {
			this.estacionPlantacion = estacionPlantacion;
		}

		return mensaje;
	}

	/**
	 * Devuelve el habitat de la planta
	 * 
	 * @return String
	 */
	public String getHabitat() {
		return habitat;
	}

	/**
	 * Setea el valor del habitat de la planta, validando que sea correcto
	 * 
	 * @see #validarHabitatLlamada(String, String)
	 * @param String
	 * @param String
	 * @return String
	 */
	public String setHabitat(String opcion, String habitat) {

		String mensaje = null;
		mensaje = validarHabitatLlamada(opcion, habitat);
		if (mensaje == null) {
			this.habitat = habitat;
		}
		return mensaje;
	}

	/**
	 * Devuelve si la planta tiene flor o no
	 * 
	 * @return boolean
	 */
	public boolean isFlor() {
		return flor;
	}

	/**
	 * Setea el valor que indica si la planta tiene o no flor, validando que sea
	 * correcto
	 * 
	 * @see #validarBooleanLlamada(Boolean)
	 * @param boolean
	 * @return String
	 */
	public String setFlor(boolean flor) {
		String mensaje = null;
		mensaje = validarBooleanLlamada(flor);
		if (mensaje == null) {
			this.flor = flor;
		}
		return mensaje;
	}

	/**
	 * Devuelve la exposicion solar de la planta
	 * 
	 * @return String
	 */
	public String getNivelExposicionSolar() {
		return nivelExposicionSolar;
	}

	/**
	 * Setea el valor de exposicion solar apto para la planta, validando que sea
	 * correcto
	 * 
	 * @see #validarExpSolarLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setNivelExposicionSolar(String nivelExposicionSolar) {
		String mensaje = null;
		mensaje = validarExpSolarLlamada(nivelExposicionSolar);
		if (mensaje == null) {
			this.nivelExposicionSolar = nivelExposicionSolar;
		}
		return mensaje;
	}

	/**
	 * Devuelve el tipo de raiz de la planta
	 * 
	 * @return String
	 */
	public String getTipoRaiz() {
		return tipoRaiz;
	}

	/**
	 * Setea el valor del tipo de raiz de la planta, validando que sea correcto
	 * 
	 * @see #validarTipoRaizLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setTipoRaiz(String tipoRaiz) {
		String mensaje = null;
		mensaje = validarTipoRaizLlamada(tipoRaiz);
		if (mensaje == null) {
			this.tipoRaiz = tipoRaiz;
		}
		return mensaje;
	}

	/**
	 * Devuelve si la planta es o no aromatica
	 * 
	 * @return boolean
	 */
	public boolean isAromatica() {
		return aromatica;
	}

	/**
	 * Setea el valor que indica si la planta es aromatica o no, validando que sea
	 * correcto
	 * 
	 * @see #validarBooleanLlamada(Boolean)
	 * @param boolean
	 * @return String
	 */
	public String setAromatica(boolean aromatica) {
		String mensaje = null;
		mensaje = validarBooleanLlamada(aromatica);
		if (mensaje == null) {
			this.aromatica = aromatica;
		}
		return mensaje;
	}

	/**
	 * Setea la cantidad apta de fertilizante para la planta
	 * 
	 * @param int
	 */
	public void setCantidadFertilizante(int cantidadFertilizante) {
		this.cantidadFertilizante = cantidadFertilizante;
	}

	/**
	 * Devuelve la cantidad de fertilizante apta para la planta
	 * 
	 * @return int
	 */
	public int getCantidadFertilizante() {
		return cantidadFertilizante;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", Meses de ida: " + mesesDeVida + ", Estacion de plantacion: " + estacionPlantacion
				+ ", Habitat: " + habitat + ", Altura: " + altura + "\n" + ", Posee flor: " + flor
				+ ", Nivel de Exposicion Solar: " + nivelExposicionSolar + ", Tipo de raiz: " + tipoRaiz
				+ ", Es aromatica: " + aromatica + ", Cantidad de fertilizante: " + cantidadFertilizante;
	}

}
