package ventas;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

import domain.Cliente;
import domain.Pedido;
import domain.PeticionCompra;

/**
 * Almacena en un linkedList del tipo Pedido los pedidos realizados
 */

public class RegistroVentas implements Serializable {

	private LinkedList<Pedido> registroPedidos;

	public RegistroVentas() {
		this.registroPedidos = new LinkedList<Pedido>();
	}

	/**
	 * Retorna true si el cliente tiene un pedido impago
	 * 
	 * @param int
	 * @return boolean
	 */
	public boolean buscarPedidoImpago(int idCliente) {
		ListIterator<Pedido> it = registroPedidos.listIterator();
		boolean encontrado = false;
		while (it.hasNext() && encontrado == false) {
			Pedido pedido = it.next();
			if (pedido.getIdCliente() == idCliente) {
				if (!pedido.isFueAbonado()) {
					encontrado = true;
				}
			}
		}
		return encontrado;
	}

	/**
	 * Retorna el pedido impago, si no retorna un objeto null
	 * 
	 * @param int
	 * @return Pedido
	 */
	public Pedido retornarPedidoImpago(int idCliente) {
		ListIterator<Pedido> it = registroPedidos.listIterator();
		Pedido pedido = null;
		boolean encontrado = false;
		while (it.hasNext() && encontrado == false) {
			pedido = it.next();
			if (pedido.getIdCliente() == idCliente) {
				if (!pedido.isFueAbonado()) {
					encontrado = true;
				}
			}
		}
		return pedido;
	}

	/**
	 * Establece el medio de pago
	 * 
	 * @see #setMedioDePago
	 * @param int
	 * @param String
	 * @return boolean
	 */
	public boolean establecerMetodoPago(int idCliente, String metodoPago) {
		ListIterator<Pedido> it = registroPedidos.listIterator();

		boolean encontrado = false;
		while (it.hasNext() && encontrado == false) {
			Pedido pedido = it.next();
			if (pedido.getIdCliente() == idCliente) {
				if (!pedido.isFueAbonado()) {
					encontrado = true;
					pedido.setMedioDePago(metodoPago);
				}
			}
		}
		return encontrado;
	}

	/**
	 * Agrega una peticion de compra al pedido de un cliente, e ingresa dicho
	 * pedidos a la lista de pedidos del sistema
	 * 
	 * @see #agregarPedido(Pedido)
	 * @param Pedido
	 * @param PeticionCompra
	 * @return String
	 */
	public String agregarPeticionToNuevoPedido(Pedido pedido, PeticionCompra peticion) {
		pedido.agregarPeticionCompra(peticion);
		String mensaje = agregarPedido(pedido);
		return mensaje;
	}

	/**
	 * Agrega un nuevo pedido al cliente, no debe tener un pedido impago para poder
	 * generar uno nuevo
	 * 
	 * @see #buscarPedidoImpago(int)
	 * @param pedido
	 * @return String
	 */
	private String agregarPedido(Pedido pedido) {
		String mensaje = null;
		boolean encontrado = buscarPedidoImpago(pedido.getIdCliente());

		if (encontrado == true) {
			mensaje = "Error, este cliente ya posee actualmente un pedido impago";
		} else {
			registroPedidos.add(pedido);
			mensaje = "Pedido ingresado con exito";
		}

		return mensaje;
	}

	/**
	 * Agrega al carrito del carrito, la nueva peticion de compra
	 * 
	 * @param int
	 * @param PeticionCompra
	 */
	public void ingresarACarrito(int idCliente, PeticionCompra peticion) {

		boolean encontrado = false;
		for (int i = 0; i < registroPedidos.size() && encontrado == false; i++) {
			Pedido pedido = registroPedidos.get(i);
			if (pedido.getIdCliente() == idCliente) {
				if (!pedido.isFueAbonado()) {
					pedido.agregarPeticionCompra(peticion);
					registroPedidos.set(i, pedido); // pedido con un producto mas en su carrito
					encontrado = true;
				}
			}
		}
	}

