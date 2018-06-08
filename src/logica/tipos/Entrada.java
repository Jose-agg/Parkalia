package logica.tipos;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Entrada implements Mercancia {

	private String codigoEntrada;
	private String codigoParque;
	private int precioAdulto;
	private int precioKid;

	private Image imagenAsignada;
	private String denominacion;

	public Entrada(String[] datosArticulo) {
		rellenarDatos(datosArticulo);
		asignarImagen();
	}

	private void rellenarDatos(String[] datosArticulo) {
		this.codigoEntrada = datosArticulo[0];
		this.codigoParque = datosArticulo[1];
		this.precioAdulto = Integer.valueOf(datosArticulo[2]);
		this.precioKid = Integer.valueOf(datosArticulo[3]);
	}

	private void asignarImagen() {
		Image imagen = null;
		try {
			imagen = new ImageIcon(getClass().getResource("/img/Parques/" + this.codigoParque + ".jpg")).getImage();
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
		return getCodigoEntrada();
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getCodigoEntrada() {
		return codigoEntrada;
	}

	public String getCodigoParque() {
		return codigoParque;
	}

	public int getPrecioAdulto() {
		return precioAdulto;
	}

	public int getPrecioKid() {
		return precioKid;
	}

}
