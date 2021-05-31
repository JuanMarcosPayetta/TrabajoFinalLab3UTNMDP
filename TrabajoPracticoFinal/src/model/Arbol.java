package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Arbol extends PlantaTerrestre{

	private int diametroDelTronco;
	private String tipoDeCorteza;
	
	public Arbol()
	{
		super();
		this.diametroDelTronco=0;
		this.tipoDeCorteza=null;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
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
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
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
	
	/*
	 * Validacion tipo de corteza, llamada main
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

	public static void validarTipoCorteza(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
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
			throw new InputMismatchException("Ingrese un tipo de corteza valido (laminada, geometica, lobulada, rugosa, placoide, escasa)");
		}
	}
	
	public static boolean existeTipoCorteza(String tipo){
		boolean existe=false;
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("laminada"); 
		tipos.add("geometica");
		tipos.add("rugosa");
		tipos.add("lobulada");
		tipos.add("placoide");
		tipos.add("escasa");
		
		if(tipos.contains(tipo))
		{
			existe=true;
		}

		return existe;
	}


	/*
	 * Valida el diametro del tronco, llamada main
	 */
	public static String validarDiametroTroncoLlamada(int diametro) {
		String mensaje=null;
		try {
			validarDiametroTronco(diametro);
		} catch (DatoNumeroException e){
			mensaje = e.getMessage();
		}
		return mensaje;
	}
	
	public static void validarDiametroTronco(int diametro) throws DatoNumeroException
	{
		if(diametro<2 && diametro>130) {
			throw new DatoNumeroException(DatoNumeroException.VALORFUERADELRANGOEXCEPTION+": entre 2cm y 130cm");
		}
	}
	
	
	
	public int getDiametroDelTronco() {
		return diametroDelTronco;
	}

	public String setDiametroDelTronco(int diametroTronco) {
		String mensaje=validarDiametroTroncoLlamada(diametroTronco);
		
		if(mensaje==null)
		{
			this.diametroDelTronco = diametroTronco;
		}	
		return mensaje;
	}
	

	public String getTipoDeCorteza() {
		return tipoDeCorteza;
	}

	public String setTipoCorteza(String tipoCorteza) {
		String mensaje=validarTipoCortezaLlamada(tipoCorteza);
		
		if(mensaje==null)
		{
			this.tipoDeCorteza = tipoCorteza;
		}	
		return mensaje;
	}

	
	@Override
	public String toString() {
		return super.toString()+", Diametro Del Tronco: " + diametroDelTronco + ", Tipo De Corteza: " + tipoDeCorteza;
	}

	
	/*
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

	
	/*
	 * Establece la mejor epoca de poda para un Arbol
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

	
	/*
	 * Establece la cantidad de fertilizante segun el diametro del tronco del arbol(50gr) y su habitat
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
	
	/*
	 * Establece la clafisicacion
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Arbol");
	}
	
	
	
		
	
	
	

	
}