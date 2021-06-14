package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import excepciones.CadenaInvalidaException;
import interfaces.IDescuento;

public class Pedido implements IDescuento, Serializable{

	private int idCliente;
	private int idEmpleado;
	private double totalBruto;
	private double totalNeto;
	private String medioDePago;
	private double descuento; 
	private boolean fueAbonado;
	private int numeroPedido;
	private int numeroPeidoauto=1;
	private ArrayList<PeticionCompra> carrito;
	
	public Pedido() {
		this.idCliente=0;
		this.idEmpleado=0;
		this.totalBruto=0;
		this.totalNeto=0;
		this.medioDePago=null;
		this.descuento=0;
		this.fueAbonado=false;
		this.numeroPedido=0;
		carrito = new ArrayList<PeticionCompra>();
	}
	
	public Pedido(int idCliente, int idEmpleado, double totalBruto, double totalNeto, String medioDePago, double descuento,
			boolean fueAbonado, int numeroPedido, ArrayList<PeticionCompra> carrito) {
		this.idCliente = idCliente;
		this.idEmpleado=idEmpleado;
		this.totalBruto = totalBruto;
		this.totalNeto = totalNeto;
		this.medioDePago = medioDePago;
		this.descuento = descuento;
		this.fueAbonado = fueAbonado;
		this.numeroPedido = numeroPedido;
		this.carrito = carrito;
	}
	
	public Pedido(int idCliente) {
		this.idCliente = idCliente;
		this.idEmpleado=0;
		this.totalBruto = 0;
		this.totalNeto = 0;
		this.medioDePago = null;
		this.descuento = 0;
		this.fueAbonado = false;
		this.numeroPedido = numeroPeidoauto;
		this.numeroPeidoauto++;
		carrito = new ArrayList<PeticionCompra>();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public double getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto() 
	{
		double total=0;
		if(carrito!=null) 
		{
			for(int i=0 ; i<carrito.size() ; i++) {
				double valorItem = carrito.get(i).getPrecioUnitario() * carrito.get(i).getCantidad();
				total = valorItem + total;
			}
			this.totalBruto=total;
		}
	}

	public double getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto() 
	{
		if(carrito!=null) 
		{
			this.totalNeto = this.totalBruto * this.descuento;
		}
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public String setMedioDePago(String medioDePago) {
		String mensaje=null;
		mensaje=validarMedioDePagoLlamada(medioDePago);
		
		if(mensaje==null) {
			this.medioDePago = medioDePago.toLowerCase();
		}
		return mensaje;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(String medioDePago) 
	{
		double desc=0;
		boolean existeMedioP = existeMedioDePago(medioDePago);
		if(existeMedioP==true) 
		{
			if (medioDePago.equalsIgnoreCase("efectivo")) {
				desc=((100 - DESC_EFECTIVO) / 100); 
				this.descuento = desc;
			}
			else if (medioDePago.equalsIgnoreCase("tarjeta")) {
				desc = (100 - DESC_TARJETA) / 100 ;
				this.descuento = desc;
			}
			else if (medioDePago.equalsIgnoreCase("cuentadni")) {
				desc = (100 - DESC_CUENTADNI) / 100 ;
				this.descuento = desc;
			}
			else {// si es = a "mercado pago"
				desc = (100 - DESC_MERCADOPAGO) / 100 ;
				this.descuento = desc;
			}
		}
	}

	public boolean isFueAbonado() {
		return fueAbonado;
	}

	public void setFueAbonado(boolean fueAbonado) {
		this.fueAbonado = fueAbonado;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	
	//unicamente se usa para eliminar productos desde el vivero
	public ArrayList<PeticionCompra> getCarrito() {
		return carrito;
	}

	public void setCarrito(ArrayList<PeticionCompra> carrito) {
		this.carrito = carrito;
	}
	
	public void agregarProductoToCarrito(PeticionCompra peticion) {
		carrito.add(peticion);
	}

	
	
	public static String validarMedioDePagoLlamada(String medioPago)
	{
		String mensaje=null;
		try {
			validarMedioDePago(medioPago);
		} catch (InputMismatchException e) {
			mensaje=e.getMessage();
		} catch (NullPointerException e) {
			mensaje=e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
	
	private static void validarMedioDePago (String medioPago) throws NullPointerException, CadenaInvalidaException, InputMismatchException
	{
		boolean existeMedioP = existeMedioDePago(medioPago);
		
		if(medioPago==null)
		{
			throw new NullPointerException("Error, ingrese un dato valido");
		}
		else if(medioPago.isBlank())
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		}
		else if(!medioPago.matches("[a-zA-Z ]*\\D{7}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 7 letras");
		}
		else if(existeMedioP==false)
		{
			throw new InputMismatchException("Ingrese un tipo valido (efectivo, tarjeta, cuentaDNI, mercadopago)");
		}
	}

	private static boolean existeMedioDePago (String medioPago) 
	{
		boolean existe=false;
		ArrayList<String> medios = new ArrayList<String>();
		medios.add("efectivo");
		medios.add("tarjeta");
		medios.add("cuentaDNI");
		medios.add("mercadopago");

		if(medios.contains(medioPago.toLowerCase())) {
			existe=true;
		}
		return existe;
	}
	
	
	@Override
	public String toString() {
		return " - Pedido " + numeroPedido + " - \n"
				+ " idCliente: " + idCliente + "\n"
				+ " idEmpleado: "+idEmpleado + "\n"
				+ " total bruto: " + totalBruto + "\n"
				+ " total neto: " + totalNeto + "\n"
				+ " medio de pago: " + medioDePago + "\n"
				+ " fue abonado: " + fueAbonado + "\n"
				+ " carrito: " + carrito;
	}
	
	
	public void agregarPeticionCompra(PeticionCompra peticion)
	{
		carrito.add(peticion);
	}
	
	public JSONObject javaToJson()
    {
        JSONObject json= new JSONObject();
        try {
        	
        	JSONArray carri= new JSONArray();
        	for(int i=0; i<carrito.size(); i++)
        	{
        		PeticionCompra peticion= carrito.get(i);
        		JSONObject obj= new JSONObject();
        		obj.put("codigo", peticion.getCodigo());
        		obj.put("precioUnitario", peticion.getPrecioUnitario());
        		obj.put("cantidad", peticion.getCantidad());
        		carri.put(obj);
        		
        	}
        	
            json.put("idCliente", getIdCliente());
            json.put("idEmpleado", getIdEmpleado());
            json.put("totalBruto", getTotalBruto());
            json.put("totalNeto", getTotalNeto());
            json.put("medioDePago", getMedioDePago());
            json.put("fueAbonado", isFueAbonado());
            json.put("numeroPedido", getNumeroPedido());
            json.put("carrito", carri);
            

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
