package productos;

import org.json.JSONException;
import org.json.JSONObject;

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

	@Override
	public void establecerPrecio() {
		
	   double precioDiametro=30.6;
       double precioFinal=precioDiametro*getDiametroBoca();
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
            json.put("diametroBoca", getDiametroBoca());
            json.put("base", getBase());
            json.put("descripcion", getDescripcion());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
