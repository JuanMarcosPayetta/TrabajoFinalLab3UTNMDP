package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

	public Vivero() {
		this.catalogoProductos = new HashMap<String, ArrayList<Producto>>();
		this.catalogoServicios = new HashMap<String, Servicio>();
		this.listaClientes = new HashSet<Cliente>();
		this.listaEmpleados = new HashSet<Empleado>();
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

	private boolean existeServicio(String codigo) {

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

				if (entrada.getKey().equalsIgnoreCase(unServicio.getCodigo())) { // si existe el codigo
					mensaje = entrada.toString();
					flag = 1;
				}
			}

		}

		else if (elemento instanceof Cliente) {
			Cliente unCliente = (Cliente) elemento;

			Iterator<Cliente> it = listaClientes.iterator();

			while (it.hasNext() && flag == 0) {
				if (it.next().getDni().equals(unCliente.getDni())) {
					mensaje = it.toString();
					flag = 1;
				}
			}
		}

		else if (elemento instanceof Empleado) {
			Empleado unEmpleado = (Empleado) elemento;

			Iterator<Empleado> it = listaEmpleados.iterator();

			while (it.hasNext() && flag == 0) {
				if (it.next().getID() == unEmpleado.getID()) {
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
				if (entry.getKey().equals(unServicio.getCodigo())) {
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
				if (otroCliente.getDni().equals(unCliente.getDni())) {
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
				if (otroEmpleado.getID() == unEmpleado.getID()) {
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

	public String mostrarEmpleado() {
		StringBuilder mensaje = new StringBuilder();

		Iterator<Empleado> it = listaEmpleados.iterator();

		while (it.hasNext()) {
			mensaje.append("[ " + it.next().toString() + " ]");
		}

		return mensaje.toString();
	}

	public String mostrarCliente() {
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

	public String ComprarProducto(Producto elProducto, String dni, int cantidadP) {
		// BUSCAR CLIENTE
		int flag = 0;
		int otroFlag = 0;
		String mensaje = "ERROR en la compra";
		Producto productoComprado = null;
		Cliente elCliente = null;

		Iterator<Cliente> itt = listaClientes.iterator();

		while (itt.hasNext() && flag == 0) {
			elCliente = itt.next(); // guardo un cliente y debajo compruebo que sea el que busco
			if (elCliente.getDni().equals(dni)) {
				flag = 1;
			}
		}

		if (flag == 0) {
			mensaje = "Cliente no encontrado";
		}

		else {

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

							if (arreglo.get(i).ComprobarStock(cantidadP)) {

								
								
							}

							else {
								mensaje = "Stock insuficiente, actualmente hay " + arreglo.get(i).getStock()
										+ " unidades";
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
		}
		return mensaje;
	}

	// El objeto principal es Vivero, el cual posee dentro 1 array por cada
	// coleccion (productos, servicios, cliente, empleado)
	public JSONObject javaToJsonProductos() {
		JSONObject vivero = new JSONObject(); // objeto json principal

		try {

			// PRODUCTOS
			JSONArray cataloProductos = new JSONArray(); // array catalogo de productos ("mapa")
			Iterator<Map.Entry<String, ArrayList<Producto>>> it = catalogoProductos.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, ArrayList<Producto>> entry = (Map.Entry<String, ArrayList<Producto>>) it.next();
				ArrayList<Producto> listaConProductos = entry.getValue();
				// String clasificacion=null;
				JSONObject objetoProducto = new JSONObject();
				JSONArray arrayParaProductos = new JSONArray();
				JSONObject objetoConArrayDentro = new JSONObject();

				for (int i = 0; i < listaConProductos.size(); i++) {
					// clasificacion=listaConProductos.get(i).getClasificacion();
					objetoProducto = listaConProductos.get(i).javaToJson();
					arrayParaProductos.put(objetoProducto);

				}
				// PREGUNTAR
				// array principal -> objeto -> arraylist dentro ?? -->PREGUNTAR
				// array principal -> arraylist dentro ??
				// objetoConArrayDentro.put("...", arrayParaProductos);

				cataloProductos.put(arrayParaProductos); // arrayList principal de productos, con un arrayList por cada
															// posicion (divididos en clasificacion)
			}

			vivero.put("productos", cataloProductos);

			// SERVICIOS
			Iterator<Map.Entry<String, Servicio>> it2 = catalogoServicios.entrySet().iterator();
			JSONArray arrayServicios = new JSONArray();
			while (it2.hasNext()) {
				Map.Entry<String, Servicio> entry = (Map.Entry<String, Servicio>) it2.next();
				JSONObject objetoServicio = entry.getValue().javaToJson();
				arrayServicios.put(objetoServicio);
			}

			vivero.put("servicios", arrayServicios); // ASI ??
			// cataloServicios.put("servicios", arrayServicios); //OBJETO CON ARRAY DENTRO ?
			// U ARRAY CON EL ARRAY DE SERVICIOS DENTRO?

			// CLIENTES
			Iterator<Cliente> it3 = listaClientes.iterator();
			JSONArray arrayClientes = new JSONArray();
			while (it3.hasNext()) {
				JSONObject objeto = new JSONObject();
				objeto = it3.next().javaToJson();
				arrayClientes.put(objeto);
			}

			vivero.put("clientes", arrayClientes);

			// EMPLEADO
			Iterator<Cliente> it4 = listaClientes.iterator();
			JSONArray arrayEmpleados = new JSONArray();
			while (it4.hasNext()) {
				JSONObject objeto = new JSONObject();
				objeto = it4.next().javaToJson();
				arrayEmpleados.put(objeto);
			}

			vivero.put("empleados", arrayEmpleados);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return vivero;
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

}
