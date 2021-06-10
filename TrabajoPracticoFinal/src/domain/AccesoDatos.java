package domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AccesoDatos { //clase para archivos

	
	ObjectOutputStream obj1=null;
	
	public void escribirArchivo()
	{
		try
		{
			obj1= new ObjectOutputStream(new FileOutputStream());
			
		}catch()
		{
			
		}
	}
	
}
