package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;
import interfaces.IDescuento;

/**
 * Gestiona todo los metodos y atributos referentes a los pedidos
 *
 */
public class Pedido implements IDescuento, Serializable {

	private int idCliente;
	private int idEmpleado;
	private double totalBruto;
	private double totalNeto;
	private String medioDePago;
	private double descuento;
	private boolean fueAbonado;
	private int numeroPedido;
	private int numeroPeidoauto = 1;
	private ArrayList<PeticionCompra> carrito;

	public Pedido() {
		this.idCliente = 0;
		this.idEmpleado = 0;
		this.totalBruto = 0;
		this.totalNeto = 0;
		this.medioDePago = null;
		this.descuento = 0;
		this.fueAbonado = false;
		this.numeroPedido = 0;
		carrito = new ArrayList<PeticionCompra>();
	}

	public Pedido(int idCliente, int idEmpleado, double totalBruto, double totalNeto, String medioDePago,
			double descuento, boolean fueAbonado, int numeroPedido, ArrayList<PeticionCompra> carrito) {
		this.idCliente = idCliente;
		this.idEmpleado = idEmpleado;
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
		this.idEmpleado = 0;
		this.totalBruto = 0;
		this.totalNeto = 0;
		this.medioDePago = null;
		this.descuento = 0;
		this.fueAbonado = false;
		this.numeroPedido = numeroPeidoauto;
		this.numeroPeidoauto++;
		carrito = new ArrayList<PeticionCompra>();
	}

	/**
	 * Retorna el Id del cliente
	 * 
	 * @return int
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * Establece el Id del objeto cliente
	 * 
	 * @param idCliente
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Retorna el Id del empleado
	 * 
	 * @return int
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * Establece el Id del empleado
	 * 
	 * @param idEmpleado
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	/**
	 * Retorna el total bruto del pedido
	 * 
	 * @return double
	 */
	public double getTotalBruto() {
		return totalBruto;
	}

	/**
	 * Setea el total bruto del pedido
	 */
	public void setTotalBruto() {
		double total = 0;
		if (carrito != null) {
			for (int i = 0; i < carrito.size(); i++) {
				double valorItem = carrito.get(i).getPrecioUnitario() * carrito.get(i).getCantidad();
				total = valorItem + total;
			}
			this.totalBruto = total;
		}
	}

	/**
	 * Retorna el totalNeto del pedido
	 * 
	 * @return double
	 */
	public double getTotalNeto() {
		return totalNeto;
	}

	/**
	 * Setea el total neto del pedido
	 */
	public void setTotalNeto() {
		if (carrito != null) {
			this.totalNeto = this.totalBruto * this.descuento;
		}
	}

	/**
	 * Retorna el medio de pago establecido en el pedido
	 * 
	 * @return String
	 */
	public String getMedioDePago() {
		return medioDePago;
	}

	/**
	 * Establece el medio de pago del pedido
	 * 
	 * @param medioDePago
	 * @return String
	 */
	public String setMedioDePago(String medioDePago) {
		String mensaje = null;
		mensaje = validarMedioDePagoLlamada(medioDePago);

		if (mensaje == null) {
			this.medioDePago = medioDePago.toLowerCase();
		}
		return mensaje;
	}

	/**
	 * Retorna el descuento establecido del objeto
	 * 
	 * @return double
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * Establece el descuento para ese objeto
	 * 
	 * @param String
	 */
	public void setDescuento(String medioDePago) {
		double desc = 0;
		boolean existeMedioP = existeMedioDePago(medioDePago);
		if (existeMedioP == true) {
			if (medioDePago.equalsIgnoreCase("efectivo")) {
				desc = ((100 - DESC_EFECTIVO) / 100);
				this.descuento = desc;
			} else if (medioDePago.equalsIgnoreCase("tarjeta")) {
				desc = (100 - DESC_TARJETA) / 100;
				this.descuento = desc;
			} else if (medioDePago.equalsIgnoreCase("cuentadni")) {
				desc = (100 - DESC_CUENTADNI) / 100;
				this.descuento = desc;
			} else {// si es = a "mercado pago"
				desc = (100 - DESC_MERCADOPAGO) / 100;
				this.descuento = desc;
			}
		}
	}

