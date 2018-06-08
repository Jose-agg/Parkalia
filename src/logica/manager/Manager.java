package logica.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;

import logica.filtros.Filtros;
import logica.reserva.Reserva;
import logica.tipos.Alojamiento;
import logica.tipos.Entrada;
import logica.tipos.Paquete;
import logica.tipos.ParqueTematico;

public class Manager {

	private ArrayList<ParqueTematico> listaParques;
	private ArrayList<Paquete> listaPaquetes;
	private ArrayList<Alojamiento> listaAlojamientos;
	private ArrayList<Entrada> listaEntradas;
	private ParqueTematico parqueOferta;
	private Reserva reserva;

	public Manager() {
		lectorArchivos();
		rellenarDenominacionEntradas();
		generarParqueOferta();
	}

	private void lectorArchivos() {
		this.listaParques = LectorArchivos.leerFicheroParques("files/tematicos.dat");
		this.listaPaquetes = LectorArchivos.leerFicheroPaquetes("files/paquetes.dat");
		this.listaEntradas = LectorArchivos.leerFicheroEntradas("files/entradas.dat");
		this.listaAlojamientos = LectorArchivos.leerFicheroAlojamientos("files/alojamientos.dat");
		this.reserva = new Reserva(this);
	}

	public String guardarFactura() {
		String nombreFichero = reserva.getNif() + "_" + obtenerFechaActual();
		nombreFichero = nombreFichero.replaceAll("/", "_");
		return CreadorArchivos.guardarFactura(nombreFichero, reserva.getResumen());
	}

	public String obtenerFechaActual() {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String folderName = formatter.format(today);
		return folderName;
	}

	private void rellenarDenominacionEntradas() {
		for (Entrada entrada : listaEntradas) {
			String codParque = entrada.getCodigoParque();
			for (ParqueTematico parqueTematico : listaParques) {
				if (parqueTematico.getCodigoParque().equals(codParque)) {
					entrada.setDenominacion("Entrada para " + parqueTematico.getDenominacion());
					break;
				}
			}
		}
	}

	private void generarParqueOferta() {
		int rand = (int) (System.currentTimeMillis() % listaParques.size());
		this.parqueOferta = listaParques.get(rand);
	}

	public String[] obtenerParques() {
		String[] nombres = new String[listaParques.size()];
		int contador = 0;
		for (ParqueTematico parqueTematico : listaParques) {
			nombres[contador] = parqueTematico.getDenominacion();
			contador++;
		}
		return nombres;
	}

	public String actualizarPrecioCarrito() {
		return reserva.actualizarPrecioCarrito();
	}

	public boolean reservaIsEmpty() {
		return reserva.isEmpty();
	}

	public void crearCliente(String nombre, String apellidos, String comentario) {
		reserva.crearCliente(nombre, apellidos, comentario);
	}

	public String introducirNif(String nif) {
		return reserva.introducirNif(nif);
	}

	public void reiniciar() {
		limpiarReserva();
	}

	public void limpiarReserva() {
		this.reserva = new Reserva(this);
	}

	// METODOS MOSTRAR DATOS DE LA RESERVA \\

	public ArrayList<String[]> getListadosReserva() {
		return reserva.getListadosReserva();
	}

	/**
	 * Este es para el carrito que esta en un lateral
	 */
	public DefaultListModel<String> refrescarCarrito(DefaultListModel<String> modeloListCarrito) {
		return reserva.refrescarCarrito(modeloListCarrito);
	}

	/**
	 * Este es para el carrito de la ventana que aparece con el cliente
	 */
	public String getStringMercanciaCarrito() {
		return reserva.getEstadoReserva();
	}

	/**
	 * Este es para la ultima ventana, el que sigue el formato pedido en el modulo
	 */
	public String getResumenReserva() {
		return reserva.getResumen();
	}

	// METODOS PARA FILTRAR LAS BUSQUEDAS \\

	/**
	 * ENTRADAS
	 */
	public ArrayList<Entrada> filtrarPorDenominacionEntrada(String palabra) {
		return Filtros.filtrarPorDenominacionEntrada(this.listaEntradas, palabra);
	}

	public ArrayList<Entrada> filtrarPorParqueEntrada(String nombreParque) {
		return Filtros.filtrarPorParqueEntrada(this.listaEntradas, this.listaParques, nombreParque);
	}

	public ArrayList<Entrada> filtrarPorPrecioEntrada(int adulto, int kid) {
		return Filtros.filtrarPorPrecioEntrada(this.listaEntradas, adulto, kid);
	}

	/**
	 * ALOJAMIENTOS
	 */
	public ArrayList<Alojamiento> filtrarPorDenominacionAlojamiento(String palabra) {
		return Filtros.filtrarPorDenominacionAlojamiento(this.listaAlojamientos, palabra);
	}

