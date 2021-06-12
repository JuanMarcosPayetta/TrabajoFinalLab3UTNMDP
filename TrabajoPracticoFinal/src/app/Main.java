package app;

import java.io.File;
import java.util.Scanner;

import domain.AccesoDatos;
import domain.Empleado;
import domain.Vivero;
import productos.Planta;
import productos.PlantaAcuatica;
import productos.Producto;

public class Main {

	static Scanner scan;
	public static void main(String[] args) {
	
		scan= new Scanner(System.in);
		int opcion=0;
		boolean validarEmpleado=false;
		Vivero vivero= new Vivero();
		AccesoDatos acceso= new AccesoDatos();
		
		acceso.leerArchivoProductos(vivero);
		acceso.leerArchivoServicios(vivero);
		acceso.leerArchivoEmpleados(vivero);
		acceso.leerArchivoClientes(vivero);
		
		while(!validarEmpleado)
		{
			System.out.println("Ingrese la opcion deseada por favor:\n");
			System.out.println("1-Crear un nuevo empleado: ");
			System.out.println("2-Ingresar al sistema: ");
			String pass=null;
			String mensaje="";
			
			opcion=scan.nextInt();
			scan.nextLine();
			
			switch (opcion) {
			case 1: {
				String nombre=null;
				String apellido=null;
		      
			while(mensaje!=null)
			{
				System.out.println("Ingrese el nombre del empleado por favor: \n");
				nombre=scan.nextLine();
			    mensaje=Empleado.validarCadenaCaracteresLlamada(nombre);
			    if(mensaje!=null)
			    {
			    	System.out.println(mensaje);
			    }
			}
			
			mensaje="";
			while(mensaje!=null)
			{
				System.out.println("Ingrese el apellido del empleado por favor: \n");
				apellido=scan.nextLine();
			    mensaje=Empleado.validarCadenaCaracteresLlamada(apellido);
			    if(mensaje!=null)
			    {
			    	System.out.println(mensaje);
			    }
			}
			
			mensaje="";
			while(mensaje!=null)
			{
				System.out.println("Ingrese la contrasenia por favor(longitud minima de 8 caracteres, numeros, letra mayuscula)\n");
				pass=scan.nextLine();
			    mensaje=Empleado.validarContraseniaLlamada(pass);
			    if(mensaje!=null)
			    {
			    	System.out.println(mensaje);
			    }
			}
			
			Empleado empleado= new Empleado(nombre, apellido, pass);
			vivero.agregarElemento(empleado);
			System.out.println("Empleado creado con exito. Su ID es: "+empleado.getID());
			System.out.println(vivero.mostrarEmpleados());
			break;
			
			}
			case 2:
			{
				boolean encontrado=false;
				char opcion2=0;
				while(opcion2!='n')
				{
					System.out.println("Ingrese su ID de empleado por favor\n");
					int idE=scan.nextInt();
					scan.nextLine();
					System.out.println("Ingrese su contrasenia de empleado por favor\n");
					pass=scan.nextLine();
					
					encontrado=vivero.buscarEmpleadoRegistrado(pass, idE);
					System.out.println(encontrado);
					if(!encontrado)
					{
						System.out.println("Error, su ID u contraseñia son incorrectos. Si desea continuar presione cualquier tecla, caso contrario 'n':\n");
						opcion2=scan.next().charAt(0);
					}
					else
					{
						opcion2='n';
						validarEmpleado=true;
					}
				}
				
				break;
			}
			default:
				System.out.println("Opcion erronea");
			}
			
			//switch general
			if(validarEmpleado)
			{
				char opcion2=0;
				int opcion3=0;
		
				 while(opcion2!='n')
				{
					System.out.println("Ingrese la opcion deseada por favor: \n");
					System.out.println("1-Ingresar un producto al sistema: \n");
					System.out.println("2-Ingresar un servicio al sistema: \n");
					System.out.println("3-Ingresar un cliente al sistema: \n");
			
					opcion3=scan.nextInt();
					scan.nextLine();
					
					switch (opcion3) {
					case 1: {
						System.out.println("Seleccione el producto a ingresar: \n");
						System.out.println("1-Planta Acuatica: \n");
						System.out.println("2-Arbol: \n");
						System.out.println("3-Arbusto: \n");
						System.out.println("4-Hierba: \n");
						System.out.println("5-Sustrato: \n");
						System.out.println("6-Semillas: \n");
						System.out.println("7-Sanidad Vegetal: \n");
						System.out.println("8-Lombrices: \n");
						System.out.println("9-Maceta Redonda: \n");
						System.out.println("10-Maceta Poliedro: \n");
						System.out.println("11-Herramienta manual: \n");
						System.out.println("12-Herramienta electrica/combustible: \n");
						System.out.println("13-Mueble de jardin: \n");
						System.out.println("14-Decoracion: \n");
						
						opcion3=scan.nextInt();
						scan.nextLine();
						
						String nombre=null;
						String marca=null;
						int stock=0;
						String descripcion=null;
						int mesesDeVida=0;
						String estacionPlantacion=null;
						String habitat=null;
						int altura=0;
						boolean tieneflor=false;
						String nivelDeExposicionSolar=null;
						String tipoRaiz=null;
						boolean esAromatica=false;
						String tipoDeAgua=null;
						int temperaturaAgua=0;
						String durezaAgua=null;
						String tipo=null;
						
						switch (opcion3) {
						case 1: {

							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el nombre por favor");
								nombre=scan.nextLine();
							    mensaje=Producto.validarCadenaCaracteresLlamada(nombre);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese la marca por favor");
								marca=scan.nextLine();
							    mensaje=Producto.validarCadenaCaracteresLlamada(marca);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el stock actual por favor");
								stock=scan.nextInt();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(stock);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
								System.out.println("Ingrese la descripcion por favor");
								descripcion=scan.nextLine();
							   
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese los meses de vida actual de la planta por favor");
									mesesDeVida=scan.nextInt();
									scan.nextLine();
								    mensaje=Producto.validarValorNumericoLlamada(mesesDeVida);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la estacion de plantacion la planta por favor(otonio, invierno, primavera, verano, anual)");
									estacionPlantacion=scan.nextLine();
								    mensaje=Planta.validarEstacionLlamada(estacionPlantacion);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el habitat de la planta por favor(rio, lago, arrecife, estanque, pantano, delta)");
									habitat=scan.nextLine();
								    mensaje=Planta.validarHabitatLlamada("acuatica", habitat);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la altura actual de la planta por favor");
									altura=scan.nextInt();
									scan.nextLine();
								    mensaje=Producto.validarValorNumericoLlamada(altura);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la planta posee flor por favor (true/false)");
									tieneflor=scan.nextBoolean();
								    mensaje=Producto.validarBooleanLlamada(tieneflor);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el nivel de exposicion solar de la planta por favor (sombra, mediasombra, pleno sol)");
									nivelDeExposicionSolar=scan.nextLine();
								    mensaje=Planta.validarExpSolarLlamada(nivelDeExposicionSolar);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de raiz de la planta por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
									tipoRaiz=scan.nextLine();
								    mensaje=Planta.validarTipoRaizLlamada(tipoRaiz);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la planta es aromatica por favor (true/false)");
									esAromatica=scan.nextBoolean();
								    mensaje=Producto.validarBooleanLlamada(esAromatica);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								

								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de agua de la planta por favor (dulce, salada)");
									tipoDeAgua=scan.nextLine();
								    mensaje=PlantaAcuatica.validarTipoDeAguaLlamada(tipoDeAgua);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la temperatura promedio del agua de la planta por favor (mayor o igual a 20 y menor o igual a 27)");
									temperaturaAgua=scan.nextInt();
									scan.nextLine();
								    mensaje=PlantaAcuatica.validarTempAguaLlamada(temperaturaAgua);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la dureza del agua de la planta por favor (blanda, moderada, dura)");
									durezaAgua=scan.nextLine();
								    mensaje=PlantaAcuatica.validarDurezaAguaLlamada(durezaAgua);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de planta acuatica por favor (flotante, oxigenante, ribera, profundidades)");
									tipo=scan.nextLine();
								    mensaje=PlantaAcuatica.validarTipoPlantaLlamada(tipo);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								Producto producto= new PlantaAcuatica(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura, tieneflor, nivelDeExposicionSolar, tipoRaiz, esAromatica, tipoDeAgua, temperaturaAgua, durezaAgua, tipo);
								vivero.agregarElemento(producto);
								System.out.println("Producto agregado con exito");
								System.out.println(vivero.mostrarProductos());
							
							break;
						}
						default:
						System.out.println("Opcion erronea");
						}
				
						
				     break;
					}
					
					default:
						System.out.println("Opcion erronea");
					}
					
					System.out.println("Si desea elegir otra opcion presione cualquier tecla, caso contrario 'n': ");
					opcion2=scan.next().charAt(0);
				}
				
			}
		}
		
		acceso.escribirArchivoProductos(vivero);
		acceso.escribirArchivoServicios(vivero);
		acceso.escribirArchivoEmpleados(vivero);
		acceso.escribirArchivoClientes(vivero);
	}
	

}
