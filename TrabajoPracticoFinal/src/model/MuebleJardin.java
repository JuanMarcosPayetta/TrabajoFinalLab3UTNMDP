package model;

public class MuebleJardin extends ProductoDeHogar{

     private String tipo;
     private double alto;
     private double largo;
     private double ancho;
     
     public MuebleJardin()
     {
    	 super();
    	 this.tipo=null;
    	 this.alto=0;
    	 this.ancho=0;
    	 this.largo=0;
     }

	public MuebleJardin(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String tipo, double alto, double largo, double ancho) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
	}

	public MuebleJardin(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String tipo, double alto, double largo, double ancho) {
		super(nombre, marca, precio, stock, descripcion, material);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	@Override
	public String toString() {
		return super.toString()+", tipo=" + tipo + ", alto=" + alto + ", largo=" + largo + ", ancho=" + ancho;
	}
     
     
     
     
}
