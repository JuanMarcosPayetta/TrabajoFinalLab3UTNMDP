package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import interfaces.IVivero;
import productos.Producto;

public class Vivero implements IVivero{

	/*
	 * EL MAPA DE PRODUCTOS COMO CLAVE TIENE LA CLASIFICACION
	 * ("ARBOL, ARBUSTO, ETC") Y DENTRO COMO VALOR TIENE UN ARRAY LIST CON TODOS LOS
	 * ARBOLES, ARBUSTOS, ETC
	 */

	private HashMap<String, ArrayList<Producto>> catalogoProductos;
	private HashMap<String, Servicio> catalogoServicios;
	private HashSet<Cliente>listaClientes;
	private HashSet<Empleado>listaEmpleados;
	

	
	public Vivero()
	{
		this.catalogoProductos= new HashMap<String, ArrayList<Producto>>();
		this.catalogoServicios= new HashMap<String, Servicio>();
		this.listaClientes= new HashSet<Cliente>();
		this.listaEmpleados= new HashSet<Empleado>();
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

		Servicio servicio= new Servicio();
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
		if(elemento instanceof Producto)
		{
			Producto unProducto= (Producto) elemento;
			if(!existeProducto(unProducto.getClasificacion()))
			{
				ArrayList<Producto>listaProductos=new ArrayList<Producto>();
				listaProductos.add(unProducto);
				catalogoProductos.put(unProducto.getClasificacion(), listaProductos);
			}
			else
			{
				ArrayList<Producto>listaProductos=catalogoProductos.get(unProducto.getClasificacion());
				listaProductos.add(unProducto);
				catalogoProductos.put(unProducto.getClasificacion(), listaProductos);
			}
		}
		else if (elemento instanceof Servicio) 
		{
           Servicio unservicio = (Servicio) elemento;

			if (!existeServicio(unservicio.getCodigo())) {

				catalogoServicios.put(unservicio.getCodigo(), unservicio);
			}
		}
		else if(elemento instanceof Empleado)
		{
			Empleado unEmpleado= (Empleado) elemento;
			listaEmpleados.add(unEmpleado);
		}
		else if(elemento instanceof Cliente)
        {
            Cliente unCliente= (Cliente) elemento;
            listaClientes.add(unCliente);
        }
	}
	

	
	private boolean existeProducto(String clasificacion)
	{
		return catalogoProductos.containsKey(clasificacion);
	}

	private boolean existeServicio(String codigo) {

		return catalogoServicios.containsKey(codigo); // si existe te devuelve true
	}

	
	@Override
	public <T> String buscarElemento(T elemento) { //producto,servicio,empleado, cliente (RECIBO ELEMENTO CON LA CLASIFICACION Y EL CODIGO) 
	
		if(elemento instanceof Producto)
		{
			
		}
		return null;
	}

	@Override
	public <T> String eliminarElemento(T elemento) {
		
		return null;
	}

	@Override
	public <T> String mostrarElemento(T elemento) {
		
		return null;
	}

	
	
}
