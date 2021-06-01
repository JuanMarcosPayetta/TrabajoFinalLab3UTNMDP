package model;

public class Semilla extends ProductoOrganico{

	String destino;
	
	public Semilla()
	{
		super();
		this.destino=null;
	}

	public Semilla(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos, String destino) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, gramos);
		this.destino = destino;
	}

	public Semilla(String nombre, String marca, double precio, int stock, String descripcion, int gramos,
			String destino) {
		super(nombre, marca, precio, stock, descripcion, gramos);
		this.destino = destino;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return super.toString()+ ", destino: " + destino;
	}
	
	
	
}
