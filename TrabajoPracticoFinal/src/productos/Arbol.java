package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import excepciones.DatoNumeroException;

public class Arbol extends PlantaTerrestre{

	private int diametroDelTronco;
	private String tipoDeCorteza;
	
	public Arbol()
	{
		super();
		this.diametroDelTronco=0;
		this.tipoDeCorteza=null;
	}

	public Arbol(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura,
			boolean flor, String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante,
			boolean fruto, boolean semilla, boolean interior, String epocaDePoda, int cantidadRiego, String especie,
			int diametroDelTronco, String tipoDeCorteza) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, mesesDeVida, estacionPlantacion,
				habitat, altura, flor, nivelExposicionSolar, tipoRaiz, aromatica, cantidadFertilizante, fruto,
				semilla, interior, epocaDePoda, cantidadRiego, especie);
		this.diametroDelTronco = diametroDelTronco;
		this.tipoDeCorteza = tipoDeCorteza;
	}

	public Arbol(String nombre, String marca, double precio, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie, int diametroDelTronco, String tipoDeCorteza) {
		super(nombre, marca, precio, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica, fruto, semilla, interior, especie);
		this.diametroDelTronco = diametroDelTronco;
		this.tipoDeCorteza = tipoDeCorteza;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
	}
	
	public Arbol(String nombre, String marca, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie, int diametroDelTronco, String tipoDeCorteza) {
		super(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica, fruto, semilla, interior, especie);
		this.diametroDelTronco = diametroDelTronco;
		this.tipoDeCorteza = tipoDeCorteza;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
		establecerPrecio();
	}
	
	/**
	 * Valida que el tipo de corteza pasado por parametro sea correcto, llamada
	 * @param String
	 * @return String
	 */
	public static String validarTipoCortezaLlamada(String tipo) {
		String mensaje=null; //si devuelve "null" es correcto
		try {
			validarTipoCorteza(tipo);
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
	 * Valida que el tipo de corteza pasado por parametro sea correcto
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarTipoCorteza(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		boolean existe=existeTipoCorteza(tipo);
		if(tipo==null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		}
		else if(tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-z]*\\D{6}")) {
			 throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+ "6 letras");
		}
		else if(!existe) {
			throw new InputMismatchException("Ingrese un tipo de corteza valido (laminada, geometrica, lobulada, rugosa, placoide, escasa)");
		}
	}
	
	/**
	 * @param String
	 * @return boolean
	 */
	private static boolean existeTipoCorteza(String tipo){
		boolean existe=false;
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("laminada"); 
		tipos.add("geometrica");
		tipos.add("rugosa");
		tipos.add("lobulada");
		tipos.add("placoide");
		tipos.add("escasa");
		
		if(tipos.contains(tipo.toLowerCase()))
		{
			existe=true;
		}

		return existe;
	}


	/**
	 * Valida el diametro del tronco, llamada 
	 * @param Integer
	 * @return String
	 */
	public static String validarDiametroTroncoLlamada(Integer diametro) {
		String mensaje=null;
		try {
			validarDiametroTronco(diametro);
		} catch (DatoNumeroException e){
			mensaje = e.getMessage();
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}
	
	/**
	 * @param Integer
	 * @throws DatoNumeroException
	 * @throws NullPointerException
	 */
	private static void validarDiametroTronco(Integer diametro) throws DatoNumeroException, NullPointerException
	{
		if(diametro==null) {
    		throw new NullPointerException("Error");
    	}
		if(diametro<2 && diametro>130) {
			throw new DatoNumeroException(DatoNumeroException.VALORFUERADELRANGOEXCEPTION+": entre 2cm y 130cm");
		}
	}
	
	/**
	 * Devuelve el diametro del tronco
	 * @return int
	 */
	public int getDiametroDelTronco() {
		return diametroDelTronco;
	}

	/**
	 * Setea el diametro del tronco, corroborando primero que el valor ingresado sea valido
	 * @param int
	 * @return String
	 */
	public String setDiametroDelTronco(int diametroTronco) {
		String mensaje=validarDiametroTroncoLlamada(diametroTronco);
		
		if(mensaje==null)
		{
			this.diametroDelTronco = diametroTronco;
		}	
		return mensaje;
	}
	
	/**
	 * Retorna el tipo de corteza del arbol
	 * @return String
	 */
	public String getTipoDeCorteza() {
		return tipoDeCorteza;
	}

	/**
	 * Setea el tipo de corteza del arbol 
	 * @param String
	 * @return String
	 */
	public String setTipoCorteza(String tipoCorteza) {
		String mensaje=validarTipoCortezaLlamada(tipoCorteza);
		
		if(mensaje==null)
		{
			this.tipoDeCorteza = tipoCorteza;
		}	
		return mensaje;
	}

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", diametro del tronco: " + diametroDelTronco + ", tipo de corteza: " + tipoDeCorteza;
	}

	
	/**
	 * Establece la cantidad de riego segun el diametro del tronco de un arbol (diametro x litros) y su habitat
	 */
	@Override
	public void establecerCantidadRiego() {
		
		if(getDiametroDelTronco()!=0)
		{
			if(getHabitat().equalsIgnoreCase("pantano"))
			{
				if(getDiametroDelTronco()>30)
				{
					setCantidadRiego(40);
				}
				else
				{
					setCantidadRiego(30);
				}
			}
			else if(getHabitat().equalsIgnoreCase("selva"))
			{
				if(getDiametroDelTronco()>30)
				{
					setCantidadRiego(30);
				}
				else
				{
					setCantidadRiego(20);
				}
			}
			else if(getHabitat().equalsIgnoreCase("pradera") || getHabitat().equalsIgnoreCase("bosque"))
			{
				if(getDiametroDelTronco()>30)
				{
					setCantidadRiego(18);
				}
				else
				{
					setCantidadRiego(14);
				}
			}
			else
			{
				if(getDiametroDelTronco()>30)
				{
					setCantidadRiego(12);
				}
				else
				{
					setCantidadRiego(10);
				}
			}
		}
	}

	
	/**
	 * Establece la mejor epoca de poda para un producto de tipo Arbol
	 */
	@Override
	public void establecerEpocaPoda() {
		
		if(isFlor())
		{
			setEpocaDePoda("Posterior a su floracion");
		}
		else
		{
			setEpocaDePoda("Invierno");
		}
	}

	
	/**
	 * Establece la cantidad de fertilizante segun el diametro del tronco del arbol y su habitat
	 */
	@Override
	public void establecerCantidadFertilizante() {
		
		if(getDiametroDelTronco()!=0)
		{
			if(getHabitat().equalsIgnoreCase("pradera") || getHabitat().equalsIgnoreCase("bosque") || getHabitat().equalsIgnoreCase("selva"))
			{
				setCantidadFertilizante(getDiametroDelTronco()*6);
			}
			else
			{
				setCantidadFertilizante(getDiametroDelTronco()*3);
			}
		}
	}
	
	/**
	 * Establece la clafisicacion a "Arbol" 
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Arbol");
	}

	/**
	 * Retorna el precio del arbol, dependiendo de los meses de vida que tiene
	 * @param int
	 * @return double
	 */
	@Override
	public double precioMesDeVida(int mesesVida) {

		double precioMesVida=35;
		return precioMesVida*mesesVida;
	}

	/**
	 * Calcula el precio del arbol segun su altura (en cm)
	 * @param int
	 * @return double
	 */
	@Override
	public double precioPorCentimentoAltura(int centimentros) {
		double precioCentimetro=3;
		return precioCentimetro*centimentros;
	}

	/**
	 * Establece el precio del arbol
	 */
	@Override
	public void establecerPrecio() {
		double precioMinino=0;
		double precioFinal=0;
		
		if(isFruto())
		{
			precioMinino=600;
		}
		else
		{
			if(getDiametroDelTronco()<40)
			{
				precioMinino=400;
			}
			else
			{
				precioMinino=550;
			}
			
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
			obj=new JSONObject();
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
