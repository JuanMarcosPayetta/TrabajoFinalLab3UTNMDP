package domain;

import java.util.LinkedList;
import java.util.ListIterator;

public class RegistroVentas {

	private LinkedList<Pedido> registroPedidos;
	
	public RegistroVentas() {
		this.registroPedidos = new LinkedList<Pedido>();
	}
	
	/*
	 * busca un pedido impago
	 */
	public boolean buscarPedidoImpago(int idCliente)
	{
		ListIterator<Pedido> it= registroPedidos.listIterator();
		boolean encontrado=false;
		while(it.hasNext() && encontrado==false)
		{
			Pedido pedido=it.next();
			if(pedido.getIdCliente()==idCliente)
			{
				if(!pedido.isFueAbonado())
				{
					encontrado=true;
				}
			}
		}
		
		return encontrado;
	}
	

	/*
	 * agrega una peticion de compra a un nuevo pedido, y agrega este pedido a la lista de pedidos
	 */
	public void agregarPeticionToNuevoPedido(Pedido pedido, PeticionCompra peticion)
	{
		pedido.agregarPeticionCompra(peticion);
		agregarPedido(pedido);
	}
	
	/*
	 * agrega un pedido a la lista de pedidos
	 */
	private String agregarPedido(Pedido pedido)
	{
		String mensaje=null;
		ListIterator<Pedido> it= registroPedidos.listIterator();
		boolean encontrado=false;
		while(it.hasNext() && encontrado==false)
		{
			Pedido pedidoBuscado= it.next();
			if(pedidoBuscado.getIdCliente()==pedido.getIdCliente())
			{
				if(!pedidoBuscado.isFueAbonado())
				{
					encontrado=true;
				}
			}
		}
		
		if(encontrado==true)
		{
			mensaje="Error, este cliente ya posee actualmente un pedido impago";
		}
		else
		{
			registroPedidos.add(pedido);
			mensaje="Pedido ingresado con exito";
		}
		
		return mensaje;
	}
	
	/*
	 * ingresa una peticion al carrito de un pedido existente
	 */
	public void ingresarACarrito(int idCliente, PeticionCompra peticion)
	{
	
		boolean encontrado=false;
	     for(int i=0; i<registroPedidos.size() && encontrado==false; i++)
		{
			Pedido pedido= registroPedidos.get(i);
			if(pedido.getIdCliente()==idCliente)
			{
				if(!pedido.isFueAbonado())
				{
					pedido.agregarPeticionCompra(peticion);
					registroPedidos.set(i, pedido); //pedido con un producto mas en su carrito
					encontrado=true;
				}
			}
		}
	}
	
}
