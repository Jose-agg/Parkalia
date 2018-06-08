package igu.carrito;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import igu.main.VentanaPrincipal;

public class VentanaResumenReserva extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel pnBotones;
	private JPanel pnSubBotones;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JScrollPane scrpnResumen;
	private JTextArea txaResumen;

	private VentanaPrincipal vp;

	public VentanaResumenReserva(VentanaPrincipal vp) {
		this.vp = vp;
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaCambiosEntrada.class.getResource("/img/Logo_Transparente_Negro.jpg")));
		setResizable(false);
		setTitle("Parkalia");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 634, 513);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(getScrpnResumen(), BorderLayout.CENTER);
		mainPanel.add(getPnBotones(), BorderLayout.SOUTH);
		rellenarResumen();
	}

	private JScrollPane getScrpnResumen() {
		if (scrpnResumen == null) {
			scrpnResumen = new JScrollPane();
			scrpnResumen.setViewportView(getTxaResumen());
		}
		return scrpnResumen;
	}

	private JTextArea getTxaResumen() {
		if (txaResumen == null) {
			txaResumen = new JTextArea();
			txaResumen.setWrapStyleWord(true);
			txaResumen.setLineWrap(true);
			txaResumen.setEnabled(false);
			txaResumen.setEditable(false);
		}
		return txaResumen;
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
			pnSubBotones.add(getBtnGuardar());
			pnSubBotones.add(getBtnCancelar());
		}
		return pnSubBotones;
	}

	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String salida = vp.getManager().guardarFactura();
					if (salida.equals("Completado")) {
						JOptionPane.showMessageDialog(null, "Reserva guardada con exito");
						vp.getManager().reiniciar();
						vp.cambiarPanelContenidos(vp.getPanelSelector());
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, salida);
					}
				}
			});
			btnGuardar.setMnemonic('G');
		}
		return btnGuardar;
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

	// MIS METODOS \\

	private void rellenarResumen() {
		txaResumen.setText(this.vp.getManager().getResumenReserva());
	}
}