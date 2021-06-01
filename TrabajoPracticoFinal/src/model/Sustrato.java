package model;

public class Sustrato extends ProductoOrganico{

	boolean abonada;
	String tipoDeSuelo;
	
	public Sustrato()
	{
		super();
		this.abonada=false;
		this.tipoDeSuelo=null;
	}

	public Sustrato(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, int gramos, boolean abonada, String tipoDeSuelo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, gramos);
		this.abonada = abonada;
		this.tipoDeSuelo = tipoDeSuelo;
	}

	public Sustrato(String nombre, String marca, double precio, int stock, String descripcion, int gramos,
			boolean abonada, String tipoDeSuelo) {
		super(nombre, marca, precio, stock, descripcion, gramos);
		this.abonada = abonada;
		this.tipoDeSuelo = tipoDeSuelo;
	}

	public boolean isAbonada() {
		return abonada;
	}

	public void setAbonada(boolean abonada) {
		this.abonada = abonada;
	}

	public String getTipoDeSuelo() {
		return tipoDeSuelo;
	}

	public void setTipoDeSuelo(String tipoDeSuelo) {
		this.tipoDeSuelo = tipoDeSuelo;
	}

	@Override
	public String toString() {
		return super.toString()+", abonada=" + abonada + ", tipoDeSuelo=" + tipoDeSuelo;
	}
	
	
	
	
}
