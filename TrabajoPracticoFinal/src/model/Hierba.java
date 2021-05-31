package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

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
	}

	
	
	/*
	 * Validar tipo de tallo, llamada main
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
	
	public static void validarTipoTallo(String tipo) throws CadenaInvalidaException, NullPointerException, InputMismatchException
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
	
	public static boolean existeTipoTallo(String tipo){
		boolean existe=false;
		ArrayList<String> tipos = new ArrayList<String>();
		
		tipos.add("erguido"); 
		tipos.add("rastrero");
		tipos.add("voluble");
		tipos.add("segmentado");
		
		if(tipos.contains(tipo)) {
			existe=true;
		}
		return existe;
	}
	
	
	public String getTipoDeTallo() {
		return tipoDeTallo;
	}

	public String setTipoDeTallo(String tipoTallo) {
		String mensaje=validarTipoTalloLlamada(tipoTallo);
		
		if(mensaje==null)
		{
			this.tipoDeTallo=tipoTallo;
		}	
		return mensaje;
	}
	
	
	public boolean isComestible() {
		return comestible;
	}

	public void setComestible(boolean comestible) {
		this.comestible = comestible;
	}

	public boolean isMedicinal() {
		return medicinal;
	}

	public void setMedicinal(boolean medicinal) {
		this.medicinal = medicinal;
	}

	@Override
	public String toString() {
		return super.toString()+", Tipo De Tallo: " + tipoDeTallo + ", Comestible: " + comestible + ", Medicinal: " + medicinal;
	}

	/*
	 * Establece la cantidad de litros  de agua de una hierba segun su desarrollo y habitat
	 */
	@Override
	public void establecerCantidadRiego() {

		if(getMesesDeVida()<12)
		{
			if(getHabitat().equalsIgnoreCase("pantano") || getHabitat().equalsIgnoreCase("selva"))
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
			if(getHabitat().equalsIgnoreCase("pantano") || getHabitat().equalsIgnoreCase("selva"))
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
	
	/*
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

	/*
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

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Hierba");
	}
	
	
}
