package productos;


import excepciones.CadenaInvalidaException;

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
	
	public abstract void establecerCantidadRiego();
	public abstract void establecerEpocaPoda();

	
	public boolean isFruto() {
		return fruto;
	}

	public String setFruto(boolean fruto) {
		
		String mensaje=null;
		mensaje=validarBooleanLlamada(fruto);
		if(mensaje==null)
		{
			this.fruto = fruto;
		}
	   return mensaje;
	}

	public boolean isSemilla() {
		return semilla;
	}

	public String setSemilla(boolean semilla) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(semilla);
		if(mensaje==null)
		{
		   this.semilla=semilla;
		}
	   return mensaje;
	}

	public boolean isInterior() {
		return interior;
	}

	public String setInterior(boolean interior) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(interior);
		if(mensaje==null)
		{
		   this.interior=interior;
		}
	   return mensaje;
	}

	public String getEpocaDePoda() {
		return epocaDePoda;
	}

	public int getCantidadRiego() {
		return cantidadRiego;
	}

	public String getEspecie() {
		return especie;
	}

	public String setEspecie(String especie) {
		String mensaje=null;
		mensaje=Producto.validarCadenaCaracteresLlamada(especie);
		if(mensaje==null)
		{
			this.especie=especie;
		}
		return mensaje;
	}

	public void setEpocaDePoda(String epocaDePoda) {
		this.epocaDePoda = epocaDePoda;
	}

	public void setCantidadRiego(int cantidadRiego) {
		this.cantidadRiego = cantidadRiego;
	}

	@Override
	public String toString() {
		return super.toString()+", especie: " + especie + ", posee fruto: " + fruto + ", posee semilla: " + semilla 
				+ ", de interior: " + interior + "\n" + ", epoca de poda: "+ epocaDePoda + ", cantidad de riego: " + cantidadRiego;
	}
	
	
}
