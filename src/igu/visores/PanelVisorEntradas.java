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

import igu.listas.PanelListasEntradas;
import logica.tipos.Entrada;
import logica.tipos.ParqueTematico;

public class PanelVisorEntradas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblDenominacion;
	private JLabel lblImagen;
	private JPanel pnInformacion;
	private JButton btnAgregarCarrito;
	private JPanel pnBotones;
	private JButton btnAtras;
	private JTextArea txaInformacion;
	private JPanel pnCantidades;
	private JPanel pnCantidadesAdultos;
	private JPanel pnIntroducirAdultos;
	private JLabel lblCantidadesAdultos;
	private JPanel pnPrecioAdultos;
	private JLabel lblPrecioAdultos;
	private JTextField txtPrecioAdultos;
	private JPanel pnSubCantidades;
	private JPanel pnCantidadesKid;
	private JPanel pnIntroducirKid;
	private JLabel lblCantidadesKid;
	private JPanel pnPrecioKid;
	private JLabel lblPrecioKid;
	private JTextField txtPrecioKid;
	private JPanel pnTotal;
	private JPanel pnSubTotal;
	private JLabel lblPrecioTotal;
	private JTextField txtPrecioTotal;
	private JSpinner spinCantidadesAdultos;
	private JSpinner spinCantidadesKids;
	private JPanel pnImagen;
	private JScrollPane scrpnInformacion;
	private JPanel pnFechas;
	private JPanel pnCalendario;
	private JPanel pnSubCalendario;
	private JLabel lblFechaInicio;
	private static JDateChooser dateChooser;
	private JPanel pnDias;
	private JLabel lblDias;
	private JSpinner spinDias;

	private PanelListasEntradas panelPrevio;
	private Entrada entrada;

	public PanelVisorEntradas(PanelListasEntradas panelPrevio, Entrada entrada) {
		this.panelPrevio = panelPrevio;
		this.entrada = entrada;
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
			pnInformacion.add(getPnCantidades(), BorderLayout.CENTER);
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

	private JPanel getPnCantidades() {
		if (pnCantidades == null) {
			pnCantidades = new JPanel();
			pnCantidades.setLayout(new BorderLayout(0, 0));
			pnCantidades.add(getPnCantidadesAdultos(), BorderLayout.NORTH);
			pnCantidades.add(getPnSubCantidades(), BorderLayout.CENTER);
		}
		return pnCantidades;
	}

	private JPanel getPnCantidadesAdultos() {
		if (pnCantidadesAdultos == null) {
			pnCantidadesAdultos = new JPanel();
			pnCantidadesAdultos.setLayout(new BorderLayout(0, 0));
			pnCantidadesAdultos.add(getPnIntroducirAdultos(), BorderLayout.WEST);
			pnCantidadesAdultos.add(getPnPrecioAdultos(), BorderLayout.EAST);
		}
		return pnCantidadesAdultos;
	}

	private JPanel getPnIntroducirAdultos() {
		if (pnIntroducirAdultos == null) {
			pnIntroducirAdultos = new JPanel();
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

	private JPanel getPnPrecioAdultos() {
		if (pnPrecioAdultos == null) {
			pnPrecioAdultos = new JPanel();
			pnPrecioAdultos.add(getLblPrecioAdultos());
			pnPrecioAdultos.add(getTxtPrecioAdultos());
		}
		return pnPrecioAdultos;
	}

	private JLabel getLblPrecioAdultos() {
		if (lblPrecioAdultos == null) {
			lblPrecioAdultos = new JLabel("Total adultos");
		}
		return lblPrecioAdultos;
	}

	private JTextField getTxtPrecioAdultos() {
		if (txtPrecioAdultos == null) {
			txtPrecioAdultos = new JTextField();
			txtPrecioAdultos.setDisabledTextColor(Color.BLACK);
			txtPrecioAdultos.setText("0 \u20AC");
			txtPrecioAdultos.setEnabled(false);
			txtPrecioAdultos.setEditable(false);
			txtPrecioAdultos.setColumns(4);
		}
		return txtPrecioAdultos;
	}

	private JPanel getPnSubCantidades() {
		if (pnSubCantidades == null) {
			pnSubCantidades = new JPanel();
			pnSubCantidades.setLayout(new BorderLayout(0, 0));
			pnSubCantidades.add(getPnCantidadesKid(), BorderLayout.NORTH);
			pnSubCantidades.add(getPnFechas(), BorderLayout.CENTER);
		}
		return pnSubCantidades;
	}

	private JPanel getPnCantidadesKid() {
		if (pnCantidadesKid == null) {
			pnCantidadesKid = new JPanel();
			pnCantidadesKid.setLayout(new BorderLayout(0, 0));
			pnCantidadesKid.add(getPnIntroducirKid(), BorderLayout.WEST);
			pnCantidadesKid.add(getPnPrecioKid(), BorderLayout.EAST);
		}
		return pnCantidadesKid;
	}

	private JPanel getPnIntroducirKid() {
		if (pnIntroducirKid == null) {
			pnIntroducirKid = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnIntroducirKid.getLayout();
			flowLayout.setHgap(11);
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

	private JPanel getPnPrecioKid() {
		if (pnPrecioKid == null) {
			pnPrecioKid = new JPanel();
			pnPrecioKid.add(getLblPrecioKid());
			pnPrecioKid.add(getTxtPrecioKid());
		}
		return pnPrecioKid;
	}

	private JLabel getLblPrecioKid() {
		if (lblPrecioKid == null) {
			lblPrecioKid = new JLabel("Total niños");
		}
		return lblPrecioKid;
	}

	private JTextField getTxtPrecioKid() {
		if (txtPrecioKid == null) {
			txtPrecioKid = new JTextField();
			txtPrecioKid.setDisabledTextColor(Color.BLACK);
			txtPrecioKid.setText("0 \u20AC");
			txtPrecioKid.setEnabled(false);
			txtPrecioKid.setEditable(false);
			txtPrecioKid.setColumns(4);
		}
		return txtPrecioKid;
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

	private JPanel getPnFechas() {
		if (pnFechas == null) {
			pnFechas = new JPanel();
			pnFechas.setLayout(new BorderLayout(0, 0));
			pnFechas.add(getPnCalendario(), BorderLayout.NORTH);
		}
		return pnFechas;
	}

	private JPanel getPnCalendario() {
		if (pnCalendario == null) {
			pnCalendario = new JPanel();
			pnCalendario.setLayout(new BorderLayout(0, 0));
			pnCalendario.add(getPnSubCalendario(), BorderLayout.WEST);
			pnCalendario.add(getPnDias(), BorderLayout.EAST);
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

	private JPanel getPnDias() {
		if (pnDias == null) {
			pnDias = new JPanel();
			pnDias.add(getLblDias());
			pnDias.add(getSpinDias());
		}
		return pnDias;
	}

	private JLabel getLblDias() {
		if (lblDias == null) {
			lblDias = new JLabel("Numero de dias");
			lblDias.setDisplayedMnemonic('D');
			lblDias.setLabelFor(getSpinDias());
		}
		return lblDias;
	}

	private JSpinner getSpinDias() {
		if (spinDias == null) {
			spinDias = new JSpinner();
			spinDias.setPreferredSize(new Dimension(75, 22));
			spinDias.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					actualizarPrecios();
				}
			});
			spinDias.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spinDias;
	}

	// Mis metodos \\

	private void rellenarDatos() {
		lblDenominacion.setText(entrada.getDenominacion());
		ParqueTematico pt = panelPrevio.getPLG().getVP().getManager().getParqueByCodigo(entrada.getCodigoParque());
		String infoExtra = "Precio de entrada para adulto: " + entrada.getPrecioAdulto()
				+ "€.\n\rPrecio de entrada para niño: " + entrada.getPrecioKid() + "€.";
		if (entrada.getCodigoParque()
				.equals(panelPrevio.getPLG().getVP().getManager().getParqueOferta().getCodigoParque())) {
			infoExtra += "\n\r\n\rEl parque vinculado a esta entrada esta en oferta.\n\r¡Compra ahora y llevate un descuento del 20%!";
		}
		infoExtra += "\n\r\n\rInformacion del Parque:\n\r" + "Denominacion: " + pt.getDenominacion() + ".\n\rPais: "
				+ pt.getPais() + ". Localidad: " + pt.getLocalidad() + ".\n\r\n\rDescripcion\n\r " + pt.getDescripcion()
				+ ".";
		txaInformacion.setText(infoExtra);
		redimensionarImagen();
	}

	private void redimensionarImagen() {
		Image imgOriginal = entrada.getImagen();
		Image imgEscalada = imgOriginal.getScaledInstance((400), (400), Image.SCALE_FAST);
		lblImagen.setIcon(new ImageIcon(imgEscalada));
		lblImagen.setDisabledIcon(new ImageIcon(imgEscalada));
	}

	private void actualizarPrecios() {
		int precioAdultos = ((int) spinCantidadesAdultos.getValue()) * entrada.getPrecioAdulto();
		int precioKids = ((int) spinCantidadesKids.getValue()) * entrada.getPrecioKid();
		int numDias = (int) spinDias.getValue();
		int precioTotal = (precioAdultos + precioKids) * numDias;
		txtPrecioAdultos.setText(String.valueOf(precioAdultos) + " €");
		txtPrecioKid.setText(String.valueOf(precioKids) + " €");
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
			int numDias = (int) spinDias.getValue();
			String fecha = obtenerFecha();
			panelPrevio.getPLG().getVP().getManager().agregarNuevaReservaEntrada(entrada, fecha, numDias, numAdultos,
					numKids);
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
