package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import excepciones.DatoNumeroException;

public class PlantaAcuatica extends Planta{

	private String tipoDeAgua; 
	private int temperaturaAgua; 
	private String durezaAgua;  
	private String tipo; 
	
	public PlantaAcuatica()
	{
		super();
		this.tipoDeAgua=null;
		this.temperaturaAgua=0;
		this.durezaAgua=null;
		this.tipo=null;
	}

	public PlantaAcuatica(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura,
			boolean flor, String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante,
			String tipoDeAgua, int tempraturaAgua, String durezaAgua, String tipo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, mesesDeVida, estacionPlantacion,
				habitat, altura, flor, nivelExposicionSolar, tipoRaiz, aromatica, cantidadFertilizante);
		this.tipoDeAgua = tipoDeAgua;
		this.temperaturaAgua = tempraturaAgua;
		this.durezaAgua = durezaAgua;
		this.tipo=tipo;
	}

	public PlantaAcuatica(String nombre, String marca, double precio, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, String tipoDeAgua, int temperaturaAgua,
			String durezaAgua, String tipo) {
		super(nombre, marca, precio, stock, descripcion, mesesDeVida, estacionPlantacion, habitat,  altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica);
		this.tipoDeAgua = tipoDeAgua;
		this.temperaturaAgua = temperaturaAgua;
		this.durezaAgua = durezaAgua;
		this.tipo = tipo;
		establecerClasificacion();
		establecerCantidadFertilizante();
	}
	
	public PlantaAcuatica(String nombre, String marca, int stock, String descripcion, int mesesDeVida,
			String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica, String tipoDeAgua, int temperaturaAgua,
			String durezaAgua, String tipo) {
		super(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat,  altura,
				flor, nivelExposicionSolar, tipoRaiz, aromatica);
		this.tipoDeAgua = tipoDeAgua;
		this.temperaturaAgua = temperaturaAgua;
		this.durezaAgua = durezaAgua;
		this.tipo = tipo;
		establecerClasificacion();
		establecerCantidadFertilizante();
		establecerPrecio();
	}
	
	//para la compra de productos (ya que producto no se puede instanciar por ser abstracta)
	public PlantaAcuatica(String codigo, String clasificacion)
	{
		super(codigo, clasificacion);
		this.tipoDeAgua=null;
		this.temperaturaAgua=0;
		this.durezaAgua=null;
		this.tipo=null;
		
	}
	
	
	/**
	 * Valida que el tipo de agua pasado por parametro sea correcto
	 * @see #validarTipoDeAgua(String)
	 * @param String
	 * @return String
	 */
	public static String validarTipoDeAguaLlamada(String tipo) {
		String mensaje=null; //si devuelve "null" es correcto
		try {
			validarTipoDeAgua(tipo);
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
	 * Valida que el tipo de agua pasado por parametro sea correcto, lanzando una excepcion que es capturada por {@link #validarTipoDeAguaLlamada(String)}
	 * @see #validarTipoPlantaLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarTipoDeAgua(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("dulce");
		tipos.add("salada");
		
		if(tipo==null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		}
		else if(tipo.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-Z]*\\D{5}")) {
			 throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 5 letras");
		}
		else if(!tipos.contains(tipo.toLowerCase())) {
			throw new InputMismatchException("Error, ingrese un tipo de agua valido (dulce o salada) ");
		}
	}
	
	
	/**
	 * Valida que la temperatura del agua pasada por parametro sea correcta, llamada 
	 * @see #validarTempAgua(Integer)
	 * @param Integer
	 * @return String
	 */ 
	public static String validarTempAguaLlamada(Integer temperatura) {
        String mensaje=null; //si devuelve "null" es correcto
        try {
            validarTempAgua(temperatura);
        } catch(DatoNumeroException e){
            mensaje = e.getMessage();
        } catch(NullPointerException e) {
        	mensaje = e.getMessage();
        } catch(NumberFormatException e) {
        	mensaje = e.getMessage();
        }
        return mensaje;
    }
	
	/**
	 * Valida que la temperatura del agua pasada por parametro sea correcta, lanzando una excepcion que es capturada por {@link #validarTempAguaLlamada(Integer)}
	 * @see #validarTempAguaLlamada(Integer)
	 * @param Integer
	 * @throws DatoNumeroException
	 * @throws NullPointerException
	 * @throws NumberFormatException
	 */
    private static void validarTempAgua(Integer temperatura) throws DatoNumeroException, NullPointerException, NumberFormatException
    {
    	if(temperatura==null) {
    		throw new NullPointerException("Error");
    	}
    	else if(temperatura<0) {
            throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION + " y debe ser mayor a los 20°C");
        }
        else if(temperatura<20 && temperatura>27) {
            throw new DatoNumeroException(DatoNumeroException.VALORFUERADELRANGOEXCEPTION + ": entre los 20°C y los 27°C");
        }
    }
    
    
    /**
     * Valida que la dureza del agua pasada por parametro sea correcta
     * @see #validarDurezaAgua(String)
     * @param String
     * @return String
     */
	public static String validarDurezaAguaLlamada(String durezaAgua)
	{
		String mensaje=null; //si devuelve "null" es correcto
		try {
			validarDurezaAgua(durezaAgua);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
    
	/**
	 * Valida que la dureza del agua pasada por parametro sea correcta, lanzando una excepcion que es capturada por {@link #validarDurezaAguaLlamada(String)}
	 * @see #validarDurezaAguaLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarDurezaAgua(String durezaAgua) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>dureza=new ArrayList<String>();
		dureza.add("blanda");
		dureza.add("moderada");
		dureza.add("dura");

		if(durezaAgua==null)
		{
			throw new NullPointerException("Error, Ingrese un dato valido");
		}
		else if(durezaAgua.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!durezaAgua.matches("[a-zA-Z]*\\D{4}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 4 letras");
		}
		else if(!dureza.contains(durezaAgua.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un grado de dureza del agua valido (blanda, moderada, dura)");
		}
	}
	
	
	/**
	 * Valida que el tipo de planta acuatica pasado por parametro sea correcto
	 * @see #validarTipoPlanta(String)
	 * @param String
	 * @return String
	 */
	public static String validarTipoPlantaLlamada(String tipoPlanta)
	{
		String mensaje=null;
		try {
			validarTipoPlanta(tipoPlanta);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		
		return mensaje;
	}
	
	/**
	 * Valida que el tipo de planta acuatica pasado por parametro sea correcto, lanzando una excepcion que es capturada por {@link #validarTipoPlantaLlamada(String)}
	 * @see #validarTipoPlantaLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarTipoPlanta (String tipoPlanta) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>tipos=new ArrayList<String>();
		tipos.add("flotante");
		tipos.add("oxigenante");
		tipos.add("ribera");
		tipos.add("profundidades");

		if(tipoPlanta==null)
		{
			throw new NullPointerException("Error, Ingrese un dato valido");
		}
		else if(tipoPlanta.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipoPlanta.matches("[a-zA-Z]*\\D{6}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 6 letras");
		}
		else if(!tipos.contains(tipoPlanta.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un tipo valido (flotante, oxigenante, ribera, profundidades)");
		}
	}

	/**
	 * Devuelve el tipo de agua que necesita la planta
	 * @return String
	 */
	public String getTipoDeAgua() {
		return tipoDeAgua;
	}

	/**
	 * Setea el valor del tipo de agua que necesita la planta
	 * @param String
	 * @return String
	 */
	public String setTipoDeAgua(String tipoDeAgua) {
		String mensaje=null;
		mensaje=validarTipoDeAguaLlamada(tipoDeAgua);
		if(mensaje==null)
		{
			this.tipoDeAgua=tipoDeAgua;
		}
		return mensaje;
	}

	/**
	 * Devuelve el valor de la temperatura del agua
	 * @return int
	 */
	public int getTemperaturaAgua() {
		return temperaturaAgua;
	}

	/**
	 * Setea el valor de la temperatura del agua
	 * @param int
	 * @return String
	 */
	public String setTemperaturaAgua(int temperaturaAgua) {
		String mensaje=null;
		mensaje=validarTempAguaLlamada(temperaturaAgua);
		if(mensaje==null)
		{
			this.temperaturaAgua=temperaturaAgua;
		}
		
		return mensaje;
	}

	/**
	 * Devuelve la dureza del agua
	 * @return String
	 */
	public String getDurezaAgua() {
		return durezaAgua;
	}

	/**
	 * Setea el valor de dureza del agua
	 * @param String
	 * @return String
	 */
	public String setDurezaAgua(String durezaAgua) {
		String mensaje=null;
		mensaje=validarDurezaAguaLlamada(durezaAgua);
		if(mensaje==null)
		{
			this.durezaAgua=durezaAgua;
		}
		
		return mensaje;
	}

	/**
	 * Devuelve el tipo de la planta acuatica
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Setea el tipo de planta acuatica
	 * @param String
	 * @return String
	 */
	public String setTipo(String tipo) {
		String mensaje=null;
		mensaje=validarTipoPlantaLlamada(tipo);
		if(mensaje==null)
		{
			this.tipo=tipo;
		}
		
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto 
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", Tipo del planta: " + tipo+ ", Tipo De Agua: " + tipoDeAgua + ", Temperatura del Agua: " + temperaturaAgua + ", Dureza del Agua: "
				+ durezaAgua;
	}

	/**
	 * Establece la cantidad de fertilizante necesario para la planta
	 */
	@Override
	public void establecerCantidadFertilizante() {
		this.setCantidadFertilizante(0);
	}

	/**
	 * Establece la clasificacion a "Planta acuatica"
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Planta acuatica");
	}

	/**
	 * Devuelve el precio que calcula segun los meses de vida de la planta
	 * @param int
	 * @return double
	 */
	@Override
	public double precioMesDeVida(int mesesVida)
	{
		double precioPorMes=0; //precio por defecto por cada mes de vida
		if(getTipoDeAgua().equalsIgnoreCase("salada"))
		{
			precioPorMes=5;
		}
		else
		{
			precioPorMes=3;
		}
		
		if(getTemperaturaAgua()>=25)
		{
			precioPorMes+=2;
		}
		
		return precioPorMes*mesesVida;
	}
	
	/**
	 * Establece el precio de la planta segun su alura(en cm)
	 * @param int
	 * @return double
	 */
	@Override
	public double precioPorCentimentoAltura(int centimentros)
	{
		double precioPorCentimetro=1;  //precio por defecto por cada mes de vida
		return precioPorCentimetro*centimentros;
	}
	
	/**
	 * Establece el precio de la planta acuatica
	 */
	@Override
	public void establecerPrecio() {
		
		double precioMinino=0;
		double precioFinal=0;
		
	  if(getTipoDeAgua().equalsIgnoreCase("salada"))
	  {
		  if(getTipo().equalsIgnoreCase("oxigenante"))
		  {
			  precioMinino=22;
		  }
		  else if(getTipo().equalsIgnoreCase("ribera") || getTipo().equalsIgnoreCase("flotante"))
		  {
			  precioMinino=28;
		  }
		  else
		  {
			  precioMinino=35;
		  }
		  
	  }
	  else
	  {
		  if(getTipo().equalsIgnoreCase("profundidades"))
		  {
			  precioMinino=25;
		  }
		  else
		  {
			  precioMinino=18;
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
	 * Compara dos objetos, indicando si son iguales, mayor o menor
	 * @param Objeto Producto
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
