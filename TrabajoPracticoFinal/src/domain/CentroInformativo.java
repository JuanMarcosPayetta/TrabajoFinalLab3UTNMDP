package domain;

import java.util.ArrayList;

import productos.Arbol;
import productos.Arbusto;
import productos.Hierba;
import productos.PlantaAcuatica;
import productos.Producto;
import productos.SanidadVegetal;

public class CentroInformativo<T extends Producto> {

	ArrayList<T>datos;
	
	public CentroInformativo()
	{
		this.datos=new ArrayList<T>();
	}
	
	public String mostrarTodo()
	{
		StringBuilder builder= new StringBuilder();
		for(int i=0; i<datos.size(); i++)
		{
			builder.append(datos.get(i).toString()+"\n");
		}
		
		return builder.toString();
	}
	
	public String elementoMasCostoso()
	{
		T masCostoso=datos.get(0);
		for(int i=1; i<datos.size(); i++)
		{
		   if(masCostoso.compareTo(datos.get(i))==-1)
		   {
			   masCostoso=datos.get(i);
		   }
		}
		
		return masCostoso.toString();
	}
	
	public String elementoMasEconomico()
	{
		T masEconomico=datos.get(0);
		for(int i=1; i<datos.size(); i++)
		{
		   if(masEconomico.compareTo(datos.get(i))==1)
		   {
			   masEconomico=datos.get(i);
		   }
		}
		
		return masEconomico.toString();
	}
	
	public String informacion()
	{
		int cant1=0;
		int cant2=0;
		int cant3=0;
		int cant4=0;
		int cant5=0;
		int cant6=0;
		String mensaje=null;
		
		if(datos.get(0) instanceof PlantaAcuatica)
		{
			for(int i=0; i<datos.size(); i++)
			{
				PlantaAcuatica planta= (PlantaAcuatica) datos.get(i);
				
				if(planta.getStock()>0)
				{
					if(planta.getTipo().equalsIgnoreCase("flotante"))
					{
						cant1++;
					}
					else if(planta.getTipo().equalsIgnoreCase("oxigenante"))
					{
						cant2++;
					}
					else if(planta.getTipo().equalsIgnoreCase("ribera"))
					{
						cant3++;
					}
					else
					{
						cant4++;
					}
					
					if(planta.getTipoDeAgua().equalsIgnoreCase("salada"))
					{
						cant5++;
					}
					else
					{
						cant6++;
					}
				}
			}
			
			mensaje="Existe actualmente en stock: \n"+"Plantas acuaticas flotantes: "+cant1+"\n"+"Plantas acuaticas oxigenantes: "+cant2+"\n"+"Plantas acuatica de ribera: "+cant3+"\n"+"Plantas acuaticas de profundidades: "+cant4+"\n"+"Cantidad por tipo de agua en stock: "+cant5+" de agua salada, "+cant6+" de agua dulce \n";
		}
		else if(datos.get(0) instanceof Hierba)
		{
			for(int i=0; i<datos.size(); i++)
			{
				Hierba planta= (Hierba) datos.get(i);
				
				if(planta.getStock()>0)
				{
					if(planta.getTipoDeTallo().equalsIgnoreCase("erguido"))
					{
						cant1++;
					}
					else if(planta.getTipoDeTallo().equalsIgnoreCase("voluble"))
					{
						cant2++;
					}
					else if(planta.getTipoDeTallo().equalsIgnoreCase("segmentado"))
					{
						cant3++;
					}
					else
					{
						cant4++;
					}
					
					if(planta.isComestible())
					{
						cant5++;
					}
					else if(planta.isMedicinal())
					{
						cant6++;
					}
				}
			}
			mensaje="Existe actualmente en stock: \n"+"Hierba tallo erguido: "+cant1+"\n"+"Hierba tallo voluble: "+cant2+"\n"+"Hierba tallo segmentado: "+cant3+"\n"+"Hierba tallo rastrero : "+cant4+"\n"+"Cantidad de hierbas con caracteristicas notables: "+cant5+" comestibles , "+cant6+" medicinales\n";
		}
		else if(datos.get(0) instanceof Arbol)
		{
			for(int i=0; i<datos.size(); i++)
			{
				Arbol planta= (Arbol) datos.get(i);
				
				if(planta.getStock()>0)
				{
					if(planta.getTipoDeCorteza().equalsIgnoreCase("laminada"))
					{
						cant1++;
					}
					else if(planta.getTipoDeCorteza().equalsIgnoreCase("geometrica"))
					{
						cant2++;
					}
					else if(planta.getTipoDeCorteza().equalsIgnoreCase("rugosa"))
					{
						cant3++;
					}
					else if(planta.getTipoDeCorteza().equalsIgnoreCase("lobulada"))
					{
						cant4++;
					}
					else if(planta.getTipoDeCorteza().equalsIgnoreCase("placoide"))
					{
						cant5++;
					}
					else if(planta.getTipoDeCorteza().equalsIgnoreCase("escasa"))
					{
						cant6++;
					}
				}
			}
			mensaje="Existe actualmente en stock: \n"+"Arbol de corteza laminada: "+cant1+"\n"+"Arbol de corteza geometrica: "+cant2+"\n"+"Arbol de corteza rugosa: "+cant3+"\n"+"Arbol de corteza lobulada: "+cant4+"\n"+"Arbol de corteza placoide: "+cant5+"Arbol de corteza escasa: "+cant6+"\n";
		}
		else if(datos.get(0) instanceof Arbusto)
		{
			for(int i=0; i<datos.size(); i++)
			{
				Arbusto planta= (Arbusto) datos.get(i);
				
				if(planta.getStock()>0)
				{
					if(planta.getTipoDeHoja().equalsIgnoreCase("caduca"))
					{
						cant1++;
					}
					else if(planta.getTipoDeHoja().equalsIgnoreCase("semicaduca"))
					{
						cant2++;
					}
					else 
					{
						cant3++;
					}
					
					if(planta.isTrepador())
					{
						cant4++;
					}
				}
			}
			mensaje="Existe actualmente en stock: \n"+"Arbusto de hoja caduca: "+cant1+"\n"+"Arbusto de hoja semicaduca: "+cant2+"\n"+"Arbusto de hoja persistente: "+cant3+"\n"+"Cantidad de arbustos trepadores: "+cant4+"\n";
		}
		else if(datos.get(0) instanceof SanidadVegetal)
		{
			for(int i=0; i<datos.size(); i++)
			{
				SanidadVegetal planta= (SanidadVegetal) datos.get(i);
				
				if(planta.getStock()>0)
				{
					if(planta.getFuncion().equalsIgnoreCase("funguicida"))
					{
						cant1++;
					}
					else if(planta.getFuncion().equalsIgnoreCase("insecticida"))
					{
						cant2++;
					}
					else if(planta.getFuncion().equalsIgnoreCase("herbicida"))
					{
						cant3++;
					}

				}
			}
			mensaje="Existe actualmente en stock: \n"+"Funguicidas: "+cant1+"\n"+"Insecticidas: "+cant2+"\n"+"Herbicidas: "+cant3+"\n";
		}
	}
}
