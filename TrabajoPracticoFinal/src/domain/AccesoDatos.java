package domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import productos.Producto;

public class AccesoDatos { //clase para archivos

	
	
	public String escribirArchivoProductos(HashMap<String, ArrayList<Producto>>mapa)
	{
	   File file=null;
	   FileOutputStream out= null;
	   ObjectOutputStream obj=null;
	   
	   try {
		   file= new File("productos.dat");
		   out= new FileOutputStream(file);
		   obj=new ObjectOutputStream(out);
		   
		   
	   }
	   catch()
		
	}
	
}
