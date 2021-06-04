package model;

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
	
	
	
	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
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

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	@Override
	public String toString() {
		return super.toString() + ", potencia:" + potencia + ", tipoMotor:" + tipoMotor + ", consumo:" + consumo;
	}

	
	
	
	
}
