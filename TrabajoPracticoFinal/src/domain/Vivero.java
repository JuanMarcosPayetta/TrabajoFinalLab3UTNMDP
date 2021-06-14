package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


import excepciones.DatoNumeroException;
import interfaces.IVivero;
import productos.Producto;

public class Vivero implements IVivero {

	/*
	 * EL MAPA DE PRODUCTOS COMO CLAVE TIENE LA CLASIFICACION
	 * ("ARBOL, ARBUSTO, ETC") Y DENTRO COMO VALOR TIENE UN ARRAY LIST CON TODOS LOS
	 * ARBOLES, ARBUSTOS, ETC
	 */

	private HashMap<String, ArrayList<Producto>> catalogoProductos;
	private HashMap<String, Servicio> catalogoServicios;
	private HashSet<Cliente> listaClientes;
	private HashSet<Empleado> listaEmpleados;
	private RegistroVentas reg;


	public Vivero() {
		this.catalogoProductos = new HashMap<String, ArrayList<Producto>>();
		this.catalogoServicios = new HashMap<String, Servicio>();
		this.listaClientes = new HashSet<Cliente>();
		this.listaEmpleados = new HashSet<Empleado>();
		this.reg=new RegistroVentas();
	}

	public ArrayList<String> obtenerCodigosProducto() {
		ArrayList<Producto> productos = null;
		ArrayList<String> codigos = new ArrayList<String>();
		String codigoEncontrado = null;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<Producto>> entry = (Map.Entry<String, ArrayList<Producto>>) it.next();
			productos = entry.getValue(); // entry me devuelve tanto la clave como el valor, obtengo el arreglo en ese
											// nivel

			for (int i = 0; i < productos.size(); i++) { // recorro el arreglo obtenido y obtengo los codigos
				codigoEncontrado = productos.get(i).getCodigo();
				codigos.add(codigoEncontrado); // y guardo los codigos en un arreglo
			}
		}

