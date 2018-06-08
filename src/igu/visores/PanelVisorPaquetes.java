package igu.visores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import igu.listas.PanelListasPaquetes;
import logica.tipos.Alojamiento;
import logica.tipos.Paquete;
import logica.tipos.ParqueTematico;

public class PanelVisorPaquetes extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblDenominacion;
	private JLabel lblImagen;
	private JPanel pnInformacion;
	private JButton btnAgregarCarrito;
	private JPanel pnBotones;
	private JButton btnAtras;
	private JTextArea txaInformacion;
	private JPanel pnDatos;
	private JPanel pnFecha;
	private JPanel pnIntroducirAdultos;
	private JLabel lblCantidadesAdultos;
	private JPanel pnSubDatos;
	private JPanel pnAdultos;
	private JPanel pnIntroducirKid;
	private JLabel lblCantidadesKid;
	private JPanel pnTotal;
	private JPanel pnSubTotal;
	private JLabel lblPrecioTotal;
	private JTextField txtPrecioTotal;
	private JSpinner spinCantidadesAdultos;
	private JSpinner spinCantidadesKids;
	private JPanel pnImagen;
	private JScrollPane scrpnInformacion;
	private JPanel pnKids;
	private JPanel pnCalendario;
	private JPanel pnSubCalendario;
	private JLabel lblFechaInicio;
	private static JDateChooser dateChooser;

	private PanelListasPaquetes panelPrevio;
	private Paquete paquete;

	public PanelVisorPaquetes(PanelListasPaquetes panelPrevio, Paquete paquete) {
		this.panelPrevio = panelPrevio;
		this.paquete = paquete;
		setLayout(new BorderLayout(5, 5));
		add(getLblDenominacion(), BorderLayout.NORTH);
		add(getPnImagen(), BorderLayout.WEST);
		add(getPnInformacion(), BorderLayout.CENTER);
		add(getPnBotones(), BorderLayout.SOUTH);
		rellenarDatos();
	}

	private JLabel getLblDenominacion() {
		if (lblDenominacion == null) {
			lblDenominacion = new JLabel("");
			lblDenominacion.setBounds(new Rectangle(5, 5, 0, 0));
			lblDenominacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblDenominacion;
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblImagen(), BorderLayout.NORTH);
		}
		return pnImagen;
	}

	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
		}
		return lblImagen;
	}

	private JPanel getPnInformacion() {
		if (pnInformacion == null) {
			pnInformacion = new JPanel();
			pnInformacion.setLayout(new BorderLayout(0, 0));
			pnInformacion.add(getScrpnInformacion(), BorderLayout.NORTH);
			pnInformacion.add(getPnDatos(), BorderLayout.CENTER);
			pnInformacion.add(getPnTotal(), BorderLayout.SOUTH);
		}
		return pnInformacion;
	}

	private JButton getBtnAgregarCarrito() {
		if (btnAgregarCarrito == null) {
			btnAgregarCarrito = new JButton("A\u00F1adir al carrito");
			btnAgregarCarrito.setMnemonic('O');
			btnAgregarCarrito.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String continuar = comprobarDatos();
					agregarMercanciaCarrito(continuar);
				}
			});
		}
		return btnAgregarCarrito;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new BorderLayout(0, 0));
			pnBotones.add(getBtnAgregarCarrito(), BorderLayout.EAST);
			pnBotones.add(getBtnAtras(), BorderLayout.WEST);
		}
		return pnBotones;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.setMnemonic('T');
			btnAtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panelPrevio.getPLG().mostrarPanelesVisores(panelPrevio);
					panelPrevio.limpiarCampos();
				}
			});
		}
		return btnAtras;
	}

	private JScrollPane getScrpnInformacion() {
		if (scrpnInformacion == null) {
			scrpnInformacion = new JScrollPane();
			scrpnInformacion.setViewportView(getTxaInformacion());
		}
		return scrpnInformacion;
	}

	private JTextArea getTxaInformacion() {
		if (txaInformacion == null) {
			txaInformacion = new JTextArea();
			txaInformacion.setRows(10);
			txaInformacion.setDisabledTextColor(Color.BLACK);
			txaInformacion.setBackground(new Color(240, 240, 240));
			txaInformacion.setWrapStyleWord(true);
			txaInformacion.setLineWrap(true);
			txaInformacion.setEnabled(false);
			txaInformacion.setEditable(false);
		}
		return txaInformacion;
	}

	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setLayout(new BorderLayout(0, 0));
			pnDatos.add(getPnFecha(), BorderLayout.NORTH);
			pnDatos.add(getPnSubDatos(), BorderLayout.CENTER);
		}
		return pnDatos;
	}

	private JPanel getPnFecha() {
		if (pnFecha == null) {
			pnFecha = new JPanel();
			pnFecha.setLayout(new BorderLayout(0, 0));
			pnFecha.add(getPnCalendario(), BorderLayout.NORTH);
		}
		return pnFecha;
	}

	private JPanel getPnIntroducirAdultos() {
		if (pnIntroducirAdultos == null) {
			pnIntroducirAdultos = new JPanel();
			pnIntroducirAdultos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnIntroducirAdultos.add(getLblCantidadesAdultos());
			pnIntroducirAdultos.add(getSpinCantidadesAdultos());
		}
		return pnIntroducirAdultos;
	}

	private JSpinner getSpinCantidadesAdultos() {
		if (spinCantidadesAdultos == null) {
			spinCantidadesAdultos = new JSpinner();
			spinCantidadesAdultos.setPreferredSize(new Dimension(75, 22));
			spinCantidadesAdultos.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					actualizarPrecios();
				}
			});
			spinCantidadesAdultos
					.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		}
		return spinCantidadesAdultos;
	}

	private JLabel getLblCantidadesAdultos() {
		if (lblCantidadesAdultos == null) {
			lblCantidadesAdultos = new JLabel("Numero de adultos");
			lblCantidadesAdultos.setDisplayedMnemonic('N');
			lblCantidadesAdultos.setLabelFor(getSpinCantidadesAdultos());
		}
		return lblCantidadesAdultos;
	}

	private JPanel getPnSubDatos() {
		if (pnSubDatos == null) {
			pnSubDatos = new JPanel();
			pnSubDatos.setLayout(new BorderLayout(0, 0));
			pnSubDatos.add(getPnAdultos(), BorderLayout.NORTH);
			pnSubDatos.add(getPnKids(), BorderLayout.CENTER);
		}
		return pnSubDatos;
	}

	private JPanel getPnAdultos() {
		if (pnAdultos == null) {
			pnAdultos = new JPanel();
			pnAdultos.setLayout(new BorderLayout(0, 0));
			pnAdultos.add(getPnIntroducirAdultos(), BorderLayout.WEST);
		}
		return pnAdultos;
	}

	private JPanel getPnIntroducirKid() {
		if (pnIntroducirKid == null) {
			pnIntroducirKid = new JPanel();
			pnIntroducirKid.add(getLblCantidadesKid());
			pnIntroducirKid.add(getSpinCantidadesKids());
		}
		return pnIntroducirKid;
	}

	private JSpinner getSpinCantidadesKids() {
		if (spinCantidadesKids == null) {
			spinCantidadesKids = new JSpinner();
			spinCantidadesKids.setPreferredSize(new Dimension(75, 22));
			spinCantidadesKids.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					actualizarPrecios();
				}
			});
			spinCantidadesKids.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		}
		return spinCantidadesKids;
	}

	private JLabel getLblCantidadesKid() {
		if (lblCantidadesKid == null) {
			lblCantidadesKid = new JLabel("Numero de niños");
			lblCantidadesKid.setDisplayedMnemonic('U');
			lblCantidadesKid.setLabelFor(getSpinCantidadesKids());
		}
		return lblCantidadesKid;
	}

	private JPanel getPnTotal() {
		if (pnTotal == null) {
			pnTotal = new JPanel();
			pnTotal.setLayout(new BorderLayout(0, 0));
			pnTotal.add(getPnSubTotal(), BorderLayout.EAST);
		}
		return pnTotal;
	}

	private JPanel getPnSubTotal() {
		if (pnSubTotal == null) {
			pnSubTotal = new JPanel();
			pnSubTotal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnSubTotal.add(getLblPrecioTotal());
			pnSubTotal.add(getTxtPrecioTotal());
		}
		return pnSubTotal;
	}

	private JLabel getLblPrecioTotal() {
		if (lblPrecioTotal == null) {
			lblPrecioTotal = new JLabel("Precio Total");
		}
		return lblPrecioTotal;
	}

	private JTextField getTxtPrecioTotal() {
		if (txtPrecioTotal == null) {
			txtPrecioTotal = new JTextField();
			txtPrecioTotal.setText("0 \u20AC");
			txtPrecioTotal.setDisabledTextColor(Color.BLACK);
			txtPrecioTotal.setEnabled(false);
			txtPrecioTotal.setEditable(false);
			txtPrecioTotal.setColumns(8);
		}
		return txtPrecioTotal;
	}

	private JPanel getPnKids() {
		if (pnKids == null) {
			pnKids = new JPanel();
			pnKids.setLayout(new BorderLayout(0, 0));
			pnKids.add(getPnIntroducirKid(), BorderLayout.WEST);
		}
		return pnKids;
	}

	private JPanel getPnCalendario() {
		if (pnCalendario == null) {
			pnCalendario = new JPanel();
			pnCalendario.setLayout(new BorderLayout(0, 0));
			pnCalendario.add(getPnSubCalendario(), BorderLayout.WEST);
		}
		return pnCalendario;
	}

	private JPanel getPnSubCalendario() {
		if (pnSubCalendario == null) {
			pnSubCalendario = new JPanel();
			pnSubCalendario.add(getLblFechaInicio());
			pnSubCalendario.add(getDateChooser());
		}
		return pnSubCalendario;
	}

	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha de Inicio");
			lblFechaInicio.setLabelFor(getDateChooser());
			lblFechaInicio.setDisplayedMnemonic('F');
		}
		return lblFechaInicio;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setPreferredSize(new Dimension(125, 25));
			dateChooser.setVerifyInputWhenFocusTarget(false);
			dateChooser.setDateFormatString("dd/MM/yyyy");
			dateChooser.setMinSelectableDate(new Date());
			dateChooser.getCalendarButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		return dateChooser;
	}

	// Mis metodos \\

	private void rellenarDatos() {
		lblDenominacion.setText(paquete.getDenominacion());
		ParqueTematico pt = panelPrevio.getPLG().getVP().getManager().getParqueByCodigo(paquete.getCodigoParque());
		Alojamiento aloja = panelPrevio.getPLG().getVP().getManager()
				.getAlojamientoByCodigo(paquete.getCodigoAlojamiento());

		// Informacion del paquete
		StringBuilder infoExtra = new StringBuilder("Duracion: " + paquete.getDuracion() + " dias.\n\r");

		infoExtra.append("Precio por adulto: " + paquete.getPrecioAdulto() + "€.\n\rPrecio por niño: "
				+ paquete.getPrecioKid() + "€.");

		if (paquete.getCodigoParque()
				.equals(panelPrevio.getPLG().getVP().getManager().getParqueOferta().getCodigoParque())) {
			infoExtra.append(
					"\n\r\n\rEl parque vinculado a este paquete esta en oferta.\n\r¡Compra ahora y llevate un descuento del 20%!");
		}

		// Informacion del parque
		infoExtra.append("\n\r\n\rInformacion del Parque:\n\r" + "Denominacion: " + pt.getDenominacion() + ".\n\rPais: "
				+ pt.getPais() + ". Localidad: " + pt.getLocalidad() + ".\n\r\n\rDescripcion\n\r " + pt.getDescripcion()
				+ ".");

		// Informacion del alojamiento
		String tipo = aloja.getTipo();
		if (tipo.equals("HO")) {
			tipo = "Hotel";
		}
		if (tipo.equals("AH")) {
			tipo = "Apartahotel";
		}
		if (tipo.equals("AP")) {
			tipo = "Apartamento";
		}

		infoExtra.append("\n\r\n\rInformacion del Alojamiento:\n\r" + "Denominacion: " + aloja.getDenominacion()
				+ ".\n\rTipo: " + tipo + ".\n\rNumero de estrellas: " + aloja.getNumEstrellas() + ".");
		txaInformacion.setText(infoExtra.toString());
		redimensionarImagen();
	}

	private void redimensionarImagen() {
		Image imgOriginal = paquete.getImagen();
		Image imgEscalada = imgOriginal.getScaledInstance((400), (400), Image.SCALE_FAST);
		lblImagen.setIcon(new ImageIcon(imgEscalada));
		lblImagen.setDisabledIcon(new ImageIcon(imgEscalada));
	}

	private void actualizarPrecios() {
		int numAdultos = ((int) spinCantidadesAdultos.getValue());
		int numKids = ((int) spinCantidadesKids.getValue());
		int precioTotal = ((numAdultos * paquete.getPrecioAdulto()) + (numKids * paquete.getPrecioKid()));
		txtPrecioTotal.setText(String.valueOf(precioTotal) + " €");
	}

	private static String obtenerFecha() {
		Date today = dateChooser.getDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String folderName = formatter.format(today);
		return folderName;
	}

	private void agregarMercanciaCarrito(String continuar) {
		if (continuar.equals("Continuar")) {
			int numAdultos = (int) spinCantidadesAdultos.getValue();
			int numKids = (int) spinCantidadesKids.getValue();
			String fecha = obtenerFecha();
			panelPrevio.getPLG().getVP().getManager().agregarNuevaReservaPaquete(paquete, numAdultos, numKids, fecha);
			panelPrevio.getPLG().actualizarCarrito();
		} else {
			JOptionPane.showMessageDialog(null, continuar);
		}
	}

	private String comprobarDatos() {
		if (((int) spinCantidadesAdultos.getValue() == 0) && ((int) spinCantidadesKids.getValue() == 0)) {
			return "Introduzca al menos una persona.";
		}
		try {
			obtenerFecha();
		} catch (Exception e) {
			return "Seleccione una fecha valida.";
		}
		return "Continuar";

	}
}
