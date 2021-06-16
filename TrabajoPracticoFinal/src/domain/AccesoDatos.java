package domain;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import productos.Producto;

public class AccesoDatos { //clase para archivos

	private final static String nombreArchivo = "json.txt";
	
	public String escribirArchivoProductos(Vivero vivero)
	{
	   File file=null;
	   FileOutputStream out= null;
	   ObjectOutputStream obj=null;
	   String mensaje=null;
	   
	   try {

		   file= new File("productos.dat");
		   out= new FileOutputStream(file);
		   obj=new ObjectOutputStream(out);
		   
		   Iterator<Map.Entry<String, ArrayList<Producto>>>it=vivero.getCatalogoProductos().entrySet().iterator();
		   while(it.hasNext())
		   {
			   Map.Entry<String, ArrayList<Producto>>entry=(Map.Entry<String, ArrayList<Producto>>)it.next();
			   ArrayList<Producto>lista=entry.getValue();
			   for(int i=0; i<lista.size(); i++)
			   {
				   obj.writeObject(lista.get(i));
			   }
		   }
	   }
	   catch(IOException e)
	   {
		   mensaje="Se produjo un error en la escritura del archivo "+e.getMessage();
	   }
	   finally
	   {
		   try
		   {
			   obj.close();
		   }
		   catch(IOException e)
		   {
			   mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
		   }
	   }
	   return mensaje;
		
	}
	
	public String leerArchivoProductos(Vivero vivero)
	{
		FileInputStream f1=null;
		ObjectInputStream obj=null;
		String mensaje=null;
		
		try
		{
			f1= new FileInputStream("productos.dat");
			obj=new ObjectInputStream(f1);
			Producto producto= null;
			while((producto=(Producto)obj.readObject())!=null)
			{
				vivero.agregarElemento(producto);
			}
		}
		catch(EOFException e)
		{
			mensaje="Llegaste al final del archivo"+e.getMessage();
		}
		catch(FileNotFoundException e)
		{
			mensaje="Error, archivo no encontrado"+e.getMessage();
		}
		catch(ClassNotFoundException e)
		{
			mensaje="Error, el elemento que se quiere leer no fue encontrado en el archivo"+e.getMessage();
		}
		catch(IOException e)
		{
			mensaje="Error"+e.getMessage();
		}
		finally
		{
			try
			{
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Error al cerrar el archivo";
			}
		}
		return mensaje;
	}
	
	public String escribirArchivoServicios(Vivero vivero) {

		File file = null;
		FileOutputStream fOutp = null;
		ObjectOutputStream obj = null;
		String mensaje = null;

		try {

			file = new File("servicios.dat");
			fOutp = new FileOutputStream(file);
			obj = new ObjectOutputStream(fOutp);

			Iterator<Map.Entry<String, Servicio>> it = vivero.getCatalogoServicios().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Servicio> entrada = (Map.Entry<String, Servicio>) it.next();

				obj.writeObject(entrada.getValue());
			}

		}

		catch (IOException e) {
			mensaje = "Se podrujo un error en la escritura del archivo" + e.getMessage();
		}

		finally {

			try {

				obj.close();
			}

			catch (IOException e) {

				mensaje = "Error al cerrar el archivo" + e.getMessage();
			}
		}

