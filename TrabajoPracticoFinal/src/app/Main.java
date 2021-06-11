package app;

import java.util.Scanner;

import domain.Empleado;
import domain.Vivero;

public class Main {

	static Scanner scan;
	public static void main(String[] args) {
	
		scan= new Scanner(System.in);
		int opcion=0;
		boolean validarEmpleado=false;
		Vivero vivero= new Vivero();
		
		
		System.out.println("Ingrese la opcion deseada por favor:\n");
		System.out.println("1-Crear un nuevo empleado: ");
		
		opcion=scan.nextInt();
		switch (opcion) {
		case 1: {
			String mensaje="";
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
		
		Empleado empleado= new Empleado(nombre, apellido);
		vivero.agregarElemento(empleado);
		System.out.println("Empleado creado con exito. Su iD es: "+empleado.getID());
		break;
		
		}
		case 2:
		{
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
		
	}
	

}
