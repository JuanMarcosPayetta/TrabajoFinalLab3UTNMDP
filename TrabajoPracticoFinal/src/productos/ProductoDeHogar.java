package productos;

import interfaces.IEstablecerPrecioProductoInerte;
/**
 * 
 * Atributos de los productos clasificacion Producto de hogar 
 *
 */
public abstract class ProductoDeHogar extends Producto implements IEstablecerPrecioProductoInerte{

	private String material;

	public ProductoDeHogar() {
		super();
		this.material = null;
	}

	public ProductoDeHogar(String codigo, String nombre, String marca, String clasificacion, double precio, int stock,
			String descripcion, String material) {
		super(codigo, nombre, marca, clasificacion, precio, stock, descripcion);
		this.material = material;
	}

	public ProductoDeHogar(String nombre, String marca, double precio, int stock, String descripcion, String material) {
		super(nombre, marca, precio, stock, descripcion);
		this.material = material;
	}

	public ProductoDeHogar(String nombre, String marca, int stock, String descripcion, String material) {
		super(nombre, marca, stock, descripcion);
		this.material = material;
	}
	
	/**
	 * Devuelve el material del que esta compuesto el producto
	 * @return String
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Setea el valor del material del que esta compuesto el producto, validando que sea correcto
	 * @see #validarCadenaCaracteresLlamada(String)
	 * @param String
	 * @return String
	 */
	public String setMaterial(String material) {

		String mensaje = validarCadenaCaracteresLlamada(material); // si devuelve null es correcto

		if (mensaje == null) {

			this.material = material;
		}
		return mensaje;
	}

	/**
	 * Retorna informacion relevante del objeto
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + ", Material: " + material;
	}

}
