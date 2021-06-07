package domain;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;
import interfaces.IDescuento;

public class Pedido implements IDescuento {

	private int idCliente;
	private double totalBruto;
	private double totalNeto;
	private String medioDePago;
	private int descuento; 
	private boolean fueAbonado;
	private int numeroPedido;
	private ArrayList<PeticionCompra> carrito;
	
	public Pedido() {
		this.idCliente=0;
		this.totalBruto=0;
		this.totalNeto=0;
		this.medioDePago=null;
		this.descuento=0;
		this.fueAbonado=false;
		this.numeroPedido=0;
		carrito = new ArrayList<PeticionCompra>();
	}
	
	public Pedido(int idCliente, double totalBruto, double totalNeto, String medioDePago, int descuento,
			boolean fueAbonado, int numeroPedido, ArrayList<PeticionCompra> carrito) {
		this.idCliente = idCliente;
		this.totalBruto = totalBruto;
		this.totalNeto = totalNeto;
		this.medioDePago = medioDePago;
		this.descuento = descuento;
		this.fueAbonado = fueAbonado;
		this.numeroPedido = numeroPedido;
		this.carrito = carrito;
	}
	
	public Pedido(int idCliente, String medioDePago, boolean fueAbonado, int numeroPedido) {
		this.idCliente = idCliente;
		this.totalBruto = 0;
		this.totalNeto = 0;
		this.medioDePago = medioDePago;
		this.descuento = 0;
		this.fueAbonado = fueAbonado;
		this.numeroPedido = numeroPedido;
		carrito = new ArrayList<PeticionCompra>();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(String medioDePago) 
	{
		boolean existeMedioP = existeMedioDePago(medioDePago);
		if(existeMedioP==true) 
		{
			if (medioDePago.equalsIgnoreCase("efectivo")) {
				this.descuento = (100 - DESC_EFECTIVO) / 100 ;
			}
			else if (medioDePago.equalsIgnoreCase("tarjeta")) {
				this.descuento = (100 - DESC_TARJETA) / 100 ;
			}
			else if (medioDePago.equalsIgnoreCase("cuenta dni")) {
				this.descuento = (100 - DESC_CUENTADNI) / 100 ;
			}
			else {// si es = a "mercado pago"
				this.descuento = (100 - DESC_MERCADOPAGO) / 100 ;
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
		else if(!medioPago.matches("[a-zA-Z]*\\D{7}"))
		{
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION+" 7 letras");
		}
		else if(existeMedioP==false)
		{
			throw new InputMismatchException("Ingrese un tipo valido (efectivo, tarjeta, cuenta DNI, mercado pago)");
		}
	}

	private static boolean existeMedioDePago (String medioPago) 
	{
		boolean existe=false;
		ArrayList<String> medios = new ArrayList<String>();
		medios.add("efectivo");
		medios.add("tarjeta");
		medios.add("cuenta DNI");
		medios.add("mercado pago");

		if(medios.contains(medioPago.toLowerCase())) {
			existe=true;
		}
		return existe;
	}
	
	
	@Override
	public String toString() {
		return "Pedido \n idCliente: " + idCliente + ", total bruto: " + totalBruto + ", total neto: " + totalNeto
				+ ", medio de pago: " + medioDePago + ", fue abonado: " + fueAbonado
				+ ", numero pedido: " + numeroPedido + ", carrito: " + carrito;
	}
	
	
	public void agregarPeticionCompra(PeticionCompra peticion)
	{
		carrito.add(peticion);
	}
	
}
