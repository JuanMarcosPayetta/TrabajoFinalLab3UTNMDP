package domain;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import productos.Producto;

public class AccesoDatos { //clase para archivos

	
	
	public String escribirArchivoProductos(HashMap<String, ArrayList<Producto>>mapa)
	{
	   File file=null;
	   FileOutputStream out= null;
	   ObjectOutputStream obj=null;
	   String mensaje=null;
	   
	   try {
		   file= new File("productos.dat");
		   out= new FileOutputStream(file);
		   obj=new ObjectOutputStream(out);
		   
		   Iterator<Map.Entry<String, ArrayList<Producto>>>it=mapa.entrySet().iterator();
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
			mensaje="Llegaste al final del archivo";
		}
		catch(FileNotFoundException e)
		{
			mensaje="Error, archivo no encontrado";
		}
		catch(ClassNotFoundException e)
		{
			mensaje="Error, el elemento que se quiere leer no fue encontrado en el archivo";
		}
		catch(IOException e)
		{
			mensaje="Error";
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
	
	public String escribirArchivoClientes()
}
