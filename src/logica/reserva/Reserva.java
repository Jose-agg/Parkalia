package logica.reserva;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import logica.manager.Manager;
import logica.tipos.Alojamiento;
import logica.tipos.Entrada;
import logica.tipos.Paquete;
import logica.tipos.ParqueTematico;

public class Reserva {

	private ArrayList<String[]> entradas;
	private ArrayList<String[]> alojamientos;
	private ArrayList<String[]> paquetes;
	private Manager manager;
	private Cliente cliente;

	public Reserva(Manager manager) {
		this.entradas = new ArrayList<String[]>();
		this.alojamientos = new ArrayList<String[]>();
		this.paquetes = new ArrayList<String[]>();
		this.manager = manager;
	}

	public boolean isEmpty() {
		return entradas.isEmpty() && alojamientos.isEmpty() && paquetes.isEmpty();
	}

	public void crearCliente(String nombre, String apellidos, String comentario) {
		cliente = new Cliente(nombre, apellidos, comentario);
	}

	public String introducirNif(String nif) {
		return cliente.introducirNif(nif);
	}

	public String getNif() {
		return cliente.getNif();
	}

	/***
	 *** *** METODOS PARA MOSTRAR DATOS DE LA RESERVA *** ***
	 ***/

	public ArrayList<String[]> getListadosReserva() {
		ArrayList<String[]> aDevolver = new ArrayList<String[]>();
		for (String[] strings : paquetes) {
			aDevolver.add(strings);
		}
		for (String[] strings : alojamientos) {
			aDevolver.add(strings);
		}
		for (String[] strings : entradas) {
			aDevolver.add(strings);
		}
		return aDevolver;
	}

	/**
	 * Este es para el carrito que esta en un lateral
	 */
	public String getEstadoReserva() {
		StringBuilder aDevolver = new StringBuilder("");
		if (!paquetes.isEmpty()) {
			aDevolver.append("****************\n\r");
			aDevolver.append("PAQUETES\n\r");
			aDevolver.append("****************\n\r");
			for (String[] strings : paquetes) {
				aDevolver.append("Denominacion: " + strings[1]);
				aDevolver.append("\n\rFecha: " + strings[4]);
				aDevolver.append("\n\rAdultos: " + strings[5]);
				aDevolver.append("\n\rNiños: " + strings[6]);
				aDevolver.append("\n\r-----------------\n\r");
			}
		}
		if (!alojamientos.isEmpty()) {
			aDevolver.append("********************\n\r");
			aDevolver.append("ALOJAMIENTOS\n\r");
			aDevolver.append("********************\n\r");
			for (String[] strings : alojamientos) {
				aDevolver.append("Denominacion: " + strings[3]);
				aDevolver.append("\n\rFecha: " + strings[6]);
				aDevolver.append("\n\rNoches: " + strings[7]);
				aDevolver.append("\n\rPersonas: " + strings[8]);
				if (strings[1].equals("HO")) {
					if (strings[5].equals("true")) {
						aDevolver.append("\n\rDesayuno: S");
					} else {
						aDevolver.append("\n\rDesayuno: N");
					}
				}
				aDevolver.append("\n\r-----------------\n\r");
			}
		}
		if (!entradas.isEmpty()) {
			aDevolver.append("****************\n\r");
			aDevolver.append("ENTRADAS\n\r");
			aDevolver.append("****************\n\r");
			for (String[] strings : entradas) {
				aDevolver.append("Denominacion: " + strings[1]);
				aDevolver.append("\n\rFecha: " + strings[2]);
				aDevolver.append("\n\rDias: " + strings[3]);
				aDevolver.append("\n\rAdultos: " + strings[4]);
				aDevolver.append("\n\rNiños: " + strings[5]);
				aDevolver.append("\n\r-----------------\n\r");
			}
		}
		return aDevolver.toString();
	}

