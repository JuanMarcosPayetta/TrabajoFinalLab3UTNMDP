package model;

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

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getTipoMotor() {
		return tipoMotor;
	}

	public void setTipoMotor(String tipoMotor) {
		this.tipoMotor = tipoMotor;
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
