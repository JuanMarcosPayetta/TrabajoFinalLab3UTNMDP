package servicios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import domain.Vivero;
import interfaces.IGenerarCodigo;
import productos.Producto;

/**
 * 
 * Atributos de los Servicios
 *
 */
public class Servicio implements IGenerarCodigo, Serializable {

	private String codigo;
	private double precio;
	private String nombre;
	private String descripcion;
	private boolean materialesIncluidos;

	public Servicio() {
		this.codigo = null;
		this.precio = 0;
		this.nombre = null;
		this.descripcion = null;
		this.materialesIncluidos = false;
	}

	public Servicio(String codigo, String nombre, double precio, String descripcion, boolean materialesIncluidos) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.materialesIncluidos = materialesIncluidos;
	}

	public Servicio(String nombre, double precio, String descripcion, boolean materialesIncluidos) {
		this.codigo = generarCodigo();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.materialesIncluidos = materialesIncluidos;
	}

	public Servicio(String codigo) {
		this.codigo = codigo;
		this.precio = 0;
		this.nombre = null;
		this.descripcion = null;
		this.materialesIncluidos = false;
	}

	/**
	 * Genera automaticamente el codigo del servicio, verificando que ya no exista
	 * ese codigo
	 * 
	 * @see #buscarCodigoExistentes(String)
	 * @return String
	 */
	public String generarCodigo() {
		boolean existe = true;
		int longitud = 5;
		String codigoGenerado = null;
		String minusculas = "abcdefghijkmnlopqrstuvxyz";
		String numeros = "0123456789";
		String total = minusculas + numeros;

		char[] pass = new char[longitud];
		Random random = new Random();

		while (existe) // Mientras el codigo generado actualmente sea = al codigo actual de servicio
		{
			for (int i = 0; i < pass.length; i++) {
				pass[i] = total.charAt(random.nextInt(total.length()));
			}
			codigoGenerado = String.valueOf(pass); // codigo generado

			boolean encontrado = buscarCodigoExistentes(codigoGenerado); // busco si ya existe el codigo generado

			if (!encontrado) // Si el codigo generado no se repite con el codigo actual de otro servicio,
								// corta el ciclo while
			{
				existe = false;
			}
		}
		return codigoGenerado;
	}

	/**
	 * Busca si un codigo existe o no actualmente en algun servicio
	 * 
	 * @see #generarCodigo()
	 * @param String
	 * @return boolean
	 * 
	 */
	public boolean buscarCodigoExistentes(String codigoGenerado) {

		Vivero vivero = new Vivero();
		ArrayList<String> codigosActuales = new ArrayList<String>();
		codigosActuales = vivero.obtenerCodigosServicio();

		boolean encontrado = false;
		for (int i = 0; i < codigosActuales.size() && !encontrado; i++) {
			if (codigosActuales.get(i).equals(codigoGenerado)) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	/**
	 * Retorna el codigo del servicio
	 * 
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna el precio del servicio
	 * 
	 * @return double
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Setea el precio del servicio
	 * 
	 * @param double
	 * @return String
	 */
	public String setPrecio(double precio) {
		String mensaje = null;
		mensaje = Producto.validarPrecioLlamada(precio);
		if (mensaje == null) {
			this.precio = precio;
		}

		return mensaje;
	}

	/**
	 * Retorna el nombre del servicio
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre del servicio
	 * 
	 * @param String
	 * @return String
	 */
	public String setNombre(String nombre) {
		String mensaje = null;
		mensaje = Producto.validarCadenaCaracteresLlamada(nombre);
		if (mensaje == null) {
			this.nombre = nombre;
		}
		return mensaje;
	}

	/**
	 * Retorna la descripcion del servicio
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setea la descripcion del servicio
	 * 
	 * @param String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna true o false si estan incluidos los materiales del servicio
	 * 
	 * @return boolean
	 */
	public boolean isMaterialesIncluidos() {
		return materialesIncluidos;
	}

	/**
	 * Setea en true o false si estan incluidos los materiales en el valor del
	 * servicio
	 * 
	 * @param boolean
	 * @return String
	 */
	public String setMaterialesIncluidos(boolean materialesIncluidos) {
		String mensaje = null;
		mensaje = Producto.validarBooleanLlamada(materialesIncluidos);
		if (mensaje == null) {
			this.materialesIncluidos = materialesIncluidos;
		}
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	public String toString() {
		return "Codigo: " + codigo + ", Nombre Servicio: " + nombre + ", Precio: " + precio + ", Descripcion: "
				+ descripcion + ", MaterialesIncluidos: " + materialesIncluidos;
	}

	/**
	 * Compara dos objetos, indicando si son iguales o no
	 * 
	 * @param Object
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		boolean validacion = false;
		if (obj != null) {
			if (obj instanceof Servicio) {
				Servicio unServicio = (Servicio) obj;
				if (this.getCodigo().equals(unServicio.getCodigo())) {
					validacion = true;
				}
			}
		}
		return validacion;
	}

}