	/**
	 * Este es para el carrito de la ventana que aparece con el cliente
	 */
	public DefaultListModel<String> refrescarCarrito(DefaultListModel<String> aDevolver) {
		boolean primeraEntrada = true, primerAlojamiento = true, primerPaquete = true;
		String cabecera;
		StringBuilder aAgregar;
		for (String[] datos : getListadosReserva()) {
			aAgregar = new StringBuilder("");

			if (datos[0].contains("PV")) {
				if (primerPaquete) {
					cabecera = "Paquete: <nombre>--<fecha>--<num adultos>--<num niños>";
					aDevolver.addElement(cabecera);
					primerPaquete = false;
				}
				aAgregar.append("Paquete: " + datos[1] + "--");
				aAgregar.append(datos[4] + "--");
				aAgregar.append(datos[5] + "--");
				aAgregar.append(datos[6]);
			}
			if (datos[0].contains("AL")) {
				if (primerAlojamiento) {
					cabecera = "Alojamiento: <nombre>--<fecha>--<num noches>--<num personas>--<desayuno>";
					aDevolver.addElement(cabecera);
					primerAlojamiento = false;
				}
				aAgregar.append("Alojamiento: " + datos[3] + "--");
				aAgregar.append(datos[6] + "--");
				aAgregar.append(datos[7] + "--");
				aAgregar.append(datos[8] + "--");
				if (datos[1].equals("HO")) {
					if (datos[5].equals("true")) {
						aAgregar.append("S");
					} else {
						aAgregar.append("N");
					}
				} else {
					aAgregar.append("__");
				}
			}
			if (datos[0].contains("EN")) {
				if (primeraEntrada) {
					cabecera = "Entrada para <parque>--<fecha>--<num dias>--<num adultos>--<num niños>";
					aDevolver.addElement(cabecera);
					primeraEntrada = false;
				}
				aAgregar.append("Entrada para " + datos[1] + "--");
				aAgregar.append(datos[2] + "--");
				aAgregar.append(datos[3] + "--");
				aAgregar.append(datos[4] + "--");
				aAgregar.append(datos[5]);
			}
			aDevolver.addElement(aAgregar.toString());
		}
		return aDevolver;
	}