		return mensaje;
	}

	public String leerArchivoServicios(Vivero vivero) {
		String mensaje = null;

		FileInputStream fInput = null;
		ObjectInputStream obj = null;

		try {

			fInput = new FileInputStream("servicios.dat");
			obj = new ObjectInputStream(fInput);
			Servicio unServicio = null;

			while ((unServicio = (Servicio) obj.readObject()) != null) {
				vivero.agregarElemento(unServicio);
			}

		} catch (EOFException e) {
			mensaje = "llegaste al final del archivo";

		} catch (FileNotFoundException e) {
			mensaje = "Error, archivo no encontrado";

		} catch (IOException e) {

			mensaje = "error" + e.getMessage();
		} catch (ClassNotFoundException e) {
			mensaje = "Clase no encontrada" + e.getMessage();
		}

		finally {

			try {
				obj.close();
			}

			catch (IOException e) {

				mensaje = "Error al cerrar el archivo";
			}
		}

		return mensaje;
	}
	
	
	public String escribirArchivoClientes(Vivero vivero)
	{
		File f1=null;
		FileOutputStream out=null;
		ObjectOutputStream obj=null;
		String mensaje=null;
		
		try
		{
			f1= new File("clientes.dat");
			out= new FileOutputStream(f1);
			obj= new ObjectOutputStream(out);
			
			Iterator<Cliente>it= vivero.getListaClientes().iterator();
			while(it.hasNext())
			{
				Cliente cliente= it.next();
				obj.writeObject(cliente);
			}
		}
		catch(IOException e)
		{
			mensaje="Se produjo un error en la escritura del archivo "+e.getMessage();
		}
		finally
		{
			try
			{
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
			}
		}
		return mensaje;
	}
	
	public String leerArchivoClientes(Vivero vivero)
	{
		FileInputStream f1= null;
		ObjectInputStream obj= null;
		String mensaje=null;
		
		try
		{
			f1= new FileInputStream("clientes.dat");
			obj= new ObjectInputStream(f1);
			Cliente cliente=null;
			
			while((cliente= (Cliente)obj.readObject())!=null)
			{
				vivero.agregarElemento(cliente);
			}
		}
		catch(EOFException e)
		{
			mensaje="Llegaste al final del archivo"+e.getMessage();
		}
		catch(FileNotFoundException e)
		{
			mensaje="Error, archivo no encontrado"+e.getMessage();
		}
		catch(ClassNotFoundException e)
		{
			mensaje="Error, el elemento que se quiere leer no fue encontrado en el archivo"+e.getMessage();
		}
		catch(IOException e)
		{
			mensaje="Error"+e.getMessage();
		}
		finally
		{
			try
			{
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
			}
		}
		return mensaje;
	}
	
	public String escribirArchivoEmpleados(Vivero vivero)
	{
		File f1= null;
		FileOutputStream out=null;
		ObjectOutputStream obj=null;
		String mensaje=null;
		try {
			f1= new File("empleados.dat");
			out= new FileOutputStream(f1);
			obj= new ObjectOutputStream(out);
			Iterator<Empleado>it=vivero.getListaEmpleados().iterator();
			while(it.hasNext())
			{
				Empleado empleado=it.next();
				obj.writeObject(empleado);
			}
			
		}
		catch(IOException e)
		{
			mensaje="Se produjo un error en la escritura del archivo "+e.getMessage();
		}
		finally
		{
			try
			{
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
			}
		}
		return mensaje;
	}
	
	public String leerArchivoEmpleados(Vivero vivero)
	{
		FileInputStream f1= null;
		ObjectInputStream obj=null;
		String mensaje=null;
		
		try
		{
			f1= new FileInputStream("empleados.dat");
			obj=new ObjectInputStream(f1);
			
			Empleado empleado=null;
			while((empleado = (Empleado)obj.readObject())!=null)
			{
				vivero.agregarElemento(empleado);
			}
			
		}
		catch(EOFException e)
		{
			mensaje="Llegaste al final del archivo"+e.getMessage();
		}
		catch(FileNotFoundException e)
		{
			mensaje="Error, archivo no encontrado"+e.getMessage();
		}
		catch(ClassNotFoundException e)
		{
			mensaje="Error, el elemento que se quiere leer no fue encontrado en el archivo"+e.getMessage();
		}
		catch(IOException e)
		{
			mensaje="Error"+e.getMessage();
		}
		finally
		{
			try
			{
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
			}
		}
		return mensaje;
	}
	
	
	public String escribirArchivoPedidos(Vivero vivero)
	{
		String mensaje=null;
		
		File f1= null;
		FileOutputStream out=null;
		ObjectOutputStream obj=null;
		
		try {
			f1= new File("pedidos.dat");
			out= new FileOutputStream(f1);
			obj= new ObjectOutputStream(out);
			
			for(int i=0; i<vivero.getListaPedidos().size(); i++)
			{
				obj.writeObject(vivero.getListaPedidos().get(i));
			}
		}
		catch(IOException e)
		{
			mensaje="Se produjo un error en la escritura del archivo "+e.getMessage();
		}
		finally
		{
			try {
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
			}
			
		}
		return mensaje;
	}
	
	public String leerArchivoPedidos(Vivero vivero)
	{
		String mensaje=null;
		FileInputStream f1= null;
		ObjectInputStream obj=null;
		
		try
		{
			f1= new FileInputStream("pedidos.dat");
			obj= new ObjectInputStream(f1);
			Pedido pedido=null;
			while((pedido= (Pedido)obj.readObject())!=null)
			{
				vivero.agregarPedidoDesdeArchivo(pedido);
			}
			
		}catch(EOFException e)
		{
			mensaje="Llegaste al final del archivo"+e.getMessage();
		}
		catch(FileNotFoundException e)
		{
			mensaje="Error, archivo no encontrado"+e.getMessage();
		}
		catch(ClassNotFoundException e)
		{
			mensaje="Error, el elemento que se quiere leer no fue encontrado en el archivo"+e.getMessage();
		}
		catch(IOException e)
		{
			mensaje="Error"+e.getMessage();
		}
		finally
		{
			try
			{
				obj.close();
			}
			catch(IOException e)
			{
				mensaje="Se produjo un error en el cierre del archivo "+e.getMessage();
			}
		}
		return mensaje;
	}
	
	/*
	 * Escribe las marcas y clasificaciones en un json object
	 */
	public JSONObject javaToJson(Vivero vivero)
	{
		JSONObject obj=null;
		JSONArray clasificaciones=null;
		JSONArray marcasComercializadas=null;
		JSONArray servicios=null;
		
		try
		{
			ArrayList<String>marcas=new ArrayList<String>(); //marcas ya agregadas al objeto
			
			obj= new JSONObject();
			clasificaciones= new JSONArray();
			marcasComercializadas= new JSONArray();
			servicios= new JSONArray();
			
			Iterator<Map.Entry<String, ArrayList<Producto>>>it=vivero.getCatalogoProductos().entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, ArrayList<Producto>>entry=(Map.Entry<String, ArrayList<Producto>>)it.next();
				ArrayList<Producto>lista= entry.getValue();
				
				clasificaciones.put(entry.getKey()); //almaceno las clasificaciones
				
				for(int i=0; i<lista.size(); i++)
				{
					if(!marcas.contains(lista.get(i).getMarca()))
					{
						marcasComercializadas.put(lista.get(i).getMarca()); //almaceno las marcas
						marcas.add(lista.get(i).getMarca());
					}
				}
			}
			
			Iterator<Map.Entry<String, Servicio>>it2=vivero.getCatalogoServicios().entrySet().iterator();
			while(it2.hasNext())
			{
				Map.Entry<String, Servicio>entry=(Map.Entry<String, Servicio>)it2.next();
				servicios.put(entry.getValue().getNombre());
			}
			
			obj.put("clasificaciones", clasificaciones);
			obj.put("marcas", marcasComercializadas);
			obj.put("servicios", servicios);
			
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
	
	
    //graba JSONObject
    public static void grabar(JSONObject jsonObject) {
        try {
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(jsonObject.toString());
            file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static String leer() 
    {
        String contenido = "";
        try 
        {
            contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contenido;
    }
	
}
