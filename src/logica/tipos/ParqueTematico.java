package logica.tipos;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ParqueTematico implements Mercancia {

	private String codigoParque;
	private String denominacion;
	private String pais;
	private String localidad;
	private String descripcion;

	private Image imagenAsignada;

	public ParqueTematico(String[] datosArticulo) {
		rellenarDatos(datosArticulo);
		asignarImagen();
	}

	private void rellenarDatos(String[] datosArticulo) {
		this.codigoParque = datosArticulo[0];
		this.denominacion = datosArticulo[1];
		this.pais = datosArticulo[2];
		this.localidad = datosArticulo[3];
		this.descripcion = datosArticulo[4];
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
		return getCodigoParque();
	}

	public String getCodigoParque() {
		return codigoParque;
	}

	public String getPais() {
		return pais;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
