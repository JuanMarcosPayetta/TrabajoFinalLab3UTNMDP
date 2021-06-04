package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

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
	
	
	
	/*
	 * Validacion tipo de agua, llamada main
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
	
	
	/*
	 * Validacion temperatura del agua, llamada main
	 * ("Se espera un dato entero entre los 20°C y los 27°C inclusive")
	 * - en validacion del main: ver ingreso String (aunq lo convierta a  Integer) 
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
	
	
    private static void validarTempAgua(Integer temperatura) throws DatoNumeroException, NullPointerException, NumberFormatException
    {
    	if(temperatura==null) {
    		throw new NullPointerException("Error");
    	}
    	else if(temperatura<0) {
            throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION + " y debe ser mayor a los 20°C");
        }
        else if(temperatura>=20 && temperatura<=27) {
            throw new DatoNumeroException(DatoNumeroException.VALORFUERADELRANGOEXCEPTION + ": entre los 20°C y los 27°C");
        }
    }
    
	
    /*
	 * Validacion dureza del agua, llamada en main
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
	
	
	/*
	 * Validacion tipo de planta acuatica, llamada main
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

	
	public String getTipoDeAgua() {
		return tipoDeAgua;
	}

	public String setTipoDeAgua(String tipoDeAgua) {
		String mensaje=null;
		mensaje=validarTipoDeAguaLlamada(tipoDeAgua);
		if(mensaje==null)
		{
			this.tipoDeAgua=tipoDeAgua;
		}
		return mensaje;
	}

	public int getTemperaturaAgua() {
		return temperaturaAgua;
	}

	public String setTemperaturaAgua(int temperaturaAgua) {
		String mensaje=null;
		mensaje=validarTempAguaLlamada(temperaturaAgua);
		if(mensaje==null)
		{
			this.temperaturaAgua=temperaturaAgua;
		}
		
		return mensaje;
	}

	public String getDurezaAgua() {
		return durezaAgua;
	}

	public String setDurezaAgua(String durezaAgua) {
		String mensaje=null;
		mensaje=validarDurezaAguaLlamada(durezaAgua);
		if(mensaje==null)
		{
			this.durezaAgua=durezaAgua;
		}
		
		return mensaje;
	}

	public String getTipo() {
		return tipo;
	}

	public String setTipo(String tipo) {
		String mensaje=null;
		mensaje=validarTipoPlantaLlamada(tipo);
		if(mensaje==null)
		{
			this.tipo=tipo;
		}
		
		return mensaje;
	}

	@Override
	public String toString() {
		return ", Tipo del planta: " + tipo+ ", Tipo De Agua: " + tipoDeAgua + ", Temperatura del Agua: " + temperaturaAgua + ", Dureza del Agua: "
				+ durezaAgua;
	}

	@Override
	public void establecerCantidadFertilizante() {
		this.setCantidadFertilizante(0);
	}

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Planta acuatica");
	}
	
	
	
	
	
	
}
