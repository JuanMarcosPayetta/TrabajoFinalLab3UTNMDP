package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

public class HerramientaNoManual extends HerramientaJardineria {
	
	private int potencia;
	private String tipoMotor; //eléctrico, combustible
	private String consumo; //alto, medio, bajo
	
	public HerramientaNoManual() {
		super();
		this.potencia = 0;
		this.tipoMotor = null;
		this.consumo = null;
	}
	
	public HerramientaNoManual(String nombre, String marca, double precio, int stock, String descripcion,
			String material, String funcion, int potencia, String tipoMotor, String consumo) {
		super(nombre, marca, precio, stock, descripcion, material, funcion);
		this.potencia = potencia;
		this.tipoMotor = tipoMotor;
		this.consumo = consumo;
		establecerClasificacion();
	}
	
	public HerramientaNoManual(String codigo, String nombre, String marca, String clasificacion, double precio,
			int stock, String descripcion, String material, String funcion, int potencia, String tipoMotor, String consumo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, funcion);
		this.potencia = potencia;
		this.tipoMotor = tipoMotor;
		this.consumo = consumo;
	}

	/*
	 * valida el tipo de motor, electirco o a combustible
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
	
	/*
	 * valida el consumo (bajo, medio, alto)
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
	
	public int getPotencia() {
		return potencia;
	}

	public String setPotencia(int potencia) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(potencia);
		if(mensaje==null)
		{
			this.potencia = potencia;
		}
		return mensaje;
	}

	public String getTipoMotor() {
		return tipoMotor;
	}

	public String setTipoMotor(String tipoMotor) {
		String mensaje=null;
		mensaje=validarMotorLlamada(tipoMotor);
		if(mensaje==null)
		{
			this.tipoMotor = tipoMotor;
		}
		return mensaje;
	}

	public String getConsumo( ) {
		return consumo;
	}

	public String setConsumo(String consumo) {
		String mensaje=null;
		mensaje=validarConsumoLlamada(consumo);
		if(mensaje==null)
		{
			this.consumo = consumo;
		}
		return mensaje;
	}

	
	@Override
	public String toString() {
		return super.toString() + ", potencia: " + potencia + ", tipoMotor: " + tipoMotor + ", consumo: " + consumo;
	}

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Herramienta no manual");
	}

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

	
	
	
}
