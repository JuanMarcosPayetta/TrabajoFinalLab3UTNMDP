package model;

public class Lombriz extends ProductoOrganico{

	private String especie;
	
	public Lombriz()
	{
		super();
		this.especie=null;
	}

	public Lombriz(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos, String especie) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, gramos);
		this.especie = especie;
	}

	public Lombriz(String nombre, String marca, double precio, int stock, String descripcion, int gramos,
			String especie) {
		super(nombre, marca, precio, stock, descripcion, gramos);
		this.especie = especie;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return super.toString()+", especie=" + especie;
	}
	
	
}