	public ArrayList<Alojamiento> filtrarPorParqueAlojamiento(String nombreParque) {
		return Filtros.filtrarPorParqueAlojamiento(this.listaAlojamientos, this.listaParques, nombreParque);
	}

	public ArrayList<Alojamiento> filtrarPorPrecioAlojamiento(int precioMinimo) {
		return Filtros.filtrarPorPrecioAlojamiento(this.listaAlojamientos, precioMinimo);
	}

	public ArrayList<Alojamiento> filtrarPorTipoAlojamiento(boolean hotel, boolean apartahotel, boolean apartamento) {
		return Filtros.filtrarPorPrecioAlojamiento(this.listaAlojamientos, hotel, apartahotel, apartamento);
	}

	public ArrayList<Alojamiento> filtrarPorCategoriaAlojamiento(int value) {
		return Filtros.filtrarPorCategoriaAlojamiento(this.listaAlojamientos, value);
	}

	/**
	 * PAQUETES
	 */
	public ArrayList<Paquete> filtrarPorDenominacionPaquete(String palabra) {
		return Filtros.filtrarPorDenominacionPaquete(this.listaPaquetes, palabra);
	}

	public ArrayList<Paquete> filtrarPorParquePaquete(String nombreParque) {
		return Filtros.filtrarPorParquePaquete(this.listaPaquetes, this.listaParques, nombreParque);
	}

	public ArrayList<Paquete> filtrarPorPrecioPaquete(int adulto, int kid) {
		return Filtros.filtrarPorPrecioPaquete(this.listaPaquetes, adulto, kid);
	}

	public ArrayList<Paquete> filtrarPorDuracionPaquete(int duracion) {
		return Filtros.filtrarPorDuracionPaquete(this.listaPaquetes, duracion);
	}

	// METODOS PARA BUSCAR TIPOS POR CAMPOS \\

	public ParqueTematico getParqueByCodigo(String codigoParque) {
		for (ParqueTematico parqueTematico : listaParques) {
			if (parqueTematico.getCodigoParque().equals(codigoParque)) {
				return parqueTematico;
			}
		}
		return null;
	}

	public ParqueTematico getParqueByDenominacion(String nombre) {
		for (ParqueTematico parqueTematico : listaParques) {
			if (parqueTematico.getDenominacion().equals(nombre)) {
				return parqueTematico;
			}
		}
		return null;
	}

	public Alojamiento getAlojamientoByCodigo(String codigoAlojamiento) {
		for (Alojamiento alojamiento : listaAlojamientos) {
			if (alojamiento.getCodigoAlojamiento().equals(codigoAlojamiento)) {
				return alojamiento;
			}
		}
		return null;
	}

	// GETTERS \\

	public ArrayList<Paquete> getListaPaquetes() {
		return listaPaquetes;
	}

	public ArrayList<Alojamiento> getListaAlojamientos() {
		return listaAlojamientos;
	}

	public ArrayList<Entrada> getListaEntradas() {
		return listaEntradas;
	}

	public ParqueTematico getParqueOferta() {
		return parqueOferta;
	}

	/***
	 *** *** METODOS PARA EL MANEJO DE TIPOS DE LA APLICACION *** ***
	 ***/

	// METODOS PROPIOS DE ENTRADAS \\

	public int[] getPreciosEntradas() {
		int minAdulto = listaEntradas.get(0).getPrecioAdulto();
		int maxAdulto = listaEntradas.get(0).getPrecioAdulto();
		int minKid = listaEntradas.get(0).getPrecioAdulto();
		int maxKid = listaEntradas.get(0).getPrecioAdulto();
		for (Entrada entrada : listaEntradas) {
			if (entrada.getPrecioAdulto() < minAdulto) {
				minAdulto = entrada.getPrecioAdulto();
			}
			if (entrada.getPrecioAdulto() > maxAdulto) {
				maxAdulto = entrada.getPrecioAdulto();
			}
			if (entrada.getPrecioKid() < minKid) {
				minKid = entrada.getPrecioKid();
			}
			if (entrada.getPrecioKid() > maxKid) {
				maxKid = entrada.getPrecioKid();
			}
		}
		return new int[] { minAdulto, maxAdulto, minKid, maxKid };
	}

	public void agregarNuevaReservaEntrada(Entrada entrada, String fecha, int numDias, int numAdultos, int numKids) {
		ParqueTematico parque = getParqueByCodigo(entrada.getCodigoParque());
		reserva.addEntrada(entrada.getCodigoEntrada(), parque.getDenominacion(), fecha, numDias, numAdultos, numKids);
	}

	public void eliminarReservaEntrada(String[] original) {
		String[] aux = original[0].split("Entrada para ");
		String[] aTrabajar = original;
		aTrabajar[0] = aux[1];
		reserva.eliminarReservaEntrada(aTrabajar);
	}

