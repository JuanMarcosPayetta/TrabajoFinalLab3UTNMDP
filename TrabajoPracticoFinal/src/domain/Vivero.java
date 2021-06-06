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
	public <T> String buscarElemento(T elemento) {
	
		boolean validacion=false;
		String encontrado="Elemento no encontrado";
		
		if(elemento instanceof Producto)
		{
			Producto unProducto= (Producto) elemento;
			Iterator<Map.Entry<String, ArrayList<Producto>>> it= catalogoProductos.entrySet().iterator();
			while(it.hasNext() && validacion==false)
			{
				Map.Entry<String, ArrayList<Producto>> entry= (Map.Entry<String, ArrayList<Producto>>) it.next();
				if(entry.getKey().equalsIgnoreCase(unProducto.getClasificacion()))
				{
					ArrayList<Producto>productos=entry.getValue();
					for(int i=0; i<productos.size() && validacion==false; i++)
					{
						if(productos.get(i).getCodigo()==unProducto.getCodigo())
						{
							encontrado=productos.get(i).toString();
							validacion=true;
						}
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public <T> String eliminarElemento(T elemento) {
		
		String mensaje="Elemento no encontrado";
		boolean validacion=false;
		
		if(elemento instanceof Producto)
		{
			Producto unProducto= (Producto) elemento;
			Iterator<Map.Entry<String, ArrayList<Producto>>> it= catalogoProductos.entrySet().iterator();
			
			while(it.hasNext() && validacion==false)
			{
				Map.Entry<String, ArrayList<Producto>> entry= (Map.Entry<String, ArrayList<Producto>>) it.next();
				if(entry.getKey().equalsIgnoreCase(unProducto.getClasificacion()))
				{
					ArrayList<Producto>productos=entry.getValue();
					for(int i=0; i<productos.size() && validacion==false; i++)
					{
						if(productos.get(i).getCodigo().equals(unProducto.getCodigo()))
						{
							productos.remove(i);
							validacion=true;
							mensaje="Producto eliminado con exito";
							catalogoProductos.replace(unProducto.getClasificacion(), productos);
						}
					}
				}
			}
		}
		else if(elemento instanceof Servicio)
		{
			Servicio unServicio= (Servicio) elemento;
			Iterator<Map.Entry<String, Servicio>> it= catalogoServicios.entrySet().iterator();
			while(it.hasNext() && validacion==false)
			{
				Map.Entry<String, Servicio> entry= (Map.Entry<String, Servicio>)it.next();
				if(entry.getKey().equals(unServicio.getCodigo()))
				{
					it.remove();
					validacion=true;
					mensaje="Servicio eliminado con exito";
				}
			}
		}
		else if(elemento instanceof Cliente)
		{
			Cliente unCliente= (Cliente) elemento;
			Iterator<Cliente>it=listaClientes.iterator();
			while(it.hasNext() && validacion==false)
			{
				Cliente otroCliente= it.next();
				if(otroCliente.getDni().equals(unCliente.getDni()))
				{
					it.remove();
					validacion=true;
					mensaje="Cliente eliminado con exito";
				}
			}
		}
		else if(elemento instanceof Empleado)
		{
			Empleado unEmpleado= (Empleado) elemento;
			Iterator<Empleado>it=listaEmpleados.iterator();
			while(it.hasNext() && validacion==false)
			{
				Empleado otroEmpleado= it.next();
				if(otroEmpleado.getID()==unEmpleado.getID())
				{
					it.remove();
					validacion=true;
					mensaje="Empleado eliminado con exito";
				}
			}
		}
		
		return mensaje;
	}

	@Override
	public <T> String mostrarElemento(T elemento) {
		
		return null;
	}

	
	
}
