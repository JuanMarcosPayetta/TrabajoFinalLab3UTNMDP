package domain;

import java.util.LinkedList;
import java.util.ListIterator;

public class RegistroVentas {

	private LinkedList<Pedido> registroPedidos;
    
    public RegistroVentas() 
    {
    	this.registroPedidos=new LinkedList<Pedido>();
    }
    

    
    /*
	 * busca un pedido impago
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

	/*
	 * agrega una peticion de compra a un nuevo pedido, y agrega este pedido a la
	 * lista de pedidos
	 */
	public void agregarPeticionToNuevoPedido(Pedido pedido, PeticionCompra peticion) {
		pedido.agregarPeticionCompra(peticion);
		agregarPedido(pedido);
	}

	/*
	 * agrega un pedido a la lista de pedidos
	 */
	private String agregarPedido(Pedido pedido) {
		String mensaje = null;
		ListIterator<Pedido> it = registroPedidos.listIterator();
		boolean encontrado = false;
		while (it.hasNext() && encontrado == false) {
			Pedido pedidoBuscado = it.next();
			if (pedidoBuscado.getIdCliente() == pedido.getIdCliente()) {
				if (!pedidoBuscado.isFueAbonado()) {
					encontrado = true;
				}
			}
		}
		if (encontrado == true) {
			mensaje = "Error, este cliente ya posee actualmente un pedido impago";
		} else {
			registroPedidos.add(pedido);
			mensaje = "Pedido ingresado con exito";
		}

		return mensaje;
	}

	/*
	 * ingresa una peticion al carrito de un pedido existente
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

	// primero tiene q llamar al metodo BuscaCliente y despues, si existe el cliente
	// puede usar el metodo
	public String pagarPedido(Cliente cliente) {
		int flag = 0;
		boolean pedidoI = buscarPedidoImpago(cliente.getId());
		String mensaje="El cliente ingresado no tiene ningun pedido impago";

		if (pedidoI == true) {
			for (int j = 0; j < registroPedidos.size() && flag == 0; j++) {
				if (registroPedidos.get(j).getIdCliente() == cliente.getId() 
						&& registroPedidos.get(j).isFueAbonado() == false) 
				{
					registroPedidos.get(j).setDescuento(registroPedidos.get(j).getMedioDePago());
					registroPedidos.get(j).setTotalBruto();
					registroPedidos.get(j).setTotalNeto();
					registroPedidos.get(j).setFueAbonado(true);
					flag = 1;
					mensaje="Pedido abonado con exito";
				}
			}
		}
		return mensaje;
	}
	
	// primero tiene q llamar al metodo BuscaCliente y despues, si existe el cliente
		// puede usar el metodo
		// muestra pedido actual del cliente
		public String mostrarPedidoImpagoCliente(Cliente cliente) {
			StringBuilder sb = new StringBuilder();
			int flag = 0;
			boolean pedidoImp = buscarPedidoImpago(cliente.getId());

			if (pedidoImp == true) {
				for (int j = 0; j < registroPedidos.size() && flag == 0; j++) {
					if (registroPedidos.get(j).getIdCliente() == cliente.getId()) {
						for (int i = 0; i < registroPedidos.get(j).getCarrito().size(); i++) {
							sb.append(registroPedidos.get(j).getCarrito().get(i).toString());
						}
						flag = 1;
					}
				}
			}
			return sb.toString();
		}
		
		
		// primero tiene q llamar al metodo BuscaCliente y despues, si existe el cliente
		// puede usar el metodo
		public String mostrarHistorialPedidosCliente(Cliente cliente) {
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < registroPedidos.size(); j++) {
				if (registroPedidos.get(j).getIdCliente() == cliente.getId()) {
					for (int i = 0; i < registroPedidos.get(j).getCarrito().size(); i++) {
						sb.append(registroPedidos.get(j).getCarrito().get(i).toString() + "\n");
					}
				}
			}
			return sb.toString();
		}
		
		// primero tiene q llamar al metodo BuscaCliente (esta en la clase vivero) y despues, si existe el cliente
		// puede usar el metodo
		// para eliminar servicios y productor del carrito
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

}
