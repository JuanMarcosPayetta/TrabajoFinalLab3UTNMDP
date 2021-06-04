package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

import excepciones.CadenaInvalidaException;

public class MacetaRedonda extends Maceta{

	private double diametroBoca;
	private double base;
	
	public MacetaRedonda()
	{
		super();
		this.diametroBoca=0;
		this.base=0;
	}

	public MacetaRedonda(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material, String forma, double diametroBoca, double base) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion, material, forma);
		this.diametroBoca = diametroBoca;
		this.base = base;
	}

	public MacetaRedonda(String nombre, String marca, double precio, int stock, String descripcion, String material,
			String forma, double diametroBoca, double base) {
		super(nombre, marca, precio, stock, descripcion, material, forma);
		this.diametroBoca = diametroBoca;
		this.base = base;
	}

	
	public double getDiametroBoca() {
		return diametroBoca;
	}

	public void setDiametroBoca(double diametroBoca) {
		this.diametroBoca = diametroBoca;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return super.toString()+", diametroBoca=" + diametroBoca + ", base=" + base;
	}

	
	
	
}
