package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;

public class Hierba extends PlantaTerrestre{

	private String tipoDeTallo;
	private boolean comestible;
	private boolean medicinal;
	
	public Hierba()
	{
		super();
		this.tipoDeTallo=null;
		this.comestible=false;
		this.medicinal=false;
	}

	public Hierba(String codigo, String nombre, String marca, String clasificacion, double precio, int stock, 
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura,
			boolean flor, String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante,
			boolean fruto, boolean semilla, boolean interior, String epocaDePoda, int cantidadRiego, String especie,
			String tipoDeTallo, boolean comestible, boolean medicinal) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, mesesDeVida, estacionPlantacion,
				habitat, altura, flor, nivelExposicionSolar, tipoRaiz, aromatica, cantidadFertilizante, fruto,
				semilla, interior, epocaDePoda, cantidadRiego, especie);
		this.tipoDeTallo = tipoDeTallo;
		this.comestible = comestible;
		this.medicinal = medicinal;
	}

	public Hierba(String nombre, String marca, double precio, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat,  int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie, String tipoDeTallo, boolean comestible, boolean medicinal) {
		super(nombre, marca, precio, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica, fruto, semilla, interior, especie);
		this.tipoDeTallo = tipoDeTallo;
		this.comestible = comestible;
		this.medicinal = medicinal;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
	}

	public Hierba(String nombre, String marca, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat,  int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie, String tipoDeTallo, boolean comestible, boolean medicinal) {
		super(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica, fruto, semilla, interior, especie);
		this.tipoDeTallo = tipoDeTallo;
		this.comestible = comestible;
		this.medicinal = medicinal;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
		establecerPrecio();
		
	}

	
	/**
	 * Valida que el tipo de tallo pasado por parametro sea correcto, llamada 
	 * @param String
	 * @return String
	 */
	public static String validarTipoTalloLlamada (String tipo) {
		String mensaje=null;
		try {
			validarTipoTallo(tipo);
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
	 * Valida que el tipo de tallo pasado por parametro sea correcto
	 * @param String
	 * @throws CadenaInvalidaException
	 * @throws NullPointerException
	 * @throws InputMismatchException
	 */
	private static void validarTipoTallo(String tipo) throws CadenaInvalidaException, NullPointerException, InputMismatchException
	{
		boolean existe=existeTipoTallo(tipo);
		if(tipo==null) {
			throw new NullPointerException("Error");
		} 
		else if(tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-z]*\\D{7}")) {
			 throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + "7 letras");
		}
		else if(!existe) {
			throw new InputMismatchException("Ingrese un tipo de tallo valido (erguido, rastrero, voluble, segmentado)");
		}
	}
	
	/**
	 * Verifica si el tipo de tallo pasado por parametro es valido
	 * @param String
	 * @return boolean
	 */
	private static boolean existeTipoTallo(String tipo){
		boolean existe=false;
		ArrayList<String> tipos = new ArrayList<String>();
		
		tipos.add("erguido"); 
		tipos.add("rastrero");
		tipos.add("voluble");
		tipos.add("segmentado");
		
		if(tipos.contains(tipo.toLowerCase())) {
			existe=true;
		}
		return existe;
	}
	
	/**
	 * Devuelve el tipo de tallo de la hierba
	 * @return String
	 */
	public String getTipoDeTallo() {
		return tipoDeTallo;
	}

	/**
	 * Setea el tipo de tallo de la hierba
	 * @param String
	 * @return String
	 */
	public String setTipoDeTallo(String tipoTallo) {
		String mensaje=validarTipoTalloLlamada(tipoTallo);
		
		if(mensaje==null)
		{
			this.tipoDeTallo=tipoTallo;
		}	
		return mensaje;
	}
	
	/**
	 * Retorna si la hierba es comestible o no
	 * @return boolean
	 */
	public boolean isComestible() {
		return comestible;
	}

	/**
	 * Setea el valor para indicar si la hierba es comestible o no
	 * @param boolean
	 * @return String
	 */
	public String setComestible(boolean comestible) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(comestible);
		if(mensaje==null)
		{
			this.comestible = comestible;
		}
		
	   return mensaje;
	}

	/**
	 * Retorna si la hierba es medicinal o no
	 * @return boolean
	 */
	public boolean isMedicinal() {
		return medicinal;
	}

	/**
	 * Setea el valor para indicar si la hierba es medicinal o no
	 * @param boolean
	 * @return String
	 */
	public String setMedicinal(boolean medicinal) {
		String mensaje=null;
		mensaje=validarBooleanLlamada(medicinal);
		if(mensaje==null)
		{
			this.medicinal = medicinal;
		}
		
	   return mensaje;
	}

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", tipo de tallo: " + tipoDeTallo + ", comestible: " + comestible + "\n" + ", medicinal: " + medicinal;
	}

	/**
	 * Establece la cantidad de litros de agua de una hierba segun su desarrollo y habitat
	 */
	@Override
	public void establecerCantidadRiego() {

		if(getMesesDeVida()<12)
		{
			if(getHabitat().equalsIgnoreCase("pantano"))
			{
				setCantidadRiego(3);
			}
			else if(getHabitat().equalsIgnoreCase("selva"))
			{
				setCantidadRiego(2);
			}
			else
			{
				setCantidadRiego(1);
			}
		}
		else
		{
			if(getHabitat().equalsIgnoreCase("pantano"))
			{
				setCantidadRiego(5);
			}
			else if(getHabitat().equalsIgnoreCase("selva"))
			{
				setCantidadRiego(4);
			}
			else if(getHabitat().equalsIgnoreCase("pradera") || getHabitat().equalsIgnoreCase("bosque"))
			{
				setCantidadRiego(2);
			}
			else
			{
				setCantidadRiego(1);
			}
		}
	}
	
	/**
	 * Establece la epoca de vida segun las caracteristicas de la hierba
	 */
	@Override
	public void establecerEpocaPoda() {
	
		if(getTipoDeTallo().equalsIgnoreCase("rastrero"))
		{
			setEpocaDePoda("Anual");
		}
		else if(getTipoDeTallo().equals("voluble"))
		{
			if(isFlor())
			{
				setEpocaDePoda("Posterior a su floracion");
			}
			else
			{
				setEpocaDePoda("Invierno");
			}
		}
		else if(getTipoDeTallo().equalsIgnoreCase("segmentado"))
		{
			setEpocaDePoda("Invierno");
		}
		else
		{
			if(isFlor())
			{
				setEpocaDePoda("Posterior a su floracion");
			}
			else if(isAromatica() && !isFlor())
			{
				setEpocaDePoda("Verano");
			}
			else
			{
				setEpocaDePoda("Invierno");
			}
		}
	}

	/**
	 * Establece la cantidad de fertilizante segun sea una planta joven o mas desarrollada (en gramos)
	 */
	@Override
	public void establecerCantidadFertilizante() {
	
		if(getMesesDeVida()<6)
		{
			setCantidadFertilizante(100);
		}
		else if(getMesesDeVida()>6 && getMesesDeVida()<12)
		{
			setCantidadFertilizante(180);
		}
		else
		{
			setCantidadFertilizante(200);
		}
	}
	
	/**
	 * Establece la clasificacion a "Hierba"
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Hierba");
	}

	/**
	 * Calcula el precio de la hierba segun los meses de vida que tiene
	 * @param int
	 * @return double
	 */
	@Override
	public double precioMesDeVida(int mesesVida) {
		double precioPorMes=0; //precio por defecto por cada mes de vida
		if(isInterior())
		{
			if(isComestible() || isMedicinal())
			{
				precioPorMes=10;
			}
			else
			{
				precioPorMes=8;
			}
		}
		else
		{
			if(isComestible() || isMedicinal())
			{
				precioPorMes=7;
			}
			else
			{
				precioPorMes=5;
			}
		}
		
		return precioPorMes*mesesVida;
	}

	/**
	 * Calcula el precio de la hierba segun su altura(en cm)
	 * @param int
	 * @return double
	 */
	@Override
	public double precioPorCentimentoAltura(int centimentros) {
		
		double precioPorCentimetro=0;
		if(getTipoDeTallo().equalsIgnoreCase("segmentado"))
		{
		   precioPorCentimetro=0.50;  //precio por defecto por cada mes de vida
		}
		else if(getTipoDeTallo().equalsIgnoreCase("erguido") || getTipoDeTallo().equalsIgnoreCase("voluble"))
		{
			precioPorCentimetro=2; 
		}
		else
		{
			precioPorCentimetro=1;
		}
		
		return precioPorCentimetro*centimentros;
	}

	/**
	 * Establece el precio de la hierba
	 */
	@Override
	public void establecerPrecio() {
	
		double precioMinino=0;
		double precioFinal=0;
		
		if(isInterior())
		{
			precioMinino=55;
		}
		else
		{
			precioMinino=42;
		}
		
		double precioMesesVida=precioMesDeVida(getMesesDeVida());
		double precioPorAltura=precioPorCentimentoAltura(getAltura());
		double precioIntermedio=precioMesesVida+precioPorAltura;
		
		if(precioIntermedio<precioMinino)
		{
			precioFinal=precioMinino;
		}
		else
		{
			precioFinal=precioIntermedio+precioMinino;
		}
		
		setPrecio(precioFinal);
	}
	
	public JSONObject javaToJson()
	{
		JSONObject obj=null;
		try
		{
			obj= new JSONObject();
			obj.put("marca", getMarca());
			obj.put("clasificacion", getClasificacion());
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * @param Producto
	 * @return int
	 */
	@Override
	public int compareTo(Producto o) {
		int res=-2;
		if(o!=null)
		{
			if(this.getPrecio()==o.getPrecio())
			{
				res=0;
			}
			else if (this.getPrecio()>o.getPrecio())
			{
				res=1;
			}
			else
			{
				res=-1;
			}
		}
		return res;
	}
	
}
