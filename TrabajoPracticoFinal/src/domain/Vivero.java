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
import servicios.Servicio;
import ventas.RegistroVentas;

public class Vivero implements IVivero {

/**
 * Clase contenedora del sistema
 * Administra los productos, servicios, clientes, empleados del sistema. Invoca a los metodos que gestionan los pedidos.
 * Contiene las colecciones (HashMap, HashSet) que almacenan la informacion del sistema
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

	/**
	 * Retorna los codigos correspondientes a el catalogo de productos
	 * @return ArrayList<String>
	 */
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

	/**
	 * Retorna los codigos correspondientes a el catalogo de servicios
	 * @return ArrayList<String>
	 */
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

	/**
	 * Metodo generico que permite ingresar un nuevo producto, servicio, cliente u empleado al sistema
	 * @param Dato generico 
	 */
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

	/**
	 * Verifica que exista o no una determinada clave en el catalogo de productos
	 * @param String
	 * @return boolean
	 */
	private boolean existeProducto(String clasificacion) {
		return catalogoProductos.containsKey(clasificacion);
	}

	/**
	 * Verifica que exista o no una determinada clave en el catalogo de servicios
	 * @param String
	 * @return boolean
	 */
	public boolean existeServicio(String codigo) {

		return catalogoServicios.containsKey(codigo); // si existe te devuelve true
	}
	
	
    /**
     * Metodo generico que permite buscar un producto, servicio, cliente u empleado en el sistema
     * @param Dato generico
     * @return String
     */
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
				Cliente otroCliente=it.next();
				if (otroCliente.equals(unCliente)) {
					mensaje = otroCliente.toString();
					flag = 1;
				}
			}
		}

		else if (elemento instanceof Empleado) {
			Empleado unEmpleado = (Empleado) elemento;

			Iterator<Empleado> it = listaEmpleados.iterator();

			while (it.hasNext() && flag == 0) {
				Empleado otroEmpleado= it.next();
				if (otroEmpleado.equals(unEmpleado)) {
					mensaje = otroEmpleado.toString();
					flag = 1;
				}
			}
		}

		return mensaje;
	}

	/**
	 * Metodo generico que permite eliminar un producto, servicio, cliente u empleado del sistema
	 * @param Dato generico
	 * @return  String
	 */
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

	
	/**
	 * Muestra por pantalla todos los productos del sistema
	 * @return String
	 */
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

	/**
	 * Muestra por pantalla todos los servicios del sistema
	 * @return String
	 */
	public String mostrarServicios() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Map.Entry<String, Servicio>> it = catalogoServicios.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, Servicio> entrada = (Map.Entry<String, Servicio>) it.next();

			mensaje.append("[ " + entrada.getValue().toString() + " ]");
		}

		return mensaje.toString();
	}

	/**
	 * Muestra por pantalla todos los empleados del sistema
	 * @return String
	 */
	public String mostrarEmpleados() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Empleado> it = listaEmpleados.iterator();

		while (it.hasNext()) {
			Empleado empleado=it.next();
			mensaje.append("[ " + empleado.toString() + " ]");
		}

		return mensaje.toString();
	}

	/**
	 * Muestra por pantalla todos los clientes del sistema
	 * @return String
	 */
	public String mostrarClientes() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Cliente> it = listaClientes.iterator();

		while (it.hasNext()) {
			mensaje.append("[ " + it.next().toString() + " ]");
		}

		return mensaje.toString();
	}

	/**
	 * Permite modificar el precio de un producto
	 * @param Producto
	 * @param double
	 * @return String
	 */
	public String modificarPrecioProducto(Producto elemento, double precio) {
		String mensaje = "Producto no encontrado\n";
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
							mensaje = "Precio modificado con exito\n";
						}
					}
				}
			}
		}
		return mensaje;
	}
	
    /**
     * Verifica que se encuentre o no registrado un determinado empleado, retornando true o false en cada caso
     * @param String
     * @param int
     * @return boolean
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

	/**
	 * Permite reducir el stock actual de un producto del sistema
	 * @param Producto
	 * @param int
	 * @return String
	 */
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

	/**
	 * Permite aumentar el stock actual de un producto del sistema
	 * @param Producto
	 * @param int
	 * @return String
	 */
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
							mensaje = "Nuevo stock establecido con exito\n";
						}
					}
				}
			}
		}

		return mensaje;
	}

	/**
	 * Permite modificar el nombre actual de un producto del sistema
	 * @param Producto
	 * @param String
	 * @return String
	 */
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
							mensaje = "Nombre modificado con exito\n";
						}
					}
				}
			}
		}

		return mensaje;
	}

	/**
	 * Permite modificar la marca actual de un producto del sistema
	 * @param Producto
	 * @param String
	 * @return String
	 */
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
							mensaje = "Marca modificada con exito\n";
						}
					}
				}
			}
		}

		return mensaje;
	}
	
	/**
	 * Permite modificar la descripcion actual de un producto del sistema
	 * @param Producto
	 * @param String
	 * @return String
	 */
	public String modificarDescripcionProdcuto(Producto elProducto, String descripcion) {
		String mensaje = "Producto no encontrado";
		int flag = 0;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();

		while (it.hasNext() && flag == 0) {
			Map.Entry<String, ArrayList<Producto>> entrada = (Map.Entry<String, ArrayList<Producto>>) it.next();
			if (entrada.getKey().equalsIgnoreCase(elProducto.getClasificacion())) {
				ArrayList<Producto> arreglo = entrada.getValue();
				for (int i = 0; flag == 0 && i < arreglo.size(); i++) {
					if (arreglo.get(i).equals(elProducto)) {
						arreglo.get(i).setDescripcion(descripcion);
						catalogoProductos.replace(elProducto.getClasificacion(), arreglo);
						flag = 1;
						mensaje = "Descripcion modificada con exito\n";
					}
				}
			}
		}

		return mensaje;
	}
	
	/**
	 * Permite modificar el nombre actual de un servicio del sistema
	 * @param String
	 * @param String
	 * @return String
	 */
	public String modificarNombreServicio(String codigo, String nombre)
	{
		String mensaje="Servicio no encontrado\n";
		Iterator<Map.Entry<String, Servicio>>it= catalogoServicios.entrySet().iterator();
		boolean encontrado=false;
		while(it.hasNext() && !encontrado)
		{
			Map.Entry<String, Servicio>entry=(Map.Entry<String, Servicio>)it.next();
			if(entry.getKey().equalsIgnoreCase(codigo))
			{
				Servicio servicio=entry.getValue();
				mensaje=servicio.setNombre(nombre);
				if(mensaje==null)
				{
					catalogoServicios.replace(codigo, servicio);
					encontrado=true;
					mensaje="Nombre modificado con exito\n";
				}
			}
		}
		return mensaje;
	}
	
	/**
	 * Permite modificar el precio actual de un servicio del sistema
	 * @param String
	 * @param double
	 * @return String
	 */
	public String modificarPrecioServicio(String codigo, double precio)
	{
		String mensaje="Servicio no encontrado\n";
		Iterator<Map.Entry<String, Servicio>>it= catalogoServicios.entrySet().iterator();
		boolean encontrado=false;
		while(it.hasNext() && !encontrado)
		{
			Map.Entry<String, Servicio>entry=(Map.Entry<String, Servicio>)it.next();
			if(entry.getKey().equalsIgnoreCase(codigo))
			{
				Servicio servicio=entry.getValue();
				mensaje=servicio.setPrecio(precio);
				if(mensaje==null)
				{
					catalogoServicios.replace(codigo, servicio);
					encontrado=true;
					mensaje="Precio modificado con exito\n";
				}
			}
		}
		return mensaje;
	}
	
	

	/**
	 * //Muestra el nombre, clasificacion, stock, precio y codigo de los productos del sistema
	 * @return String
	 */
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
	
	
	    /**
	     * Muestra el nombre, stock, precio y codigo de los servicios del sistema
	     * @return String
	     */
		public String mostrarServicioResumido()
		{
			StringBuilder builder= new StringBuilder();
			Iterator<Map.Entry<String, Servicio>>it= catalogoServicios.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, Servicio>entry= (Map.Entry<String, Servicio>)it.next();
				Servicio servicio=entry.getValue();
				
				builder.append("Nombre servicio: "+servicio.getNombre()+" // Precio: "+servicio.getPrecio()+" // Codigo: "+servicio.getCodigo()+"\n");
			}
			return builder.toString();
		}
	
		
	/**
	 * Busca y retorna la clasifiacion del producto deseado
	 * @param String
	 * @return String
	 */
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
	

	/**
	 * Busca y retorna un cliente, en caso de no encontrarlo retorna NULL
	 * @param String
	 * @return Cliente
	 */
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

	
	/**
	 * Permite adquirir un producto e ingresarlo al pedido del cliente correspondiente
	 * @param Producto
	 * @param Cliente
	 * @param int
	 * @return String
	 */
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




	/**
	 * Permite adquirir un servicio e ingresarlo al pedido del cliente correspondiente
	 * @param String
	 * @param Cliente
	 * @return String
	 */
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

	
	/**
	 * Valida que el dato ingresado sea valido y captura la excepcion en caso de ser preciso 
	 * @see #cantidadCompra(int)
	 * @param int
	 * @return String
	 */
	public String pedirCantidadCompra(int cantidad) {
		String mensaje = null;
		try {
			cantidadCompra(cantidad);
		} catch (DatoNumeroException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Valida que el dato ingresado sea valido, lanzando una excepcion que sera capturada por {@link #pedirCantidadCompra(int)}
	 * @see #pedirCantidadCompra(int)
	 * @param int
	 * @throws DatoNumeroException
	 */
	private void cantidadCompra(int cantidad) throws DatoNumeroException {
		if (cantidad <= 0) {
			throw new DatoNumeroException(DatoNumeroException.VALORNEGATIVOEXCEPTION + ", ni menor a 1");
		}
	}
	
	/**
	 * Busca el mayor ID de un empleado, para generar el siguiente
	 * @return int
	 */
	public int buscarMayorIdEmpleado()
	{
		int mayorId=-1;
		
		Iterator<Empleado>it=listaEmpleados.iterator();
		if(!listaEmpleados.isEmpty()) //si no esta vacia la lista
		{
			Empleado empleado=it.next();
			mayorId=empleado.getID();
			
			while(it.hasNext())
			{
				empleado=it.next();
				if(mayorId<empleado.getID())
				{
					mayorId=empleado.getID();
				}
			}
		}
		
		return mayorId;
	}
	
	/**
	 * Busca el mayor ID de un cliente, para generar el siguiente
	 * @return int
	 */
	public int buscarMayorIdCliente()
	{
		int mayorId=-1;
		
		Iterator<Cliente>it=listaClientes.iterator();
		if(!listaClientes.isEmpty()) //si no esta vacia la lista
		{
			Cliente cliente=it.next();
			mayorId=cliente.getId();
			
			while(it.hasNext())
			{
				cliente=it.next();
				if(mayorId<cliente.getId())
				{
					mayorId=cliente.getId();
				}
			}
		}
		
		return mayorId;
	}
	
	
	/**
	 * Muestra todos las clasifiaciones de productos disponibles en el sistema
	 * @return String
	 */
	public String mostrarClasificacionesProducto()
	{
		StringBuilder builder= new StringBuilder();
	
			Iterator<Map.Entry<String, ArrayList<Producto>>> it= catalogoProductos.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, ArrayList<Producto>> entry= (Map.Entry<String, ArrayList<Producto>>) it.next();
				String clasificacion= entry.getKey();
				builder.append("[ "+clasificacion+" ]"+"\n");
			}
			
		return builder.toString();
	}
	
	
	/**
	 * Muestra todos los productos de una determinada clasificacion
	 * @param String
	 * @return String
	 */
	public String mostrarProductosClasificacion(String clasificacion)
	{
		StringBuilder builder= new StringBuilder();
		boolean encontrado=false;
		if(catalogoProductos.containsKey(clasificacion))
		{
			Iterator<Map.Entry<String, ArrayList<Producto>>> it= catalogoProductos.entrySet().iterator();
			while(it.hasNext() && !encontrado)
			{
				Map.Entry<String, ArrayList<Producto>> entry= (Map.Entry<String, ArrayList<Producto>>) it.next();
				if(entry.getKey().equalsIgnoreCase(clasificacion))
				{
					ArrayList<Producto>listaProductos= entry.getValue();
					for(int i=0; i<listaProductos.size(); i++)
					{
						builder.append("[ "+listaProductos.get(i).toString()+" ]"+"\n");
					}
					
					encontrado=true;
				}
			}
		}
		else
		{
			builder.append("La clasifiacion ingresada no se encuentra en el sistema\n");
		}
		return builder.toString();
	}
	
	//LLAMADAS A METODOS DE REGISTROVENTAS
	
	/**
	 * Invoca al metodo que muestra todos los pedidos existentes en el sistema
	 * @see #mostrarTodosLosPedidos()
	 * @return String
	 */
	public String mostrarTodosLosPedidos()
	{
		return reg.mostrarTodosLosPedidos();
	}
	
	/**
	 * Invoca al metodo que abona un pedido existentes en el sistema
	 * @see #pagarPedido(cliente)
	 * @return String
	 */
	public String abonarPedido(Cliente cliente)
	{
		return reg.pagarPedido(cliente);
	}
	
	/**
	 * Invoca al metodo que establece el metodo de pago de un pedido existentes en el sistema
	 * @see #establecerMetodoPago(idCliente, medioPago)
	 * @return boolean
	 */
	public boolean establecerMedioPago(int idCliente, String medioPago)
	{
		return reg.establecerMetodoPago(idCliente, medioPago);
	}
	
	/**
	 * Invoca al metodo que busca e indica si existe un pedido impago de un cliente
	 * @see #buscarPedidoImpago(idCliente)
	 * @return boolean
	 */
	public boolean buscarPedidoImpago(int idCliente)
	{
		return reg.buscarPedidoImpago(idCliente);
	}
	
	/**
	 * Invoca al metodo que busca y retorna el pedido impago de un cliente en caso de existir
	 * @see #retornarPedidoImpago(idCliente)
	 * @return Pedido
	 */
	public Pedido retornarPedidoImpago(int idCliente)
	{
		return reg.retornarPedidoImpago(idCliente);
	}
	
	/**
	 * Invoca al metodo que muestra el pedido impago de un cliente en caso de existir
	 * @see #mostrarPedidoImpagoCliente(cliente)
	 * @return String
	 */
	public String mostrarPedidoImpago(Cliente cliente)
	{
		return reg.mostrarPedidoImpagoCliente(cliente);
		
	}
	
	/**
	 * Invoca al metodo que muestra todos los pedidos de cliente, pagos e impagos
	 * @see #mostrarHistorialPedidosCliente(cliente)
	 * @return String
	 */
	public String mostrarHistorialPedidosCliente(Cliente cliente)
	{
		return reg.mostrarHistorialPedidosCliente(cliente);
		
	}
	
	/**
	 * Invoca al metodo que verifica que el carrito de un pedido no se encuentre vacio
	 * @see #verificarCarritoVacio(idCliente)
	 * @return boolean
	 */
	public boolean verificarCarritoNoVacio(int idCliente)
	{
		return reg.verificarCarritoVacio(idCliente);
	}
	
	/**
	 * Invoca al metodo que muestra todos los pedidos impagos del sistema
	 * @see #mostrarTodosLosPedidosImpagos()
	 * @return String
	 */
	public String mostrarTodosLosPedidosImpagos()
	{
		return reg.mostrarTodosLosPedidosImpagos();
	}
	
	/**
	 * Invoca al metodo que muestra los totales bruto, neto y medio de pago correspondiente al pedido impago de un cliente
	 * @see #detallesFinalesPedido(cliente)
	 * @return String
	 */
	public String visualizarTotalesPedido(Cliente cliente)
	{
		return reg.detallesFinalesPedido(cliente);
	}
	
	/**
	 * Invoca al metodo que establece el empleado que administro el pago de un pedido
	 * @see #setearEmpleado(idEmpleado, idCliente)
	 */
	public void setearEmpleado(int idEmpleado, int idCliente)
	{
		reg.setearEmpleado(idEmpleado, idCliente); 
	}
	
	/**
	 * Invoca al metodo que elimina una peticion de compra del carrito de un cliente
	 * @see #eliminarPeticionCarrito(codigoP, cliente)
	 * @return String
	 */
	public String eliminarPeticionCarrito(String codigoP, Cliente cliente)
	{
       return reg.eliminarPeticionCarrito(codigoP, cliente);	
	}
	
	/**
	 * Invoca al metodo que elimina el pedido impago existente de un cliente
	 * @see #eliminarPedidoImpagoCliente(cliente)
	 * @return String
	 */
	public String eliminarPedidoImpagoCliente(Cliente cliente)
	{
		return reg.eliminarPedidoImpagoCliente(cliente);
	}
	
	/**
	 * Retorna un ArrayList con los productos de la clasificacion solicitada (para el centro informativo)
	 * @param String
	 * @return ArrayList
	 */
	public ArrayList<Producto> productosClasificacion(String clasificacion)
	{
		Iterator<Map.Entry<String, ArrayList<Producto>>>it= catalogoProductos.entrySet().iterator();
		boolean encontrado=false;
		ArrayList<Producto>array=null;
		while(it.hasNext() && !encontrado)
		{
			Map.Entry<String, ArrayList<Producto>>entry=(Map.Entry<String, ArrayList<Producto>>)it.next();
			if(entry.getKey().equalsIgnoreCase(clasificacion))
			{
				array=entry.getValue();
				encontrado=true;
			}
		}
		return array;
	}
	
	
	/**
	 * Permite acceder al catalogo de productos (utilizado para la escritura del archivo base de datos)
	 * @return HashMap<String, ArrayList<Producto>>
	 */
	public HashMap<String, ArrayList<Producto>> getCatalogoProductos() {
		return catalogoProductos;
	}

	/**
	 * Permite acceder al catalogo de servicios (utilizado para la escritura del archivo base de datos)
	 * @return HashMap<String, Servicio>
	 */
	public HashMap<String, Servicio> getCatalogoServicios() {
		return catalogoServicios;
	}

	/**
	 * Permite acceder a la lista de clientes (utilizado para la escritura del archivo base de datos)
	 * @return HashSet
	 */
	public HashSet<Cliente> getListaClientes() {
		return listaClientes;
	}

	/**
	 * Permite acceder a la lista de empleados (utilizado para la escritura del archivo base de datos)
	 * @return HashSet
	 */
	public HashSet<Empleado> getListaEmpleados() {
		return listaEmpleados;
	} 
	
	/**
	 * Permite acceder a la lista de pedidos (utilizado para la escritura del archivo base de datos)
	 * @return LinkedList
	 */
	public LinkedList<Pedido> getListaPedidos()
	{
		LinkedList<Pedido>listaPedidos=null;
		listaPedidos=reg.getRegistroPedidos();
		return listaPedidos;
	}

	/**
	 * Invoca al metodo que permite ingresar un pedido al sistema (utilizado en la lectura del archivo base de datos)
	 * @see #agregarPedidoDesdeArchivo(Pedido)
	 * @param Pedido
	 */
	public void agregarPedidoDesdeArchivo(Pedido pedido)
	{
		reg.agregarPedidoDesdeArchivo(pedido);
	}
	
	

}
