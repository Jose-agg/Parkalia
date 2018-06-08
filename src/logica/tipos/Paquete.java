package logica.tipos;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Paquete implements Mercancia {

	private String codigoPaquete;
	private String denominacion;
	private String codigoParque;
	private String codigoAlojamiento;
	private int duracion;
	private int precioAdulto;
	private int precioKid;

	private Image imagenAsignada;

	public Paquete(String[] datosArticulo) {
		rellenarDatos(datosArticulo);
		asignarImagen();
	}

	private void rellenarDatos(String[] datosArticulo) {
		this.codigoPaquete = datosArticulo[0];
		this.denominacion = datosArticulo[1];
		this.codigoParque = datosArticulo[2];
		this.codigoAlojamiento = datosArticulo[3];
		this.duracion = Integer.valueOf(datosArticulo[4]);
		this.precioAdulto = Integer.valueOf(datosArticulo[5]);
		this.precioKid = Integer.valueOf(datosArticulo[6]);
	}

	private void asignarImagen() {
		Image imagen = null;
		try {
			imagen = new ImageIcon(getClass().getResource("/img/Paquetes/" + this.codigoPaquete + ".jpg")).getImage();
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
		return getCodigoPaquete();
	}

	public String getCodigoPaquete() {
		return codigoPaquete;
	}

	public String getCodigoParque() {
		return codigoParque;
	}

	public String getCodigoAlojamiento() {
		return codigoAlojamiento;
	}

	public int getDuracion() {
		return duracion;
	}

	public int getPrecioAdulto() {
		return precioAdulto;
	}

	public int getPrecioKid() {
		return precioKid;
	}
}
