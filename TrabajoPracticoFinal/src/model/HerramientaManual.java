package model;

public class HerramientaManual extends HerramientaJardineria{
	
	private String tamanio; //grande, mediano, pequeño
	private boolean reforzada;
	
	
	public HerramientaManual() {
		super();
		this.tamanio = null;
		this.reforzada = false;
	}
	
	public HerramientaManual(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String funcion, String tamanio, boolean reforzada) {
		super(nombre, marca, precio, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
	}
	public HerramientaManual(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String funcion, String tamanio, boolean reforzada) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, funcion);
		this.tamanio = tamanio;
		this.reforzada = reforzada;
	}
	
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public boolean isReforzada() {
		return reforzada;
	}
	public void setReforzada(boolean reforzada) {
		this.reforzada = reforzada;
	}

	@Override
	public String toString() {
		return super.toString()+", tamanio:" + tamanio + ", reforzada:" + reforzada;
	}
	
	
	
	
	
	
	
}
