package productos;

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
		establecerClasificacion();
	}

	public MacetaRedonda(String nombre, String marca, int stock, String descripcion, String material,
			String forma, double diametroBoca, double base) {
		super(nombre, marca, stock, descripcion, material, forma);
		this.diametroBoca = diametroBoca;
		this.base = base;
		establecerClasificacion();
		establecerPrecio();
	}
	
	/**
	 * Devuelve el diametro de la boca de la maceta
	 * @return double
	 */
	public double getDiametroBoca() {
		return diametroBoca;
	}

	/**
	 * Setear el valor que indica el diametro de la boca de la maceta, validando que sea correcto
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setDiametroBoca(double diametroBoca) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(diametroBoca);
		
		if(mensaje==null) {
			this.diametroBoca = diametroBoca;
		}
		return mensaje;
	}

	/**
	 * Devuelve el valor de la base de la maceta
	 * @return double
	 */
	public double getBase() {
		return base;
	}

	/**
	 * Setea el valor que indica el tamanio de la base, validando que sea correcto
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setBase(double base) {
		String mensaje=null;
		mensaje=validarValorNumericoLlamada(base);
		
		if(mensaje==null) {
			this.base = base;
		}
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString()+", Diametro de boca: " + diametroBoca + ", Base: " + base;
	}

	/**
	 * Establece la clasificacion de la maceta a: "Maceta redonda"
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Maceta redonda");
	}

	/**
	 * Establece el precio de la maceta
	 * @see #setPrecio(double)
	 */
	@Override
	public void establecerPrecio() {
		
	   double precioDiametro=30.6;
       double precioFinal=precioDiametro*getDiametroBoca();
	   setPrecio(precioFinal);
		
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
