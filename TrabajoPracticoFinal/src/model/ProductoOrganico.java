package model;

public abstract class ProductoOrganico extends Producto{

	int gramos;
	
	public ProductoOrganico()
	{
		super();
		this.gramos=0;
	}

	public ProductoOrganico(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.gramos = gramos;
	}

	public ProductoOrganico(String nombre, String marca, double precio, int stock, String descripcion, int gramos) {
		super(nombre, marca, precio, stock, descripcion);
		this.gramos = gramos;
	}

	
	public int getGramos() {
		return gramos;
	}

	public void setGramos(int gramos) {
		this.gramos = gramos;
	}

	@Override
	public String toString() {
		return super.toString()+ ", gramos: " + gramos;
	}
	
	
	
	
	
	
}
