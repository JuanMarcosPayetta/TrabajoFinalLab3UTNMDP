package productos;
/**
 * 
 * Atributos de los productos clasificacion Planta terrestre
 *
 */
public abstract class PlantaTerrestre extends Planta{

	private boolean fruto;
	private boolean semilla;
	private boolean interior;
	private String epocaDePoda; 
	private int cantidadRiego; 
	private String especie;
	
	public PlantaTerrestre()
	{
		super();
		this.fruto=false;
		this.semilla=false;
		this.interior=false;
		this.epocaDePoda=null;
		this.cantidadRiego=0;
		this.especie=null;
	}

	public PlantaTerrestre(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura,
			boolean flor, String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante,
			boolean fruto, boolean semilla, boolean interior, String epocaDePoda, int cantidadRiego, String especie) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, mesesDeVida, estacionPlantacion,
				habitat, altura, flor, nivelExposicionSolar, tipoRaiz, aromatica, cantidadFertilizante);
		this.fruto = fruto;
		this.semilla = semilla;
		this.interior = interior;
		this.epocaDePoda = epocaDePoda;
		this.cantidadRiego = cantidadRiego;
		this.especie = especie;
	}

	public PlantaTerrestre(String nombre, String marca, double precio, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie) {
		super(nombre, marca, precio, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica);
		this.fruto = fruto;
		this.semilla = semilla;
		this.interior = interior;
		this.epocaDePoda = null; 
		this.cantidadRiego = 0; 
		this.especie = especie;
	}
	
	public PlantaTerrestre(String nombre, String marca, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie) {
		super(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica);
		this.fruto = fruto;
		this.semilla = semilla;
		this.interior = interior;
		this.epocaDePoda = null; 
		this.cantidadRiego = 0; 
		this.especie = especie;
	}
	
	/**
	 * Metodo abstracto, establece la cantidad de riego que necesita la planta
	 */
	public abstract void establecerCantidadRiego();
	
	/**
	 * Metodo abstracto, establece la epoca de poda de la planta
	 */
	public abstract void establecerEpocaPoda();

	/**
	 * Devuelve si la planta tiene fruto o no
	 * @return boolean
	 */
	public boolean isFruto() {
		return fruto;
	}

	/**
	 * Setea el valor que indica si la planta tiene fruto o no
	 * @param boolean
	 * @return String
	 */
	public String setFruto(boolean fruto) {
		
		String mensaje=null;
		mensaje=validarBooleanLlamada(fruto);
		if(mensaje==null)
		{
			this.fruto = fruto;
		}
	   return mensaje;
	}

	/**
	 * Retorna si la planta tiene semilla o no
	 * @return boolean
	 */
	public boolean isSemilla() {
		return semilla;
	}

	/**
	 * Setea el valor que indica si la planta tiene semilla
	 * @param boolean
	 * @return String
	 */
	public String setSemilla(boolean semilla) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(semilla);
		if(mensaje==null)
		{
		   this.semilla=semilla;
		}
	   return mensaje;
	}

	/**
	 * Retorna si la planta es o no es de interior
	 * @return
	 */
	public boolean isInterior() {
		return interior;
	}

	/**
	 * Setea el valor que indica si la planta es o no es de interior, validando que sea correcto
	 * @see #validarBooleanLlamada(Boolean)
	 * @param boolean
	 * @return String
	 */
	public String setInterior(boolean interior) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(interior);
		if(mensaje==null)
		{
		   this.interior=interior;
		}
	   return mensaje;
	}

	/**
	 * Devuelve la epoca de poda de la planta
	 * @return String
	 */
	public String getEpocaDePoda() {
		return epocaDePoda;
	}

	/**
	 * Devuelve la cantidad de riego que necesita la planta
	 * @return int
	 */
	public int getCantidadRiego() {
		return cantidadRiego;
	}

	/**
	 * Devuelve la especie de la planta
	 * @return String
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Setea el tipo de especie de la planta, validando que sea correcto
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setEspecie(String especie) {
		String mensaje=null;
		mensaje=Producto.validarCadenaCaracteresLlamada(especie);
		if(mensaje==null)
		{
			this.especie=especie;
		}
		return mensaje;
	}

	/**
	 * Setea la epoca de poda de la planta
	 * @param String
	 */
	public void setEpocaDePoda(String epocaDePoda) {
		this.epocaDePoda = epocaDePoda;
	}

	/**
	 * Setea la cantidad de riego que necesita la planta
	 * @param int
	 */
	public void setCantidadRiego(int cantidadRiego) {
		this.cantidadRiego = cantidadRiego;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", Especie: " + especie + ", Posee fruto: " + fruto + ", Posee semilla: " + semilla 
				+ ", De interior: " + interior + "\n" + ", Epoca de poda: "+ epocaDePoda + ", Cantidad de riego: " + cantidadRiego;
	}
	
	
}
