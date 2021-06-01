package model;

public abstract class Maceta extends ProductoDeHogar{

	private String forma;
	private double volumen;
	
	public Maceta()
	{
		super();
		this.forma=null;
		this.volumen=0;
	}

	public Maceta(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String forma, double volumen) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material);
		this.forma = forma;
		this.volumen = volumen;
	}

	public Maceta(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String forma, double volumen) {
		super(nombre, marca, precio, stock, descripcion, material);
		this.forma = forma;
		this.volumen = volumen;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	@Override
	public String toString() {
		return super.toString()+", forma:" + forma + ", volumen:" + volumen;
	}
	
	
	
	
}
