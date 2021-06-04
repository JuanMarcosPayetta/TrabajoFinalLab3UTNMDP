package productos;

public class Decoracion extends ProductoDeHogar {

	private boolean exterior;
	private String colorPrimario;

	public Decoracion() {
		super();
		this.exterior = false;
		this.colorPrimario = null;
	}

	public Decoracion(String nombre, String marca, double precio, int stock, String descripcion, String material,
			boolean exterior, String colorPrimario) {
		super(nombre, marca, precio, stock, descripcion, material);
		this.exterior = exterior;
		this.colorPrimario = colorPrimario;
	}

	public Decoracion(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, boolean exterior, String colorPrimario) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material);
		this.exterior = exterior;
		this.colorPrimario = colorPrimario;
	}

	public boolean isExterior() {
		return exterior;
	}

	public String setExterior(boolean exterior) {
		String mensaje = validarBooleanLlamada(exterior);// si devuelve null es correcto

		if (mensaje == null) {

			this.exterior = exterior;
		}

		return mensaje;
	}

	public String getColorPrimario() {
		return colorPrimario;
	}

	public String setColorPrimario(String colorPrimario) {

		String mensaje = validarCadenaCaracteresLlamada(colorPrimario);// si devuelve null es correcto

		if (mensaje == null) {

			this.colorPrimario = colorPrimario;
		}
		
		return mensaje;
	}

	@Override
	public String toString() {
		return super.toString() + ", exterior:" + exterior + ", colorPrimario:" + colorPrimario;
	}

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Decoracion");
	}

}
