package app;


import java.util.Scanner;

import domain.AccesoDatos;
import domain.Cliente;
import domain.Empleado;
import domain.Servicio;
import domain.Vivero;
import productos.Arbol;
import productos.Arbusto;
import productos.Decoracion;
import productos.HerramientaJardineria;
import productos.HerramientaManual;
import productos.HerramientaNoManual;
import productos.Hierba;
import productos.Lombriz;
import productos.Maceta;
import productos.MacetaPoliedro;
import productos.MacetaRedonda;
import productos.MuebleJardin;
import productos.Planta;
import productos.PlantaAcuatica;
import productos.Producto;
import productos.SanidadVegetal;
import productos.Semilla;
import productos.Sustrato;

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
		acceso.leerArchivoPedidos(vivero);
		
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
					System.out.println("1 - Ingresar un producto al sistema: \n");
					System.out.println("2 - Ingresar un servicio al sistema: \n");
					System.out.println("3 - Ingresar un cliente al sistema: \n");
					System.out.println("4 - Agregar producto al carrito: \n");
					System.out.println("5 - Agregar servicio al carrito: \n");
					System.out.println("6 - Abonar carrito: \n");
					
					/*
					 * menu buscar: producto, servicio, cliente, empleado
					 * menu eliminar: producto, servicio (los elimina del catalogo)
					 * menu mostrar: productos, empleados, clientes  
					 * menu pedidos: carritos impagos, historial total de compras, pedido de determinado cliente, 
					 * 				eliminar un producto del carrito, eliminar pedido impago completo
					 * menu estadisticas (centro informativo)
					 * menu modificaciones: stock, atributos en general
					 */
			
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
						boolean tieneFruto=false;
						boolean tieneSemilla=false;
						boolean deInterior=false;
						String especie=null;
						int diametroDelTronco=0;
						String corteza=null;
						String tipoHoja=null;
						boolean trepador=false;
						String tipoDeTallo=null;
						boolean comestible=false;
						boolean medicinal=false;
						int gramos=0;
						boolean abonada=false;
						String tipoDeSuelo=null;
						String destinoS=null;
						String funcion=null;
						int centimetroCubico=0;
						String forma=null;
						double alto=0;
						double ancho=0;
						double largo=0;
						double diametroBoca=0;
						double base=0;
						String material=null;
						String tamanio=null;
						boolean reforzada=false;
						int potencia=0;
						String tipoMotor=null;
						String consumo=null;
						String color=null;
						
						
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
									scan.nextLine();
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
									scan.nextLine();
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
							
							break;
						}
						case 2: //Arbol
						{
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
									System.out.println("Ingrese los meses de vida actual del arbol por favor");
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
									System.out.println("Ingrese la estacion de plantacion del arbol por favor(otonio, invierno, primavera, verano, anual)");
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
									System.out.println("Ingrese el habitat del arbol por favor(pradera, desierto, bosque, pantano, polar, savana, selva)");
									habitat=scan.nextLine();
								    mensaje=Planta.validarHabitatLlamada("terrestre", habitat);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la altura actual del arbol por favor");
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
									System.out.println("Ingrese si el arbol posee flor por favor (true/false)");
									tieneflor=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneflor);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el nivel de exposicion solar del arbol por favor (sombra, mediasombra, pleno sol)");
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
									System.out.println("Ingrese el tipo de raiz del arbol por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
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
									System.out.println("Ingrese si el arbol es aromatico por favor (true/false)");
									esAromatica=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(esAromatica);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si el arbol posee fruto por favor (true/false)");
									tieneFruto=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneFruto);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si el arbol tiene semilla por favor (true/false)");
									tieneSemilla=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneSemilla);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si es apto interior por favor (true/false)");
									deInterior=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(deInterior);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la especie del arbol por favor");
									especie=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(especie);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el diametro del tronco del arbol por favor (mayor a 1 y menor a 131)");
									diametroDelTronco=scan.nextInt();
									scan.nextLine();
								    mensaje=Arbol.validarDiametroTroncoLlamada(diametroDelTronco);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de corteza del arbol por favor (laminada, geometrica, rugosa, lobulada, placoide, escasa)");
									corteza=scan.nextLine();
									scan.nextLine();
								    mensaje=Arbol.validarTipoCortezaLlamada(corteza);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								Producto producto = new Arbol(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura, tieneflor,
										nivelDeExposicionSolar, tipoRaiz, esAromatica, tieneFruto, tieneSemilla, deInterior, especie, diametroDelTronco, corteza);
								vivero.agregarElemento(producto);
								System.out.println("Producto agregado con exito");
								
							break;
						}
						case 3: //Arbusto
						{
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
									System.out.println("Ingrese los meses de vida actual del arbusto por favor");
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
									System.out.println("Ingrese la estacion de plantacion del arbusto por favor(otonio, invierno, primavera, verano, anual)");
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
									System.out.println("Ingrese el habitat del arbusto por favor(pradera, desierto, bosque, pantano, polar, savana, selva)");
									habitat=scan.nextLine();
								    mensaje=Planta.validarHabitatLlamada("terrestre", habitat);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la altura actual del arbusto por favor");
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
									System.out.println("Ingrese si el arbusto posee flor por favor (true/false)");
									tieneflor=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneflor);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el nivel de exposicion solar del arbusto por favor (sombra, mediasombra, pleno sol)");
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
									System.out.println("Ingrese el tipo de raiz del arbusto por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
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
									System.out.println("Ingrese si el arbusto es aromatica por favor (true/false)");
									esAromatica=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(esAromatica);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si el arbusto tiene fruto por favor (true/false)");
									tieneFruto=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneFruto);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si el arbusto tiene semilla por favor (true/false)");
									tieneSemilla=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneSemilla);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si el arbusto es apto interior por favor (true/false)");
									deInterior=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(deInterior);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de hoja del arbusto por favor (caduca, semicaduca, persistente)");
									tipoHoja=scan.nextLine();
								    mensaje=Arbusto.validarTipoHojaLLamada(tipoHoja);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la especie del arbusto por favor");
									especie=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(especie);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si es arbusto trepador por favor (true/false)");
									trepador=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(trepador);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
							
							Producto producto = new Arbusto(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura, tieneflor,
									nivelDeExposicionSolar, tipoRaiz, esAromatica, tieneFruto, tieneSemilla, deInterior, especie, tipoHoja, trepador);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							System.out.println(vivero.mostrarProductos());
							
							break;
						}
						case 4: //Hierba
						{
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
									System.out.println("Ingrese los meses de vida actual de la hierba por favor");
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
									System.out.println("Ingrese la estacion de plantacion de la hierba por favor(otonio, invierno, primavera, verano, anual)");
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
									System.out.println("Ingrese el habitat de la hierba por favor(pradera, desierto, bosque, pantano, polar, savana, selva)");
									habitat=scan.nextLine();
								    mensaje=Planta.validarHabitatLlamada("terrestre", habitat);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la altura actual de la hierba por favor");
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
									System.out.println("Ingrese si la hierba posee flor por favor (true/false)");
									tieneflor=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneflor);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el nivel de exposicion solar de la hierba por favor (sombra, mediasombra, pleno sol)");
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
									System.out.println("Ingrese el tipo de raiz de la hierba por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
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
									System.out.println("Ingrese si la hierba es aromatica por favor (true/false)");
									esAromatica=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(esAromatica);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la hierba tiene fruto por favor (true/false)");
									tieneFruto=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneFruto);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la hierba tiene semilla por favor (true/false)");
									tieneSemilla=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(tieneSemilla);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la hierba es apta interior por favor (true/false)");
									deInterior=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(deInterior);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de tallo de la hierba por favor (erguido, rastrero, voluble, segmentado)");
									tipoDeTallo=scan.nextLine();
								    mensaje=Hierba.validarTipoTalloLlamada(tipoDeTallo);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la especie del arbusto por favor");
									especie=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(especie);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la hierba es comestible por favor (true/false)");
									comestible=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(comestible);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si la hierba es medicinal por favor (true/false)");
									medicinal=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(medicinal);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
							
							Producto producto = new Hierba(nombre, marca, stock, descripcion, mesesDeVida, estacionPlantacion, habitat, altura, tieneflor,
									nivelDeExposicionSolar, tipoRaiz, esAromatica, tieneFruto, tieneSemilla, deInterior, especie, 
									tipoDeTallo, comestible, medicinal);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							System.out.println(vivero.mostrarProductos());
							break;
						}

						case 5: //Sustrato 
						{
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
								System.out.println("Ingrese la cantidad en gramos por favor");
								gramos=scan.nextInt();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(gramos);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese si el sustrato esta abonado por favor (true/false)");
								abonada=scan.nextBoolean();
								scan.nextLine();
							    mensaje=Producto.validarBooleanLlamada(abonada);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el tipo de suelo al que esta destinado por favor(calizo, humifero, arcilloso, arenoso, pedregoso, mixto)");
								tipoDeSuelo=scan.nextLine();
							    mensaje=Sustrato.validarTipoSueloLlamada(tipoDeSuelo);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							Producto producto = new Sustrato(nombre, marca, stock, descripcion, gramos, abonada, tipoDeSuelo);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");	
							break;
						}
						case 6: //Semilla
						{
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
								System.out.println("Ingrese la cantidad en gramos por favor");
								gramos=scan.nextInt();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(gramos);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el destino de la semilla por favor (huerta, floral, cesped)");
								destinoS=scan.nextLine();
							    mensaje=Semilla.validarDestinoSemillaLlamada(destinoS);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							Producto producto = new Semilla(nombre, marca, stock, descripcion, gramos, destinoS);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");	
							break;
						}
						case 7: //Sanidad Vegetal
						{
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
								System.out.println("Ingrese la funcion por favor(insecticida, funguicida, herbicida)");
								funcion=scan.nextLine();
								
							    mensaje=SanidadVegetal.validarSanidadLlamada(funcion);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el volumen en cm cubicos por favor");
								centimetroCubico=scan.nextInt();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(centimetroCubico);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							Producto producto = new SanidadVegetal(nombre, marca, stock, descripcion, funcion, centimetroCubico);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");	
							break;
						}
						case 8: //Lombriz
						{
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
								System.out.println("Ingrese la cantidad en gramos por favor");
								gramos=scan.nextInt();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(gramos);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 

							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese la especie de la lombriz por favor (rayada, dendra, roja, comun)");
								especie=scan.nextLine();
								
							    mensaje=Lombriz.validarEspecieLombrizLlamada(especie);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							Producto producto = new Lombriz(nombre, marca, stock, descripcion, gramos, especie);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 9: //Maceta Redonda
						{
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
								System.out.println("Ingrese el material de la maceta por favor");
								material=scan.nextLine();
								
							    mensaje=Producto.validarCadenaCaracteresLlamada(material);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese la forma de la maceta por favor(circular, ovalada)");
								forma=scan.nextLine();
								
							    mensaje=Maceta.validarFormaMacetaLlamada("redonda", forma);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el diametro en cm de la boca de la maceta por favor");
								diametroBoca=scan.nextDouble();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(diametroBoca);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el largo en cm de la base por favor");
								base=scan.nextDouble();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(base);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							Producto producto = new MacetaRedonda(nombre, marca, stock, descripcion, material, forma, diametroBoca, base);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 10: //Maceta poliedro
						{
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
								System.out.println("Ingrese el material de la maceta por favor");
								material=scan.nextLine();
								
							    mensaje=Producto.validarCadenaCaracteresLlamada(material);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese la forma de la maceta por favor(jardinera, cubo, piramidal)");
								forma=scan.nextLine();
								
							    mensaje=Maceta.validarFormaMacetaLlamada("poliedro", forma);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el alto en cm por favor");
								alto=scan.nextDouble();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(alto);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el ancho en cm por favor");
								ancho=scan.nextDouble();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(ancho);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							mensaje="";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el largo en cm por favor");
								largo=scan.nextDouble();
								scan.nextLine();
							    mensaje=Producto.validarValorNumericoLlamada(largo);
							    if(mensaje!=null)
							    {
							    	System.out.println(mensaje);
							    }
							} 
							
							Producto producto = new MacetaPoliedro(nombre, marca, stock, descripcion, material, forma, 
									alto, ancho, largo);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 11:
						{
							mensaje=" ";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el nombre de la herramienta por favor");
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
								System.out.println("Ingrese la marca de la herramienta por favor");
								marca=scan.nextLine();
								mensaje=Producto.validarCadenaCaracteresLlamada(marca);
								if(mensaje!=null)
								{
									System.out.println(mensaje);
								}
							}
							
							mensaje=" ";
							
							while(mensaje!=null)
							{
								System.out.println("Ingrese el stock actual de la herramienta por favor");
								stock=scan.nextInt();
								scan.nextLine();
								mensaje=Producto.validarValorNumericoLlamada(stock);
								if(mensaje!=null)
								{
									System.out.println(mensaje);
								}
							}
							
							
								System.out.println("Ingrese la descripcion de la herramienta por favor");
								descripcion=scan.nextLine();
				
							mensaje=" ";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el material de la herramienta por favor");
								material=scan.nextLine();
								mensaje=Producto.validarCadenaCaracteresLlamada(material);
								if(mensaje!=null)
								{
									System.out.println(mensaje);
								}
							}
							mensaje=" ";
							while(mensaje!=null)
							{
								System.out.println("Ingrese la funcion de la herramienta por favor(corte, desmalezado, labrado, limpieza, riego, transporte)");
								funcion=scan.nextLine();
								mensaje=HerramientaJardineria.validarFuncionHerramientaLlamada(funcion);
								if(mensaje!=null)
								{
									System.out.println(mensaje);
								}
							}
							
							mensaje=" ";
							while(mensaje!=null)
							{
								System.out.println("Ingrese el tamañio de la herramienta por favor (grande-mediano-pequeña)");
								tamanio=scan.nextLine();
								mensaje=HerramientaManual.validarTamanioHManualLlamada(tamanio);
								if(mensaje!=null)
								{
									System.out.println(mensaje);
								}
							}
							
							mensaje=" ";
							while(mensaje!=null)
							{
								System.out.println("Es reforzada (True/False)");
								reforzada=scan.nextBoolean();
								mensaje=Producto.validarBooleanLlamada(reforzada);
								if(mensaje!=null)
								{
									System.out.println(mensaje);
								}
							}
							
							Producto producto = new HerramientaManual(nombre, marca, stock, descripcion, material, funcion, tamanio, reforzada);
							vivero.agregarElemento(producto);
							System.out.println("Herramienta manual agregada con exito");
							System.out.println(vivero.mostrarProductos());
							break;
						}
						case 12:
						{
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
									System.out.println("Ingrese el material de la herramienta por favor");
									material=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(material);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
							
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la funcion de la herramienta por favor(corte, desmalezado, labrado, limpieza, riego, transporte)");
									funcion=scan.nextLine();
								    mensaje=HerramientaJardineria.validarFuncionHerramientaLlamada(funcion);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese la potencia de la herramienta por favor");
									potencia=scan.nextInt();
									scan.nextLine();
								    mensaje=Producto.validarValorNumericoLlamada(potencia);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de motor de la herramienta por favor(electrico, combustible)");
									tipoMotor=scan.nextLine();
								    mensaje=HerramientaNoManual.validarMotorLlamada(tipoMotor);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el consumor del motor de la herramienta por favor(alto, medio, bajo)");
									consumo=scan.nextLine();
								    mensaje=HerramientaNoManual.validarConsumoLlamada(consumo);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
							
							
							Producto producto= new HerramientaNoManual(nombre, marca, stock, descripcion, material, funcion, potencia, tipoMotor, consumo);
							vivero.agregarElemento(producto);
							System.out.println("Herramienta agregada con exito\n");
							break;
						}
						case 13:
						{
	
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
									System.out.println("Ingrese el material principal del mueble por favor");
									material=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(material);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el tipo de mueble de jardin por favor(mesa, silla, reposera, camastro, hamaca)");
									tipo=scan.nextLine();
								    mensaje=MuebleJardin.validarTipoMuebleLlamada(tipo);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el alto en cm por favor");
									alto=scan.nextDouble();
									scan.nextLine();
								    mensaje=Producto.validarValorNumericoLlamada(alto);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el ancho en cm por favor");
									ancho=scan.nextDouble();
									scan.nextLine();
								    mensaje=Producto.validarValorNumericoLlamada(ancho);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el largo en cm por favor");
									largo=scan.nextDouble();
									scan.nextLine();
								    mensaje=Producto.validarValorNumericoLlamada(largo);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								Producto producto= new MuebleJardin(nombre, marca, stock, descripcion, material, tipo, alto, largo, ancho);
								vivero.agregarElemento(producto);
								System.out.println("Mueble de jardin agregado con exito");
								break;
						}
						case 14:
						{
	
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
									System.out.println("Ingrese el material principal por favor");
									material=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(material);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese si es apto exterior por favor (true/false)");
									deInterior=scan.nextBoolean();
									scan.nextLine();
								    mensaje=Producto.validarBooleanLlamada(deInterior);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								} 
								
								mensaje="";
								while(mensaje!=null)
								{
									System.out.println("Ingrese el color primario por favor");
									color=scan.nextLine();
								    mensaje=Producto.validarCadenaCaracteresLlamada(color);
								    if(mensaje!=null)
								    {
								    	System.out.println(mensaje);
								    }
								}
								
								
								Producto producto= new Decoracion(nombre, marca, stock, descripcion, material, deInterior, color);
								vivero.agregarElemento(producto);
								System.out.println("Objeto decorativo agregado con exito");
								System.out.println(vivero.mostrarProductos());
								break;
							
						}
						default:
						System.out.println("Opcion erronea");
						}

				     break;
					}
					case 2:
					{
						String descripcion=null;
						boolean materialesIncluidos=false;
						String nombre=null;
						double precio=0;
						
						mensaje=" "; 
						while(mensaje!=null)
						{
							System.out.println("Ingrese el nombre del servicio por favor: ");
							nombre=scan.nextLine();
							mensaje=Producto.validarCadenaCaracteresLlamada(nombre);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						
						
							System.out.println("Ingrese la descripcion del servicio por favor: ");
							descripcion=scan.nextLine();
						
							
						mensaje=" ";
						while(mensaje!=null)
						{
							System.out.println("¿Estan los materiales incluidos? Ingrese true/false");
							materialesIncluidos=scan.nextBoolean();
							scan.nextLine();
							mensaje=Producto.validarBooleanLlamada(materialesIncluidos);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						
						mensaje=" ";
						while(mensaje!=null)
						{
							System.out.println("Ingrese el costo del servicio por favor: ");
							precio=scan.nextDouble();
							mensaje=Producto.validarValorNumericoLlamada(precio);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						
						Servicio miServicio=new Servicio(nombre, precio, descripcion, materialesIncluidos);
						vivero.agregarElemento(miServicio);
						System.out.println("El servicio fue agregado con exito");
						break;
					}
					case 3: 
					{
						String nombre=null;
						String apellido=null;
						String telefono=null;
						String dni=null;
						
						mensaje=" "; 
						while(mensaje!=null)
						{
							System.out.println("Ingrese el nombre del cliente por favor: ");
							nombre=scan.nextLine();
							mensaje=Producto.validarCadenaCaracteresLlamada(nombre);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						
						mensaje=" ";
						while(mensaje!=null)
						{
							System.out.println("Ingrese el apellido del cliente por favor: ");
							apellido=scan.nextLine();
							mensaje=Producto.validarCadenaCaracteresLlamada(apellido);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						
						mensaje=" ";
						while(mensaje!=null)
						{
							System.out.println("Ingrese el telefono del cliente por favor: ");
							telefono=scan.nextLine();
							mensaje=Cliente.validarCadenaCaracteresTELDNILlamada(telefono);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						mensaje=" ";
						
						while(mensaje!=null)
						{
							System.out.println("Ingrese el DNI del cliente por favor: (solo numeros)");
							dni=scan.nextLine();
							mensaje=Cliente.validarCadenaCaracteresTELDNILlamada(dni);
							if(mensaje!=null)
							{
								System.out.println(mensaje);
							}
						}
						
						Cliente miCliente= new Cliente(nombre, apellido, telefono, dni);
						vivero.agregarElemento(miCliente);
						System.out.println("El nuevo cliente fue agregado con exito");
						System.out.println(vivero.mostrarClientes());
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
		acceso.escribirArchivoPedidos(vivero);
	}
	

}
