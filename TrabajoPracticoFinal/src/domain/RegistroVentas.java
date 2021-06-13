package domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

public class RegistroVentas implements Serializable {

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
	public String agregarPeticionToNuevoPedido(Pedido pedido, PeticionCompra peticion) {
		pedido.agregarPeticionCompra(peticion);
		String mensaje=agregarPedido(pedido);
		return mensaje;
	}

	/*
	 * agrega un pedido a la lista de pedidos (verificando que no se ingrese un nuevo pedido a un cliente con un pedido impago actual)
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

	//EL MEDIO DE PAGO HAY QUE ENVIARSELO A ESTE METODO, ESTABLECERLO ANTES DE SETEAR EL DESCUENTO?
	
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
					//SET MEDIO DE PAGO ??
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

			if (pedidoImp == true) 
			{
				for (int j = 0; j < registroPedidos.size() && flag == 0; j++) 
			    {
					if (registroPedidos.get(j).getIdCliente() == cliente.getId()) 
					{
						if(!registroPedidos.get(j).isFueAbonado())
						{
							for (int i = 0; i < registroPedidos.get(j).getCarrito().size(); i++) 
							{
								sb.append(registroPedidos.get(j).getCarrito().get(i).toString()+"\n");
							}
							flag = 1;
						}
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
					sb.append("PEDIDO N°"+j+":"+"\n");
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


        //getter para poder escribir los datos en el archivo 
		public LinkedList<Pedido> getRegistroPedidos() {
			return registroPedidos;
		}
		
		//metodo para agregar todos los pedidos desde el archivo a la lista de pedidos
		public void agregarPedidoDesdeArchivo(Pedido pedido)
		{
			registroPedidos.add(pedido);
		}

}

