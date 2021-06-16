package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;

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
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
	}

	public Arbusto(String nombre, String marca, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, boolean fruto, boolean semilla,
			boolean interior, String especie, String tipoDeHoja,
			boolean trepador) {
		super(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica, fruto, semilla, interior, especie);
		this.tipoDeHoja=tipoDeHoja;
		this.trepador = trepador;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerEpocaPoda();
		establecerCantidadRiego();
		establecerPrecio();
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
	
	private static void validarTipoHoja(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
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

	public String setTrepador(boolean trepador) {
        
		String mensaje=null;
		mensaje=validarBooleanLlamada(trepador);
		if(mensaje==null)
		{
			this.trepador=trepador;
		}
		
	   return mensaje;
	}

	@Override
	public String toString() {
		return super.toString()+", tipo de hoja: " + tipoDeHoja + ", trepador: " + trepador;
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

	@Override
	public double precioMesDeVida(int mesesVida) {
		
		double precioPorMes=0; //precio por defecto por cada mes de vida
		if(isInterior())
		{
			precioPorMes=23;
		}
		else
		{
			precioPorMes=17;
		}
		
		return precioPorMes*mesesVida;
	}

	@Override
	public double precioPorCentimentoAltura(int centimentros) {
		
		double precioPorCentimetro=0;
		if(isTrepador()) 
		{
			precioPorCentimetro=2;
		}
		else
		{
			precioPorCentimetro=3;
		}
		return precioPorCentimetro*centimentros;
	}

	

	@Override
	public void establecerPrecio() {
	
		double precioMinino=0;
		double precioFinal=0;
		
		if(isInterior())
		{
			precioMinino=300;
		}
		else
		{
			if(getTipoDeHoja().equalsIgnoreCase("persistente"))
			{
				precioMinino=260;
			}
			else
			{
				precioMinino=220;
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