	public void modificarReservaEntrada(String[] original, String nuevaFecha, int nuevoNumDias, int nuevoNumAdultos,
			int nuevoNumKids) {
		String[] aux = original[0].split("Entrada para ");
		String nombreParque = aux[1];
		reserva.modificarEntrada(nombreParque, original[1], original[2], original[3], original[4], nuevaFecha,
				nuevoNumDias, nuevoNumAdultos, nuevoNumKids);
	}

	// METODOS PROPIOS DE ALOJAMIENTOS \\

	public int[] getPreciosAlojamientos() {
		int minimo = listaAlojamientos.get(0).getPrecio();
		int maximo = listaAlojamientos.get(0).getPrecio();
		for (Alojamiento aloja : listaAlojamientos) {
			if (aloja.getPrecio() < minimo) {
				minimo = aloja.getPrecio();
			}
			if (aloja.getPrecio() > maximo) {
				maximo = aloja.getPrecio();
			}
		}
		return new int[] { minimo, maximo };
	}

	public void agregarNuevaReservaAlojamiento(Alojamiento alojamiento, String fecha, int numNoches, int numPersonas,
			boolean desayuno) {
		ParqueTematico parque = getParqueByCodigo(alojamiento.getCodigoParque());
		reserva.addAlojamiento(alojamiento.getCodigoAlojamiento(), alojamiento.getTipo(), alojamiento.getNumEstrellas(),
				alojamiento.getDenominacion(), parque.getDenominacion(), desayuno, fecha, numNoches, numPersonas);
	}

	public void eliminarReservaAlojamiento(String[] original) {
		String[] aux = original[0].split("Alojamiento: ");
		String[] aTrabajar = original;
		aTrabajar[0] = aux[1];
		reserva.eliminarReservaAlojamiento(aTrabajar);
	}

	public String comprobarAforoAlojamiento(String denom, int numPersonas) {
		String aDevolver = "";
		for (Alojamiento alojamiento : listaAlojamientos) {
			if (alojamiento.getDenominacion().equals(denom)) {
				if (alojamiento.getNumPlazas() < numPersonas) {
					aDevolver = "Excede el aforo, el maximo para este alojamiento es de " + alojamiento.getNumPlazas()
							+ " personas.";
				} else {
					aDevolver = "Continuar";
				}
				break;
			}
		}
		return aDevolver;
	}

	public void modificarReservaAlojamiento(String[] original, String fecha, int numNoches, int numPersonas,
			boolean desayuno) {
		String antiguoDesayuno = "false";
		if (original[4].equals("S")) {
			antiguoDesayuno = "true";
		}
		reserva.modificarAlojamiento(original[0].split("Alojamiento: ")[1], original[1], original[2], original[3],
				antiguoDesayuno, fecha, numNoches, numPersonas, String.valueOf(desayuno));
	}

	// METODOS PROPIOS DE PAQUETES \\

	public int[] getPreciosPaquetes() {
		int minAdulto = listaPaquetes.get(0).getPrecioAdulto();
		int maxAdulto = listaPaquetes.get(0).getPrecioAdulto();
		int minKid = listaPaquetes.get(0).getPrecioAdulto();
		int maxKid = listaPaquetes.get(0).getPrecioAdulto();
		for (Paquete paquete : listaPaquetes) {
			if (paquete.getPrecioAdulto() < minAdulto) {
				minAdulto = paquete.getPrecioAdulto();
			}
			if (paquete.getPrecioAdulto() > maxAdulto) {
				maxAdulto = paquete.getPrecioAdulto();
			}
			if (paquete.getPrecioKid() < minKid) {
				minKid = paquete.getPrecioKid();
			}
			if (paquete.getPrecioKid() > maxKid) {
				maxKid = paquete.getPrecioKid();
			}
		}
		return new int[] { minAdulto, maxAdulto, minKid, maxKid };
	}

	public int getMaximaDuracionPaquete() {
		int maximo = listaPaquetes.get(0).getDuracion();
		for (Paquete paquete : listaPaquetes) {
			if (paquete.getDuracion() > maximo) {
				maximo = paquete.getDuracion();
			}
		}
		return maximo;
	}

	public void agregarNuevaReservaPaquete(Paquete paquete, int numAdultos, int numKids, String fecha) {
		ParqueTematico parque = getParqueByCodigo(paquete.getCodigoParque());
		reserva.addPaquete(paquete, parque.getDenominacion(), fecha, numAdultos, numKids);
	}

	public void eliminarReservaPaquete(String[] original) {
		String[] aux = original[0].split("Paquete: ");
		String[] aTrabajar = original;
		aTrabajar[0] = aux[1];
		reserva.eliminarReservaPaquete(aTrabajar);
	}

	public void modificarReservaPaquete(String[] original, String fecha, int numAdultos, int numKids) {
		reserva.modificarPaquete(original[0].split("Paquete: ")[1], original[1], original[2], original[3], fecha,
				numAdultos, numKids);
	}

}
