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

<<<<<<< Updated upstream
	
	public ArrayList<String> obtenerCodigos() {
=======
	public Vivero()
	{
		this.catalogoProductos= new HashMap<String, ArrayList<Producto>>();
		this.catalogoServicios= new HashMap<String, Servicio>();
		this.listaClientes= new HashSet<Cliente>();
	}

	
	public ArrayList<String> obtenerCodigosProducto() {
>>>>>>> Stashed changes
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
	
		
		
		
	}

	@Override
	public <T> String buscarElemento(T elemento) {
	
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