	/**
	 * Este es para la ultima ventana, el que sigue el formato pedido en el modulo
	 */
	public String getResumen() {
		StringBuilder aDevolver = new StringBuilder("");
		aDevolver.append("VIAJES PARKALIA\n\n");
		aDevolver.append("JUSTIFICANTE DE RESERVA - " + manager.obtenerFechaActual() + "\n");
		aDevolver.append("--------------------------------------------------\n");
		aDevolver.append(cliente.getNif().toUpperCase() + " - " + cliente.getNombre().toUpperCase() + " "
				+ cliente.getApellidos().toUpperCase() + "\n\n");
		aDevolver.append("**** DATOS DE LA/S RESERVA/S ****\n\n");

		aDevolver.append("** PAQUETES TEMATICOS**\n");
		int numPaquetes = paquetes.size();
		int cont = 1;
		if (!paquetes.isEmpty()) {
			for (String[] e : paquetes) {
				aDevolver.append("Paquete: " + e[0] + " / " + e[1] + " / " + e[2] + " / " + e[3] + " días\n");
				aDevolver.append("Fecha inicio: " + e[4] + "\n");
				aDevolver.append("N. Adultos: " + e[5] + " / N. Niños: " + e[6] + "\n");
				if (numPaquetes != cont) {
					aDevolver.append("\n ****************************** \n\n");
					cont++;
				}
			}
		}
		aDevolver.append("\n");

		aDevolver.append("** ALOJAMIENTOS **\n");
		int numAlojamientos = alojamientos.size();
		cont = 1;
		if (!alojamientos.isEmpty()) {
			for (String[] e : alojamientos) {
				aDevolver.append("Alojamiento: " + e[0] + " / " + e[1] + " / " + e[3] + " / " + e[2] + " estrellas / "
						+ e[4] + "\n");
				aDevolver.append("Fecha inicio: " + e[6] + " / Número de noches: " + e[7]);
				if (e[1].equals("HO")) {
					if (e[5].equals("true")) {
						aDevolver.append(" / Desayuno: S\n");
					} else {
						aDevolver.append(" / Desayuno: N\n");
					}
				} else {
					aDevolver.append("\n");
				}
				aDevolver.append("N. Personas: " + e[8] + "\n");
				if (numAlojamientos != cont) {
					aDevolver.append("\n ****************************** \n\n");
					cont++;
				}
			}
		}
		aDevolver.append("\n");

		aDevolver.append("** ENTRADAS **\n");
		int numEntradas = entradas.size();
		cont = 1;
		if (!entradas.isEmpty()) {
			for (String[] e : entradas) {
				aDevolver.append("Entrada: " + e[0] + " / " + e[1] + "\n");
				aDevolver.append("Fecha inicio: " + e[2] + " / Número de días: " + e[3] + "\n");
				aDevolver.append("N. Adultos: " + e[4] + " / N. Niños: " + e[5] + "\n");
				if (numEntradas != cont) {
					aDevolver.append("\n ****************************** \n\n");
					cont++;
				}
			}
		}
		aDevolver.append("\n");

		aDevolver.append("\n **** PAGO RESERVA ****\n");
		int[] precios = calcularPrecios();
		aDevolver.append("Paquetes temáticos:\t\t" + precios[0] + " €\n");
		aDevolver.append("Alojamientos:\t\t\t" + precios[1] + " €\n");
		aDevolver.append("Entradas:\t\t\t" + precios[2] + " €\n");
		if (precios[3] > 0) {
			aDevolver.append("Descuentos oferta:\t\t" + precios[3] + " €\n");
		}
		aDevolver.append("\n");
		aDevolver.append("Importe Total:\t\t\t" + precios[4] + " €\n");
		if (!cliente.getComentario().contains("...")) {
			aDevolver.append("\n");
			aDevolver.append("Comentario: \n");
			aDevolver.append(cliente.getComentario());
		}
		return aDevolver.toString();
	}

	public String actualizarPrecioCarrito() {
		int[] precios = calcularPrecios();
		return String.valueOf(precios[4] + precios[3]);
	}

	// METODOS PARA RELLENAR EL RESUMEN DE LA RESERVA \\

	private int[] calcularPrecios() {
		int[] totalPaquetes = calcularTotalPaquetes();
		int[] totalAlojamientos = calcularTotalAlojamientos();
		int[] totalEntradas = calcularTotalEntradas();
		int total = totalPaquetes[0] + totalAlojamientos[0] + totalEntradas[0];
		int totalDescuentoOferta = totalPaquetes[1] + totalAlojamientos[1] + totalEntradas[1];
		int importeFinal = total - totalDescuentoOferta;
		int[] aDevolver = new int[] { totalPaquetes[0], totalAlojamientos[0], totalEntradas[0], totalDescuentoOferta,
				importeFinal };
		return aDevolver;
	}

	private int[] calcularTotalPaquetes() {
		int[] aDevolver = new int[2];
		int total = 0, descuentoTotal = 0, adultosAux = 0, kidsAux = 0, subTotal = 0;
		Paquete paquete = null;
		ParqueTematico parqueOferta = manager.getParqueOferta();
		for (String[] datos : paquetes) {
			for (Paquete e : manager.getListaPaquetes()) {
				if (e.getCodigoPaquete().equals(datos[0])) {
					paquete = e;
					break;
				}
			}
			adultosAux = paquete.getPrecioAdulto() * Integer.valueOf(datos[5]);
			kidsAux = paquete.getPrecioKid() * Integer.valueOf(datos[6]);
			subTotal = adultosAux + kidsAux;
			if (parqueOferta.getDenominacion().equals(datos[2])) {
				descuentoTotal += subTotal * 0.2;
			}
			total += subTotal;
		}
		aDevolver[0] = total;
		aDevolver[1] = descuentoTotal;
		return aDevolver;
	}

