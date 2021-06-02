package model;


public abstract class HerramientaJardineria extends Producto {

	private String material;
	private String funcion; //riego, corte, limpieza, labrado, carga,...
	
	
	public HerramientaJardineria() {
		super();
		this.material = null;
		this.funcion = null;
	}
	public HerramientaJardineria(String nombre, String marca, double precio, int stock, String descripcion, String material, String funcion) {
		super(nombre, marca, precio, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}
	public HerramientaJardineria(String codigo, String nombre, String marca, String clasificacion, double precio,
			int stock, String descripcion, String material, String funcion) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.material = material;
		this.funcion = funcion;
	}
	
	
	
	
	public String getMaterial() {
		return material;
	}
	
	public String setMaterial(String material) {
		String mensaje=null;
		mensaje=validarCadenaCaracteresLlamada(material);
		if(mensaje==null)
		{
			this.material = material;
		}
		return mensaje;
	}
	
	public String getFuncion() {
		return funcion;
	}
	
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + ", material: " + material + ", funcion: " + funcion;
	}
	
	
	
	
}