		return codigos;
	}

	public ArrayList<String> obtenerCodigosServicio() {

		Servicio servicio = new Servicio();
		ArrayList<String> codigos = new ArrayList<String>();
		String codigoEncontrado = null;
		Iterator<Map.Entry<String, Servicio>> it = catalogoServicios.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Servicio> entry = (Map.Entry<String, Servicio>) it.next();
			servicio = entry.getValue();
			codigoEncontrado = servicio.getCodigo();
			codigos.add(codigoEncontrado);
		}

		return codigos;
	}

	@Override
	public <T> void agregarElemento(T elemento) {
		if (elemento instanceof Producto) {
			Producto unProducto = (Producto) elemento;
			if (!existeProducto(unProducto.getClasificacion())) {
				ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				listaProductos.add(unProducto);
				catalogoProductos.put(unProducto.getClasificacion(), listaProductos);
			} else {
				ArrayList<Producto> listaProductos = catalogoProductos.get(unProducto.getClasificacion());
				listaProductos.add(unProducto);
				catalogoProductos.put(unProducto.getClasificacion(), listaProductos);
			}
		} else if (elemento instanceof Servicio) {
			Servicio unservicio = (Servicio) elemento;

			if (!existeServicio(unservicio.getCodigo())) {

				catalogoServicios.put(unservicio.getCodigo(), unservicio);
			}
		} else if (elemento instanceof Empleado) {
			Empleado unEmpleado = (Empleado) elemento;
			listaEmpleados.add(unEmpleado);
		} else if (elemento instanceof Cliente) {
			Cliente unCliente = (Cliente) elemento;
			listaClientes.add(unCliente);
		}
	}

	private boolean existeProducto(String clasificacion) {
		return catalogoProductos.containsKey(clasificacion);
	}

	public boolean existeServicio(String codigo) {

		return catalogoServicios.containsKey(codigo); // si existe te devuelve true
	}

	@Override
	public <T> String buscarElemento(T elemento) { // producto,servicio,empleado, cliente

		String mensaje = "Elemento no encontrado";
		int flag = 0;

		if (elemento instanceof Producto) // es producto (RECIBO ELEMENTO CON LA CLASIFICACION Y EL CODIGO)
		{
			Producto unProducto = (Producto) elemento; // casteo

			Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator(); // iterador

			while (it.hasNext() && flag == 0) {
				Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();

				if (entrada.getKey().equalsIgnoreCase(unProducto.getClasificacion())) // si se encuentra la
																						// clasificacion
				{
					ArrayList<Producto> arreglo = entrada.getValue();

					for (int i = 0; i < arreglo.size() && flag == 0; i++) {
						if (arreglo.get(i).equals(unProducto)) {
							mensaje = arreglo.get(i).toString();
							flag = 1;
						}
					}
				}
			}
		} else if (elemento instanceof Servicio) // es un servicio
		{

			Servicio unServicio = (Servicio) elemento;

			Iterator<Map.Entry<String, Servicio>> it = catalogoServicios.entrySet().iterator();

			while (it.hasNext() && flag == 0) {
				Map.Entry<String, Servicio> entrada = (Map.Entry<String, Servicio>) it.next();

				if (entrada.getValue().equals(unServicio)) { // si existe el codigo
					mensaje = entrada.toString();
					flag = 1;
				}
			}

		}

		else if (elemento instanceof Cliente) {
			Cliente unCliente = (Cliente) elemento;

			Iterator<Cliente> it = listaClientes.iterator();

			while (it.hasNext() && flag == 0) {
				if (it.next().equals(unCliente)) {
					mensaje = it.toString();
					flag = 1;
				}
			}
		}

		else if (elemento instanceof Empleado) {
			Empleado unEmpleado = (Empleado) elemento;

			Iterator<Empleado> it = listaEmpleados.iterator();

			while (it.hasNext() && flag == 0) {
				if (it.next().equals(unEmpleado)) {
					mensaje = it.toString();
					flag = 1;
				}
			}
		}

		return mensaje;
	}

	@Override
	public <T> String eliminarElemento(T elemento) {

		String mensaje = "Elemento no encontrado";
		boolean validacion = false;

		if (elemento instanceof Producto) {
			Producto unProducto = (Producto) elemento;
			Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

			while (it.hasNext() && validacion == false) {
				Map.Entry<String, ArrayList<Producto>> entry = (Map.Entry<String, ArrayList<Producto>>) it.next();
				if (entry.getKey().equalsIgnoreCase(unProducto.getClasificacion())) {
					ArrayList<Producto> productos = entry.getValue();
					for (int i = 0; i < productos.size() && validacion == false; i++) {
						if (productos.get(i).equals(unProducto)) {
							productos.remove(i);
							validacion = true;
							mensaje = "Producto eliminado con exito";
							catalogoProductos.replace(unProducto.getClasificacion(), productos);
						}
					}
				}
			}
		} else if (elemento instanceof Servicio) {
			Servicio unServicio = (Servicio) elemento;
			Iterator<Map.Entry<String, Servicio>> it = catalogoServicios.entrySet().iterator();
			while (it.hasNext() && validacion == false) {
				Map.Entry<String, Servicio> entry = (Map.Entry<String, Servicio>) it.next();
				if (entry.getValue().equals(unServicio)) {
					it.remove();
					validacion = true;
					mensaje = "Servicio eliminado con exito";
				}
			}
		} else if (elemento instanceof Cliente) {
			Cliente unCliente = (Cliente) elemento;
			Iterator<Cliente> it = listaClientes.iterator();
			while (it.hasNext() && validacion == false) {
				Cliente otroCliente = it.next();
				if (otroCliente.equals(unCliente)) {
					it.remove();
					validacion = true;
					mensaje = "Cliente eliminado con exito";
				}
			}
		} else if (elemento instanceof Empleado) {
			Empleado unEmpleado = (Empleado) elemento;
			Iterator<Empleado> it = listaEmpleados.iterator();
			while (it.hasNext() && validacion == false) {
				Empleado otroEmpleado = it.next();
				if (otroEmpleado.equals(unEmpleado)) {
					it.remove();
					validacion = true;
					mensaje = "Empleado eliminado con exito";
				}
			}
		}

		return mensaje;
	}

	public String mostrarProductos() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			ArrayList<Producto> arreglo = entrada.getValue();

			mensaje.append(entrada.getKey());
			for (int i = 0; i < arreglo.size(); i++) {
				mensaje.append(" [" + arreglo.get(i).toString() + " ] \n");
			}
		}
		return mensaje.toString();
	}

	public String mostrarServicios() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Map.Entry<String, Servicio>> it = catalogoServicios.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, Servicio> entrada = (Map.Entry<String, Servicio>) it.next();

			mensaje.append("[ " + entrada.getValue().toString() + " ]");
		}

		return mensaje.toString();
	}

	public String mostrarEmpleados() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Empleado> it = listaEmpleados.iterator();

		while (it.hasNext()) {
			mensaje.append("[ " + it.next().toString() + " ]");
		}

		return mensaje.toString();
	}

	public String mostrarClientes() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Cliente> it = listaClientes.iterator();

		while (it.hasNext()) {
			mensaje.append("[ " + it.next().toString() + " ]");
		}

		return mensaje.toString();
	}

	public String moficarPrecioProducto(Producto elemento, int precio) {
		String mensaje = "Producto no Encontrado.";
		int flag = 0;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext() && flag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			if (entrada.getKey().equalsIgnoreCase(elemento.getClasificacion())) {
				ArrayList<Producto> arreglo = entrada.getValue();

				for (int i = 0; flag == 0 && i < arreglo.size(); i++) {
					if (arreglo.get(i).equals(elemento)) {
						mensaje = arreglo.get(i).setPrecio(precio);
						if (mensaje == null)
						// si la exepcion del set no devuelve error
						{
							catalogoProductos.replace(elemento.getClasificacion(), arreglo);
							flag = 1;
							mensaje = "Precio modificado";
						}
					}
				}
			}
		}
		return mensaje;
	}
	
	/*
	 * valida que exista el empleado registrado
	 */
	public boolean buscarEmpleadoRegistrado(String pass, int id)
	{
		boolean validacion=false;
		Iterator<Empleado>it= listaEmpleados.iterator();
		while(it.hasNext() && !validacion)
		{
			Empleado empleado=it.next();
			if(empleado.getID()==id)
			{
				if(empleado.getContrasenia().equals(pass))
				{
					validacion=true;
				}
			}
		}
		return validacion;
	}

	public String modificarStockDisminuye(Producto elProducto, int stock) {
		String mensaje = "Producto no encontrado";
		int flag = 0;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext() && flag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			if (entrada.getKey().equalsIgnoreCase(elProducto.getClasificacion())) {
				ArrayList<Producto> arreglo = entrada.getValue();
				for (int i = 0; flag == 0 && i < arreglo.size(); i++) {
					if (arreglo.get(i).equals(elProducto)) {
						mensaje = arreglo.get(i).disminuitStock(stock);
						if (mensaje == null)// si la exepcion no devuelve error
						{
							catalogoProductos.replace(elProducto.getClasificacion(), arreglo);
							flag = 1;
							mensaje = "Nuevo Stock Establecido";
						}
					}
				}
			}
		}

		return mensaje;
	}

	public String modificarStockAumenta(Producto elProducto, int stock) {
		String mensaje = "Producto no encontrado";
		int flag = 0;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext() && flag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			if (entrada.getKey().equalsIgnoreCase(elProducto.getClasificacion())) {
				ArrayList<Producto> arreglo = entrada.getValue();
				for (int i = 0; flag == 0 && i < arreglo.size(); i++) {
					if (arreglo.get(i).equals(elProducto)) {
						mensaje = arreglo.get(i).aumentarStock(stock);
						if (mensaje == null)// si la exepcion no devuelve error
						{
							catalogoProductos.replace(elProducto.getClasificacion(), arreglo);
							flag = 1;
							mensaje = "Nuevo Stock Establecido";
						}
					}
				}
			}
		}

		return mensaje;
	}

	public String modificarNombreProdcuto(Producto elProducto, String nombre) {
		String mensaje = "Producto no encontrado";
		int flag = 0;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext() && flag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			if (entrada.getKey().equalsIgnoreCase(elProducto.getClasificacion())) {
				ArrayList<Producto> arreglo = entrada.getValue();
				for (int i = 0; flag == 0 && i < arreglo.size(); i++) {
					if (arreglo.get(i).equals(elProducto)) {
						mensaje = arreglo.get(i).setNombre(nombre);
						if (mensaje == null)// si la exepcion no devuelve error
						{
							catalogoProductos.replace(elProducto.getClasificacion(), arreglo);
							flag = 1;
							mensaje = "Nombre modificado";
						}
					}
				}
			}
		}

		return mensaje;
	}

	public String modificarMarcaProdcuto(Producto elProducto, String marca) {
		String mensaje = "Producto no encontrado";
		int flag = 0;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext() && flag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			if (entrada.getKey().equalsIgnoreCase(elProducto.getClasificacion())) {
				ArrayList<Producto> arreglo = entrada.getValue();
				for (int i = 0; flag == 0 && i < arreglo.size(); i++) {
					if (arreglo.get(i).equals(elProducto)) {
						mensaje = arreglo.get(i).setMarca(marca);
						if (mensaje == null)// si la exepcion no devuelve error
						{
							catalogoProductos.replace(elProducto.getClasificacion(), arreglo);
							flag = 1;
							mensaje = "Marca modificada";
						}
					}
				}
			}
		}

		return mensaje;
	}

	//Muestra el nombre, clasificacion, stock, precio y codigo del producto (para el momento de elegir el codigo del producto al comprar)
	public String mostrarProductoResumido()
	{
		StringBuilder builder= new StringBuilder();
		Iterator<Map.Entry<String, ArrayList<Producto>>>it= catalogoProductos.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<String, ArrayList<Producto>>entry= (Map.Entry<String, ArrayList<Producto>>)it.next();
			ArrayList<Producto> productos=entry.getValue();
			for(int i=0; i<productos.size(); i++)
			{
				builder.append("Nombre producto: "+ productos.get(i).getNombre()+" // Clasifiacion: "+productos.get(i).getClasificacion()+" // Precio: "+productos.get(i).getPrecio()+" // Stock actual: "+productos.get(i).getStock()+" // Codigo: "+productos.get(i).getCodigo()+"\n");
			}
		}
		
		return builder.toString();
	}
	
	
	//Muestra el nombre, stock, precio y codigo del servicio (para el momento de elegir el codigo del producto al comprar)
		public String mostrarServicioResumido()
		{
			StringBuilder builder= new StringBuilder();
			Iterator<Map.Entry<String, Servicio>>it= catalogoServicios.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, Servicio>entry= (Map.Entry<String, Servicio>)it.next();
				Servicio servicio=entry.getValue();
				
				builder.append(servicio.getNombre()+" - "+servicio.getPrecio()+" - "+servicio.getCodigo()+"\n");
			}
			return builder.toString();
		}
	
		
	//busca y retorna la "clasifiacion" del producto que sea desea comprar
	public String buscarClasificacionProducto(String codigoProducto)
	{
		boolean encontrado=false;
		String clasificacion=null;
		Iterator<Map.Entry<String, ArrayList<Producto>>>it= catalogoProductos.entrySet().iterator();
		while(it.hasNext() && !encontrado)
		{
			Map.Entry<String, ArrayList<Producto>>entry= (Map.Entry<String, ArrayList<Producto>>)it.next();
			ArrayList<Producto>productos= entry.getValue();
			for(int i=0; i<productos.size() && !encontrado; i++)
			{
				if(productos.get(i).getCodigo().equals(codigoProducto))
				{
					clasificacion=productos.get(i).getClasificacion();
					encontrado=true;
				}
			}
		}
		return clasificacion;
	}
	

	
	public Cliente BuscaCliente(String dni) { // si retorna null "Cliente no encontrado"...desea crear unuevo?(en main)
		int flag = 0;
		Cliente elCliente = null;
		Cliente encontrado=null;

		Iterator<Cliente> itt = listaClientes.iterator();

		while (itt.hasNext() && flag == 0) {
			elCliente = itt.next(); // guardo un cliente y debajo compruebo que sea el que busco
			if (elCliente.getDni().equals(dni)) {
				flag = 1;
				encontrado=elCliente;
			}
		}
		return encontrado;
	}

	
	// primero tiene q llamar al metodo BuscaCliente y despues, si existe el cliente
	// puede usar el metodo
	public String ComprarProducto(Producto elProducto, Cliente elCliente, int cantidadP) {

		int otroFlag = 0;
		String mensaje = "ERROR en la compra";
		Producto productoComprado = null;

		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator(); // iterador

		while (it.hasNext() && otroFlag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();

			if (entrada.getKey().equalsIgnoreCase(elProducto.getClasificacion())) // si se encuentra la
																					// clasificacion
			{
				ArrayList<Producto> arreglo = entrada.getValue();

				for (int i = 0; i < arreglo.size() && otroFlag == 0; i++) {
					if (arreglo.get(i).equals(elProducto)) {
						otroFlag = 1;
						productoComprado = arreglo.get(i);
						if (productoComprado.ComprobarStock(cantidadP)) {

							PeticionCompra petiCompra = new PeticionCompra(productoComprado.getCodigo(),
									productoComprado.getPrecio(), cantidadP);

							if (reg.buscarPedidoImpago(elCliente.getId())) // busco si hay un pedido abierto
							{
								reg.ingresarACarrito(elCliente.getId(), petiCompra);
							}

							else // si no hay un pedido abierto
							{
								Pedido miPedido = new Pedido(elCliente.getId());
								reg.agregarPeticionToNuevoPedido(miPedido, petiCompra);
							}

							arreglo.get(i).disminuitStock(cantidadP); // resto stock
							mensaje = "Producto agregado al carrito con exito";

						}

						else {
							mensaje = "Stock insuficiente, actualmente hay " + arreglo.get(i).getStock() + " unidades";
						}
					}
				}

				if (otroFlag == 0) {
					mensaje = "No existe un producto con ese codigo";
				}

			} else {
				mensaje = "Clasificacion incorrecta";
			}
		}
		return mensaje;
	}



	// primero tiene q llamar al metodo BuscaCliente y despues, si existe el cliente
	// puede usar el metodo
	public String ComprarServicio(String codigoServicio, Cliente elCliente) {

		int otroFlag = 0;
		String mensaje = "ERROR en la compra";
		Servicio servicio = null;

		Iterator<Map.Entry<String, Servicio>> it = catalogoServicios.entrySet().iterator(); // iterador

		while (it.hasNext() && otroFlag == 0) {
			Map.Entry<String, Servicio> entrada = (Map.Entry<String, Servicio>) it.next();

			if (entrada.getKey().equalsIgnoreCase(codigoServicio)) // si se encuentra el codigo
			{
				servicio = entrada.getValue();
				PeticionCompra petiCompra = new PeticionCompra(servicio.getCodigo(), servicio.getPrecio(), 1);

				if (reg.buscarPedidoImpago(elCliente.getId())) // busco si hay un pedido abierto
				{
					reg.ingresarACarrito(elCliente.getId(), petiCompra);
				}

				else // si no hay un pedido abierto
				{
					Pedido miPedido = new Pedido(elCliente.getId());
					reg.agregarPeticionToNuevoPedido(miPedido, petiCompra);
				}
				otroFlag = 1;
				mensaje = "Servicio agregado al carrito con exito";
			}
		}
		if (otroFlag == 0) {
			mensaje = "No existe un servicio con ese codigo";
		}

		return mensaje;
	}

	
	public String pedirCantidadCompra(int cantidad) {
		String mensaje = null;
		try {
			cantidadCompra(cantidad);
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	private void cantidadCompra(int cantidad) throws DatoNumeroException {
		if (cantidad <= 0) {
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION + ", ni menor a 1");
		}
	}
	
	public String mostrarTodosLosPedidos()
	{
		return reg.mostrarTodosLosPedidos();
	}
	
	public String abonarPedido(Cliente cliente)
	{
		return reg.pagarPedido(cliente);
	}
	
	public boolean establecerMedioPago(int idCliente, String medioPago)
	{
		return reg.establecerMetodoPago(idCliente, medioPago);
	}
	
	public boolean buscarPedidoImpago(int idCliente)
	{
		return reg.buscarPedidoImpago(idCliente);
	}
	
	public Pedido retornarPedidoImpago(int idCliente)
	{
		return reg.retornarPedidoImpago(idCliente);
	}
	
	public String mostrarPedidoImpago(Cliente cliente)
	{
		return reg.mostrarPedidoImpagoCliente(cliente);
		
	}
	
	public String mostrarHistorialPedidosCliente(Cliente cliente)
	{
		return reg.mostrarHistorialPedidosCliente(cliente);
		
	}
	
	public boolean verificarCarritoNoVacio(int idCliente)
	{
		return reg.verificarCarritoVacio(idCliente);
	}
	
	public String mostrarTodosLosPedidosImpagos()
	{
		return reg.mostrarTodosLosPedidosImpagos();
	}
	
	public String visualizarTotalesPedido(Cliente cliente)
	{
		return reg.detallesFinalesPedido(cliente);
	}
	
	public void setearEmpleado(int idEmpleado, int idCliente)
	{
		reg.setearEmpleado(idEmpleado, idCliente); 
	}
	
	public String eliminarPeticionCarrito(String codigoP, Cliente cliente)
	{
       return reg.eliminarPeticionCarrito(codigoP, cliente);	
	}
	
	public String eliminarPedidoImpagoCliente(Cliente cliente)
	{
		return reg.eliminarPedidoImpagoCliente(cliente);
	}
	
	
	/*
	 * GETTERS PARA ACCEDER A LAS COLECCIONES Y GRABARLAS/LEER ARCHIVOS
	 */
	public HashMap<String, ArrayList<Producto>> getCatalogoProductos() {
		return catalogoProductos;
	}

	public HashMap<String, Servicio> getCatalogoServicios() {
		return catalogoServicios;
	}

	public HashSet<Cliente> getListaClientes() {
		return listaClientes;
	}

	public HashSet<Empleado> getListaEmpleados() {
		return listaEmpleados;
	} 
	
	public LinkedList<Pedido> getListaPedidos()
	{
		LinkedList<Pedido>listaPedidos=null;
		listaPedidos=reg.getRegistroPedidos();
		return listaPedidos;
	}

	public void agregarPedidoDesdeArchivo(Pedido pedido)
	{
		reg.agregarPedidoDesdeArchivo(pedido);
	}

}