	private int[] calcularTotalAlojamientos() {
		int[] aDevolver = new int[2];
		int total = 0, descuentoTotal = 0, numNoches = 0, numPersonas = 0, precioNoche = 0, subTotal = 0;
		Alojamiento aloja = null;
		ParqueTematico parqueOferta = manager.getParqueOferta();
		for (String[] datos : alojamientos) {
			for (Alojamiento e : manager.getListaAlojamientos()) {
				if (e.getCodigoAlojamiento().equals(datos[0])) {
					aloja = e;
					break;
				}
			}
			numNoches = Integer.valueOf(datos[7]);
			numPersonas = Integer.valueOf(datos[8]);
			precioNoche = aloja.getPrecio();
			if (datos[1].equals("HO")) {
				if (datos[5].equals("true")) {
					subTotal = precioNoche * numNoches * numPersonas;
					subTotal += subTotal * 0.1;
				} else {
					subTotal = precioNoche * numNoches * numPersonas;
				}
			} else {
				subTotal = precioNoche * numNoches;
			}
			if (parqueOferta.getDenominacion().equals(datos[4])) {
				descuentoTotal += subTotal * 0.2;
			}
			total += subTotal;
		}
		aDevolver[0] = total;
		aDevolver[1] = descuentoTotal;
		return aDevolver;
	}

	private int[] calcularTotalEntradas() {
		int[] aDevolver = new int[2];
		int total = 0, descuentoTotal = 0, adultosAux = 0, kidsAux = 0, subTotal = 0;
		Entrada entrada = null;
		ParqueTematico parqueOferta = manager.getParqueOferta();
		for (String[] datos : entradas) {
			for (Entrada e : manager.getListaEntradas()) {
				if (e.getCodigoEntrada().equals(datos[0])) {
					entrada = e;
					break;
				}
			}
			adultosAux = entrada.getPrecioAdulto() * Integer.valueOf(datos[4]);
			kidsAux = entrada.getPrecioKid() * Integer.valueOf(datos[5]);
			subTotal = (adultosAux + kidsAux) * Integer.valueOf(datos[3]);
			if (parqueOferta.getDenominacion().equals(datos[1])) {
				descuentoTotal += subTotal * 0.2;
			}
			total += subTotal;
		}
		aDevolver[0] = total;
		aDevolver[1] = descuentoTotal;
		return aDevolver;
	}

	// METODOS PARA LAS ENTRADAS \\

	public void addEntrada(String codigoEntrada, String denominacion, String fecha, int numDias, int numAdultos,
			int numKids) {
		String[] nueva = new String[] { codigoEntrada, denominacion, fecha, String.valueOf(numDias),
				String.valueOf(numAdultos), String.valueOf(numKids) };
		this.entradas.add(nueva);
	}

	public void eliminarReservaEntrada(String[] original) {
		int cont = 1;
		int pos = 0;
		boolean igual = true;
		for (String[] enReserva : entradas) {
			for (String dato : original) {
				if (!dato.equals(enReserva[cont])) {
					igual = false;
					break;
				}
				cont++;
			}
			if (igual) {
				entradas.remove(pos);
				break;
			}
			cont = 1;
			pos++;
		}

	}

	public void modificarEntrada(String nombreParque, String antiguaFecha, String antiguoNumDias,
			String antiguoNumAdultos, String antiguoNumKids, String nuevaFecha, int nuevoNumDias, int nuevoNumAdultos,
			int nuevoNumKids) {
		for (String[] aux : entradas) {
			if (aux[1].equals(nombreParque) && aux[2].equals(antiguaFecha) && aux[3].equals(antiguoNumDias)
					&& aux[4].equals(antiguoNumAdultos) && aux[5].equals(antiguoNumKids)) {
				aux[2] = nuevaFecha;
				aux[3] = String.valueOf(nuevoNumDias);
				aux[4] = String.valueOf(nuevoNumAdultos);
				aux[5] = String.valueOf(nuevoNumKids);
				break;
			}
		}
	}

