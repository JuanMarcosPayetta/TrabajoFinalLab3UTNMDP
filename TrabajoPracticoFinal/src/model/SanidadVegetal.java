package model;

public class SanidadVegetal extends Producto{

	private String funcion;
	private int centimetroCubico;
	
	public SanidadVegetal()
	{
		super();
		this.funcion=null;
		this.centimetroCubico=0;
	}

	public SanidadVegetal(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String funcion, int centimetroCubico) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.funcion = funcion;
		this.centimetroCubico = centimetroCubico;
	}

	public SanidadVegetal(String nombre, String marca, double precio, int stock, String descripcion, String funcion,
			int centimetroCubico) {
		super(nombre, marca, precio, stock, descripcion);
		this.funcion = funcion;
		this.centimetroCubico = centimetroCubico;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public int getCentimetroCubico() {
		return centimetroCubico;
	}

	public void setCentimetroCubico(int centimetroCubico) {
		this.centimetroCubico = centimetroCubico;
	}

	@Override
	public String toString() {
		return super.toString()+", funcion=" + funcion + ", centimetroCubico=" + centimetroCubico;
	}
	
	
}
