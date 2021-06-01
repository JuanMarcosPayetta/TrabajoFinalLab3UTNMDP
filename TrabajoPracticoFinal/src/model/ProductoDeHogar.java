package model;

public abstract class ProductoDeHogar extends Producto{

	String material;
	
	public ProductoDeHogar(){
		super();
		this.material=null;
	}

	public ProductoDeHogar(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.material = material;
	}

	public ProductoDeHogar(String nombre, String marca, double precio, int stock, String descripcion, String material) {
		super(nombre, marca, precio, stock, descripcion);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return super.toString()+", material:" + material;
	}
	
	
}