	/**
	 * Establece como pago el pedido actual del cliente, primero es necesario buscar
	 * al cliente
	 * 
	 * @see #BuscaCliente
	 * @param cliente
	 * @return String
	 */
	public String pagarPedido(Cliente cliente) {
		int flag = 0;
		boolean pedidoI = buscarPedidoImpago(cliente.getId());
		String mensaje = "El cliente ingresado no tiene ningun pedido impago";

		if (pedidoI == true) {
			for (int j = 0; j < registroPedidos.size() && flag == 0; j++) {
				if (registroPedidos.get(j).getIdCliente() == cliente.getId()
						&& registroPedidos.get(j).isFueAbonado() == false) {
					registroPedidos.get(j).setDescuento(registroPedidos.get(j).getMedioDePago());
					registroPedidos.get(j).setTotalBruto();
					registroPedidos.get(j).setTotalNeto();
					registroPedidos.get(j).setFueAbonado(true);
					flag = 1;
					mensaje = "Pedido abonado con exito, muchas gracias por su compra";
				}
			}
		}
		return mensaje;
	}

	/**
	 * Establece el total neto y total bruto del pedido y los muestra por pantalla
	 * 
	 * @param cliente
	 * @return String
	 */
	public String detallesFinalesPedido(Cliente cliente) {
		int flag = 0;
		double totalNeto = 0;
		double totalBruto = 0;

		StringBuilder builder = new StringBuilder();

		for (int j = 0; j < registroPedidos.size() && flag == 0; j++) {
			if (registroPedidos.get(j).getIdCliente() == cliente.getId()
					&& registroPedidos.get(j).isFueAbonado() == false) {

				for (int i = 0; i < registroPedidos.get(j).getCarrito().size(); i++) {
					double valorItem = registroPedidos.get(j).getCarrito().get(i).getPrecioUnitario()
							* registroPedidos.get(j).getCarrito().get(i).getCantidad();
					totalBruto = valorItem + totalBruto;
				}

				registroPedidos.get(j).setDescuento(registroPedidos.get(j).getMedioDePago());
				totalNeto = totalBruto * registroPedidos.get(j).getDescuento();
				builder.append("Total Bruto: " + totalBruto + "\n" + "Total Neto: " + totalNeto + "\n"
						+ "Medio de Pago: " + registroPedidos.get(j).getMedioDePago());

				flag = 1;
			}
		}

		return builder.toString();
	}

