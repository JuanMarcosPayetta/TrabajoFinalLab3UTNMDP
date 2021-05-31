package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Arbusto extends PlantaTerrestre{
	
	private String tipoDeHoja;
	private boolean trepador;
	
	public Arbusto()
	{
		super();
		tipoDeHoja=null;
		this.trepador=false;
	}

	public Arbusto(String codigo, String nombre, String marca, String clasificacion, double precio, int stock, 
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura,
			boolean flor, String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante,
			boolean fruto, boolean semilla, boolean interior, String epocaDePoda, int cantidadRiego, String especie, String tipoDeHoja,
			boolean trepador) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, mesesDeVida, estacionPlantacion,
				habitat, altura, flor, nivelExposicionSolar, tipoRaiz, aromatica, cantidadFertilizante, fruto,
				semilla, interior, epocaDePoda, cantidadRiego, especie);
		
		this.tipoDeHoja=tipoDeHoja;
		this.trepador = trepador;
	}

	public Arbusto(String nombre, String marca, double precio, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie, String tipoDeHoja,
			boolean trepador) {
		super(nombre, marca, precio, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica, fruto, semilla, interior, especie);
		
		this.tipoDeHoja=tipoDeHoja;
		this.trepador = trepador;
	}


	/*
	 * Validacion tipo de hoja, llamada main
	 */
	public static String validarTipoHojaLLamada(String tipo)
	{
		String mensaje=null;
		try {
			validarTipoHoja(tipo);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		
		return mensaje;
	}
	
	public static void validarTipoHoja(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
    {
        ArrayList<String>tipos=new ArrayList<String>();
		tipos.add("caduca");
		tipos.add("semicaduca");
		tipos.add("persistente");
        
        if(tipo==null)
        {
        	throw new NullPointerException("Ingrese un dato valido");
        }
        else if(tipo.isBlank())
        {
        	throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
        }
        else if(!tipo.matches("[a-zA-Z]*\\D{6}"))
        {
        	throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 6 letras");
        }
        else if(!tipos.contains(tipo.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un tipo de hoja valido (caduca, semicaduca, persistente)");
		}

    }

	
	/*
	 * Valida si es un arbusto trepador o no, llamada main
	 */
	/*
	public static String validarTrepadoraLlamada(boolean tipo)
	{
		String mensaje=null;
		try {
			validarTrepadora(tipo);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	public static void validarTrepadora(boolean tipo)throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		if(String.valueOf(tipo)==null)
		{
			throw new NullPointerException("Error, ingrese true or false");
		}
		else if(String.valueOf(tipo).isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(String.valueOf(tipo).matches("[a-zA-Z]*\\D{4}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 4 letras");
		}
		else if(String.valueOf(tipo)!="false" || String.valueOf(tipo)!="true")
		{
			throw new InputMismatchException("Ingrese un dato valido (true o false)");
		}
	}
	*/

	public String getTipoDeHoja() {
		return tipoDeHoja;
	}

	public String setTipoDeHoja(String tipoDeHoja) {
		
		String mensaje=null;
		mensaje=validarTipoHojaLLamada(tipoDeHoja);
		if(mensaje==null)
		{
			this.tipoDeHoja=tipoDeHoja;
		}
		return mensaje;
	}

	public boolean isTrepador() {
		return trepador;
	}

	public void setTrepador(boolean trepador) {

		this.trepador=trepador;
	}

	@Override
	public String toString() {
		return super.toString()+", Tipo De Hoja: " + tipoDeHoja + ", Trepador: " + trepador;
	}
	

	/*
	 * Establece la cantidad de litros  de agua por arbusto segun su desarrollo y habitat
	 */
	@Override
	public void establecerCantidadRiego() {
		
		if(getMesesDeVida()<12)
		{
			if(getHabitat().equalsIgnoreCase("pantano") || getHabitat().equalsIgnoreCase("selva"))
			{
				setCantidadRiego(5);
			}
			else
			{
				setCantidadRiego(2);
			}
		}
		else
		{
			if(getHabitat().equalsIgnoreCase("pantano") || getHabitat().equalsIgnoreCase("selva"))
			{
				setCantidadRiego(7);
			}
			else if(getHabitat().equalsIgnoreCase("pradera") || getHabitat().equalsIgnoreCase("bosque"))
			{
				setCantidadRiego(4);
			}
			else
			{
				setCantidadRiego(2);
			}
		}
	}

	
	/*
	 * Establece la epoca de poda segun las caracteristicas del arbusto
	 */
	@Override
	public void establecerEpocaPoda() {
		
	if(isFlor())
	{
		setEpocaDePoda("Posterior a su floracion");
	}
	else
	{
		if(getTipoDeHoja().equalsIgnoreCase("caduca"))
		{
			setEpocaDePoda("Fin de Otoño - Inicio del Invierno");
		}
		else 
		{
			setEpocaDePoda("Invierno - Inicio de la Primavera");
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
			setCantidadFertilizante(150);
		}
		else if(getMesesDeVida()>6 && getMesesDeVida()<12)
		{
			setCantidadFertilizante(200);
		}
		else
		{
			setCantidadFertilizante(300);
		}
	}

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Arbusto");
		
	}

	
	



}
