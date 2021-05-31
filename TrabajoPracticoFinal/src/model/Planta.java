package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;
import excepciones.DatoNumeroException;

public abstract class Planta extends Producto{

	private int mesesDeVida; 
	private String estacionPlantacion; 
	private String habitat; 
	private int altura; 
	private boolean flor;
	private String nivelExposicionSolar;
	private String tipoRaiz;
	private boolean aromatica;
	private int cantidadFertilizante; 
	
	
	public Planta()
	{
		super();
		this.mesesDeVida=0;
		this.estacionPlantacion=null;
		this.habitat=null;
		this.altura=0;
		this.flor=false;
		this.nivelExposicionSolar=null;
		this.tipoRaiz=null;
		this.aromatica=false;
		this.cantidadFertilizante=0; 
	}


	public Planta(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int mesesDeVida, String estacionPlantacion, String habitat, int altura,
			boolean flor, String nivelExposicionSolar, String tipoRaiz, boolean aromatica, int cantidadFertilizante) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.mesesDeVida = mesesDeVida;
		this.estacionPlantacion = estacionPlantacion;
		this.habitat = habitat;
		this.altura = altura;
		this.flor = flor;
		this.nivelExposicionSolar = nivelExposicionSolar;
		this.tipoRaiz = tipoRaiz;
		this.aromatica = aromatica;
		this.cantidadFertilizante = cantidadFertilizante; 
	}

	public Planta(String nombre, String marca, double precio, int stock, String descripcion,
			int mesesDeVida, String estacionPlantacion, String habitat, int altura, boolean flor,
			String nivelExposicionSolar, String tipoRaiz, boolean aromatica) {
		super(nombre, marca, precio, stock, descripcion);
		this.mesesDeVida = mesesDeVida;
		this.estacionPlantacion = estacionPlantacion;
		this.habitat = habitat;
		this.altura = altura;
		this.flor = flor;
		this.nivelExposicionSolar = nivelExposicionSolar;
		this.tipoRaiz = tipoRaiz;
		this.aromatica = aromatica;
		this.cantidadFertilizante = 0; 
	}

	public abstract void establecerCantidadFertilizante(); //ESTABLECER EN CLASE FINAL

	
	
	
	/*
	 * Validacion Meses de vida/Altura, llamada main
	 */
	public static String validarDatosNumericosLlamada(int numero) {
		String mensaje = null; // si el retorno es null es correcto

		try {
			validarDatosNumericos(numero);
			
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		
		return mensaje;
	}
	
	public static void validarDatosNumericos(int numero) throws DatoNumeroException {

		if (numero<=0) {
			throw new DatoNumeroException("Ingrese un numero superior a 0");
		}

		/*
		 * PREGUNTAR SI ES NULL O SON CARACTERES SE VERFICA EN EL MAIN LUEGO DE QUE
		 * ENTRE POR TECLADO CON INPUT MISMACHT EXPETION
		 * 
		 * Scanner scan; scan=new Scanner(System.in); try {
		 * System.out.println("Ingrese un dato numerico"); int dato=scan.nextInt();
		 * System.out.println("Dato: "+dato); } catch(InputMismatchException e) {
		 * System.out.println("Ingrese un dato numerico valido"); }
		 * 
		 */
	}

	
	
	/*
	 * Validacion tipo de raiz, llamada main
	 */
	public static String validarTipoRaizLlamada(String tipo)
	{
		String mensaje=null; //retorna "null" si es correcto
		try {
			validarTipoRaiz(tipo);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}

	public static void validarTipoRaiz(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
    {
		ArrayList<String>tipos=new ArrayList<String>();
		tipos.add("axonomorfa");
		tipos.add("ramificada");
		tipos.add("fascicular");
		tipos.add("tuberosa");
		tipos.add("napiforme");
		tipos.add("adventicia");

		if(tipo==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido");
		}
		else if(tipo.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-Z]*\\D{8}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 8 letras");
		}
		else if(!tipos.contains(tipo.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un tipo de raiz valida (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia");
		}
    }
	
	
	/*
	 * Validacion estacion de plantacion, llamada main
	 */
	public static String validarEstacionLlamada(String tipo)
	{
		String mensaje=null; //retorna "null" si es correcto
		try {
			validarEstacion(tipo);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	public static void validarEstacion(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
    {
		ArrayList<String>tipos=new ArrayList<String>();
		tipos.add("verano");
		tipos.add("otonio");
		tipos.add("invierno");
		tipos.add("primavera");
		tipos.add("anual");
		
		if(tipo==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido");
		}
		else if(tipo.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-Z]*\\D{5}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+ " 5 letras");
		}
		else if(!tipos.contains(tipo.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un tipo de raiz valida (otonio, invierno, primavera, verano, anual)");
		}
    }
	
	
	/*
	 * Validacion habitat, llamada main (La opcion es para determinar los posibles habitat validos segun el tipo de planta (terrestre, acuatica))
	 */
	public static String validarHabitatLlamada(String opcion, String tipo)
	{
		String mensaje=null; //retorna "null" si es correcto
		try {
		validarHabitat(opcion, tipo);
		} catch (InputMismatchException e) {
         mensaje=e.getMessage();
		} catch (NullPointerException e) {
		mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
		mensaje=e.getMessage();	
		}
		
		return mensaje;
	}

	public static void validarHabitat(String opcion, String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
    {
		ArrayList<String>opciones=new ArrayList<String>();
		opciones.add("terrestre");
		opciones.add("acuatica");
		
		ArrayList<String>terrestre=new ArrayList<String>();
		terrestre.add("pradera");
		terrestre.add("desierto");
		terrestre.add("bosque");
		terrestre.add("pantano");
		terrestre.add("polar");
		terrestre.add("sabana");
		terrestre.add("selva");
		
		ArrayList<String>acuatica=new ArrayList<String>();
		acuatica.add("oceano");
		acuatica.add("rio");
		acuatica.add("lago");
		acuatica.add("arrecife");
		acuatica.add("estanque");
		acuatica.add("pantano");
		acuatica.add("delta");
		
		if(opcion==null)
		{
			throw new NullPointerException("Error, ingrese una opcion valida (Terrestre, Acuatica)");
		}
		else if(opcion.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!opcion.matches("[a-zA-Z]*\\D{8}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 8 letras");
		}
		else if(!opciones.contains(opcion.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese una opcion valida (Terrestre, Acuatica)");
		}
		
		
		if(tipo==null)
		{
			throw new NullPointerException("Error, ingrese un habitat valido");
		}
		else if(tipo.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-Z]*\\D{3}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+ " 3 letras");
		}
		else if(opcion.equalsIgnoreCase("terrestre"))
		{
			if(!terrestre.contains(tipo.toLowerCase()))
			{
				throw new InputMismatchException("Error, ingrese un habitat valido (pradera, desierto, bosque, pantano, polar, sabana, selva");
			}
		}
		else if(opcion.equalsIgnoreCase("acuatica"))
		{
			if(!acuatica.contains(tipo.toLowerCase()))
			{
				throw new InputMismatchException("Error, ingrese un habitat valido (oceano, rio, lago, arrecife, estanque, pantano, delta");
			}
		}
				
    }
	

	/*
	 * Validar nivel de exposicion solar, llamada main
	 */
	public static String validarExpSolarLlamada(String tipo)
	{
		String mensaje=null; //retorna "null" si es correcto
		try {
			validarExpSolar(tipo);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();			
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	public static void validarExpSolar(String tipo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
    {
        ArrayList<String>tipos=new ArrayList<String>();
		tipos.add("sombra");
		tipos.add("mediasombra");
		tipos.add("pleno sol");

		if(tipo==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido");
		}
		else if(tipo.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!tipo.matches("[a-zA-Z]*\\D{5}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 5 letras");
		}
		else if(!tipos.contains(tipo.toLowerCase()))
		{
			throw new InputMismatchException("Ingrese un grado de exposicion solar valido (sombra, mediasombra, pleno sol)");
		}
        
    }
	
	
	public int getMesesDeVida() {
		return mesesDeVida;
	}

	public String setMesesDeVida(int mesesDeVida) {
		String mensaje=null;
		mensaje=validarDatosNumericosLlamada(mesesDeVida);
		if(mensaje==null)
		{
			this.mesesDeVida=mesesDeVida;
		}
		return mensaje;
	}
	
	public int getAltura() {
		return altura;
	}

	public String setAltura(int altura) {
		String mensaje=null;
		mensaje=validarDatosNumericosLlamada(altura);
		if(mensaje==null)
		{
			this.altura=altura;		
		}
		return mensaje;
	}

	
	public String getEstacionPlantacion() {
		return estacionPlantacion;
	}

	public String setEstacionPlantacion(String estacionPlantacion) {
		
		String mensaje=null;
		mensaje=validarEstacionLlamada(estacionPlantacion);
		if(mensaje==null)
		{
			this.estacionPlantacion=estacionPlantacion;
		}
		
		return mensaje;
	}

	public String getHabitat() {
		return habitat;
	}

	public String setHabitat(String opcion, String habitat) {
		
		String mensaje=null;
		mensaje=validarHabitatLlamada(opcion, habitat);
		if(mensaje==null)
		{
			this.habitat=habitat;
		}
		return mensaje;
	}

	
	public boolean isFlor() {
		return flor;
	}

	public void setFlor(boolean flor) {
		this.flor = flor;
	}

	public String getNivelExposicionSolar() {
		return nivelExposicionSolar;
	}

	public String setNivelExposicionSolar(String nivelExposicionSolar) {
		String mensaje=null;
		mensaje=validarExpSolarLlamada(nivelExposicionSolar);
		if(mensaje==null)
		{
			this.nivelExposicionSolar=nivelExposicionSolar;
		}
		return mensaje;
	}

	public String getTipoRaiz() {
		return tipoRaiz;
	}

	public String setTipoRaiz(String tipoRaiz) {
		String mensaje=null;
		mensaje=validarTipoRaizLlamada(tipoRaiz);
		if(mensaje==null)
		{
			this.tipoRaiz=tipoRaiz;
		}
		return mensaje;
	}

	public boolean isAromatica() {
		return aromatica;
	}

	public void setAromatica(boolean aromatica) {
		this.aromatica = aromatica;
	}
	
	public void setCantidadFertilizante(int cantidadFertilizante) {
		this.cantidadFertilizante = cantidadFertilizante;
	}

	public int getCantidadFertilizante() {
		return cantidadFertilizante;
	}

	@Override
	public String toString() {
		return super.toString()+", Meses De Vida: " + mesesDeVida + ", Estacion de Plantacion: " + estacionPlantacion + ", Habitat: "
				+ habitat + ", Altura: " + altura + ", Flor: " + flor + ", Nivel de Exposicion Solar: "
				+ nivelExposicionSolar + ", tipoRaiz=" + tipoRaiz + ", aromatica=" + aromatica
				+ ", Cantidad de Fertilizante: " + cantidadFertilizante;
	}

	
	
}
