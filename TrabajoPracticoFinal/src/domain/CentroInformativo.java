package domain;

import java.util.ArrayList;
import java.util.Collections;

import productos.Arbol;
import productos.Arbusto;
import productos.Decoracion;
import productos.HerramientaManual;
import productos.HerramientaNoManual;
import productos.Hierba;
import productos.Lombriz;
import productos.MacetaPoliedro;
import productos.MacetaRedonda;
import productos.MuebleJardin;
import productos.Planta;
import productos.PlantaAcuatica;
import productos.Producto;
import productos.SanidadVegetal;
import productos.Semilla;
import productos.Sustrato;

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
	
	public String informacionStockPositivo()
	{
		int cant1=0;
		int cant2=0;
		int cant3=0;
		int cant4=0;
		int cant5=0;
		int cant6=0;
		int cant7=0;
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
			mensaje="Existe actualmente en stock: \n"+"Arbol de corteza laminada: "+cant1+"\n"+"Arbol de corteza geometrica: "+cant2+"\n"+"Arbol de corteza rugosa: "+cant3+"\n"+"Arbol de corteza lobulada: "+cant4+"\n"+"Arbol de corteza placoide: "+cant5+"\n"+"Arbol de corteza escasa: "+cant6+"\n";
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
				SanidadVegetal sanidad= (SanidadVegetal) datos.get(i);
				
				if(sanidad.getStock()>0)
				{
					if(sanidad.getFuncion().equalsIgnoreCase("funguicida"))
					{
						cant1++;
					}
					else if(sanidad.getFuncion().equalsIgnoreCase("insecticida"))
					{
						cant2++;
					}
					else if(sanidad.getFuncion().equalsIgnoreCase("herbicida"))
					{
						cant3++;
					}

				}
			}
			mensaje="Existe actualmente en stock: \n"+"Funguicidas: "+cant1+"\n"+"Insecticidas: "+cant2+"\n"+"Herbicidas: "+cant3+"\n";
		}
		else if(datos.get(0) instanceof Semilla)
		{
			for(int i=0; i<datos.size(); i++)
			{
				Semilla semilla= (Semilla) datos.get(i);
				
				if(semilla.getStock()>0)
				{
					if(semilla.getDestino().equalsIgnoreCase("floral"))
					{
						cant1++;
					}
					else if(semilla.getDestino().equalsIgnoreCase("huerta"))
					{
						cant2++;
					}
					else if(semilla.getDestino().equalsIgnoreCase("cesped"))
					{
						cant3++;
					}
				}
			}
			mensaje="Existe actualmente en stock: \n"+"Semillas con destino Floral: "+cant1+"\n"+"Semillas con destino de Huerta: "+cant2+"\n"+"Semillas con destino para Cesped: "+cant3+"\n";
	   }
		else if(datos.get(0) instanceof Sustrato)
		{
			for(int i=0; i<datos.size(); i++)
			{
				Sustrato sustrato= (Sustrato) datos.get(i);
				
				if(sustrato.getStock()>0)
				{
					if(sustrato.getTipoDeSuelo().equalsIgnoreCase("arenoso"))
					{
						cant1++;
					}
					else if(sustrato.getTipoDeSuelo().equalsIgnoreCase("calizo"))
					{
						cant2++;
					}
					else if(sustrato.getTipoDeSuelo().equalsIgnoreCase("humifero"))
					{
						cant3++;
					}
					else if(sustrato.getTipoDeSuelo().equalsIgnoreCase("arcilloso"))
					{
						cant4++;
					}
					else if(sustrato.getTipoDeSuelo().equalsIgnoreCase("pedregoso"))
					{
						cant5++;
					}
					else if(sustrato.getTipoDeSuelo().equalsIgnoreCase("mixto"))
					{
						cant6++;
					}
					
					if(sustrato.isAbonada())
					{
						cant7++;
					}
				}	
			}
			mensaje="Existe actualmente en stock: \n"+"Sustrato para suelo arenoso: "+cant1+"\n"+"Sustrato para suelo calizo: "+cant2+"\n"+"Sustrato para suelo humifero: "+cant3+"\n"+"Sustrato para suelo arcilloso: "+cant4+"\n"+"Sustrato para suelo pedregoso: "+cant5+"\n"+"Sustrato para suelo mixto: "+cant6+"\n"+"Cantidad de sustratos abonados: "+cant7;
		}
		else if(datos.get(0) instanceof MacetaPoliedro)
		{
			for(int i=0; i<datos.size(); i++)
			{
				MacetaPoliedro maceta= (MacetaPoliedro) datos.get(i);
				
				if(maceta.getStock()>0)
				{
					if(maceta.getForma().equalsIgnoreCase("jardinera"))
					{
						cant1++;
					}
					else if(maceta.getForma().equalsIgnoreCase("cubo"))
					{
						cant2++;
					}
					else 
					{
						cant3++;
					}
				}	
			}
			mensaje="Existe actualmente en stock: \n"+"Maceta jardinera: "+cant1+"\n"+"Maceta cubo: "+cant2+"\n"+"Maceta piramidal: "+cant3+"\n";
		}
		else if(datos.get(0) instanceof MacetaRedonda)
		{
			for(int i=0; i<datos.size(); i++)
			{
				MacetaRedonda maceta= (MacetaRedonda) datos.get(i);
				
				if(maceta.getStock()>0)
				{
					if(maceta.getForma().equalsIgnoreCase("ovalada"))
					{
						cant1++;
					}
					else if(maceta.getForma().equalsIgnoreCase("circular"))
					{
						cant2++;
					}
		
				}	
			}
			mensaje="Existe actualmente en stock: \n"+"Maceta jardinera: "+cant1+"\n"+"Maceta cubo: "+cant2+"\n";
		}
		else if(datos.get(0) instanceof Lombriz)
		{
			for(int i=0; i<datos.size(); i++)
			{
				Lombriz lombriz= (Lombriz) datos.get(i);
				
				if(lombriz.getStock()>0)
				{
					if(lombriz.getEspecie().equalsIgnoreCase("rayada"))
					{
						cant1++;
					}
					else if(lombriz.getEspecie().equalsIgnoreCase("dendra"))
					{
						cant2++;
					}
					else if(lombriz.getEspecie().equalsIgnoreCase("roja"))
					{
						cant3++;
					}
					else
					{
						cant4++;
					}
				}	
			}
			mensaje="Existe actualmente en stock: \n"+"Lombriz rayada: "+cant1+"\n"+"Lombriz dendra: "+cant2+"\n"+"Lombriz roja: "+cant3+"\n"+"Lombriz comun: "+cant4+"\n";
		}
		else if(datos.get(0) instanceof HerramientaManual)
		{
			for(int i=0; i<datos.size(); i++)
			{
			    HerramientaManual herramienta= (HerramientaManual) datos.get(i);
				
				if(herramienta.getStock()>0)
				{
					if(herramienta.getFuncion().equalsIgnoreCase("corte"))
					{
						cant1++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("desmalezado"))
					{
						cant2++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("labrado"))
					{
						cant3++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("limpieza"))
					{
						cant4++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("riego"))
					{
						cant5++;
					}
					else 
					{
						cant6++;
					}

				}	
			}
			mensaje="Existe actualmente en stock en herramientas manuales: \n"+"Herramienta de corte: "+cant1+"\n"+"Herramienta de desmalezado: "+cant2+"\n"+"Herramienta de labrado: "+cant3+"\n"+"Herramienta de limpieza: "+cant4+"\n"+"Herramienta de riego: "+cant5+"\n"+"Herramienta de transporte: " +cant6+"\n";
		}
		else if(datos.get(0) instanceof HerramientaNoManual)
		{
			int cant8=0;
			for(int i=0; i<datos.size(); i++)
			{
			    HerramientaNoManual herramienta= (HerramientaNoManual) datos.get(i);
				
				if(herramienta.getStock()>0)
				{
					if(herramienta.getFuncion().equalsIgnoreCase("corte"))
					{
						cant1++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("desmalezado"))
					{
						cant2++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("labrado"))
					{
						cant3++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("limpieza"))
					{
						cant4++;
					}
					else if(herramienta.getFuncion().equalsIgnoreCase("riego"))
					{
						cant5++;
					}
					else 
					{
						cant6++;
					}
					
					if(herramienta.getTipoMotor().equalsIgnoreCase("electrico"))
					{
						cant7++;
					}
					else
					{
						cant8++;
					}

				}	
			}
			mensaje="Existe actualmente en stock en herramientas no manuales: \n"+"Herramienta de corte: "+cant1+"\n"+"Herramienta de desmalezado: "+cant2+"\n"+"Herramienta de labrado: "+cant3+"\n"+"Herramienta de limpieza: "+cant4+"\n"+"Herramienta de riego: "+cant5+"\n"+"Herramienta de transporte: " +cant6+"\n"+"Total de herramientas segun tipo de motor: "+cant7+" electricas, "+cant8+" a combustible"+"\n";
		}
		else if(datos.get(0) instanceof Decoracion)
		{
			for(int i=0; i<datos.size(); i++)
			{
			    Decoracion decoracion= (Decoracion) datos.get(i);
				
				if(decoracion.getStock()>0)
				{
					if(decoracion.isExterior())
					{
						cant1++;
					}
					else 
					{
						cant2++;
					}
				
				}	
			}
			mensaje="Existe actualmente en stock: \n"+"Elemento decorativo de exterior: "+cant1+"\n"+"Elemento decorativo de interior: "+cant2+"\n";
		}
		else if(datos.get(0) instanceof MuebleJardin)
		{
			for(int i=0; i<datos.size(); i++)
			{
			    MuebleJardin mueble= (MuebleJardin) datos.get(i);
				
				if(mueble.getStock()>0)
				{
					if(mueble.getTipo().equalsIgnoreCase("mesa"))
					{
						cant1++;
					}
					else if(mueble.getTipo().equalsIgnoreCase("silla"))
					{
						cant2++;
					}
					else if(mueble.getTipo().equalsIgnoreCase("reposera"))
					{
						cant3++;
					}
					else if(mueble.getTipo().equalsIgnoreCase("camastro"))
					{
						cant4++;
					}
					else
					{
						cant5++;
					}
				}	
			}
			mensaje="Existe actualmente en stock: \n"+"Mesas: "+cant1+"\n"+"Sillas: "+cant2+"\n"+"Reposeras: "+cant3+"\n"+"Camastros: "+cant4+"\n"+"Hamacas: "+cant5+"\n";
		}
		
		return mensaje;
  }
	
	public String stockNegativo()
	{
		StringBuilder builder= new StringBuilder();
		for(int i=0; i<datos.size(); i++)
		{
			if(datos.get(i).getStock()<1)
			{
				builder.append(datos.get(i).toString()+"\n");
			}
		}
		return builder.toString();
	}
	
	
	public String mostrarSegunPrecio()
	{
		Collections.sort(datos);
		StringBuilder builder= new StringBuilder();
		for(int i=0; i<datos.size(); i++)
		{
			builder.append(datos.get(i).toString()+"\n");
		}
		
		return builder.toString();
	}
	
	//ANALIZAR SI ESTO FUNCIONA....
	public String plantasFlorales()
	{
		StringBuilder builder= new StringBuilder();
		for(int i=0; i<datos.size(); i++)
		{
			if(datos.get(i) instanceof Planta) 
			{
				Planta planta= (Planta)datos.get(i); //--> funciona??
				if(planta.isFlor())
				{
					builder.append(planta.toString()+"\n");
				}
			}
		}
		return builder.toString();
	}
	
  
}
