package logica.manager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CreadorArchivos {

	public static String guardarFactura(String nombreFichero, String factura) {
		String aDevolver = "";
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("facturas/" + nombreFichero + ".dat"));
			fichero.write(factura);
			fichero.close();
			aDevolver = "Completado";
		} catch (FileNotFoundException fnfe) {
			aDevolver = "El archivo no se ha podido guardar";
		} catch (IOException ioe) {
			aDevolver = "Error de entrada/salida";
		}
		return aDevolver;
	}

}
