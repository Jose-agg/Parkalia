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
import javax.swing.JCheckBox;
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

public class VentanaCambiosAlojamiento extends JDialog {

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
	private JPanel pnNoches;
	private JPanel pnSubNoches;
	private JLabel lblNoches;
	private JSpinner spinNoches;
	private JPanel pnSubDatos;
	private JPanel pnPersonas;
	private JPanel pnSubPersonas;
	private JLabel lblPersonas;
	private JSpinner spinCantidadesPersonas;
	private JPanel pnCantidades;
	private JPanel pnDesayuno;
	private JPanel pnSubDesayuno;
	private JLabel lblDesayuno;
	private JPanel pnFechaCambios;
	private JPanel pnFechaAntigua;
	private JTextField txtFechaAntigua;
	private JPanel pnFechaNueva;
	private static JDateChooser dateChooser;

	private String[] original;
	private VentanaPrincipal vp;
	private String tipoAccion;
	private JCheckBox chckbxDesayuno;

	public VentanaCambiosAlojamiento(VentanaPrincipal vp, String datos, String tipoAccion) {
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
			pnDatos.add(getPnNoches(), BorderLayout.NORTH);
			pnDatos.add(getPnSubDatos(), BorderLayout.CENTER);
		}
		return pnDatos;
	}

	private JPanel getPnNoches() {
		if (pnNoches == null) {
			pnNoches = new JPanel();
			pnNoches.setLayout(new BorderLayout(0, 0));
			pnNoches.add(getPnSubNoches(), BorderLayout.WEST);
			pnNoches.add(getSpinNoches(), BorderLayout.EAST);
		}
		return pnNoches;
	}

	private JPanel getPnSubNoches() {
		if (pnSubNoches == null) {
			pnSubNoches = new JPanel();
			pnSubNoches.add(getLblNoches());
		}
		return pnSubNoches;
	}

	private JLabel getLblNoches() {
		if (lblNoches == null) {
			lblNoches = new JLabel("Numero de noches");
			lblNoches.setLabelFor(getSpinNoches());
		}
		return lblNoches;
	}

	private JSpinner getSpinNoches() {
		if (spinNoches == null) {
			spinNoches = new JSpinner();
			spinNoches.setPreferredSize(new Dimension(75, 22));
			spinNoches.setModel(new SpinnerNumberModel(new Integer(5), new Integer(1), null, new Integer(1)));
		}
		return spinNoches;
	}

	private JPanel getPnSubDatos() {
		if (pnSubDatos == null) {
			pnSubDatos = new JPanel();
			pnSubDatos.setLayout(new BorderLayout(0, 5));
			pnSubDatos.add(getPnPersonas(), BorderLayout.NORTH);
			pnSubDatos.add(getPnCantidades(), BorderLayout.CENTER);
		}
		return pnSubDatos;
	}

	private JPanel getPnPersonas() {
		if (pnPersonas == null) {
			pnPersonas = new JPanel();
			pnPersonas.setLayout(new BorderLayout(0, 0));
			pnPersonas.add(getPnSubPersonas(), BorderLayout.WEST);
			pnPersonas.add(getSpinCantidadesPersonas(), BorderLayout.EAST);
		}
		return pnPersonas;
	}

	private JPanel getPnSubPersonas() {
		if (pnSubPersonas == null) {
			pnSubPersonas = new JPanel();
			pnSubPersonas.add(getLblPersonas());
		}
		return pnSubPersonas;
	}

	private JLabel getLblPersonas() {
		if (lblPersonas == null) {
			lblPersonas = new JLabel("Numero de personas");
			lblPersonas.setLabelFor(getSpinCantidadesPersonas());
		}
		return lblPersonas;
	}

	private JSpinner getSpinCantidadesPersonas() {
		if (spinCantidadesPersonas == null) {
			spinCantidadesPersonas = new JSpinner();
			spinCantidadesPersonas.setPreferredSize(new Dimension(75, 22));
			spinCantidadesPersonas
					.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		}
		return spinCantidadesPersonas;
	}

	private JPanel getPnCantidades() {
		if (pnCantidades == null) {
			pnCantidades = new JPanel();
			pnCantidades.setLayout(new BorderLayout(0, 5));
		}
		return pnCantidades;
	}

	// SOLO PARA HOTELES \\

	private JPanel getPnDesayuno() {
		if (pnDesayuno == null) {
			pnDesayuno = new JPanel();
			pnDesayuno.setLayout(new BorderLayout(0, 0));
			pnDesayuno.add(getPnSubDesayuno(), BorderLayout.WEST);
			pnDesayuno.add(getChckbxDesayuno(), BorderLayout.EAST);
		}
		return pnDesayuno;
	}

	private JPanel getPnSubDesayuno() {
		if (pnSubDesayuno == null) {
			pnSubDesayuno = new JPanel();
			pnSubDesayuno.add(getLblDesayuno());
		}
		return pnSubDesayuno;
	}

	private JLabel getLblDesayuno() {
		if (lblDesayuno == null) {
			lblDesayuno = new JLabel("Desayuno incluido");
			lblDesayuno.setLabelFor(getChckbxDesayuno());
		}
		return lblDesayuno;
	}

	private JCheckBox getChckbxDesayuno() {
		if (chckbxDesayuno == null) {
			chckbxDesayuno = new JCheckBox("");
		}
		return chckbxDesayuno;
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
		String[] aux = original[0].split("Alojamiento: ");
		txtDenominacion.setText(aux[1]);
		txtFechaAntigua.setText(original[1]);
		spinNoches.setModel(new SpinnerNumberModel(new Integer(Integer.valueOf(original[2])), new Integer(1), null,
				new Integer(1)));
		spinCantidadesPersonas.setModel(new SpinnerNumberModel(new Integer(Integer.valueOf(original[3])),
				new Integer(0), null, new Integer(1)));
		btnVariable.setText(tipoAccion);
		if (original[4].equals("S")) {
			pnCantidades.add(getPnDesayuno(), BorderLayout.NORTH);
			chckbxDesayuno.setSelected(true);
		}
		if (original[4].equals("N")) {
			pnCantidades.add(getPnDesayuno(), BorderLayout.NORTH);
			chckbxDesayuno.setSelected(false);
		}
		if (tipoAccion.equals("Borrar")) {
			btnVariable.setMnemonic('B');
			spinNoches.setEnabled(false);
			spinCantidadesPersonas.setEnabled(false);
			if (!original[4].equals("__")) {
				chckbxDesayuno.setEnabled(false);
			}
			btnVariable.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					borrarAlojamiento();
				}
			});
		} else {
			pnFechaCambios.add(getPnFechaNueva(), BorderLayout.EAST);
			lblFecha.setLabelFor(getDateChooser());
			lblFecha.setDisplayedMnemonic('F');
			lblNoches.setDisplayedMnemonic('N');
			lblPersonas.setDisplayedMnemonic('U');
			btnVariable.setMnemonic('O');
			if (!original[4].equals("__")) {
				lblDesayuno.setDisplayedMnemonic('D');
			}
			btnVariable.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					modificarAlojamiento();
				}
			});
		}

	}

	private void borrarAlojamiento() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Se procedera ha eliminar esta reserva. ¿Continuar?");
		if (JOptionPane.YES_OPTION == respuesta) {
			vp.getManager().eliminarReservaAlojamiento(original);
			dispose();
		}
	}

	private void modificarAlojamiento() {

		int respuesta = JOptionPane.showConfirmDialog(this, "Se modificara esta reserva. ¿Continuar?");
		if (JOptionPane.YES_OPTION == respuesta) {
			String fecha = "";
			try {
				fecha = obtenerFecha();
			} catch (NullPointerException n) {
				fecha = original[1];
			}
			int numNoches = (int) spinNoches.getValue();
			int numPersonas = (int) spinCantidadesPersonas.getValue();
			boolean desayuno = false;
			if (!original[4].equals("__") && chckbxDesayuno.isSelected()) {
				desayuno = true;
			}
			String res = this.vp.getManager().comprobarAforoAlojamiento(original[0].split("Alojamiento: ")[1],
					numPersonas);
			if (res.equals("Continuar")) {
				vp.getManager().modificarReservaAlojamiento(original, fecha, numNoches, numPersonas, desayuno);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, res);
			}
		}
	}

	private static String obtenerFecha() {
		Date today = dateChooser.getDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String folderName = formatter.format(today);
		return folderName;
	}
}