	/**
	 * Retorna si el pedido ha sido abonado o no
	 * 
	 * @return boolean
	 */
	public boolean isFueAbonado() {
		return fueAbonado;
	}

	/**
	 * Se utiliza para establecer que el pedido ha sido abonado
	 * 
	 * @param fueAbonado
	 */
	public void setFueAbonado(boolean fueAbonado) {
		this.fueAbonado = fueAbonado;
	}

	/**
	 * Retorna el numero del pedido
	 * 
	 * @return int
	 */
	public int getNumeroPedido() {
		return numeroPedido;
	}

	/**
	 * Establece el numero de pedido
	 * 
	 * @param int
	 */
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	/**
	 * Retorna el ArrayList de peticion Compra
	 * 
	 * @return ArrayList<PeticionCompra>
	 */
	public ArrayList<PeticionCompra> getCarrito() {
		return carrito;
	}

	/**
	 * Agrega un objeto de la clase peticionCompra al carrito de pedidos
	 * 
	 * @param peticionCompra
	 */
	public void agregarProductoToCarrito(PeticionCompra peticion) {
		carrito.add(peticion);
	}

	/**
	 * Captura la expecion lanzada por {@link #validarMedioDePago(String)}
	 * 
	 * @see #validarMedioDePago(String)
	 * @param String
	 * @return String
	 */
	public static String validarMedioDePagoLlamada(String medioPago) {
		String mensaje = null;
		try {
			validarMedioDePago(medioPago);
		} catch (InputMismatchException e) {
			mensaje = e.getMessage();
		} catch (NullPointerException e) {
			mensaje = e.getMessage();
		} catch (CadenaInvalidaException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Verifica que el medio de pago ingresado sea valido Lanza una expecion que es
	 * capturada por {@link #validarMedioDePagoLlamada(String)}
	 * 
	 * @see #validarMedioDePagoLlamada(String)
	 * @param medioPago
	 * @throws NullPointerException
	 * @throws CadenaInvalidaException
	 * @throws InputMismatchException
	 */
	private static void validarMedioDePago(String medioPago)
			throws NullPointerException, CadenaInvalidaException, InputMismatchException {
		boolean existeMedioP = existeMedioDePago(medioPago);

		if (medioPago == null) {
			throw new NullPointerException("Error, ingrese un dato valido");
		} else if (medioPago.isBlank()) {
			throw new CadenaInvalidaException(CadenaInvalidaException.ESPACIOENBLANCOEXCEPTION);
		} else if (!medioPago.matches("[a-zA-Z ]*\\D{7}")) {
			throw new CadenaInvalidaException(CadenaInvalidaException.LONGITUDYNUMEROSEXCEPTION + " 7 letras");
		} else if (existeMedioP == false) {
			throw new InputMismatchException("Ingrese un tipo valido (efectivo, tarjeta, cuentadni, mercadopago)");
		}
	}

	/**
	 * Retorna true si el medio de pago ingresado esta dentro de los permitidos
	 * 
	 * @param String
	 * @return boolean
	 */
	private static boolean existeMedioDePago(String medioPago) {
		boolean existe = false;
		ArrayList<String> medios = new ArrayList<String>();
		medios.add("efectivo");
		medios.add("tarjeta");
		medios.add("cuentadni");
		medios.add("mercadopago");

		if (medios.contains(medioPago.toLowerCase())) {
			existe = true;
		}
		return existe;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * 
	 * @return String
	 */
	public String toString() {
		return " IDCliente: " + idCliente + "\n" + " IDEmpleado: " + idEmpleado
				+ "\n" + " Total Bruto: " + totalBruto + "\n" + " Total Neto: " + totalNeto + "\n" + " Medio de Pago: "
				+ medioDePago + "\n" + " Fue Abonado: " + fueAbonado + "\n" + " Carrito: " +"\n"+ carrito;
	}

	/**
	 * Agrega un objeto peticionCompra al arrayList de Pedido
	 * 
	 * @param peticionCompra
	 */
	public void agregarPeticionCompra(PeticionCompra peticion) {
		carrito.add(peticion);
	}

}
