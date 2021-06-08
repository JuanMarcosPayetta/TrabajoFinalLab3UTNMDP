package productos;

import org.json.JSONException;
import org.json.JSONObject;

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
		establecerClasificacion();
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

	public JSONObject javaToJson()
	{
		JSONObject json= new JSONObject();
		try {
			json.put("codigo", getCodigo());
			json.put("nombre", getNombre());
			json.put("marca", getMarca());
			json.put("clasificacion", getClasificacion());
			json.put("precio", getPrecio());
			json.put("stock", getStock());
			json.put("material", getMaterial());
			json.put("esDeExterior", isExterior());
			json.put("color", getColorPrimario());
			json.put("descripcion", getDescripcion());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
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
