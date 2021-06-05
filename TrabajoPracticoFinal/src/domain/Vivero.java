package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import productos.Producto;

public class Vivero {

	private HashMap<String, ArrayList<Producto>>catalogoProductos;
	private HashMap<String, Servicio>catalogoServicios;
	
	
	public ArrayList<String> obtenerCodigos()
	{
		ArrayList<Producto>productos=null;
		ArrayList<String> codigos= new ArrayList<String>();
		String codigoEncontrado=null;
		Iterator<Map.Entry<String, ArrayList<Producto>>> it= catalogoProductos.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<String, ArrayList<Producto>> entry= (Map.Entry<String, ArrayList<Producto>>)it.next();
			productos=entry.getValue();
			
			for(int i=0; i<productos.size(); i++)
			{
				codigoEncontrado=productos.get(i).getCodigo();
				codigos.add(codigoEncontrado);
			}
		}
		
		return codigos;	
	}
	
}
 