package app;

import java.util.ArrayList;
import java.util.Scanner;

import domain.AccesoDatos;
import domain.CentroInformativo;
import domain.Cliente;
import domain.Empleado;
import domain.Pedido;
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
import servicios.Servicio;

/**
 * 
 * Clase principal Clase ejecutable contiene un switch para agilizar el uso del
 * programa
 * 
 * @author Dolores Bruzzone
 * @author Juan Marcos Payetta
 * @author Pablo Adrian Payetta
 * @version 1.0
 */
public class Main {

	static Scanner scan;

	public static void main(String[] args) {

		scan = new Scanner(System.in);
		int opcion = 0;
		boolean validarEmpleado = false;
		int idEmpleado = 0;
		Vivero vivero = new Vivero();
		AccesoDatos acceso = new AccesoDatos();

		acceso.leerArchivoProductos(vivero);
		acceso.leerArchivoServicios(vivero);
		acceso.leerArchivoEmpleados(vivero);
		acceso.leerArchivoClientes(vivero);
		acceso.leerArchivoPedidos(vivero);

		while (!validarEmpleado) {
			System.out.println("Ingrese la opcion deseada por favor:\n");
			System.out.println("1-Crear un nuevo empleado: \n");
			System.out.println("2-Ingresar al sistema: \n");
			System.out.println("3-Salir del sistema: \n");

			String pass = null;
			String mensaje = "";

			while (!scan.hasNextInt())
				scan.next();
			opcion = scan.nextInt();
			scan.nextLine();

			switch (opcion) {
			case 1: {
				String nombre = null;
				String apellido = null;

				while (mensaje != null) {
					System.out.println("Ingrese el nombre del empleado por favor: \n");
					nombre = scan.nextLine();
					mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
					if (mensaje != null) {
						System.out.println(mensaje);
					}
				}

				mensaje = "";
				while (mensaje != null) {
					System.out.println("Ingrese el apellido del empleado por favor: \n");
					apellido = scan.nextLine();
					mensaje = Producto.validarCadenaCaracteresLlamada(apellido);
					if (mensaje != null) {
						System.out.println(mensaje);
					}
				}

				mensaje = "";
				while (mensaje != null) {
					System.out.println(
							"Ingrese la contrasenia por favor(longitud minima de 8 caracteres, numeros, letra mayuscula)\n");
					pass = scan.nextLine();
					mensaje = Empleado.validarContraseniaLlamada(pass);
					if (mensaje != null) {
						System.out.println(mensaje);
					}
				}

				Empleado empleado = null;
				int idMayor = vivero.buscarMayorIdEmpleado();
				if (idMayor == -1) {
					empleado = new Empleado(1, nombre, apellido, pass);
				} else {
					empleado = new Empleado(idMayor + 1, nombre, apellido, pass);
				}

				if (empleado != null) {
					vivero.agregarElemento(empleado);
					System.out.println("Empleado creado con exito. Su ID es: " + empleado.getID());
				}

				break;

			}
			case 2: {
				boolean encontrado = false;
				char opcion2 = 0;
				while (opcion2 != 'n') {
					System.out.println("Ingrese su ID de empleado por favor\n");
					while (!scan.hasNextInt())
						scan.next();
					int idE = scan.nextInt();
					scan.nextLine();

					System.out.println("Ingrese su contrasenia de empleado por favor\n");
					pass = scan.nextLine();

					encontrado = vivero.buscarEmpleadoRegistrado(pass, idE);
					System.out.println(encontrado);
					if (!encontrado) {
						System.out.println(
								"Error, su ID u contrase�ia son incorrectos. Si desea continuar presione cualquier tecla, caso contrario 'n':\n");
						opcion2 = scan.next().charAt(0);
					} else {
						opcion2 = 'n';
						validarEmpleado = true;
						idEmpleado = idE;
					}
				}

				break;
			}
			case 3: {
				return;
			}
			default:
				System.out.println("Opcion erronea");
			}

			// switch general
			if (validarEmpleado) {
				char opcion2 = 0;
				int opcion3 = 0;

				while (opcion2 != 'n') {
					System.out.println("Ingrese la opcion deseada por favor: \n");
					System.out.println("1 - Ingresar un producto al sistema: \n");
					System.out.println("2 - Ingresar un servicio al sistema: \n");
					System.out.println("3 - Ingresar un cliente al sistema: \n");
					System.out.println("4 - Menu Pedidos: \n");
					System.out.println("5 - Menu Busquedas: \n");
					System.out.println("6 - Menu Eliminar: \n");
					System.out.println("7 - Menu Mostrar: \n");
					System.out.println("8 - Menu Modificar: \n");
					System.out.println("9 - Menu Informativo: \n");
					System.out.println("10 -Salir del sistema: \n");

					while (!scan.hasNextInt())
						scan.next();
					opcion3 = scan.nextInt();
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

						while (!scan.hasNextInt())
							scan.next();
						opcion3 = scan.nextInt();
						scan.nextLine();

						String nombre = null;
						String marca = null;
						int stock = 0;
						String descripcion = null;
						int mesesDeVida = 0;
						String estacionPlantacion = null;
						String habitat = null;
						int altura = 0;
						boolean tieneflor = false;
						String nivelDeExposicionSolar = null;
						String tipoRaiz = null;
						boolean esAromatica = false;
						String tipoDeAgua = null;
						int temperaturaAgua = 0;
						String durezaAgua = null;
						String tipo = null;
						boolean tieneFruto = false;
						boolean tieneSemilla = false;
						boolean deInterior = false;
						String especie = null;
						int diametroDelTronco = 0;
						String corteza = null;
						String tipoHoja = null;
						boolean trepador = false;
						String tipoDeTallo = null;
						boolean comestible = false;
						boolean medicinal = false;
						int gramos = 0;
						boolean abonada = false;
						String tipoDeSuelo = null;
						String destinoS = null;
						String funcion = null;
						int centimetroCubico = 0;
						String forma = null;
						double alto = 0;
						double ancho = 0;
						double largo = 0;
						double diametroBoca = 0;
						double base = 0;
						String material = null;
						String tamanio = null;
						boolean reforzada = false;
						int potencia = 0;
						String tipoMotor = null;
						String consumo = null;
						String color = null;

						switch (opcion3) {
						case 1: {

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese los meses de vida actual de la planta por favor");
								while (!scan.hasNextInt())
									scan.next();
								mesesDeVida = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(mesesDeVida);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la estacion de plantacion la planta por favor(otonio, invierno, primavera, verano, anual)");
								estacionPlantacion = scan.nextLine();
								mensaje = Planta.validarEstacionLlamada(estacionPlantacion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el habitat de la planta por favor(rio, lago, arrecife, estanque, pantano, delta)");
								habitat = scan.nextLine();
								mensaje = Planta.validarHabitatLlamada("acuatica", habitat);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la altura actual de la planta por favor");
								while (!scan.hasNextInt())
									scan.next();
								altura = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(altura);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la planta posee flor por favor (true/false)");
								tieneflor = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneflor);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el nivel de exposicion solar de la planta por favor (sombra, mediasombra, pleno sol)");
								nivelDeExposicionSolar = scan.nextLine();
								mensaje = Planta.validarExpSolarLlamada(nivelDeExposicionSolar);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de raiz de la planta por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
								tipoRaiz = scan.nextLine();
								mensaje = Planta.validarTipoRaizLlamada(tipoRaiz);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la planta es aromatica por favor (true/false)");
								esAromatica = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(esAromatica);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el tipo de agua de la planta por favor (dulce, salada)");
								tipoDeAgua = scan.nextLine();
								mensaje = PlantaAcuatica.validarTipoDeAguaLlamada(tipoDeAgua);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la temperatura promedio del agua de la planta por favor (mayor o igual a 20 y menor o igual a 27)");
								while (!scan.hasNextInt())
									scan.next();
								temperaturaAgua = scan.nextInt();
								scan.nextLine();
								mensaje = PlantaAcuatica.validarTempAguaLlamada(temperaturaAgua);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la dureza del agua de la planta por favor (blanda, moderada, dura)");
								durezaAgua = scan.nextLine();
								mensaje = PlantaAcuatica.validarDurezaAguaLlamada(durezaAgua);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de planta acuatica por favor (flotante, oxigenante, ribera, profundidades)");
								tipo = scan.nextLine();
								mensaje = PlantaAcuatica.validarTipoPlantaLlamada(tipo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new PlantaAcuatica(nombre, marca, stock, descripcion, mesesDeVida,
									estacionPlantacion, habitat, altura, tieneflor, nivelDeExposicionSolar, tipoRaiz,
									esAromatica, tipoDeAgua, temperaturaAgua, durezaAgua, tipo);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");

							break;
						}
						case 2: // Arbol
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese los meses de vida actual del arbol por favor");
								while (!scan.hasNextInt())
									scan.next();
								mesesDeVida = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(mesesDeVida);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la estacion de plantacion del arbol por favor(otonio, invierno, primavera, verano, anual)");
								estacionPlantacion = scan.nextLine();
								mensaje = Planta.validarEstacionLlamada(estacionPlantacion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el habitat del arbol por favor(pradera, desierto, bosque, pantano, polar, sabana, selva)");
								habitat = scan.nextLine();
								mensaje = Planta.validarHabitatLlamada("terrestre", habitat);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la altura actual del arbol por favor");
								while (!scan.hasNextInt())
									scan.next();
								altura = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(altura);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbol posee flor por favor (true/false)");
								tieneflor = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneflor);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el nivel de exposicion solar del arbol por favor (sombra, mediasombra, pleno sol)");
								nivelDeExposicionSolar = scan.nextLine();
								mensaje = Planta.validarExpSolarLlamada(nivelDeExposicionSolar);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de raiz del arbol por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
								tipoRaiz = scan.nextLine();
								mensaje = Planta.validarTipoRaizLlamada(tipoRaiz);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbol es aromatico por favor (true/false)");
								esAromatica = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(esAromatica);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbol posee fruto por favor (true/false)");
								tieneFruto = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneFruto);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbol tiene semilla por favor (true/false)");
								tieneSemilla = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneSemilla);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si es apto interior por favor (true/false)");
								deInterior = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(deInterior);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la especie del arbol por favor");
								especie = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(especie);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el diametro del tronco del arbol por favor (mayor a 1 y menor a 131)");
								while (!scan.hasNextInt())
									scan.next();
								diametroDelTronco = scan.nextInt();
								scan.nextLine();
								mensaje = Arbol.validarDiametroTroncoLlamada(diametroDelTronco);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de corteza del arbol por favor (laminada, geometrica, rugosa, lobulada, placoide, escasa)");
								corteza = scan.nextLine();
								scan.nextLine();
								mensaje = Arbol.validarTipoCortezaLlamada(corteza);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Arbol(nombre, marca, stock, descripcion, mesesDeVida,
									estacionPlantacion, habitat, altura, tieneflor, nivelDeExposicionSolar, tipoRaiz,
									esAromatica, tieneFruto, tieneSemilla, deInterior, especie, diametroDelTronco,
									corteza);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");

							break;
						}
						case 3: // Arbusto
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese los meses de vida actual del arbusto por favor");
								while (!scan.hasNextInt())
									scan.next();
								mesesDeVida = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(mesesDeVida);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la estacion de plantacion del arbusto por favor(otonio, invierno, primavera, verano, anual)");
								estacionPlantacion = scan.nextLine();
								mensaje = Planta.validarEstacionLlamada(estacionPlantacion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el habitat del arbusto por favor(pradera, desierto, bosque, pantano, polar, savana, selva)");
								habitat = scan.nextLine();
								mensaje = Planta.validarHabitatLlamada("terrestre", habitat);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la altura actual del arbusto por favor");
								while (!scan.hasNextInt())
									scan.next();
								altura = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(altura);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbusto posee flor por favor (true/false)");
								tieneflor = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneflor);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el nivel de exposicion solar del arbusto por favor (sombra, mediasombra, pleno sol)");
								nivelDeExposicionSolar = scan.nextLine();
								mensaje = Planta.validarExpSolarLlamada(nivelDeExposicionSolar);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de raiz del arbusto por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
								tipoRaiz = scan.nextLine();
								mensaje = Planta.validarTipoRaizLlamada(tipoRaiz);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbusto es aromatica por favor (true/false)");
								esAromatica = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(esAromatica);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbusto tiene fruto por favor (true/false)");
								tieneFruto = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneFruto);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbusto tiene semilla por favor (true/false)");
								tieneSemilla = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneSemilla);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el arbusto es apto interior por favor (true/false)");
								deInterior = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(deInterior);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de hoja del arbusto por favor (caduca, semicaduca, persistente)");
								tipoHoja = scan.nextLine();
								mensaje = Arbusto.validarTipoHojaLLamada(tipoHoja);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la especie del arbusto por favor");
								especie = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(especie);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si es arbusto trepador por favor (true/false)");
								trepador = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(trepador);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Arbusto(nombre, marca, stock, descripcion, mesesDeVida,
									estacionPlantacion, habitat, altura, tieneflor, nivelDeExposicionSolar, tipoRaiz,
									esAromatica, tieneFruto, tieneSemilla, deInterior, especie, tipoHoja, trepador);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");

							break;
						}
						case 4: // Hierba
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese los meses de vida actual de la hierba por favor");
								while (!scan.hasNextInt())
									scan.next();
								mesesDeVida = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(mesesDeVida);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la estacion de plantacion de la hierba por favor(otonio, invierno, primavera, verano, anual)");
								estacionPlantacion = scan.nextLine();
								mensaje = Planta.validarEstacionLlamada(estacionPlantacion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el habitat de la hierba por favor(pradera, desierto, bosque, pantano, polar, savana, selva)");
								habitat = scan.nextLine();
								mensaje = Planta.validarHabitatLlamada("terrestre", habitat);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la altura actual de la hierba por favor");
								while (!scan.hasNextInt())
									scan.next();
								altura = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(altura);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba posee flor por favor (true/false)");
								tieneflor = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneflor);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el nivel de exposicion solar de la hierba por favor (sombra, mediasombra, pleno sol)");
								nivelDeExposicionSolar = scan.nextLine();
								mensaje = Planta.validarExpSolarLlamada(nivelDeExposicionSolar);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de raiz de la hierba por favor (axonomorfa, ramificada, fascicular, tuberosa, napiforme, adventicia)");
								tipoRaiz = scan.nextLine();
								mensaje = Planta.validarTipoRaizLlamada(tipoRaiz);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba es aromatica por favor (true/false)");
								esAromatica = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(esAromatica);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba tiene fruto por favor (true/false)");
								tieneFruto = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneFruto);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba tiene semilla por favor (true/false)");
								tieneSemilla = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(tieneSemilla);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba es apta interior por favor (true/false)");
								deInterior = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(deInterior);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de tallo de la hierba por favor (erguido, rastrero, voluble, segmentado)");
								tipoDeTallo = scan.nextLine();
								mensaje = Hierba.validarTipoTalloLlamada(tipoDeTallo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la especie del arbusto por favor");
								especie = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(especie);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba es comestible por favor (true/false)");
								comestible = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(comestible);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si la hierba es medicinal por favor (true/false)");
								medicinal = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(medicinal);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Hierba(nombre, marca, stock, descripcion, mesesDeVida,
									estacionPlantacion, habitat, altura, tieneflor, nivelDeExposicionSolar, tipoRaiz,
									esAromatica, tieneFruto, tieneSemilla, deInterior, especie, tipoDeTallo, comestible,
									medicinal);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}

						case 5: // Sustrato
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la cantidad en gramos por favor");
								while (!scan.hasNextInt())
									scan.next();
								gramos = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(gramos);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si el sustrato esta abonado por favor (true/false)");
								abonada = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(abonada);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de suelo al que esta destinado por favor(calizo, humifero, arcilloso, arenoso, pedregoso, mixto)");
								tipoDeSuelo = scan.nextLine();
								mensaje = Sustrato.validarTipoSueloLlamada(tipoDeSuelo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Sustrato(nombre, marca, stock, descripcion, gramos, abonada,
									tipoDeSuelo);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 6: // Semilla
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la cantidad en gramos por favor");
								while (!scan.hasNextInt())
									scan.next();
								gramos = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(gramos);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out
										.println("Ingrese el destino de la semilla por favor (huerta, floral, cesped)");
								destinoS = scan.nextLine();
								mensaje = Semilla.validarDestinoSemillaLlamada(destinoS);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Semilla(nombre, marca, stock, descripcion, gramos, destinoS);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 7: // Sanidad Vegetal
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la funcion por favor(insecticida, funguicida, herbicida)");
								funcion = scan.nextLine();

								mensaje = SanidadVegetal.validarSanidadLlamada(funcion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el volumen en cm cubicos por favor");
								while (!scan.hasNextInt())
									scan.next();
								centimetroCubico = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(centimetroCubico);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new SanidadVegetal(nombre, marca, stock, descripcion, funcion,
									centimetroCubico);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 8: // Lombriz
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la cantidad en gramos por favor");
								while (!scan.hasNextInt())
									scan.next();
								gramos = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(gramos);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la especie de la lombriz por favor (rayada, dendra, roja, comun)");
								especie = scan.nextLine();

								mensaje = Lombriz.validarEspecieLombrizLlamada(especie);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Lombriz(nombre, marca, stock, descripcion, gramos, especie);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 9: // Maceta Redonda
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el material de la maceta por favor");
								material = scan.nextLine();

								mensaje = Producto.validarCadenaCaracteresLlamada(material);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la forma de la maceta por favor(circular, ovalada)");
								forma = scan.nextLine();

								mensaje = Maceta.validarFormaMacetaLlamada("redonda", forma);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el diametro en cm de la boca de la maceta por favor");
								while (!scan.hasNextDouble())
									scan.next();
								diametroBoca = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(diametroBoca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el largo en cm de la base por favor");
								while (!scan.hasNextDouble())
									scan.next();
								base = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(base);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new MacetaRedonda(nombre, marca, stock, descripcion, material, forma,
									diametroBoca, base);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 10: // Maceta poliedro
						{
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el material de la maceta por favor");
								material = scan.nextLine();

								mensaje = Producto.validarCadenaCaracteresLlamada(material);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out
										.println("Ingrese la forma de la maceta por favor(jardinera, cubo, piramidal)");
								forma = scan.nextLine();

								mensaje = Maceta.validarFormaMacetaLlamada("poliedro", forma);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el alto en cm por favor");
								while (!scan.hasNextDouble())
									scan.next();
								alto = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(alto);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el ancho en cm por favor");
								while (!scan.hasNextDouble())
									scan.next();
								ancho = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(ancho);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el largo en cm por favor");
								while (!scan.hasNextDouble())
									scan.next();
								largo = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(largo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new MacetaPoliedro(nombre, marca, stock, descripcion, material, forma,
									alto, ancho, largo);
							vivero.agregarElemento(producto);
							System.out.println("Producto agregado con exito");
							break;
						}
						case 11: {
							mensaje = " ";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre de la herramienta por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}

							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca de la herramienta por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = " ";

							while (mensaje != null) {
								System.out.println("Ingrese el stock actual de la herramienta por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion de la herramienta por favor");
							descripcion = scan.nextLine();

							mensaje = " ";
							while (mensaje != null) {
								System.out.println("Ingrese el material de la herramienta por favor");
								material = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(material);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}
							mensaje = " ";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la funcion de la herramienta por favor(corte, desmalezado, labrado, limpieza, riego, transporte)");
								funcion = scan.nextLine();
								mensaje = HerramientaJardineria.validarFuncionHerramientaLlamada(funcion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = " ";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tama�io de la herramienta por favor (grande-mediano-peque�o)");
								tamanio = scan.nextLine();
								mensaje = HerramientaManual.validarTamanioHManualLlamada(tamanio);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = " ";
							while (mensaje != null) {
								System.out.println("Es reforzada (True/False)");
								reforzada = scan.nextBoolean();
								mensaje = Producto.validarBooleanLlamada(reforzada);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new HerramientaManual(nombre, marca, stock, descripcion, material,
									funcion, tamanio, reforzada);
							vivero.agregarElemento(producto);
							System.out.println("Herramienta manual agregada con exito");
							break;
						}
						case 12: {
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el material de la herramienta por favor");
								material = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(material);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese la funcion de la herramienta por favor(corte, desmalezado, labrado, limpieza, riego, transporte)");
								funcion = scan.nextLine();
								mensaje = HerramientaJardineria.validarFuncionHerramientaLlamada(funcion);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la potencia de la herramienta por favor");
								while (!scan.hasNextInt())
									scan.next();
								potencia = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(potencia);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de motor de la herramienta por favor(electrico, combustible)");
								tipoMotor = scan.nextLine();
								mensaje = HerramientaNoManual.validarMotorLlamada(tipoMotor);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el consumor del motor de la herramienta por favor(alto, medio, bajo)");
								consumo = scan.nextLine();
								mensaje = HerramientaNoManual.validarConsumoLlamada(consumo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new HerramientaNoManual(nombre, marca, stock, descripcion, material,
									funcion, potencia, tipoMotor, consumo);
							vivero.agregarElemento(producto);
							System.out.println("Herramienta agregada con exito\n");
							break;
						}
						case 13: {

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el material principal del mueble por favor");
								material = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(material);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println(
										"Ingrese el tipo de mueble de jardin por favor(mesa, silla, reposera, camastro, hamaca)");
								tipo = scan.nextLine();
								mensaje = MuebleJardin.validarTipoMuebleLlamada(tipo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el alto en cm por favor");
								while (!scan.hasNextDouble())
									scan.next();
								alto = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(alto);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el ancho en cm por favor");
								while (!scan.hasNextDouble())
									scan.next();
								ancho = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(ancho);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el largo en cm por favor");
								while (!scan.hasNextDouble())
									scan.next();
								largo = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(largo);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new MuebleJardin(nombre, marca, stock, descripcion, material, tipo,
									alto, largo, ancho);
							vivero.agregarElemento(producto);
							System.out.println("Mueble de jardin agregado con exito");
							break;
						}
						case 14: {

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el stock actual por favor");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println("Ingrese la descripcion por favor");
							descripcion = scan.nextLine();

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el material principal por favor");
								material = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(material);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese si es apto exterior por favor (true/false)");
								deInterior = scan.nextBoolean();
								scan.nextLine();
								mensaje = Producto.validarBooleanLlamada(deInterior);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese el color primario por favor");
								color = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(color);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new Decoracion(nombre, marca, stock, descripcion, material, deInterior,
									color);
							vivero.agregarElemento(producto);
							System.out.println("Objeto decorativo agregado con exito");
							break;

						}
						default:
							System.out.println("Opcion erronea");
						}

						break;
					}
					case 2: {
						String descripcion = null;
						boolean materialesIncluidos = false;
						String nombre = null;
						double precio = 0;

						mensaje = " ";
						while (mensaje != null) {
							System.out.println("Ingrese el nombre del servicio por favor: ");
							nombre = scan.nextLine();
							mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
							if (mensaje != null) {
								System.out.println(mensaje);
							}
						}

						System.out.println("Ingrese la descripcion del servicio por favor: ");
						descripcion = scan.nextLine();

						mensaje = " ";
						while (mensaje != null) {
							System.out.println("�Estan los materiales incluidos? Ingrese true/false");
							materialesIncluidos = scan.nextBoolean();
							scan.nextLine();
							mensaje = Producto.validarBooleanLlamada(materialesIncluidos);
							if (mensaje != null) {
								System.out.println(mensaje);
							}
						}

						mensaje = " ";
						while (mensaje != null) {
							System.out.println("Ingrese el costo del servicio por favor: ");
							while (!scan.hasNextDouble())
								scan.next();
							precio = scan.nextDouble();
							scan.nextLine();
							mensaje = Producto.validarValorNumericoLlamada(precio);
							if (mensaje != null) {
								System.out.println(mensaje);
							}
						}

						Servicio miServicio = new Servicio(nombre, precio, descripcion, materialesIncluidos);
						vivero.agregarElemento(miServicio);
						System.out.println("El servicio fue agregado con exito");
						break;
					}
					case 3: {
						String nombre = null;
						String apellido = null;
						String telefono = null;
						String dni = null;

						mensaje = " ";
						while (mensaje != null) {
							System.out.println("Ingrese el nombre del cliente por favor: ");
							nombre = scan.nextLine();
							mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
							if (mensaje != null) {
								System.out.println(mensaje);
							}
						}

						mensaje = " ";
						while (mensaje != null) {
							System.out.println("Ingrese el apellido del cliente por favor: ");
							apellido = scan.nextLine();
							mensaje = Producto.validarCadenaCaracteresLlamada(apellido);
							if (mensaje != null) {
								System.out.println(mensaje);
							}
						}

						System.out.println("Ingrese el telefono del cliente por favor: ");
						telefono = scan.nextLine();

						boolean validacion = false;
						while (!validacion) {
							System.out.println("Ingrese el DNI del cliente por favor: (solo numeros)");
							dni = scan.nextLine();
							validacion = Cliente.validarDNI(dni);
							if (!validacion) {
								System.out.println("Error, ingrese un DNI valido (unicamente 8 valores numericos)");
							}
						}

						Cliente cliente = null;
						int idMayor = vivero.buscarMayorIdCliente();
						if (idMayor == -1) {
							cliente = new Cliente(1, nombre, apellido, telefono, dni);
						} else {
							cliente = new Cliente(idMayor + 1, nombre, apellido, telefono, dni);
						}

						if (cliente != null) {
							vivero.agregarElemento(cliente);
						}

						System.out.println("El nuevo cliente fue agregado con exito");
						break;
					}
					case 4: {
						// carritos impagos, historial total de compras, pedido de determinado cliente,
						// eliminar un producto del carrito, eliminar pedido impago completo

						System.out.println("Elija la opcion deseada por favor\n");
						System.out.println("1-Comprar Producto: \n");
						System.out.println("2-Comprar Servicio: \n");
						System.out.println("3-Abonar pedido: \n");
						System.out.println("4-Mostrar pedido impago de determinado cliente: \n");
						System.out.println("5-Mostrar todos los pedidos impagos del sistema: \n");
						System.out.println(
								"6-Mostrar el historial de pedidos de un determinado cliente, pagos e impagos: \n ");
						System.out.println("7-Mostrar el historial de pedidos del sistema, pagos e impagos: \n");
						System.out.println("8-Eliminar producto del carrito de un cliente: \n");
						System.out.println("9-Eliminar pedido impago de determinado cliente: \n");

						while (!scan.hasNextInt())
							scan.next();
						opcion3 = scan.nextInt();
						scan.nextLine();

						String dniCliente = null;
						Cliente cliente = null;
						String codigo = null;
						int cantidad = 0;
						String clasificacion = null;
						mensaje = " ";
						char intentar = 0;

						switch (opcion3) {
						case 1: {

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println("Ingrese el DNI del cliente que efectua al pedido por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									while (clasificacion == null) {
										System.out.println(vivero.mostrarProductoResumido());
										System.out.println(
												"Ingrese el codigo del producto que desea agregar al carrito por favor\n");
										codigo = scan.nextLine();

										clasificacion = vivero.buscarClasificacionProducto(codigo);
										if (clasificacion == null) {
											System.out.println("Error, ingrese un codigo valido por favor");
										}
									}

									mensaje = " ";
									while (mensaje != null) {
										System.out.println(
												"Ingrese la cantidad del producto que desea agregar por favor\n");
										cantidad = scan.nextInt();
										scan.nextLine();
										mensaje = Producto.validarCantidadCompraLlamada(cantidad);
										if (mensaje != null) {
											System.out.println(mensaje);
										}
									}

									// instanciamos una plantaAcuatica porque producto es abstracto y no puede
									// instanciarse. Es a fin de enviarle al metodo lo que solicita
									Producto producto = new PlantaAcuatica(codigo, clasificacion);
									System.out.println(vivero.ComprarProducto(producto, cliente, cantidad));

								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}

							break;
						}
						case 2: {
							cliente = null;
							mensaje = "";
							boolean validacion = false;

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println("Ingrese el DNI del cliente que efectua al pedido por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									while (!validacion) {
										System.out.println(vivero.mostrarServicioResumido());
										System.out.println(
												"Ingrese el codigo del servicio que desea agregar al carrito por favor\n");
										codigo = scan.nextLine();

										validacion = vivero.existeServicio(codigo);
										if (!validacion) {
											System.out.println("Error, ingrese un codigo valido por favor");
										}
									}

									System.out.println(vivero.ComprarServicio(codigo, cliente));
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}

							break;
						}
						case 3: {
							cliente = null;
							String metodoPago = null;
							mensaje = "";

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println("Ingrese el DNI del cliente que desea abonar su pedido por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);

								if (cliente != null) {
									boolean impago = vivero.buscarPedidoImpago(cliente.getId());

									if (impago == true) // si existe un pedido impago de este cliente
									{
										while (mensaje != null) {
											System.out.println(
													"Ingrese el medio de pago por favor(efectivo, tarjeta, cuentaDNI, mercadopago)");
											metodoPago = scan.nextLine();
											mensaje = Pedido.validarMedioDePagoLlamada(metodoPago);
											if (mensaje != null) {
												System.out.println(mensaje);
											}
										}

										vivero.establecerMedioPago(cliente.getId(), metodoPago);
										System.out.println(vivero.visualizarTotalesPedido(cliente));
										System.out.println(
												"Si desea abonar el pedido ingrese true, caso contrario false:");
										boolean pagar = scan.nextBoolean();
										scan.nextLine();

										if (pagar) {
											vivero.setearEmpleado(idEmpleado, cliente.getId());
											System.out.println(vivero.abonarPedido(cliente));
										} else {
											System.out.println("Operacion de pago interrumpida");
										}
									} else {
										System.out.println(
												"El cliente ingresado no posee ningun pedido impago actualmente\n");
									}
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}
							break;
						}
						case 4: {
							cliente = null;
							mensaje = "";

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println(
										"Ingrese el DNI del cliente del cual desea visualizar su pedido impago por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);

								if (cliente != null) {
									boolean impago = vivero.buscarPedidoImpago(cliente.getId());

									if (impago == true) // si existe un pedido impago de este cliente
									{

										System.out.println(vivero.mostrarPedidoImpago(cliente));
									} else {
										System.out
												.println("El cliente ingresado no posee pedidos impagos actualmente\n");
									}
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}

							break;
						}
						case 5: {
							System.out.println(vivero.mostrarTodosLosPedidosImpagos());

							break;
						}
						case 6: {
							cliente = null;

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println(
										"Ingrese el DNI del cliente del cual desea ver su historial de pedidos por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									System.out.println(vivero.mostrarHistorialPedidosCliente(cliente));
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}
							break;
						}
						case 7: {
							System.out.println(vivero.mostrarTodosLosPedidos());

							break;
						}
						case 8: {
							cliente = null;
							mensaje = "";
							clasificacion = null;

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println(
										"Ingrese el DNI del cliente que desea eliminar una peticion del carrito por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);

								if (cliente != null) {
									boolean impago = vivero.buscarPedidoImpago(cliente.getId());

									if (impago == true) // si existe un pedido impago de este cliente
									{
										boolean carritoNoVacio = vivero.verificarCarritoNoVacio(cliente.getId()); // que
																													// el
																													// carrito
																													// no
																													// este
																													// vacio
										if (!carritoNoVacio) // si el carrito no esta vacio
										{
											System.out.println(vivero.mostrarPedidoImpago(cliente));
											System.out.println(
													"Ingrese el codigo del producto/servicio a eliminar de su carrito: \n");
											codigo = scan.nextLine();

											System.out.println(vivero.eliminarPeticionCarrito(codigo, cliente));
										} else {
											System.out.println(
													"Error, el carrito del pedido se encuentra vacio actualmente\n");
										}

									} else {
										System.out
												.println("El cliente ingresado no posee pedidos impagos actualmente\n");
									}

								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}
							break;
						}
						case 9: {
							cliente = null;
							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out
										.println("Ingrese el DNI del cliente que desea eliminar el pedido por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									boolean impago = vivero.buscarPedidoImpago(cliente.getId());

									if (impago == true) // si existe un pedido impago de este cliente
									{
										System.out.println(vivero.eliminarPedidoImpagoCliente(cliente));
									} else {
										System.out.println("El cliente ingresado no posee nigun pedido impago\n");
									}
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}

							break;
						}

						default:
							System.out.println("Opcion erronea");
						}
						break;

					}
					case 5: {
						System.out.println("Elija la opcion deseada por favor\n");
						System.out.println("1-Buscar Producto: \n");
						System.out.println("2-Buscar Servicio: \n");
						System.out.println("3-Buscar Cliente: \n");
						System.out.println("4-Buscar Empleado: \n");

						while (!scan.hasNextInt())
							scan.next();
						opcion3 = scan.nextInt();
						scan.nextLine();

						String dniCliente = null;
						Cliente cliente = null;
						String codigo = null;
						String clasificacion = null;
						mensaje = " ";
						char intentar = 0;

						switch (opcion3) {
						case 1: {

							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println("Ingrese el codigo del producto que desea buscar por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}
							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.buscarElemento(producto));

							break;
						}
						case 2: {
							boolean validacion = false;
							while (!validacion) {
								System.out.println(vivero.mostrarServicioResumido());
								System.out.println("Ingrese el codigo del servicio que desea buscar por favor\n");
								codigo = scan.nextLine();

								validacion = vivero.existeServicio(codigo);
								if (!validacion) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}
							Servicio servicio = new Servicio(codigo);
							System.out.println(vivero.buscarElemento(servicio));
							break;
						}
						case 3: {
							cliente = null;

							while (cliente == null && intentar != 'n') {
								System.out.println("Ingrese el DNI del cliente que desea buscar por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									System.out.println(vivero.buscarElemento(cliente));
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}
							break;
						}
						case 4: {
							int idE = 0;

							System.out.println("Ingrese el ID del empleado a buscar por favor\n");
							while (!scan.hasNextInt())
								scan.next();
							idE = scan.nextInt();
							scan.nextLine();

							Empleado empleado = new Empleado(idE);
							System.out.println(vivero.buscarElemento(empleado));

							break;
						}
						default:
							System.out.println("Opcion erronea");
						}
						break;

					}
					case 6: {
						System.out.println("Elija la opcion deseada por favor\n");
						System.out.println("1-Eliminar Producto: \n");
						System.out.println("2-Eliminar Servicio: \n");
						System.out.println("3-Eliminar Cliente: \n");
						System.out.println("4-Eliminar Empleado: \n");

						while (!scan.hasNextInt())
							scan.next();
						opcion3 = scan.nextInt();
						scan.nextLine();

						String dniCliente = null;
						Cliente cliente = null;
						String codigo = null;
						String clasificacion = null;
						mensaje = " ";
						char intentar = 0;

						switch (opcion3) {
						case 1: {

							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println("Ingrese el codigo del producto que desea eliminar por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}
							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.eliminarElemento(producto));

							break;
						}
						case 2: {
							boolean validacion = false;
							while (!validacion) {
								System.out.println(vivero.mostrarServicioResumido());
								System.out.println("Ingrese el codigo del servicio que desea eliminar por favor\n");
								codigo = scan.nextLine();

								validacion = vivero.existeServicio(codigo);
								if (!validacion) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}
							Servicio servicio = new Servicio(codigo);
							System.out.println(vivero.eliminarElemento(servicio));

							break;
						}
						case 3: {
							cliente = null;

							while (cliente == null && intentar != 'n') {
								System.out.println(vivero.mostrarClientes());
								System.out.println("Ingrese el DNI del cliente que desea eliminar por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									boolean impago = vivero.buscarPedidoImpago(cliente.getId());
									if (impago) {
										System.out.println(vivero.eliminarPedidoImpagoCliente(cliente));
									}
									System.out.println(vivero.eliminarElemento(cliente));

								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}

							break;
						}
						case 4: {
							int idE = 0;

							System.out.println("Ingrese el ID del empleado a eliminar por favor\n");
							while (!scan.hasNextInt())
								scan.next();
							idE = scan.nextInt();
							scan.nextLine();

							if (idE == idEmpleado) {
								System.out.println(
										"Error, no es posible eliminar al empleado con el cual se esta administrando el sistema actualmente, intente mas tarde\n");
							} else {
								Empleado empleado = new Empleado(idE);
								System.out.println(vivero.eliminarElemento(empleado));
							}

							break;

						}
						default:
							System.out.println("Opcion erronea");
						}

						break;
					}
					case 7: {
						System.out.println("Elija la opcion deseada por favor\n");
						System.out.println("1-Mostrar un producto: \n");
						System.out.println("2-Mostrar todos los productos de una clasificacion determinada: \n");
						System.out.println("3-Mostrar todos los productos del catalogo: \n");
						System.out.println("4-Mostrar un servicio: \n");
						System.out.println("5-Mostrar todos los servicios: \n");
						System.out.println("6-Mostrar un cliente: \n");
						System.out.println("7-Mostrar todos los clientes: \n");
						System.out.println("8-Mostrar un empleado: \n");
						System.out.println("9-Mostrar todos los empleados: \n");

						while (!scan.hasNextInt())
							scan.next();
						opcion3 = scan.nextInt();
						scan.nextLine();

						String dniCliente = null;
						Cliente cliente = null;
						String codigo = null;
						String clasificacion = null;
						mensaje = " ";
						char intentar = 0;

						switch (opcion3) {
						case 1: {

							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto que desea mostrar por pantalla por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}
							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.buscarElemento(producto));

							break;
						}
						case 2: {
							System.out.println(vivero.mostrarClasificacionesProducto());
							System.out.println(
									"Ingrese la clasificacion de los productos que desea mostrar por pantalla por favor\n");
							clasificacion = scan.nextLine();
							System.out.println(vivero.mostrarProductosClasificacion(clasificacion));
							break;
						}
						case 3: {
							System.out.println(vivero.mostrarProductos());
							break;
						}
						case 4: {
							boolean validacion = false;
							while (!validacion) {
								System.out.println(vivero.mostrarServicioResumido());
								System.out.println(
										"Ingrese el codigo del servicio que desea mostrar por pantalla por favor\n");
								codigo = scan.nextLine();

								validacion = vivero.existeServicio(codigo);
								if (!validacion) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}
							Servicio servicio = new Servicio(codigo);
							System.out.println(vivero.buscarElemento(servicio));

							break;

						}
						case 5: {
							System.out.println(vivero.mostrarServicios());

							break;
						}
						case 6: {
							cliente = null;

							while (cliente == null && intentar != 'n') {
								System.out.println(
										"Ingrese el DNI del cliente que desea mostrar por pantalla por favor\n");
								dniCliente = scan.nextLine();
								cliente = vivero.BuscaCliente(dniCliente);
								if (cliente != null) {
									System.out.println(vivero.buscarElemento(cliente));
								} else {
									System.out.println(
											"Error, el DNI ingresado es erroneo, si desea intentar nuevamente presione cualquier tecla, caso contrario 'n':");
									intentar = scan.next().charAt(0);
									scan.nextLine();
								}
							}

							break;
						}
						case 7: {
							System.out.println(vivero.mostrarClientes());

							break;
						}
						case 8: {
							int idE = 0;

							System.out.println("Ingrese el ID del empleado a mostrar por pantalla por favor\n");
							while (!scan.hasNextInt())
								scan.next();
							idE = scan.nextInt();
							scan.nextLine();

							Empleado empleado = new Empleado(idE);
							System.out.println(vivero.buscarElemento(empleado));

							break;
						}
						case 9: {
							System.out.println(vivero.mostrarEmpleados());

							break;
						}
						default:
							System.out.println("Opcion erronea");
						}

						break;
					}
					case 8: {
						System.out.println("Elija la opcion deseada por favor\n");
						System.out.println("1-Modificar nombre de un producto: \n");
						System.out.println("2-Modificar marca de un producto: \n");
						System.out.println("3-Modificar precio de un producto: \n");
						System.out.println("4-Aumentar stock de un producto: \n");
						System.out.println("5-Reducir stock de un producto: \n");
						System.out.println("6-Modificar descripcion de un producto: \n");
						System.out.println("7-Modificar nombre de un servicio: \n");
						System.out.println("8-Modificar precio de un servicio: \n");

						while (!scan.hasNextInt())
							scan.next();
						opcion3 = scan.nextInt();
						scan.nextLine();

						String codigo = null;
						String clasificacion = null;
						mensaje = " ";

						switch (opcion3) {
						case 1: {

							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto al que desea modificar el nombre por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}

							mensaje = "";
							String nombre = null;
							while (mensaje != null) {
								System.out.println("Ingrese el nuevo nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.modificarNombreProdcuto(producto, nombre));
							break;

						}
						case 2: {
							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto al que desea modificar la marca por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}

							mensaje = "";
							String marca = null;
							while (mensaje != null) {
								System.out.println("Ingrese la nueva marca por favor");
								marca = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(marca);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.modificarMarcaProdcuto(producto, marca));
							break;
						}
						case 3: {
							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto al que desea modificar el precio por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}

							mensaje = "";
							double precio = 0;
							while (mensaje != null) {
								System.out.println("Ingrese el nuevo precio por favor");
								while (!scan.hasNextDouble())
									scan.next();
								precio = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarPrecioLlamada(precio);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.modificarPrecioProducto(producto, precio));
							break;
						}
						case 4: {

							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto al que desea aumentar el stock por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor\n");
								}
							}

							int stock = 0;
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la cantidad de stock a aumentar por favor\n");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.modificarStockAumenta(producto, stock));
							System.out.println(vivero.mostrarProductoResumido());
							break;

						}
						case 5: {
							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto al que desea reducir el stock por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor\n");
								}
							}

							int stock = 0;
							mensaje = "";
							while (mensaje != null) {
								System.out.println("Ingrese la cantidad de stock a reducir por favor\n");
								while (!scan.hasNextInt())
									scan.next();
								stock = scan.nextInt();
								scan.nextLine();
								mensaje = Producto.validarValorNumericoLlamada(stock);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.modificarStockDisminuye(producto, stock));
							System.out.println(vivero.mostrarProductoResumido());
							break;
						}
						case 6: {
							while (clasificacion == null) {
								System.out.println(vivero.mostrarProductoResumido());
								System.out.println(
										"Ingrese el codigo del producto al que desea modificar la descripcion por favor\n");
								codigo = scan.nextLine();

								clasificacion = vivero.buscarClasificacionProducto(codigo);
								if (clasificacion == null) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}

							mensaje = "";
							String descripcion = null;
							System.out.println("Ingrese la nueva descripcion por favor\n");
							descripcion = scan.nextLine();

							Producto producto = new PlantaAcuatica(codigo, clasificacion);
							System.out.println(vivero.modificarDescripcionProdcuto(producto, descripcion));
							break;

						}
						case 7: {
							boolean validacion = false;
							while (!validacion) {
								System.out.println(vivero.mostrarServicioResumido());
								System.out.println(
										"Ingrese el codigo del servicio al que desea modificar el nombre por favor\n");
								codigo = scan.nextLine();

								validacion = vivero.existeServicio(codigo);
								if (!validacion) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}

							mensaje = "";
							String nombre = null;
							while (mensaje != null) {
								System.out.println("Ingrese el nuevo nombre por favor");
								nombre = scan.nextLine();
								mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println(vivero.modificarNombreServicio(codigo, nombre));
							System.out.println(vivero.mostrarServicioResumido());

							break;
						}
						case 8: {
							boolean validacion = false;
							while (!validacion) {
								System.out.println(vivero.mostrarServicioResumido());
								System.out.println(
										"Ingrese el codigo del servicio al que desea modificar el precio por favor\n");
								codigo = scan.nextLine();

								validacion = vivero.existeServicio(codigo);
								if (!validacion) {
									System.out.println("Error, ingrese un codigo valido por favor");
								}
							}

							mensaje = "";
							double precio = 0;
							while (mensaje != null) {
								System.out.println("Ingrese el nuevo precio por favor\n");
								while (!scan.hasNextDouble())
									scan.next();
								precio = scan.nextDouble();
								scan.nextLine();
								mensaje = Producto.validarPrecioLlamada(precio);
								if (mensaje != null) {
									System.out.println(mensaje);
								}
							}

							System.out.println(vivero.modificarPrecioServicio(codigo, precio));
							System.out.println(vivero.mostrarServicioResumido());

							break;
						}
						default:
							System.out.println("Opcion erronea");
						}

						break;
					}
					case 9: {
						System.out.println(vivero.mostrarClasificacionesProducto());
						System.out.println("Ingrese la clasificacion de los productos por favor\n");
						String clasificacion = scan.nextLine();
						ArrayList<Producto> array = vivero.productosClasificacion(clasificacion);
						if (array != null) {
							System.out.println("Elija la opcion deseada por favor\n");
							System.out.println("1-Mostrar todos los productos dentro de la clasificacion: \n");
							System.out.println("2-Mostrar el producto mas costoso dentro de la clasificacion: \n");
							System.out.println("3-Mostrar el producto mas economico dentro de la clasificacion: \n");
							System.out.println("4-Mostrar informacion relativa al stock positivo: \n");
							System.out.println("5-Mostrar informacion relativa al stock faltante: \n");
							System.out.println("6-Mostrar productos por orden de precio: \n");

							while (!scan.hasNextInt())
								scan.next();
							opcion3 = scan.nextInt();
							scan.nextLine();

							CentroInformativo<Producto> centro = new CentroInformativo<Producto>(array);
							switch (opcion3) {
							case 1: {

								System.out.println(centro.mostrarTodo());

								break;
							}
							case 2: {
								System.out.println(centro.elementoMasCostoso());
								break;
							}
							case 3: {
								System.out.println(centro.elementoMasEconomico());
								break;
							}
							case 4: {
								System.out.println(centro.informacionStockPositivo());
								break;
							}
							case 5: {
								System.out.println(centro.stockNegativo());
								break;
							}
							case 6: {
								System.out.println(centro.mostrarSegunPrecio());
								break;
							}
							default:
								System.out.println("Opcion erronea");
							}
						} else {
							System.out.println("Error, la clasificacion ingresada no fue encontrada en el sistema\n");
						}

						break;
					}
					case 10: {
						return;

					}

					default:
						System.out.println("Opcion erronea");
					}

					System.out.println("Si desea elegir otra opcion presione cualquier tecla, caso contrario 'n': ");
					opcion2 = scan.next().charAt(0);
				}

			}
			acceso.escribirArchivoProductos(vivero);
			acceso.escribirArchivoServicios(vivero);
			acceso.escribirArchivoEmpleados(vivero);
			acceso.escribirArchivoClientes(vivero);
			acceso.escribirArchivoPedidos(vivero);
			AccesoDatos.grabar(acceso.javaToJson(vivero));
		}
	}
}
