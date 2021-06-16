package productos;

import org.json.JSONException;
import org.json.JSONObject;

public class MacetaPoliedro extends Maceta {

	private double alto;
	private double ancho;
	private double largo;

	public MacetaPoliedro() {
		super();
		this.alto = 0;
		this.ancho = 0;
		this.largo = 0;
	}

	public MacetaPoliedro(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String forma, double alto, double ancho,
			double largo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, forma);
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
	}

	public MacetaPoliedro(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String forma, double alto, double ancho, double largo) {
		super(nombre, marca, precio, stock, descripcion, material, forma);
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		establecerClasificacion();
	}
	
	public MacetaPoliedro(String nombre, String marca, int stock, String descripcion, String material,
			String forma, double alto, double ancho, double largo) {
		super(nombre, marca, stock, descripcion, material, forma);
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		establecerClasificacion();
		establecerPrecio();
	}

	public double getAlto() {
		return alto;
	}

	public String setAlto(double alto) {
		String mensaje = validarValorNumericoLlamada(alto);
		if (mensaje == null) {

			this.alto = alto;
		}
		return mensaje;
	}

	/**
	 * Devuelve el ancho de la maceta
	 * @return double
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * Setea el valor que indica el ancho de la maceta
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setAncho(double ancho) {
		String mensaje = validarValorNumericoLlamada(ancho);

		if (mensaje == null) {
			this.ancho = ancho;
		}
		return mensaje;
	}

	/**
	 * Devuelve el largo de la maceta
	 * @return double
	 */
	public double getLargo() {
		return largo;
	}

	/**
	 * Setea el valor que indica el largo de la maceta
	 * @see #validarValorNumericoLlamada(Number)
	 * @param double
	 * @return String
	 */
	public String setLargo(double largo) { 
		String mensaje = validarValorNumericoLlamada(largo);

		if (mensaje == null) {

			this.largo = largo;
		}
		return mensaje; 
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", alto:" + alto + ", ancho:" + ancho + ", largo:" + largo;
	}

	/**
	 * Establece la clasificacion del objeto a: "Maceta poliedro"
	 * @see #setClasificacion(String)
	 */
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Maceta poliedro");
	}

	/**
	 * Establece el precio de la maceta
	 * @see #getForma()
	 */
	@Override
	public void establecerPrecio() {
		
		double precioFinal=0;
		
		if(getForma().equalsIgnoreCase("cubo"))
		{
			double precioAncho=18.75;
			precioFinal=precioAncho*getAncho();
		}
		else if(getForma().equalsIgnoreCase("jardinera"))
		{
			double precioLargo=59;
			precioFinal=precioLargo*getLargo();
		}
		else if(getForma().equalsIgnoreCase("piramidal"))
		{
			double precioAlto=19;
			precioFinal=precioAlto*getAlto();
		}
		
		setPrecio(precioFinal);
	}

	public JSONObject javaToJson()
	{
		JSONObject obj= null;
		try
		{
			obj= new JSONObject();
			obj.put("marca", getMarca());
			obj.put("clasificacion", getClasificacion());
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
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
