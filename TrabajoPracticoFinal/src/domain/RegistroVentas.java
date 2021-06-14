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
		 * busca y retorna un pedido impago
		 */
		public Pedido retornarPedidoImpago(int idCliente) {
			ListIterator<Pedido> it = registroPedidos.listIterator();
			Pedido pedido=null;
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
	
	
	/*
	 * busca un pedido impago
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
					mensaje="Pedido abonado con exito, muchas gracias por su compra";
				}
			}
		}
		return mensaje;
	}
	
	//Visualizacion totales pedido antes de pagar (no setea, solo muestra)
	public String detallesFinalesPedido(Cliente cliente) {
		int flag = 0;
		double totalNeto=0;
		double totalBruto=0;
		
		StringBuilder builder= new StringBuilder();
		
			for (int j = 0; j < registroPedidos.size() && flag == 0; j++) 
			{
				if (registroPedidos.get(j).getIdCliente() == cliente.getId() 
						&& registroPedidos.get(j).isFueAbonado() == false) 
				{
					
					for(int i=0; i<registroPedidos.get(j).getCarrito().size(); i++)
					{
						double valorItem = registroPedidos.get(j).getCarrito().get(i).getPrecioUnitario() * registroPedidos.get(j).getCarrito().get(i).getCantidad();
						totalBruto = valorItem + totalBruto;
					}
					
					registroPedidos.get(j).setDescuento(registroPedidos.get(j).getMedioDePago());
					totalNeto=totalBruto*registroPedidos.get(j).getDescuento();
                    builder.append("Total Bruto: "+totalBruto+"\n"+"Total Neto: "+totalNeto+"\n"+"Medio de Pago: "+registroPedidos.get(j).getMedioDePago());

					flag = 1;
				}
			}

		return builder.toString();
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
		
		public String mostrarTodosLosPedidos()
		{
			StringBuilder builder= new StringBuilder();
			for(int i=0; i<registroPedidos.size(); i++)
			{
				builder.append(registroPedidos.get(i).toString()+"\n");
			}
			return builder.toString();
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

         public void setearEmpleado(int idEmpleado, int idCliente)
         {
        	 boolean encontrado=false;
        	 for(int i=0; i<registroPedidos.size() && !encontrado; i++)
        	 {
        		 if(registroPedidos.get(i).getIdCliente()==idCliente)
        		 {
        			 if(!registroPedidos.get(i).isFueAbonado())
        			 {
        				 registroPedidos.get(i).setIdEmpleado(idEmpleado);
        				 encontrado=true;
        			 }
        		 }
        	 }
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

