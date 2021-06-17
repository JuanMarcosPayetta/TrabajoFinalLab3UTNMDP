package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;


import excepciones.CadenaInvalidaException;

public class HerramientaNoManual extends HerramientaJardineria {
	
	private int potencia;
	private String tipoMotor; 
	private String consumo; 
	
	public HerramientaNoManual() {
		super();
		this.potencia = 0;
		this.tipoMotor = null;
		this.consumo = null;
	}
	
	public HerramientaNoManual(String codigo, String nombre, String marca, String clasificacion, double precio,
			int stock, String descripcion, String material, String funcion, int potencia, String tipoMotor, String consumo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, funcion);
		this.potencia = potencia;
		this.tipoMotor = tipoMotor;
		this.consumo = consumo;
	}
	
	public HerramientaNoManual(String nombre, String marca, double precio, int stock, String descripcion,
			String material, String funcion, int potencia, String tipoMotor, String consumo) {
		super(nombre, marca, precio, stock, descripcion, material, funcion);
		this.potencia = potencia;
		this.tipoMotor = tipoMotor;
		this.consumo = consumo;
		establecerClasificacion();
	}

	public HerramientaNoManual(String nombre, String marca, int stock, String descripcion,
			String material, String funcion, int potencia, String tipoMotor, String consumo) {
		super(nombre, marca, stock, descripcion, material, funcion);
		this.potencia = potencia;
		this.tipoMotor = tipoMotor;
		this.consumo = consumo;
		establecerClasificacion();
		establecerPrecio();
	}
	
	/**
	 * Valida que el tipo de motor pasado por parametro sea correcto (electirco o a combustible)
	 * @see #validarMotor(String)
	 * @param String
	 * @return String
	 */
	public static String validarMotorLlamada(String motor)
	{
		String mensaje=null;
		try {
			validarMotor(motor);
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
	 * Valida que el tipo de motor pasado por parametro sea correcto, lanzando una excepcion que sera capturada por {@link #validarMotorLlamada(String)}
	 * @see #validarMotorLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarMotor(String motor) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>motorValido= new ArrayList<String>();
		motorValido.add("electrico");
		motorValido.add("combustible");
	
		if(motor==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido (electrico, combustible)");
		}
		else if(motor.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!motor.matches("[a-zA-Z]*\\D{9}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 9 letras");
		}
		else if(!motorValido.contains(motor.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese un dato valido (electrico, combustible)");
		}
	}
	
	/**
	 * Valida que el consumo enviado por parametro sea correcto(bajo, medio, alto)
	 * @see #validarConsumo(String)
	 * @param String
	 * @return String
	 */
	public static String validarConsumoLlamada(String consumo)
	{
		String mensaje=null;
		try {
			validarConsumo(consumo);
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
	 * Valida que el consumo enviado por parametro sea correcto(bajo, medio, alto), lanzando una excepcion que sera capturada por {@link #validarConsumoLlamada(String))}
	 * @see #validarConsumoLlamada(String)
	 * @param String
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarConsumo(String consumo) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		ArrayList<String>consumoValido= new ArrayList<String>();
		consumoValido.add("alto");
		consumoValido.add("medio");
		consumoValido.add("bajo");
	
		if(consumo==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido (alto, medio, bajo)");
		}
		else if(consumo.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!consumo.matches("[a-zA-Z]*\\D{4}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 4 letras");
		}
		else if(!consumoValido.contains(consumo.toLowerCase()))
		{
			throw new InputMismatchException("Error, ingrese un dato valido (alto, medio, bajo)");
		}
	}
	
	/**
	 * Devuelve la potencia de la herramienta
	 * @return int
	 */
	public int getPotencia() {
		return potencia;
	}

	/**
	 * Setea el valor que indica la potencia de la herramienta, validando que sea correcto
	 * @see #validarValorNumericoLlamada(Number)
	 * @param int
	 * @return String
	 */
	public String setPotencia(int potencia) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(potencia);
		if(mensaje==null)
		{
			this.potencia = potencia;
		}
		return mensaje;
	}

	/**
	 * Devuelve el tipo de motor de la herramienta
	 * @return String
	 */
	public String getTipoMotor() {
		return tipoMotor;
	}
	
	/**
	 * Setea el valor que indica el tipo de motor de la herramienta, validando que sea correcto
	 * @see #validarMotorLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setTipoMotor(String tipoMotor) {
		String mensaje=null;
		mensaje=validarMotorLlamada(tipoMotor);
		if(mensaje==null)
		{
			this.tipoMotor = tipoMotor;
		}
		return mensaje;
	}

	/**
	 * Devuelve el consumo de la herramienta
	 * @return Srting
	 */
	public String getConsumo( ) {
		return consumo;
	}

	/**
	 * Setea el valor que indica el consumo de la herramienta, validando que sea correcto
	 * @see #validarConsumoLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setConsumo(String consumo) {
		String mensaje=null;
		mensaje=validarConsumoLlamada(consumo);
		if(mensaje==null)
		{
			this.consumo = consumo;
		}
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", Potencia: " + potencia + ", Tipo de motor: " + tipoMotor + ", Consumo: " + consumo;
	}

	/**
	 * Establece la clasificacion del objeto a "Herramienta no manual"
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Herramienta no manual");
	}

	/**
	 * Establece el precio de la herramienta
	 * @see #getConsumo()
	 * @see #getPotencia()
	 * @see #setPrecio(double)
	 */
	@Override
	public void establecerPrecio() {
	
		double precioH=0;
		if(getConsumo().equalsIgnoreCase("alto"))
		{
			if(getPotencia()<200)
			{
				precioH=1700;
			}
			else if(getPotencia()>=200 && getPotencia()<500)
			{
				precioH=2700.5;
			}
			else if(getPotencia()>=500 && getPotencia()<1000)
			{
				precioH=3850.9;
			}
			else if(getPotencia()>=1000 && getPotencia()<1500)
			{
				precioH=5980;
			}
			else if(getPotencia()>=1500 && getPotencia()<2000)
			{
				precioH=6999;
			}
			else if(getPotencia()>=1000 && getPotencia()<1500)
			{
				precioH=8020.9;
			}
			else
			{
				precioH=12000;
			}
		}
		else if(getConsumo().equalsIgnoreCase("medio"))
		{
			if(getPotencia()<200)
			{
				precioH=2210;
			}
			else if(getPotencia()>=200 && getPotencia()<500)
			{
				precioH=3510.5;
			}
			else if(getPotencia()>=500 && getPotencia()<1000)
			{
				precioH=4500.9;
			}
			else if(getPotencia()>=1000 && getPotencia()<1500)
			{
				precioH=6000;
			}
			else if(getPotencia()>=1500 && getPotencia()<2000)
			{
				precioH=8300.5;
			}
			else if(getPotencia()>=1000 && getPotencia()<1500)
			{
				precioH=11800;
			}
			else
			{
				precioH=14600;
			}
		}
		else
		{
			if(getPotencia()<200)
			{
				precioH=3500;
			}
			else if(getPotencia()>=200 && getPotencia()<500)
			{
				precioH=4859;
			}
			else if(getPotencia()>=500 && getPotencia()<1000)
			{
				precioH=5670.9;
			}
			else if(getPotencia()>=1000 && getPotencia()<1500)
			{
				precioH=7299.5;
			}
			else if(getPotencia()>=1500 && getPotencia()<2000)
			{
				precioH=9235.5;
			}
			else if(getPotencia()>=1000 && getPotencia()<1500)
			{
				precioH=13560;
			}
			else
			{
				precioH=18900;
			}
		}
		
		setPrecio(precioH);
	}

	
	/**
	 * Compara dos objetos, indicando si son iguales, mayor o menor
	 * @see #getPrecio()
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
