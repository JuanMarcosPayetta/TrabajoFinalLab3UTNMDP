package interfaces;

/**
 * 
 * Establece automaticamente los precio de ventas
 *
 */
public interface IEstablecerPrecioPlanta {

	public double precioMesDeVida(int mesesVida);
	public double precioPorCentimentoAltura(int centimentros);
	public void establecerPrecio();
}