	// METODOS PARA LOS ALOJAMIENTOS \\

	public void addAlojamiento(String codigoAlojamiento, String tipo, int numEstrellas, String alojaDenom,
			String parqueDenom, boolean desayuno, String fecha, int numNoches, int numPersonas) {
		String[] nueva = new String[] { codigoAlojamiento, tipo, String.valueOf(numEstrellas), alojaDenom, parqueDenom,
				String.valueOf(desayuno), String.valueOf(fecha), String.valueOf(numNoches),
				String.valueOf(numPersonas) };
		this.alojamientos.add(nueva);
	}

	public void eliminarReservaAlojamiento(String[] original) {
		int pos = 0;
		for (String[] enReserva : alojamientos) {
			if (original[0].equals(enReserva[3]) && original[1].equals(enReserva[6]) && original[2].equals(enReserva[7])
					&& original[3].equals(enReserva[8])) {
				if (original[4].equals("S") && enReserva[5].equals("true")) {
					alojamientos.remove(pos);
					break;
				}
				if (!original[4].equals("S") && enReserva[5].equals("false")) {
					alojamientos.remove(pos);
					break;
				}
			}
			pos++;
		}
	}

	public void modificarAlojamiento(String nombreAloja, String antiguaFecha, String antiguoNumNoches,
			String antiguoNumPersonas, String antiguoDesayuno, String nuevaFecha, int nuevoNumNoches,
			int nuevoNumPersonas, String nuevoDesayuno) {
		for (String[] aux : alojamientos) {
			if (aux[3].equals(nombreAloja) && aux[6].equals(antiguaFecha) && aux[7].equals(antiguoNumNoches)
					&& aux[8].equals(antiguoNumPersonas) && aux[5].equals(antiguoDesayuno)) {
				aux[6] = nuevaFecha;
				aux[7] = String.valueOf(nuevoNumNoches);
				aux[8] = String.valueOf(nuevoNumPersonas);
				aux[5] = nuevoDesayuno;
				break;
			}
		}

	}

	// METODOS PARA LOS PAQUETES \\

	public void addPaquete(Paquete paquete, String parqueDenom, String fecha, int numAdultos, int numKids) {
		String[] nueva = new String[] { paquete.getCodigoPaquete(), paquete.getDenominacion(), parqueDenom,
				String.valueOf(paquete.getDuracion()), fecha, String.valueOf(numAdultos), String.valueOf(numKids) };
		this.paquetes.add(nueva);
	}

	public void eliminarReservaPaquete(String[] original) {
		int pos = 0;
		for (String[] enReserva : paquetes) {
			if (original[0].equals(enReserva[1]) && original[1].equals(enReserva[4]) && original[2].equals(enReserva[5])
					&& original[3].equals(enReserva[6])) {
				paquetes.remove(pos);
				break;
			}
			pos++;
		}
	}

	public void modificarPaquete(String denomPaquete, String antiguaFecha, String antiguoNumAdultos,
			String antiguoNumKids, String nuevaFecha, int nuevoNumAdultos, int nuevoNumKids) {
		for (String[] aux : paquetes) {
			if (aux[1].equals(denomPaquete) && aux[4].equals(antiguaFecha) && aux[5].equals(antiguoNumAdultos)
					&& aux[6].equals(antiguoNumKids)) {
				aux[4] = nuevaFecha;
				aux[5] = String.valueOf(nuevoNumAdultos);
				aux[6] = String.valueOf(nuevoNumKids);
				break;
			}
		}
	}
}
