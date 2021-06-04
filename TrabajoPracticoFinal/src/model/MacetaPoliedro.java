package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

public class MacetaPoliedro extends Maceta{

	private double alto;
	private double ancho;
	private double largo;
	
	public MacetaPoliedro()
	{
		super();
		this.alto=0;
		this.ancho=0;
		this.largo=0;
	}

	public MacetaPoliedro(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String forma, double alto, double ancho,
			double largo) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, forma);
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
	}

	public MacetaPoliedro(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String forma, double alto, double ancho, double largo) {
		super(nombre, marca, precio, stock, descripcion, material, forma);
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	@Override
	public String toString() {
		return super.toString()+", alto: " + alto + ", ancho: " + ancho + ", largo: " + largo;
	}
	

	@Override
	public void establecerClasificacion() {
		this.setClasificacion("Maceta poliedro");
	}

	
	
}
