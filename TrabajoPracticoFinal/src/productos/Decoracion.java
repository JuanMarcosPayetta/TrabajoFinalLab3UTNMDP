package productos;
/**
 * 
 * Atributos de los productos clasificacion Decoracion
 *
 */
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

	public Decoracion(String nombre, String marca, int stock,
			String descripcion, String material, boolean exterior, String colorPrimario) {
		super(nombre, marca, stock, descripcion, material);
		this.exterior = exterior;
		this.colorPrimario = colorPrimario;
		establecerClasificacion();
		establecerPrecio();
	}

	/**
	 * Devuelve si la decoracion es o no de exterior
	 * @return boolean
	 */
	public boolean isExterior() {
		return exterior;
	}
	
	/**
	 * Setea el valor que indica si la decoracion es de exterior o no, validando que sea correcto
	 * @see #validarBooleanLlamada(Boolean)
	 * @param boolean
	 * @return String
	 */
	public String setExterior(boolean exterior) {
		String mensaje = validarBooleanLlamada(exterior);// si devuelve null es correcto

		if (mensaje == null) {

			this.exterior = exterior;
		}

		return mensaje;
	}

	/**
	 * Devuelve el color primario de la decoracion
	 * @return String
	 */
	public String getColorPrimario() {
		return colorPrimario;
	}

	/**
	 * Setea el valor que indica el color primario de la decoracion, validando que sea correcto
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setColorPrimario(String colorPrimario) {

		String mensaje = validarCadenaCaracteresLlamada(colorPrimario);// si devuelve null es correcto

		if (mensaje == null) {

			this.colorPrimario = colorPrimario;
		}
		
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", De exterior: " + exterior + ", Color primario: " + colorPrimario;
	}

	/**
	 * Establece la clasificacion del objeto a "Decoracion"
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Decoracion");
	}

	/**
	 * Establece el precio del producto
	 * @see #isExterior()
	 * @see #setPrecio(double)
	 */
	@Override
	public void establecerPrecio() {
		
		double precioPorDefecto=0;
		if(isExterior())
		{
			precioPorDefecto=270.5;
		}
		else
		{
			precioPorDefecto=180;
		}
		
		setPrecio(precioPorDefecto);
	}

   
	
	/**
	 * Compara dos objetos, indicando si son iguales, mayor o menor
	 * @see #getPrecio()
	 * @param Producto
	 * @return int
	 */
	@Override
	public int compareTo(Producto o) {
		int res=-2;
		if(o!=null)
		{
			if(this.getPrecio()==o.getPrecio())
			{
				res=0;
			}
			else if (this.getPrecio()>o.getPrecio())
			{
				res=1;
			}
			else
			{
				res=-1;
			}
		}
		return res;
	}
	
}