	/**
	 * Muestra si el cliente tiene actualmente un pedido impago
	 * 
	 * @see #buscarPedidoImpago(int)
	 * @see BuscaCliente
	 * @param cliente
	 * @return String
	 */
	public String mostrarPedidoImpagoCliente(Cliente cliente) {
		StringBuilder sb = new StringBuilder();
		int flag = 0;
		boolean pedidoImp = buscarPedidoImpago(cliente.getId());

		if (pedidoImp == true) {
			for (int j = 0; j < registroPedidos.size() && flag == 0; j++) {
				if (registroPedidos.get(j).getIdCliente() == cliente.getId()) {
					if (!registroPedidos.get(j).isFueAbonado()) {
						for (int i = 0; i < registroPedidos.get(j).getCarrito().size(); i++) {
							sb.append(registroPedidos.get(j).getCarrito().get(i).toString() + "\n");
						}
						flag = 1;
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Valida que el carrito del pedido impago del cliente no este vacio, de lo
	 * contrario retorna false
	 * 
	 * @param int
	 * @return boolean
	 */
	public boolean verificarCarritoVacio(int idcliente) {
		boolean verificar = false;
		for (int i = 0; i < registroPedidos.size() && !verificar; i++) {
			if (registroPedidos.get(i).getIdCliente() == idcliente) {
				if (!registroPedidos.get(i).isFueAbonado()) {
					if (registroPedidos.get(i).getCarrito().isEmpty()) {
						verificar = true;
					}
				}
			}
		}
		return verificar;
	}

	/**
	 * Muestra el historial de pedidos del cliente
	 * 
	 * @see #BuscaCliente
	 * @param cliente
	 * @return String
	 */
	public String mostrarHistorialPedidosCliente(Cliente cliente) {
		StringBuilder sb = new StringBuilder();

		for (int j = 0; j < registroPedidos.size(); j++) {
			if (registroPedidos.get(j).getIdCliente() == cliente.getId()) {
				sb.append("\n"+"PEDIDO " + j + ":" + "\n");
				sb.append("Abonado: " + registroPedidos.get(j).isFueAbonado() + "\n");
				sb.append("Carrito: " + "\n");
				for (int i = 0; i < registroPedidos.get(j).getCarrito().size(); i++) {
					sb.append(registroPedidos.get(j).getCarrito().get(i).toString() + "\n");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Muestra todos los pedidos del cliente
	 * 
	 * @return String
	 */
	public String mostrarTodosLosPedidos() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < registroPedidos.size(); i++) {
			builder.append("\n"+"PEDIDO: "+"\n");
			builder.append(registroPedidos.get(i).toString() + "\n");
		}
		return builder.toString();
	}

	/**
	 * Muestra todos los pedidos impagos de todos los clientes
	 * 
	 * @return String
	 */
	public String mostrarTodosLosPedidosImpagos() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < registroPedidos.size(); i++) {
			if (!registroPedidos.get(i).isFueAbonado()) {
				builder.append(registroPedidos.get(i).toString() + "\n");
			}
		}
		return builder.toString();
	}

	/**
	 * Elimina una peticion de compra de un pedido impago
	 * 
	 * @see #BuscaCliente
	 * @param String
	 * @param elCliente
	 * @return String
	 */
	public String eliminarPeticionCarrito(String codigoP, Cliente elCliente) {
		String mensaje = "No se encontro ningun pedido actual";
		boolean pedidoImp = false;
		int flag = 0;

		pedidoImp = buscarPedidoImpago(elCliente.getId());
		if (pedidoImp == true) {
			for (int j = 0; j < registroPedidos.size() && flag == 0; j++) {
				if (registroPedidos.get(j).getIdCliente() == elCliente.getId()
						&& registroPedidos.get(j).isFueAbonado() == false) {
					mensaje = "No se encontro ningun elemento en su carrito con el codigo ingresado";
					for (int i = 0; i < registroPedidos.get(j).getCarrito().size() && flag == 0; i++) {
						if (registroPedidos.get(j).getCarrito().get(i).getCodigo().equalsIgnoreCase(codigoP)) {

							registroPedidos.get(j).getCarrito().remove(i);
							mensaje = "Elemento eliminado del carrito con exito";
							flag = 1;
						}
					}
				}
			}

		}
		return mensaje;
	}

	/**
	 * Establece el emplado que gestiona el pago del pedido del cliente
	 * 
	 * @param int
	 * @param int
	 */
	public void setearEmpleado(int idEmpleado, int idCliente) {
		boolean encontrado = false;
		for (int i = 0; i < registroPedidos.size() && !encontrado; i++) {
			if (registroPedidos.get(i).getIdCliente() == idCliente) {
				if (!registroPedidos.get(i).isFueAbonado()) {
					registroPedidos.get(i).setIdEmpleado(idEmpleado);
					encontrado = true;
				}
			}
		}
	}

	/**
	 * Elimina completamente un pedido impago de un cliente
	 * 
	 * @param cliente
	 * @return String
	 */
	public String eliminarPedidoImpagoCliente(Cliente cliente) {
		String mensaje = "";
		boolean encontrado = false;
		for (int i = 0; i < registroPedidos.size() && !encontrado; i++) {
			if (registroPedidos.get(i).getIdCliente() == cliente.getId()) {
				if (!registroPedidos.get(i).isFueAbonado()) {
					registroPedidos.remove(i);
					mensaje = "Pedido eliminado con exito\n";
					encontrado = true;
				}
			}
		}
		return mensaje;
	}

	/**
	 * Retorna la linkedList de Pedido para poder grabarlo en el archivo
	 * 
	 * @return LinkedList<Pedido>
	 */
	public LinkedList<Pedido> getRegistroPedidos() {
		return registroPedidos;
	}

	/**
	 * Recibe los pedidos almacenados en el archivo para poder agregarlos al
	 * LinkedList<Pedido>
	 * 
	 * @param pedido
	 */
	public void agregarPedidoDesdeArchivo(Pedido pedido) {
		registroPedidos.add(pedido);
	}

}
