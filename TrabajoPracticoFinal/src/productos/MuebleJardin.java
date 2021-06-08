package productos;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;

public class MuebleJardin extends ProductoDeHogar {

	private String tipo;
	private double alto;
	private double largo;
	private double ancho;

	public MuebleJardin() {
		super();
		this.tipo = null;
		this.alto = 0;
		this.ancho = 0;
		this.largo = 0;
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
		establecerClasificacion();
	}
	
	public MuebleJardin(String nombre, String marca, int stock, String descripcion, String material,
			String tipo, double alto, double largo, double ancho) {
		super(nombre, marca, stock, descripcion, material);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
		establecerClasificacion();
		establecerPrecio();
	}

	public String getTipo() {
		return tipo;
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

	@Override
	public String toString() {
		return super.toString() + ", tipo=" + tipo + ", alto=" + alto + ", largo=" + largo + ", ancho=" + ancho;
	}

	public static String validarTipoMuebleLlamada(String mueble) {
		String mensaje = null;
		try {
			validarTipoMueble(mueble);
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		} catch (InputMismatchException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	private static void validarTipoMueble(String mueble)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		ArrayList<String> tiposMueble = new ArrayList<String>();
		tiposMueble.add("mesa");
		tiposMueble.add("silla");
		tiposMueble.add("reposera");
		tiposMueble.add("camastro");
		tiposMueble.add("hamaca");

		if (mueble == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (mueble.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!mueble.matches("[a-zA-Z]*\\D{4}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + "4 letras");
		} else if (!tiposMueble.contains(mueble.toLowerCase())) {
			throw new InputMismatchException(
					"Ingrese un tipo de mueble valido (mesa, silla, reposera, camastro, hamaca)");
		}
	}

	public String setTipo(String tipoMueble) {
		String mensaje = null;
		mensaje = validarTipoMuebleLlamada(tipoMueble);

		if (mensaje == null) {
			this.tipo = tipoMueble;
		}
		return mensaje;
	}
	
	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Mueble de jardin");
	}

	@Override
	public void establecerPrecio() {
		double precioMueble=0;
		
		if(getTipo().equalsIgnoreCase("mesa"))
		{
			precioMueble=24.500;
		}
		else if(getTipo().equalsIgnoreCase("silla"))
		{
			precioMueble=9000;
		}
		else if(getTipo().equalsIgnoreCase("reposera"))
		{
			precioMueble=5200;
		}
		else if(getTipo().equalsIgnoreCase("camastro"))
		{
			precioMueble=45.450;
		}
		else if(getTipo().equalsIgnoreCase("hamaca"))
		{
			precioMueble=37.780;
		}
		
		setPrecio(precioMueble);
		
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
            json.put("tipo", getTipo());
            json.put("alto", getAlto());
            json.put("largo", getLargo());
            json.put("ancho", getAncho());
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
