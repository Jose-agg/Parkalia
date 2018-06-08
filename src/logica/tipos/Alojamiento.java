package logica.tipos;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Alojamiento implements Mercancia {

	private String codigoAlojamiento;
	private String tipo;
	private int numEstrellas;
	private String denominacion;
	private String codigoParque;
	private int numPlazas;
	private int precio;

	private Image imagenAsignada;

	public Alojamiento(String[] datosArticulo) {
		rellenarDatos(datosArticulo);
		asignarImagen();
	}

	private void rellenarDatos(String[] datosArticulo) {
		this.codigoAlojamiento = datosArticulo[0];
		this.tipo = datosArticulo[1];
		this.numEstrellas = Integer.valueOf(datosArticulo[2]);
		this.denominacion = datosArticulo[3];
		this.codigoParque = datosArticulo[4];
		this.numPlazas = Integer.valueOf(datosArticulo[5]);
		this.precio = Integer.valueOf(datosArticulo[6]);
	}

	private void asignarImagen() {
		Image imagen = null;
		try {
			imagen = new ImageIcon(getClass().getResource("/img/Alojamientos/" + this.codigoAlojamiento + ".jpg"))
					.getImage();
		} catch (NullPointerException e) {
			imagen = new ImageIcon(getClass().getResource("/img/ImageNotFound.jpg")).getImage();
		}
		this.imagenAsignada = imagen;
	}

	@Override
	public Image getImagen() {
		return imagenAsignada;
	}

	@Override
	public String getDenominacion() {
		return denominacion;
	}

	@Override
	public String getCodigoPropio() {
		return getCodigoAlojamiento();
	}

	public String getCodigoAlojamiento() {
		return codigoAlojamiento;
	}

	public String getTipo() {
		return tipo;
	}

	public int getNumEstrellas() {
		return numEstrellas;
	}

	public String getCodigoParque() {
		return codigoParque;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public int getPrecio() {
		return precio;
	}

}
