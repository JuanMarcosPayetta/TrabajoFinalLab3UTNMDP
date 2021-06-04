package model;


public class MacetaRedonda extends Maceta{

	private double diametroBoca;
	private double base;
	
	public MacetaRedonda()
	{
		super();
		this.diametroBoca=0;
		this.base=0;
	}

	public MacetaRedonda(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String forma, double diametroBoca, double base) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, forma);
		this.diametroBoca = diametroBoca;
		this.base = base;
	}

	public MacetaRedonda(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String forma, double diametroBoca, double base) {
		super(nombre, marca, precio, stock, descripcion, material, forma);
		this.diametroBoca = diametroBoca;
		this.base = base;
	}

	
	public double getDiametroBoca() {
		return diametroBoca;
	}

	public String setDiametroBoca(double diametroBoca) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(diametroBoca);
		
		if(mensaje==null) {
			this.diametroBoca = diametroBoca;
		}
		return mensaje;
	}

	public double getBase() {
		return base;
	}

	public String setBase(double base) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(base);
		
		if(mensaje==null) {
			this.base = base;
		}
		return mensaje;
	}

	@Override
	public String toString() {
		return super.toString()+", diametroBoca: " + diametroBoca + ", base: " + base;
	}

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Maceta redonda");
	}

	
	
}
