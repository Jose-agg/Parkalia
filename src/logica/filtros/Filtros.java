package logica.filtros;

import java.util.ArrayList;

import logica.tipos.Alojamiento;
import logica.tipos.Entrada;
import logica.tipos.Paquete;
import logica.tipos.ParqueTematico;

public class Filtros {

	// ENTRADAS \\

	public static ArrayList<Entrada> filtrarPorDenominacionEntrada(ArrayList<Entrada> listaEntradas, String palabra) {
		ArrayList<Entrada> aDevolver = new ArrayList<Entrada>();
		String p = palabra.toLowerCase();
		for (Entrada entrada : listaEntradas) {
			String[] denomSeparada = entrada.getDenominacion().split(" ");
			for (String string : denomSeparada) {
				String s = string.toLowerCase();
				if (s.contains(p)) {
					aDevolver.add(entrada);
					break;
				}
			}
		}
		return aDevolver;
	}

	public static ArrayList<Entrada> filtrarPorParqueEntrada(ArrayList<Entrada> listaEntradas,
			ArrayList<ParqueTematico> listaParques, String nombreParque) {
		ParqueTematico pT = null;
		for (ParqueTematico parqueTematico : listaParques) {
			if (parqueTematico.getDenominacion().equals(nombreParque)) {
				pT = parqueTematico;
				break;
			}
		}
		ArrayList<Entrada> aDevolver = new ArrayList<Entrada>();
		for (Entrada entrada : listaEntradas) {
			if (entrada.getCodigoParque().equals(pT.getCodigoParque())) {
				aDevolver.add(entrada);
			}
		}
		return aDevolver;
	}

	public static ArrayList<Entrada> filtrarPorPrecioEntrada(ArrayList<Entrada> listaEntradas, int adulto, int kid) {
		ArrayList<Entrada> aDevolver = new ArrayList<Entrada>();
		for (Entrada entrada : listaEntradas) {
			if ((entrada.getPrecioAdulto() <= adulto) && (entrada.getPrecioKid() <= kid)) {
				aDevolver.add(entrada);
			}
		}
		return aDevolver;
	}

	// ALOJAMIENTOS \\

	public static ArrayList<Alojamiento> filtrarPorDenominacionAlojamiento(ArrayList<Alojamiento> listaAlojamientos,
			String palabra) {
		ArrayList<Alojamiento> aDevolver = new ArrayList<Alojamiento>();
		String p = palabra.toLowerCase();
		for (Alojamiento aloja : listaAlojamientos) {
			String[] denomSeparada = aloja.getDenominacion().split(" ");
			for (String string : denomSeparada) {
				String s = string.toLowerCase();
				if (s.contains(p)) {
					aDevolver.add(aloja);
					break;
				}
			}
		}
		return aDevolver;
	}

	public static ArrayList<Alojamiento> filtrarPorParqueAlojamiento(ArrayList<Alojamiento> listaAlojamientos,
			ArrayList<ParqueTematico> listaParques, String nombreParque) {
		ParqueTematico pT = null;
		for (ParqueTematico parqueTematico : listaParques) {
			if (parqueTematico.getDenominacion().equals(nombreParque)) {
				pT = parqueTematico;
				break;
			}
		}
		ArrayList<Alojamiento> aDevolver = new ArrayList<Alojamiento>();
		for (Alojamiento aloja : listaAlojamientos) {
			if (aloja.getCodigoParque().equals(pT.getCodigoParque())) {
				aDevolver.add(aloja);
			}
		}
		return aDevolver;
	}

	public static ArrayList<Alojamiento> filtrarPorPrecioAlojamiento(ArrayList<Alojamiento> listaAlojamientos,
			int precioMinimo) {
		ArrayList<Alojamiento> aDevolver = new ArrayList<Alojamiento>();
		for (Alojamiento aloja : listaAlojamientos) {
			if (aloja.getPrecio() <= precioMinimo) {
				aDevolver.add(aloja);
			}
		}
		return aDevolver;
	}

	public static ArrayList<Alojamiento> filtrarPorPrecioAlojamiento(ArrayList<Alojamiento> listaAlojamientos,
			boolean hotel, boolean apartahotel, boolean apartamento) {
		ArrayList<Alojamiento> aDevolver = new ArrayList<Alojamiento>();
		for (Alojamiento aloja : listaAlojamientos) {
			if (aloja.getTipo().equals("HO") && hotel) {
				aDevolver.add(aloja);
			}
			if (aloja.getTipo().equals("AH") && apartahotel) {
				aDevolver.add(aloja);
			}
			if (aloja.getTipo().equals("AP") && apartamento) {
				aDevolver.add(aloja);
			}
		}
		return aDevolver;
	}

	public static ArrayList<Alojamiento> filtrarPorCategoriaAlojamiento(ArrayList<Alojamiento> listaAlojamientos,
			int numEstrellas) {
		ArrayList<Alojamiento> aDevolver = new ArrayList<Alojamiento>();
		for (Alojamiento aloja : listaAlojamientos) {
			if (aloja.getNumEstrellas() >= numEstrellas) {
				aDevolver.add(aloja);
			}
		}
		return aDevolver;
	}

	// PAQUETES \\

	public static ArrayList<Paquete> filtrarPorDenominacionPaquete(ArrayList<Paquete> listaPaquetes, String palabra) {
		ArrayList<Paquete> aDevolver = new ArrayList<Paquete>();
		String p = palabra.toLowerCase();
		for (Paquete paquete : listaPaquetes) {
			String[] denomSeparada = paquete.getDenominacion().split(" ");
			for (String string : denomSeparada) {
				String s = string.toLowerCase();
				if (s.contains(p)) {
					aDevolver.add(paquete);
					break;
				}
			}
		}
		return aDevolver;
	}

	public static ArrayList<Paquete> filtrarPorParquePaquete(ArrayList<Paquete> listaPaquetes,
			ArrayList<ParqueTematico> listaParques, String nombreParque) {
		ParqueTematico pT = null;
		for (ParqueTematico parqueTematico : listaParques) {
			if (parqueTematico.getDenominacion().equals(nombreParque)) {
				pT = parqueTematico;
				break;
			}
		}
		ArrayList<Paquete> aDevolver = new ArrayList<Paquete>();
		for (Paquete paquete : listaPaquetes) {
			if (paquete.getCodigoParque().equals(pT.getCodigoParque())) {
				aDevolver.add(paquete);
			}
		}
		return aDevolver;
	}

	public static ArrayList<Paquete> filtrarPorPrecioPaquete(ArrayList<Paquete> listaPaquetes, int adulto, int kid) {
		ArrayList<Paquete> aDevolver = new ArrayList<Paquete>();
		for (Paquete paquete : listaPaquetes) {
			if ((paquete.getPrecioAdulto() <= adulto) && (paquete.getPrecioKid() <= kid)) {
				aDevolver.add(paquete);
			}
		}
		return aDevolver;
	}

	public static ArrayList<Paquete> filtrarPorDuracionPaquete(ArrayList<Paquete> listaPaquetes, int duracion) {
		ArrayList<Paquete> aDevolver = new ArrayList<Paquete>();
		for (Paquete paquete : listaPaquetes) {
			if (paquete.getDuracion() >= duracion) {
				aDevolver.add(paquete);
			}
		}
		return aDevolver;
	}

}
