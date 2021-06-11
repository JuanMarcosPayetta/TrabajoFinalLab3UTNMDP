package app;

import java.util.Scanner;

import domain.AccesoDatos;
import domain.Empleado;
import domain.Vivero;

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
		
				//while(opcion2!='n')
				{
					System.out.println("Ingrese la opcion deseada por favor: \n");
					System.out.println("1-Ingresar un producto al sistema: \n");
					System.out.println("2-Ingresar un servicio al sistema: \n");
					System.out.println("3-Ingresar un cliente al sistema: \n");
			
					switch (opcion) {
					case 1: {
						
				     break;
					}
					default:
						System.out.println("Opcion erronea");
					}
					
				
					
				}
				
			}
		}
		
		acceso.escribirArchivoProductos(vivero);
		acceso.escribirArchivoServicios(vivero);
		acceso.escribirArchivoEmpleados(vivero);
		acceso.escribirArchivoClientes(vivero);
	}
	

}
