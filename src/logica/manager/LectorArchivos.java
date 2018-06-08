package logica.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logica.tipos.Alojamiento;
import logica.tipos.Entrada;
import logica.tipos.Paquete;
import logica.tipos.ParqueTematico;

public class LectorArchivos {

	public static ArrayList<ParqueTematico> leerFicheroParques(String ruta) {
		String linea = "";
		String[] datosArticulo = null;
		ArrayList<ParqueTematico> array = new ArrayList<ParqueTematico>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				array.add(new ParqueTematico(datosArticulo));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		return array;
	}

	public static ArrayList<Paquete> leerFicheroPaquetes(String ruta) {
		String linea = "";
		String[] datosArticulo = null;
		ArrayList<Paquete> array = new ArrayList<Paquete>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				array.add(new Paquete(datosArticulo));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		return array;
	}

	public static ArrayList<Entrada> leerFicheroEntradas(String ruta) {
		String linea = "";
		String[] datosArticulo = null;
		ArrayList<Entrada> array = new ArrayList<Entrada>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				array.add(new Entrada(datosArticulo));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		return array;
	}

	public static ArrayList<Alojamiento> leerFicheroAlojamientos(String ruta) {
		String linea = "";
		String[] datosArticulo = null;
		ArrayList<Alojamiento> array = new ArrayList<Alojamiento>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				array.add(new Alojamiento(datosArticulo));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		return array;
	}
}
