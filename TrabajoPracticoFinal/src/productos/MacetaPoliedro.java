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

	public double getAncho() {
		return ancho;
	}

	public String setAncho(double ancho) {
		String mensaje = validarValorNumericoLlamada(ancho);

		if (mensaje == null) {
			this.ancho = ancho;
		}
		return mensaje;
	}

	public double getLargo() {
		return largo;
	}

	public String setLargo(double largo) { 
		String mensaje = validarValorNumericoLlamada(largo);

		if (mensaje == null) {

			this.largo = largo;
		}
		return mensaje; 
	}

	@Override
	public String toString() {
		return super.toString() + ", alto=" + alto + ", ancho=" + ancho + ", largo=" + largo;
	}

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Maceta poliedro");
	}

	
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
        JSONObject json= new JSONObject();
        try {
            json.put("codigo", getCodigo());
            json.put("nombre", getNombre());
            json.put("marca", getMarca());
            json.put("clasificacion", getClasificacion());
            json.put("precio", getPrecio());
            json.put("stock", getStock());
            json.put("material", getMaterial());
            json.put("forma", getForma());
            json.put("alto", getAlto());
            json.put("ancho", getAncho());
            json.put("largo", getLargo());
            json.put("descripcion", getDescripcion());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
	
}
