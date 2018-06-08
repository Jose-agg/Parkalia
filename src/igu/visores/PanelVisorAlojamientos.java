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
import javax.swing.JCheckBox;
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

import igu.listas.PanelListasAlojamientos;
import logica.tipos.Alojamiento;
import logica.tipos.ParqueTematico;

public class PanelVisorAlojamientos extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblDenominacion;
	private JLabel lblImagen;
	private JPanel pnInformacion;
	private JButton btnAgregarCarrito;
	private JPanel pnBotones;
	private JButton btnAtras;
	private JTextArea txaInformacion;
	private JPanel pnCantidades;
	private JPanel pnCantidadesNoches;
	private JPanel pnIntroducirNoches;
	private JLabel lblCantidadesNoches;
	private JPanel pnDesayuno;
	private JPanel pnSubCantidades;
	private JPanel pnCantidadesPersonas;
	private JPanel pnIntroducirPersonas;
	private JLabel lblCantidadesPersonas;
	private JPanel pnTotal;
	private JPanel pnSubTotal;
	private JLabel lblPrecioTotal;
	private JTextField txtPrecioTotal;
	private JSpinner spinCantidadesNoches;
	private JSpinner spinCantidadesPersonas;
	private JPanel pnImagen;
	private JScrollPane scrpnInformacion;
	private JPanel pnFechas;
	private JPanel pnCalendario;
	private JPanel pnSubCalendario;
	private JLabel lblFechaInicio;
	private static JDateChooser dateChooser;

	private PanelListasAlojamientos panelPrevio;
	private Alojamiento alojamiento;
	private JCheckBox chckbxDesayuno;

	public PanelVisorAlojamientos(PanelListasAlojamientos panelPrevio, Alojamiento alojamiento) {
		this.panelPrevio = panelPrevio;
		this.alojamiento = alojamiento;
		setLayout(new BorderLayout(5, 5));
		add(getLblDenominacion(), BorderLayout.NORTH);
		add(getPnImagen(), BorderLayout.WEST);
		add(getPnInformacion(), BorderLayout.CENTER);
		add(getPnBotones(), BorderLayout.SOUTH);
		rellenarDatos();
		actualizarPrecios();
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
			pnCantidades.add(getPnSubCantidades(), BorderLayout.NORTH);
			pnCantidades.add(getPnCantidadesPersonas(), BorderLayout.CENTER);
		}
		return pnCantidades;
	}

	private JPanel getPnCantidadesNoches() {
		if (pnCantidadesNoches == null) {
			pnCantidadesNoches = new JPanel();
			pnCantidadesNoches.setLayout(new BorderLayout(0, 0));
			pnCantidadesNoches.add(getPnIntroducirNoches(), BorderLayout.WEST);
		}
		return pnCantidadesNoches;
	}

	private JPanel getPnIntroducirNoches() {
		if (pnIntroducirNoches == null) {
			pnIntroducirNoches = new JPanel();
			pnIntroducirNoches.add(getLblCantidadesNoches());
			pnIntroducirNoches.add(getSpinCantidadesNoches());
		}
		return pnIntroducirNoches;
	}

	private JSpinner getSpinCantidadesNoches() {
		if (spinCantidadesNoches == null) {
			spinCantidadesNoches = new JSpinner();
			spinCantidadesNoches.setPreferredSize(new Dimension(75, 22));
			spinCantidadesNoches.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					actualizarPrecios();
				}
			});
			spinCantidadesNoches.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spinCantidadesNoches;
	}

	private JLabel getLblCantidadesNoches() {
		if (lblCantidadesNoches == null) {
			lblCantidadesNoches = new JLabel("Numero de noches");
			lblCantidadesNoches.setDisplayedMnemonic('N');
			lblCantidadesNoches.setLabelFor(getSpinCantidadesNoches());
		}
		return lblCantidadesNoches;
	}

	private JPanel getPnSubCantidades() {
		if (pnSubCantidades == null) {
			pnSubCantidades = new JPanel();
			pnSubCantidades.setLayout(new BorderLayout(0, 0));
			pnSubCantidades.add(getPnFechas(), BorderLayout.NORTH);
			pnSubCantidades.add(getPnCantidadesNoches(), BorderLayout.CENTER);
		}
		return pnSubCantidades;
	}

	private JPanel getPnCantidadesPersonas() {
		if (pnCantidadesPersonas == null) {
			pnCantidadesPersonas = new JPanel();
			pnCantidadesPersonas.setLayout(new BorderLayout(0, 0));
			pnCantidadesPersonas.add(getPnIntroducirPersonas(), BorderLayout.WEST);
		}
		return pnCantidadesPersonas;
	}

	private JPanel getPnIntroducirPersonas() {
		if (pnIntroducirPersonas == null) {
			pnIntroducirPersonas = new JPanel();
			pnIntroducirPersonas.add(getLblCantidadesPersonas());
			pnIntroducirPersonas.add(getSpinCantidadesPersonas());
		}
		return pnIntroducirPersonas;
	}

	private JSpinner getSpinCantidadesPersonas() {
		if (spinCantidadesPersonas == null) {
			spinCantidadesPersonas = new JSpinner();
			spinCantidadesPersonas.setPreferredSize(new Dimension(75, 22));
			spinCantidadesPersonas.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					actualizarPrecios();
				}
			});
			spinCantidadesPersonas
					.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spinCantidadesPersonas;
	}

	private JLabel getLblCantidadesPersonas() {
		if (lblCantidadesPersonas == null) {
			lblCantidadesPersonas = new JLabel("Numero de personas");
			lblCantidadesPersonas.setDisplayedMnemonic('U');
			lblCantidadesPersonas.setLabelFor(getSpinCantidadesPersonas());
		}
		return lblCantidadesPersonas;
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

	// SOLO PARA HOTELES \\
	private JPanel getPnDesayuno() {
		if (pnDesayuno == null) {
			pnDesayuno = new JPanel();
			pnDesayuno.add(getChckbxDesayuno());
		}
		return pnDesayuno;
	}

	private JCheckBox getChckbxDesayuno() {
		if (chckbxDesayuno == null) {
			chckbxDesayuno = new JCheckBox("Desayuno incluido");
			chckbxDesayuno.setMnemonic('D');
			chckbxDesayuno.setSelected(false);
			chckbxDesayuno.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					actualizarPrecios();
				}
			});
		}
		return chckbxDesayuno;
	}

	// MIS METODOS \\

	private void rellenarDatos() {
		lblDenominacion.setText(alojamiento.getDenominacion());
		String tipo = alojamiento.getTipo();
		if (tipo.equals("HO")) {
			tipo = "Hotel";
			pnCantidadesNoches.add(getPnDesayuno(), BorderLayout.EAST);
		}
		if (tipo.equals("AH")) {
			tipo = "Apartahotel";
		}
		if (tipo.equals("AP")) {
			tipo = "Apartamento";
		}
		ParqueTematico pt = panelPrevio.getPLG().getVP().getManager().getParqueByCodigo(alojamiento.getCodigoParque());
		StringBuilder infoExtra = new StringBuilder("Tipo: " + tipo + ".\n\rNumero de estrellas: "
				+ alojamiento.getNumEstrellas() + ".\n\rPrecio por noche: " + alojamiento.getPrecio() + "€.");
		if (alojamiento.getCodigoParque()
				.equals(panelPrevio.getPLG().getVP().getManager().getParqueOferta().getCodigoParque())) {
			infoExtra.append(
					"\n\r\n\rEl parque vinculado a este alojamiento esta en oferta.\n\r¡Compra ahora y llevate un descuento del 20%!");
		}
		infoExtra.append("\n\r\n\rInformacion del Parque:\n\r" + "Denominacion: " + pt.getDenominacion() + ".\n\rPais: "
				+ pt.getPais() + ". Localidad: " + pt.getLocalidad() + ".\n\rDescripcion\n\r " + pt.getDescripcion()
				+ ".");
		txaInformacion.setText(infoExtra.toString());
		redimensionarImagen();
	}

	private void redimensionarImagen() {
		Image imgOriginal = alojamiento.getImagen();
		Image imgEscalada = imgOriginal.getScaledInstance((400), (400), Image.SCALE_FAST);
		lblImagen.setIcon(new ImageIcon(imgEscalada));
		lblImagen.setDisabledIcon(new ImageIcon(imgEscalada));
	}

	private void actualizarPrecios() {
		int numNoches = (int) spinCantidadesNoches.getValue();
		int numPersonas = (int) spinCantidadesPersonas.getValue();
		int precioTotal = 0;
		if (alojamiento.getTipo().equals("HO")) {
			precioTotal = alojamiento.getPrecio() * numNoches * numPersonas;
			if (chckbxDesayuno.isSelected()) {
				precioTotal += precioTotal * 0.1;
			}
		} else {
			precioTotal = alojamiento.getPrecio() * numNoches;
		}
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
			String fecha = obtenerFecha();
			int numNoches = (int) spinCantidadesNoches.getValue();
			int numPersonas = (int) spinCantidadesPersonas.getValue();
			boolean b = false;
			try {
				b = chckbxDesayuno.isSelected();
			} catch (NullPointerException n) {
				b = false;
			}
			panelPrevio.getPLG().getVP().getManager().agregarNuevaReservaAlojamiento(alojamiento, fecha, numNoches,
					numPersonas, b);
			panelPrevio.getPLG().actualizarCarrito();
		} else {
			JOptionPane.showMessageDialog(null, continuar);
		}
	}

	private String comprobarDatos() {
		int cantidadSeleccionada = (int) spinCantidadesPersonas.getValue();
		int aforoMaximo = alojamiento.getNumPlazas();
		if (cantidadSeleccionada > aforoMaximo) {
			return "Excede el aforo, el maximo para este alojamiento es de " + alojamiento.getNumPlazas()
					+ " personas.";
		}
		try {
			obtenerFecha();
		} catch (Exception e) {
			return "Seleccione una fecha valida.";
		}
		return "Continuar";

	}

}
