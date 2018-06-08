package logica.reserva;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

	private String nombre;
	private String apellidos;
	private String comentario;
	private String nif;

	public Cliente(String nombre, String apellidos, String comentario) {
		this.nombre = nombre;
		this.comentario = comentario;
		this.apellidos = apellidos;
	}

	public String introducirNif(String nif) {
		String correcto = "";
		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher matcher = pattern.matcher(nif);
		if (matcher.matches()) {
			String letra = matcher.group(2);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int index = Integer.parseInt(matcher.group(1));
			index = index % 23;
			String reference = letras.substring(index, index + 1);
			if (reference.equalsIgnoreCase(letra)) {
				correcto = "Valido";
				this.nif = nif;
			} else {
				correcto = "La letra no coincide con los numeros en el NIF";
			}
		} else {
			correcto = "Introduzca un NIF valido con el formato: 123456789Z, sin guion ni espacios";
		}
		return correcto;
	}

	public String getComentario() {
		return comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getNif() {
		return nif;
	}

}
