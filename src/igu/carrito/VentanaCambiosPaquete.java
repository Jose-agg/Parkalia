package igu.carrito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import igu.main.VentanaPrincipal;

public class VentanaCambiosPaquete extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel pnInformacion;
	private JPanel pnBotones;
	private JPanel pnSubBotones;
	private JButton btnVariable;
	private JButton btnCancelar;
	private JPanel pnDenominacion;
	private JPanel pnSubDenominacion;
	private JLabel lblDenominacion;
	private JTextField txtDenominacion;
	private JPanel pnSubInformacion;
	private JPanel pnFecha;
	private JPanel pnSubFecha;
	private JLabel lblFecha;
	private JPanel pnDatos;
	private JPanel pnSubDatos;
	private JPanel pnAdultos;
	private JPanel pnSubAdultos;
	private JLabel lblAdultos;
	private JSpinner spinCantidadesAdultos;
	private JPanel pnCantidades;
	private JPanel pnKids;
	private JPanel pnSubKids;
	private JLabel lblKids;
	private JSpinner spinCantidadesKids;
	private JPanel pnFechaCambios;
	private JPanel pnFechaAntigua;
	private JTextField txtFechaAntigua;
	private JPanel pnFechaNueva;
	private static JDateChooser dateChooser;

	private String[] original;
	private VentanaPrincipal vp;
	private String tipoAccion;

	public VentanaCambiosPaquete(VentanaPrincipal vp, String datos, String tipoAccion) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaCambiosEntrada.class.getResource("/img/Logo_Transparente_Negro.jpg")));
		this.vp = vp;
		this.tipoAccion = tipoAccion;
		setResizable(false);
		setTitle("Parkalia");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 346);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		mainPanel.add(getPnInformacion(), BorderLayout.CENTER);
		mainPanel.add(getPnBotones(), BorderLayout.SOUTH);
		mostrarDatos(datos);
	}

	private JPanel getPnInformacion() {
		if (pnInformacion == null) {
			pnInformacion = new JPanel();
			pnInformacion.setLayout(new BorderLayout(5, 5));
			pnInformacion.add(getPnDenominacion(), BorderLayout.NORTH);
			pnInformacion.add(getPnSubInformacion(), BorderLayout.CENTER);
		}
		return pnInformacion;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new BorderLayout(0, 0));
			pnBotones.add(getPnSubBotones(), BorderLayout.EAST);
		}
		return pnBotones;
	}

	private JPanel getPnSubBotones() {
		if (pnSubBotones == null) {
			pnSubBotones = new JPanel();
			pnSubBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnSubBotones.add(getBtnVariable());
			pnSubBotones.add(getBtnCancelar());
		}
		return pnSubBotones;
	}

	private JButton getBtnVariable() {
		if (btnVariable == null) {
			btnVariable = new JButton("Texto Variable");
		}
		return btnVariable;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('C');
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	private JPanel getPnDenominacion() {
		if (pnDenominacion == null) {
			pnDenominacion = new JPanel();
			pnDenominacion.setLayout(new BorderLayout(0, 0));
			pnDenominacion.add(getPnSubDenominacion(), BorderLayout.WEST);
			pnDenominacion.add(getTxtDenominacion(), BorderLayout.EAST);
		}
		return pnDenominacion;
	}

	private JPanel getPnSubDenominacion() {
		if (pnSubDenominacion == null) {
			pnSubDenominacion = new JPanel();
			pnSubDenominacion.add(getLblDenominacion());
		}
		return pnSubDenominacion;
	}

	private JLabel getLblDenominacion() {
		if (lblDenominacion == null) {
			lblDenominacion = new JLabel("Denominacion");
		}
		return lblDenominacion;
	}

	private JTextField getTxtDenominacion() {
		if (txtDenominacion == null) {
			txtDenominacion = new JTextField();
			txtDenominacion.setHorizontalAlignment(SwingConstants.RIGHT);
			txtDenominacion.setEnabled(false);
			txtDenominacion.setEditable(false);
			txtDenominacion.setColumns(28);
		}
		return txtDenominacion;
	}

	private JPanel getPnSubInformacion() {
		if (pnSubInformacion == null) {
			pnSubInformacion = new JPanel();
			pnSubInformacion.setLayout(new BorderLayout(0, 5));
			pnSubInformacion.add(getPnFecha(), BorderLayout.NORTH);
			pnSubInformacion.add(getPnDatos(), BorderLayout.CENTER);
		}
		return pnSubInformacion;
	}

	private JPanel getPnFecha() {
		if (pnFecha == null) {
			pnFecha = new JPanel();
			pnFecha.setLayout(new BorderLayout(0, 0));
			pnFecha.add(getPnSubFecha(), BorderLayout.WEST);
			pnFecha.add(getPnFechaCambios(), BorderLayout.EAST);
		}
		return pnFecha;
	}

	private JPanel getPnFechaCambios() {
		if (pnFechaCambios == null) {
			pnFechaCambios = new JPanel();
			pnFechaCambios.setLayout(new BorderLayout(0, 0));
			pnFechaCambios.add(getPnFechaAntigua(), BorderLayout.WEST);
		}
		return pnFechaCambios;
	}

	private JPanel getPnFechaAntigua() {
		if (pnFechaAntigua == null) {
			pnFechaAntigua = new JPanel();
			pnFechaAntigua.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Escogida",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFechaAntigua.add(getTxtFechaAntigua());
		}
		return pnFechaAntigua;
	}

	private JTextField getTxtFechaAntigua() {
		if (txtFechaAntigua == null) {
			txtFechaAntigua = new JTextField();
			txtFechaAntigua.setEnabled(false);
			txtFechaAntigua.setEditable(false);
			txtFechaAntigua.setColumns(9);
		}
		return txtFechaAntigua;
	}

	private JPanel getPnSubFecha() {
		if (pnSubFecha == null) {
			pnSubFecha = new JPanel();
			pnSubFecha.setLayout(new GridLayout(0, 1, 0, 0));
			pnSubFecha.add(getLblFecha());
		}
		return pnSubFecha;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}

	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setLayout(new BorderLayout(0, 5));
			pnDatos.add(getPnSubDatos(), BorderLayout.CENTER);
		}
		return pnDatos;
	}

	private JPanel getPnSubDatos() {
		if (pnSubDatos == null) {
			pnSubDatos = new JPanel();
			pnSubDatos.setLayout(new BorderLayout(0, 5));
			pnSubDatos.add(getPnAdultos(), BorderLayout.NORTH);
			pnSubDatos.add(getPnCantidades(), BorderLayout.CENTER);
		}
		return pnSubDatos;
	}

	private JPanel getPnAdultos() {
		if (pnAdultos == null) {
			pnAdultos = new JPanel();
			pnAdultos.setLayout(new BorderLayout(0, 0));
			pnAdultos.add(getPnSubAdultos(), BorderLayout.WEST);
			pnAdultos.add(getSpinCantidadesAdultos(), BorderLayout.EAST);
		}
		return pnAdultos;
	}

	private JPanel getPnSubAdultos() {
		if (pnSubAdultos == null) {
			pnSubAdultos = new JPanel();
			pnSubAdultos.add(getLblAdultos());
		}
		return pnSubAdultos;
	}

	private JLabel getLblAdultos() {
		if (lblAdultos == null) {
			lblAdultos = new JLabel("Numero de adultos");
			lblAdultos.setLabelFor(getSpinCantidadesAdultos());
		}
		return lblAdultos;
	}

	private JSpinner getSpinCantidadesAdultos() {
		if (spinCantidadesAdultos == null) {
			spinCantidadesAdultos = new JSpinner();
			spinCantidadesAdultos.setPreferredSize(new Dimension(75, 22));
			spinCantidadesAdultos
					.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		}
		return spinCantidadesAdultos;
	}

	private JPanel getPnCantidades() {
		if (pnCantidades == null) {
			pnCantidades = new JPanel();
			pnCantidades.setLayout(new BorderLayout(0, 5));
			pnCantidades.add(getPnKids(), BorderLayout.NORTH);
		}
		return pnCantidades;
	}

	private JPanel getPnKids() {
		if (pnKids == null) {
			pnKids = new JPanel();
			pnKids.setLayout(new BorderLayout(0, 0));
			pnKids.add(getPnSubKids(), BorderLayout.WEST);
			pnKids.add(getSpinCantidadesKids(), BorderLayout.EAST);
		}
		return pnKids;
	}

	private JPanel getPnSubKids() {
		if (pnSubKids == null) {
			pnSubKids = new JPanel();
			pnSubKids.add(getLblKids());
		}
		return pnSubKids;
	}

	private JLabel getLblKids() {
		if (lblKids == null) {
			lblKids = new JLabel("Numero de ni\u00F1os");
			lblKids.setLabelFor(getSpinCantidadesKids());
		}
		return lblKids;
	}

	private JSpinner getSpinCantidadesKids() {
		if (spinCantidadesKids == null) {
			spinCantidadesKids = new JSpinner();
			spinCantidadesKids.setPreferredSize(new Dimension(75, 22));
			spinCantidadesKids.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		}
		return spinCantidadesKids;
	}

	// SOLO PARA MODIFICACIONES \\

	private JPanel getPnFechaNueva() {
		if (pnFechaNueva == null) {
			pnFechaNueva = new JPanel();
			pnFechaNueva.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nueva", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnFechaNueva.add(getDateChooser());
		}
		return pnFechaNueva;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setPreferredSize(new Dimension(125, 25));
			dateChooser.setVerifyInputWhenFocusTarget(false);
			dateChooser.setDateFormatString("dd/MM/yyyy");
			dateChooser.setMinSelectableDate(new Date());
		}
		return dateChooser;
	}

	// MIS METODOS \\

	private void mostrarDatos(String datos) {
		this.original = datos.split("--");
		txtDenominacion.setText(original[0].split("Paquete: ")[1]);
		txtFechaAntigua.setText(original[1]);
		spinCantidadesAdultos.setModel(new SpinnerNumberModel(new Integer(Integer.valueOf(original[2])), new Integer(0),
				null, new Integer(1)));
		spinCantidadesKids.setModel(new SpinnerNumberModel(new Integer(Integer.valueOf(original[3])), new Integer(0),
				null, new Integer(1)));
		btnVariable.setText(tipoAccion);
		if (tipoAccion.equals("Borrar")) {
			btnVariable.setMnemonic('B');
			spinCantidadesAdultos.setEnabled(false);
			spinCantidadesKids.setEnabled(false);
			btnVariable.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					borrarPaquete();
				}
			});
		} else {
			lblFecha.setLabelFor(getDateChooser());
			lblFecha.setDisplayedMnemonic('F');
			lblAdultos.setDisplayedMnemonic('U');
			lblKids.setDisplayedMnemonic('M');
			btnVariable.setMnemonic('O');
			pnFechaCambios.add(getPnFechaNueva(), BorderLayout.EAST);
			btnVariable.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					modificarPaquete();
				}
			});
		}

	}

	private void borrarPaquete() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Se procedera ha eliminar esta reserva. ¿Continuar?");
		if (JOptionPane.YES_OPTION == respuesta) {
			vp.getManager().eliminarReservaPaquete(original);
			dispose();
		}
	}

	private void modificarPaquete() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Se modificara esta reserva. ¿Continuar?");
		if (((int) spinCantidadesAdultos.getValue() == 0) && ((int) spinCantidadesKids.getValue() == 0)) {
			JOptionPane.showMessageDialog(this, "Introduzca al menos una persona");
			return;
		}
		if (JOptionPane.YES_OPTION == respuesta) {
			String fecha = "";
			try {
				fecha = obtenerFecha();
			} catch (NullPointerException n) {
				fecha = original[1];
			}
			int numAdultos = (int) spinCantidadesAdultos.getValue();
			int numKids = (int) spinCantidadesKids.getValue();
			vp.getManager().modificarReservaPaquete(original, fecha, numAdultos, numKids);
			dispose();
		}
	}

	private static String obtenerFecha() {
		Date today = dateChooser.getDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String folderName = formatter.format(today);
		return folderName;
	}
}
