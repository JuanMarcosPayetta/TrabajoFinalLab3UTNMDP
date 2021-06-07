package productos;

import interfaces.IEstablecerPrecioProductoInerte;

public abstract class ProductoOrganico extends Producto implements IEstablecerPrecioProductoInerte{

	private int gramos;
	
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

	public ProductoOrganico(String nombre, String marca, int stock, String descripcion, int gramos) {
		super(nombre, marca, stock, descripcion);
		this.gramos = gramos;
	}
	
	
	public int getGramos() {
		return gramos;
	}

	public String setGramos(int gramos) {
		
	  String mensaje=validarValorNumericoLlamada(gramos); //si devuelve "null" el dato es correcto
			
		if(mensaje==null)
		{
			this.gramos = gramos;
		}
		return mensaje;	
	}

	@Override
	public String toString() {
		return super.toString()+ ", gramos: " + gramos;
	}
	
	
	
	
	
	
}
